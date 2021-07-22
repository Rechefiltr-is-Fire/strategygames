package strategygames.format

import strategygames._

import cats.data.Validated
import cats.implicits._

sealed trait Uci {

  def uci: String
  def piotr: String

  def origDest: (Pos, Pos)

}

object Uci {

  final case class Chess(u: chess.format.Uci) extends Uci {
    def uci = u.uci
    def piotr = u.piotr
    def origDest: (Pos, Pos) = (Pos.Chess(u.origDest._1), Pos.Chess(u.origDest._2))
  }

  final case class Draughts(u: draughts.format.Uci) extends Uci {
    def uci = u.uci
    def piotr = u.piotr
    def origDest: (Pos, Pos) = (Pos.Draughts(u.origDest._1), Pos.Draughts(u.origDest._2))
  }

  abstract sealed class Move(
    val orig: Pos,
    val dest: Pos,
    val promotion: Option[PromotableRole] = None,
    val capture: Option[List[Pos]] = None
  ) extends Uci {

    def keys = orig.key + dest.key
    def uci: String

    def keysPiotr = orig.piotrStr + dest.piotrStr
    def piotr     = keysPiotr + promotionString

    def promotionString = promotion.fold("")(_.forsyth.toString)

    def origDest = orig -> dest

  }

  final case class ChessMove(m: chess.format.Uci.Move) extends Move(
    Pos.Chess(m.orig),
    Pos.Chess(m.dest),
    m.promotion.map(Role.ChessPromotableRole)
  ){
    def uci = m.uci
  }

  final case class DraughtsMove(m: draughts.format.Uci.Move) extends Move(
    Pos.Draughts(m.orig),
    Pos.Draughts(m.dest),
    m.promotion.map(Role.DraughtsPromotableRole),
    m.capture match {
      case Some(capture) => Some(capture.map(Pos.Draughts))
      case None          => None
    }
  ){
    def uci = m.uci
  }

  object Move {

    private def draughtsCaptures(captures: Option[List[Pos]]): Option[List[draughts.Pos]] =
      captures match {
        case Some(captures) => Some(captures.flatMap(c =>
          c match {
            case Pos.Draughts(c) => Some(c)
            case _               => None
          }
        ))
        case None => None
      }

    def apply(lib: GameLib, orig: Pos, dest: Pos, promotion: Option[PromotableRole], capture: Option[List[Pos]] = None): Move =
      (lib, orig, dest, promotion) match {
        case (GameLib.Draughts(), Pos.Draughts(orig), Pos.Draughts(dest), Some(Role.DraughtsPromotableRole(promotion))) => DraughtsMove(draughts.format.Uci.Move.apply(orig, dest, Some(promotion), draughtsCaptures(capture)))
        case (GameLib.Chess(), Pos.Chess(orig), Pos.Chess(dest), Some(Role.ChessPromotableRole(promotion))) => ChessMove(chess.format.Uci.Move.apply(orig, dest, Some(promotion)))
        case _ => sys.error("Mismatched gamelib types")
      }

    def apply(lib: GameLib, move: String): Option[Move] = lib match {
      case GameLib.Draughts() => draughts.format.Uci.Move.apply(move).map(DraughtsMove)
      case GameLib.Chess()    => chess.format.Uci.Move.apply(move).map(ChessMove)
    }

    def piotr(lib: GameLib, move: String): Option[Move] = lib match {
      case GameLib.Draughts() => draughts.format.Uci.Move.piotr(move).map(DraughtsMove)
      case GameLib.Chess()    => chess.format.Uci.Move.piotr(move).map(ChessMove)
    }

    def fromStrings(lib: GameLib, origS: String, destS: String, promS: Option[String]): Option[Move] = lib match {
      case GameLib.Draughts()
        => draughts.format.Uci.Move.fromStrings(origS, destS, promS).map(DraughtsMove)
      case GameLib.Chess()
        => chess.format.Uci.Move.fromStrings(origS, destS, promS).map(ChessMove)
    }
  }

  abstract sealed class WithSan(val uci: Uci, val san: String)

  final case class ChessWithSan(w: chess.format.Uci.WithSan) extends WithSan(
    Chess(w.uci),
    w.san
  )

  final case class DraughtsWithSan(w: draughts.format.Uci.WithSan) extends WithSan(
    Draughts(w.uci),
    w.san
  )

  object WithSan {

    def apply(lib: GameLib, uci: Uci, san: String): WithSan = (lib, uci) match {
      case (GameLib.Draughts(), Uci.Draughts(uci))
        => Uci.DraughtsWithSan(draughts.format.Uci.WithSan(uci, san))
      case (GameLib.Chess(), Uci.Chess(uci))
        => Uci.ChessWithSan(chess.format.Uci.WithSan(uci, san))
      case _ => sys.error("Mismatched gamelib types")
    }

  }

  //possibly wrong to handle Draughts.withCaptures likes this
  def apply(lib: GameLib, move: strategygames.Move, withCaptures: Boolean = false): Uci.Move =
    (lib, move) match {
      case (GameLib.Draughts(), strategygames.Move.Draughts(move))
        => DraughtsMove(draughts.format.Uci.apply(move, withCaptures))
      case (GameLib.Chess(), strategygames.Move.Chess(move))
        => ChessMove(chess.format.Uci.apply(move))
      case _ => sys.error("Mismatched gamelib types")
    }

  def apply(lib: GameLib, move: String): Option[Uci] = lib match {
      case GameLib.Draughts() => draughts.format.Uci.apply(move).map(Draughts)
      case GameLib.Chess()    => chess.format.Uci.apply(move).map(Chess)
  }

  def piotr(lib: GameLib, move: String): Option[Uci] = lib match {
      case GameLib.Draughts() => draughts.format.Uci.piotr(move).map(Draughts)
      case GameLib.Chess()    => chess.format.Uci.piotr(move).map(Chess)
  }

  def readList(lib: GameLib, moves: String): Option[List[Uci]] =
    moves.split(' ').toList.map(apply(lib, _)).sequence

  def writeList(moves: List[Uci]): String =
    moves.map(_.uci) mkString " "

  def readListPiotr(lib: GameLib, moves: String): Option[List[Uci]] =
    moves.split(' ').toList.map(piotr(lib, _)).sequence

  //something like this should be possible, but rewritten the function below
  //def writeListPiotr(lib: GameLib, moves: List[Uci]): String =
  //  moves.map(_.piotr(lib, _)) mkString " "

  def draughtsUci(uci: List[Uci]): List[draughts.format.Uci] =
    uci.flatMap(u =>
      u match {
        case Uci.Draughts(u) => Some(u)
        case _               => None
      }
    )

  def chessUci(uci: List[Uci]): List[chess.format.Uci] =
    uci.flatMap(u =>
      u match {
        case Uci.Chess(u) => Some(u)
        case _            => None
      }
    )

  def writeListPiotr(lib: GameLib, moves: List[Uci]): String = lib match {
    case GameLib.Draughts() => draughts.format.Uci.writeListPiotr(draughtsUci(moves))
    case GameLib.Chess()    => chess.format.Uci.writeListPiotr(chessUci(moves))
  }

}
