package strategygames.chess.format
import strategygames.chess._

import cats.data.Validated
import cats.implicits._

sealed trait Uci {

  def uci: String
  def piotr: String

  def origDest: (Pos, Pos)

  def apply(situation: Situation): Validated[String, Action]
}

object Uci {

  case class Move(
      orig: Pos,
      dest: Pos,
      promotion: Option[PromotableRole] = None
  ) extends Uci {

    def keys = orig.key + dest.key
    def uci  = keys + promotionString

    def keysPiotr = orig.piotrStr + dest.piotrStr
    def piotr     = keysPiotr + promotionString

    def promotionString = promotion.fold("")(_.forsyth.toString)

    def origDest = orig -> dest

    def apply(situation: Situation) = situation.move(orig, dest, promotion)
  }

  object Move {

    def apply(move: String): Option[Move] =
      for {
        orig     <- Pos.fromKey(move take 2)
        dest     <- Pos.fromKey(move.slice(2, 4))
        promotion = move lift 4 flatMap Role.promotable
      } yield Move(orig, dest, promotion)

    def piotr(move: String) =
      for {
        orig     <- move.headOption flatMap Pos.piotr
        dest     <- move lift 1 flatMap Pos.piotr
        promotion = move lift 2 flatMap Role.promotable
      } yield Move(orig, dest, promotion)

    def fromStrings(origS: String, destS: String, promS: Option[String]) =
      for {
        orig     <- Pos.fromKey(origS)
        dest     <- Pos.fromKey(destS)
        promotion = Role promotable promS
      } yield Move(orig, dest, promotion)
  }

  case class Drop(role: Role, pos: Pos) extends Uci {

    def uci = s"${role.pgn}@${pos.key}"

    def piotr = s"${role.pgn}@${pos.piotrStr}"

    def origDest = pos -> pos

    def apply(situation: Situation) = situation.drop(role, pos)
  }

  object Drop {

    def fromStrings(roleS: String, posS: String) =
      for {
        role <- Role.allByName get roleS
        pos  <- Pos.fromKey(posS)
      } yield Drop(role, pos)
  }

  case class WithSan(uci: Uci, san: String)

  def apply(move: strategygames.chess.Move) = Uci.Move(move.orig, move.dest, move.promotion)

  def apply(drop: strategygames.chess.Drop) = Uci.Drop(drop.piece.role, drop.pos)

  def apply(move: String): Option[Uci] =
    if (move lift 1 contains '@') for {
      role <- move.headOption flatMap Role.allByPgn.get
      pos  <- Pos.fromKey(move.slice(2, 4))
    } yield Uci.Drop(role, pos)
    else Uci.Move(move)

  def piotr(move: String): Option[Uci] =
    if (move lift 1 contains '@') for {
      role <- move.headOption flatMap Role.allByPgn.get
      pos  <- move lift 2 flatMap Pos.piotr
    } yield Uci.Drop(role, pos)
    else Uci.Move.piotr(move)

  def readList(moves: String): Option[List[Uci]] =
    moves.split(' ').toList.map(apply).sequence

  def writeList(moves: List[Uci]): String =
    moves.map(_.uci) mkString " "

  def readListPiotr(moves: String): Option[List[Uci]] =
    moves.split(' ').toList.map(piotr).sequence

  def writeListPiotr(moves: List[Uci]): String =
    moves.map(_.piotr) mkString " "
}
