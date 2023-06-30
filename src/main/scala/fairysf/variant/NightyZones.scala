package strategygames.fairysf
package variant

import cats.syntax.option._

import strategygames.fairysf.format.{ FEN, Uci }
import strategygames.{ GameFamily, Player }

case object NightyZones
    extends Variant(
      id = 9,
      key = "nightyzones",
      name = "Nighty Zones",
      standardInitialPosition = true,
      fairysfName = FairySFName("nightyzones"),
      boardSize = Board.Dim10x10
    ) {

  def gameFamily: GameFamily = GameFamily.NightyZones()

  def perfIcon: Char = 'Д'
  def perfId: Int    = 206

  override def baseVariant           = true
  override def repetitionEnabled     = false
  override def dropsVariant          = true
  override val switchPlayerAfterMove = false
  override val plysPerTurn           = 2

  override def hasAnalysisBoard: Boolean = false
  override def hasFishnet: Boolean       = true

  // cache this rather than checking with the API everytime
  override def initialFen =
    format.FEN(
      "3n2n3/10/10/n8n/10/10/N8N/10/10/3N2N3[PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPpppppppppppppppppppppppppppppppppppppppppppppp] w - - 0 1"
    )

  private def boardPart(board: Board): String = {
    val fen   = new scala.collection.mutable.StringBuilder(70)
    var empty = 0
    for (y <- Rank.allReversed) {
      empty = 0
      for (x <- File.all) {
        board(x, y) match {
          case None        => empty = empty + 1
          case Some(piece) =>
            if (empty > 0) {
              fen.append(empty)
              empty = 0
            }
            if (piece.player == Player.P1)
              fen.append(piece.forsyth.toString.toUpperCase())
            else fen.append(piece.forsyth.toString.toLowerCase())
        }
      }
      if (empty > 0) fen.append(s"${empty},")
      fen.append('/')
    }
    fen.toString.replace(",/", "/").dropRight(1)
  }

  private def fullPockets: String = s"[${"P" * 46}${"p" * 46}]"

  override def exportBoardFen(board: Board): FEN =
    FEN(
      s"${boardPart(board)}${fullPockets} ${board.apiPosition.fen.value.split(" ").drop(1).mkString(" ")}"
    )

  override def validMoves(situation: Situation): Map[Pos, List[Move]] =
    situation.board.history.lastMove match {
      case Some(_: Uci.Move) => Map.empty
      case _                 =>
        situation.board.apiPosition.legalMoves
          .map(_.split(",").headOption)
          .map {
            case Some(Uci.Move.moveR(orig, dest, promotion)) =>
              (
                Pos.fromKey(orig),
                Pos.fromKey(dest),
                promotion
              )
            case Some(x)                                     => sys.error(s"Ilegal move for Nighty Zones: ${x}")
            case _                                           => sys.error(s"Illegal unknown move for Nighty Zones.")
          }
          .distinct
          .map {
            case (Some(orig), Some(dest), _) => {
              val piece = situation.board.pieces(orig)
              (
                orig,
                Move(
                  piece = piece,
                  orig = orig,
                  dest = dest,
                  situationBefore = situation,
                  after = situation.board.copy(
                    pieces = situation.board.pieces - orig + ((dest, piece))
                  ),
                  capture = None,
                  promotion = None,
                  castle = None,
                  enpassant = false
                )
              )
            }
            case (orig, dest, prom)          => sys.error(s"Invalid position from uci: ${orig}${dest}${prom}")
          }
          .groupBy(_._1)
          .map { case (k, v) => (k, v.toList.map(_._2)) }
    }

  private val defaultDropRole: Role = NightyzonesLance

  override def validDrops(situation: Situation): List[Drop] =
    situation.board.history.lastMove match {
      case Some(lastMove: Uci.Move) =>
        situation.board.apiPosition.legalMoves
          .filter(_.startsWith(s"${lastMove.uci},"))
          .map(_.split(",").reverse.headOption)
          .map { case Some(Uci.Move.moveR(_, dest, _)) => Pos.fromKey(dest) }
          .map {
            case Some(dest) => {
              // val uciDrop     = s"${defaultDropRole.forsyth}@${dest.key}"
              val uciMove     = s"${lastMove.uci},${lastMove.dest.key}${dest.key}"
              val newPosition = situation.board.apiPosition.makeMoves(List(uciMove))
              val piece       = Piece(situation.player, defaultDropRole)
              Drop(
                piece = piece,
                pos = dest,
                situationBefore = situation,
                after = situation.board.copy(
                  pieces = situation.board.pieces + ((dest, piece)),
                  uciMoves = situation.board.uciMoves :+ uciMove,
                  position = newPosition.some
                )
              )
            }
            case dest       => sys.error(s"Invalid position from uci: ${defaultDropRole}@${dest}")
          }
          .toList
      case _                        => List()
    }

  override def valid(board: Board, strict: Boolean): Boolean =
    Api.validateFEN(fairysfName.name, board.apiPosition.fen.value)

  override def staleMate(situation: Situation): Boolean     = false
  override def specialEnd(situation: Situation): Boolean    = situation.board.apiPosition.legalMoves.isEmpty
  override def winner(situation: Situation): Option[Player] =
    if (specialEnd(situation)) Option(!situation.player)
    else None

}
