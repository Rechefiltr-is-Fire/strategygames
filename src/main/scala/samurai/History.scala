package strategygames.samurai

import format.Uci

case class History(
    lastMove: Option[Uci] = None,
    positionHashes: PositionHash = Array.empty,
    halfMoveClock: Int = 0
)
