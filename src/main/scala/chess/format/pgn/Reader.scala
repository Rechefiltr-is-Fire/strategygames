package strategygames.chess
package format.pgn
import strategygames.{ ByoyomiClock, FischerClock, Move => StratMove, Situation => StratSituation }

import strategygames.format.pgn.{ ParsedPgn, Sans, Tags }

import cats.data.Validated

object Reader {

  sealed trait Result {
    def valid: Validated[String, Replay]
  }

  object Result {
    case class Complete(replay: Replay)                    extends Result {
      def valid = Validated.valid(replay)
    }
    case class Incomplete(replay: Replay, failure: String) extends Result {
      def valid = Validated.invalid(failure)
    }
  }

  def full(pgn: String, tags: Tags = Tags.empty): Validated[String, Result] =
    fullWithSans(pgn, identity, tags)

  def moves(moveStrs: Iterable[String], tags: Tags): Validated[String, Result] =
    movesWithSans(moveStrs, identity, tags)

  def fullWithSans(pgn: String, op: Sans => Sans, tags: Tags = Tags.empty): Validated[String, Result] =
    Parser.full(cleanUserInput(pgn)) map { parsed =>
      makeReplay(makeGame(parsed.tags ++ tags), op(parsed.sans))
    }

  def fullWithSans(parsed: ParsedPgn, op: Sans => Sans): Result =
    makeReplay(makeGame(parsed.tags), op(parsed.sans))

  def movesWithSans(moveStrs: Iterable[String], op: Sans => Sans, tags: Tags): Validated[String, Result] =
    Parser.moves(moveStrs, tags.chessVariant | variant.Variant.default) map { moves =>
      makeReplay(makeGame(tags), op(moves))
    }

  // remove invisible byte order mark
  def cleanUserInput(str: String) = str.replace(s"\ufeff", "")

  private def makeReplay(game: Game, sans: Sans): Result =
    sans.value.foldLeft[Result](Result.Complete(Replay(game))) {
      case (Result.Complete(replay), san) =>
        san(StratSituation.wrap(replay.state.situation)).fold(
          err => Result.Incomplete(replay, err),
          action => Result.Complete(replay addMove action.toChess)
        )
      case (r: Result.Incomplete, _)      => r
    }

  private def makeGame(tags: Tags) = {
    val g = Game(
      variantOption = tags.chessVariant,
      fen = tags.chessFen
    )
    g.copy(
      startedAtTurn = g.turns,
      clock = tags.clockConfig.flatMap {
        case fc: FischerClock.Config => Some(FischerClock.apply(fc))
        case bc: ByoyomiClock.Config => Some(ByoyomiClock.apply(bc))
        case _                       => None
      }
    )
  }
}
