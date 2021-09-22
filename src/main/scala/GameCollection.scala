package strategygames

sealed abstract class GameLogic {
  def id: Int
  def name: String

  override def toString = s"Lib($name)"
}

object GameLogic {

  final case class Chess() extends GameLogic {
    def id = 0
    def name = "Chess"
  }

  final case class Draughts() extends GameLogic {
    def id = 1
    def name = "Draughts"
  }

  def all = List(Chess, Draughts)

  // TODO: I'm sure there is a better scala way of doing this
  def apply(id: Int): GameLogic = id match {
    case 1 => Draughts()
    case _ => Chess()
  }
}

sealed abstract class GameFamily {
  def id: Int
  def name: String
  def shortName: String
  def codeLib: GameLogic

  override def toString = s"GameFamily($name)"
}

object GameFamily {

  final case class Chess() extends GameFamily {
    def id = GameLogic.Chess().id
    def name = GameLogic.Chess().name
    def shortName = GameLogic.Chess().name
    def codeLib = GameLogic.Chess()
  }

  final case class Draughts() extends GameFamily {
    def id = GameLogic.Draughts().id
    def name = GameLogic.Draughts().name
    def shortName = GameLogic.Draughts().name
    def codeLib = GameLogic.Draughts()
  }

  final case class LinesOfAction() extends GameFamily {
    def id = 2
    def name = "Lines Of Action"
    def shortName = "LOA"
    def codeLib = GameLogic.Chess()
  }

  def all = List(Chess, Draughts, LinesOfAction)

  // TODO: I'm sure there is a better scala way of doing this
  def apply(id: Int): GameFamily = id match {
    case 1 => Draughts()
    case 2 => LinesOfAction()
    case _ => Chess()
  }

}
