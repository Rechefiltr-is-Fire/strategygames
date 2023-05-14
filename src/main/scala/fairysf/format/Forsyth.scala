package strategygames.fairysf.format

import cats.implicits._

import strategygames.Player
import strategygames.fairysf._
import strategygames.fairysf.variant.Variant

/** Transform a game to standard Forsyth Edwards Notation
  * http://en.wikipedia.org/wiki/Forsyth%E2%80%93Edwards_Notation
  */
object Forsyth {

  // lishogi
  // val initial = FEN("lnsgkgsnl/1r5b1/ppppppppp/9/9/9/PPPPPPPPP/1B5R1/LNSGKGSNL b - 1")
  // pychess shogi
  val initial = FEN("lnsgkgsnl/1r5b1/ppppppppp/9/9/9/PPPPPPPPP/1B5R1/LNSGKGSNL[-] w 0 1")

  def <<@(variant: Variant, fen: FEN): Option[Situation] = {
    val (fsfFen, moveStr) =
      if (fen.value.contains("½")) {
        val parts = fen.value.split("½")
        (parts(0), parts.lift(1))
      } else
        (fen.value, None)
    val apiPosition       = Api.positionFromVariantNameAndFEN(variant.fairysfName.name, fsfFen)
    val baseSituation     = Situation(
      Board(
        pieces = apiPosition.pieceMap,
        history = History(),
        variant = variant,
        pocketData = apiPosition.pocketData,
        position = apiPosition.some
      ),
      fen.value.split(' ')(1) match {
        case "w" => P1
        case "b" => P2
        case _   => sys.error("Invalid player in fen")
      }
    )

    Some(
      moveStr
        .flatMap(moveStr => Uci.Move(variant.gameFamily, moveStr))
        .flatMap(uciMove => baseSituation.move(uciMove).toOption)
        .map(_.situationAfter)
        .getOrElse(baseSituation)
    )

  }

  def <<(fen: FEN): Option[Situation] = <<@(Variant.default, fen)

  case class SituationPlus(situation: Situation, fullMoveNumber: Int) {

    def turns = fullMoveNumber * 2 - situation.player.fold(2, 1)
  }

  def <<<@(variant: Variant, fen: FEN): Option[SituationPlus] =
    <<@(variant, fen) map { sit =>
      SituationPlus(
        // not doing half move clock history like we do in chess
        sit,
        fen.value.split(' ').last.toIntOption.map(_ max 1 min 500) | 1
      )
    }

  def <<<(fen: FEN): Option[SituationPlus] = <<<@(Variant.default, fen)

  def >>(situation: Situation): FEN = >>(SituationPlus(situation, 1))

  def >>(parsed: SituationPlus): FEN =
    parsed match {
      case SituationPlus(situation, _) => >>(Game(situation, turns = parsed.turns))
    }

  // The reason we let variants choose which board to use for the fen is because
  // some variants (amazon, for example) need to use the previous fen for half-moves
  // or fairy stockfish won't parse it properly.
  def >>(game: Game): FEN =
    game.situation.board.variant.paramsForFen(game) match {
      case (board, Some(lastMove)) => exportBoardFenWithLastMove(board, lastMove)
      case (board, _)              => exportBoardFen(board)
    }

  def exportBoard(board: Board): String = exportBoardFen(board).value

  def exportBoardFen(board: Board): FEN                             = board.variant.exportBoardFen(board)
  def exportBoardFenWithLastMove(board: Board, lastMove: Move): FEN =
    board.variant.exportBoardFenWithLastMove(board, lastMove)

  def boardAndPlayer(situation: Situation): String =
    boardAndPlayer(situation.board, situation.player)

  def boardAndPlayer(board: Board, turnPlayer: Player): String =
    s"${exportBoard(board)} ${turnPlayer.letter}"
}
