package strategygames.go
package variant

import strategygames.go._
import strategygames.{ GameFamily, Player }

case object Go9x9
    extends Variant(
      id = 1,
      key = "go9x9",
      name = "Go 9x9",
      standardInitialPosition = true,
      boardSize = Board.Dim9x9
    ) {

  def gameFamily: GameFamily = GameFamily.Go()

  def perfIcon: Char = '' // todo change
  def perfId: Int    = 500

  override def baseVariant: Boolean = true

  // cache this rather than checking with the API everytime
  override def initialFen =
    format.FEN("9/9/9/9/9/9/9/9/9 0 0 W 1")

  override def specialEnd(situation: Situation) =
    (situation.board.apiPosition.legalMoves.size == 0) ||
      (situation.board.apiPosition.gameEnd)

  override def specialDraw(situation: Situation) =
    situation.board.apiPosition.fen.player1Score == situation.board.apiPosition.fen.player2Score

  override def winner(situation: Situation): Option[Player] =
    if (specialEnd(situation) && !specialDraw(situation)) {
      if (situation.board.apiPosition.fen.player1Score > situation.board.apiPosition.fen.player2Score)
        Player.fromName("p1")
      else Player.fromName("p2")
    } else None

}
