package strategygames.draughts

import cats.syntax.option._

case class OpeningTable(key: String, name: String, url: String, categories: List[StartingPosition.Category]) {

  val positions = categories.flatMap(_.positions)

  private lazy val shuffled = new scala.util.Random(475592).shuffle(positions).toIndexedSeq

  def randomOpening: (Int, StartingPosition) = {
    val index = scala.util.Random.nextInt(shuffled.size)
    index -> shuffled(index)
  }

  private val fen2position: Map[String, StartingPosition] = positions
    .map { p =>
      p.fen -> p
    }
    .to(Map)

  def openingByFen = fen2position.get _

  def withFen(p: StartingPosition) = s"$key|${p.fen}"
  def withRandomFen                = withFen(StartingPosition.random)
}

object OpeningTable {

  import StartingPosition.Category

  private val categoriesFMJD = List(
    Category(
      "1",
      List(
        StartingPosition(
          "1-I",
          "W:W17,21,22,23,24,25,26,27,28,30,31,32:B1,2,3,4,6,7,8,9,11,12,13,14",
          "1. ab4 ba5 2. ba3 ab6 3. ab2 dc5",
          "ab4 ba5 ba3 ab6 ab2 dc5".some
        ),
        StartingPosition(
          "1-II",
          "W:W17,21,22,23,24,25,26,27,28,29,31,32:B1,2,3,4,5,7,8,9,11,12,13,15",
          "1. ab4 ba5 2. ba3 cb6 3. cb2 de5",
          "ab4 ba5 ba3 cb6 cb2 de5".some
        ),
        StartingPosition(
          "1-III",
          "W:W17,20,21,22,23,26,27,28,29,30,31,32:B1,2,3,4,5,7,8,9,10,12,13,15",
          "1. ab4 ba5 2. ba3 cb6 3. gh4 fe5",
          "ab4 ba5 ba3 cb6 gh4 fe5".some
        ),
        StartingPosition(
          "1-IV",
          "B:W17,20,21,22,23,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,12,13,15",
          "1. ab4 ba5 2. ba3 fe5 3. gh4",
          "ab4 ba5 ba3 fe5 gh4".some
        ),
        StartingPosition(
          "1-V",
          "W:W17,18,22,23,24,25,26,28,29,30,31,32:B2,3,4,5,6,7,8,9,10,11,12,13",
          "1. ab4 ba5 2. ed4 ab6 3. fe3 ba7",
          "ab4 ba5 ed4 ab6 fe3 ba7".some
        ),
        StartingPosition(
          "1-VI",
          "W:W17,18,22,23,24,25,26,28,29,30,31,32:B1,2,3,4,6,7,8,9,11,12,13,14",
          "1. ab4 ba5 2. ed4 ab6 3. fe3 dc5",
          "ab4 ba5 ed4 ab6 fe3 dc5".some
        ),
        StartingPosition(
          "1-VII",
          "W:W17,18,22,23,24,25,26,28,29,30,31,32:B1,2,3,4,6,7,8,9,10,12,13,15",
          "1. ab4 ba5 2. ed4 ab6 3. fe3 fe5",
          "ab4 ba5 ed4 ab6 fe3 fe5".some
        ),
        StartingPosition(
          "1-VIII",
          "W:W17,18,22,23,24,25,26,28,29,30,31,32:B1,2,3,4,6,7,8,9,10,11,13,16",
          "1. ab4 ba5 2. ed4 ab6 3. fe3 hg5",
          "ab4 ba5 ed4 ab6 fe3 hg5".some
        ),
        StartingPosition(
          "1-IX",
          "W:W18,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,7,8,11,12,13,15",
          "1. ab4 ba5 2. ed4 dc5 3. bd6 ce5",
          "ab4 ba5 ed4 dc5 bd6 ce5".some
        ),
        StartingPosition(
          "1-X",
          "B:W17,20,22,23,24,25,26,27,29,30,31,32:B1,2,3,4,5,6,7,8,10,12,13,15",
          "1. ab4 ba5 2. gh4 fe5 3. hg3",
          "ab4 ba5 gh4 fe5 hg3".some
        ),
        StartingPosition(
          "1-XI",
          "W:W13,18,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,12,15,17",
          "1. ab4 bc5 2. ba5 cb4 3. ed4 fe5",
          "ab4 bc5 ba5 cb4 ed4 fe5".some
        ),
        StartingPosition(
          "1-XII",
          "W:W13,18,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,12,16,17",
          "1. ab4 bc5 2. ba5 cb4 3. ed4 fg5",
          "ab4 bc5 ba5 cb4 ed4 fg5".some
        ),
        StartingPosition(
          "1-XIII",
          "W:W13,18,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,16,17",
          "1. ab4 bc5 2. ba5 cb4 3. ed4 hg5",
          "ab4 bc5 ba5 cb4 ed4 hg5".some
        ),
        StartingPosition(
          "1-XIV",
          "W:W13,19,22,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,12,15,17",
          "1. ab4 bc5 2. ba5 cb4 3. gf4 fe5",
          "ab4 bc5 ba5 cb4 gf4 fe5".some
        ),
        StartingPosition(
          "1-XV",
          "B:W13,20,22,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,17",
          "1. ab4 bc5 2. ba5 cb4 3. gh4",
          "ab4 bc5 ba5 cb4 gh4".some
        ),
        StartingPosition(
          "1-XVI",
          "B:W20,22,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,8,9,11,12,14",
          "1. ab4 dc5 2. bd6 ec5 3. gh4",
          "ab4 dc5 bd6 ec5 gh4".some
        )
      )
    ),
    Category(
      "2",
      List(
        StartingPosition(
          "2-I",
          "B:W19,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,7,8,9,11,12,15",
          "1. ab4 dc5 2. bd6 ce5 3. ef4",
          "ab4 dc5 bd6 ce5 ef4".some
        ),
        StartingPosition(
          "2-II",
          "B:W13,19,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,12,15,16",
          "1. ab4 de5 2. ba5 fg5 3. ef4",
          "ab4 de5 ba5 fg5 ef4".some
        ),
        StartingPosition(
          "2-III",
          "W:W17,21,22,23,24,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,12,15,16",
          "1. ab4 de5 2. ba3 fg5",
          "ab4 de5 ba3 fg5".some
        ),
        StartingPosition(
          "2-IV",
          "B:W17,19,21,22,23,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,12,15,16",
          "1. ab4 de5 2. ba3 fg5 3. gf4",
          "ab4 de5 ba3 fg5 gf4".some
        ),
        StartingPosition(
          "2-V",
          "B:W13,18,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,15,16",
          "1. ab4 de5 2. ed4 hg5 3. ba5",
          "ab4 de5 ed4 hg5 ba5".some
        ),
        StartingPosition(
          "2-VI",
          "B:W17,20,22,23,24,25,26,28,29,30,31,32:B1,2,3,4,5,6,7,9,10,11,12,15",
          "1. ab4 fe5 2. gh4 gf6 3. fg3",
          "ab4 fe5 gh4 gf6 fg3".some
        ),
        StartingPosition(
          "2-VII",
          "B:W13,18,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,9,10,11,12,16",
          "1. ab4 fg5 2. ba5 gf6 3. ed4",
          "ab4 fg5 ba5 gf6 ed4".some
        ),
        StartingPosition(
          "2-VIII",
          "B:W17,18,21,22,24,26,27,28,29,30,31,32:B1,2,3,4,5,6,8,9,10,11,12,16",
          "1. ab4 fg5 2. ba3 ef6 3. ed4",
          "ab4 fg5 ba3 ef6 ed4".some
        ),
        StartingPosition(
          "2-IX",
          "W:W17,21,22,23,24,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,19",
          "1. ab4 hg5 2. ba3 gf4",
          "ab4 hg5 ba3 gf4".some
        ),
        StartingPosition(
          "2-X",
          "W:W17,20,21,22,23,25,27,28,29,30,31,32:B1,2,3,4,5,7,8,9,10,12,13,15",
          "1. cb4 ba5 2. dc3 fe5 3. gh4 cb6",
          "cb4 ba5 dc3 fe5 gh4 cb6".some
        ),
        StartingPosition(
          "2-XI",
          "W:W17,21,22,23,24,25,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,13,16",
          "1. cb4 ba5 2. dc3 hg5",
          "cb4 ba5 dc3 hg5".some
        ),
        StartingPosition(
          "2-XII",
          "B:W17,19,21,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,13",
          "1. cb4 ba5 2. ef4",
          "cb4 ba5 ef4".some
        ),
        StartingPosition(
          "2-XIII",
          "W:W18,20,21,23,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,11,12,15",
          "1. cb4 ba5 2. gh4 ac3 3. bd4 de5",
          "cb4 ba5 gh4 ac3 bd4 de5".some
        ),
        StartingPosition(
          "2-XIV",
          "B:W17,18,21,22,24,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,12,14,15",
          "1. cb4 bc5 2. bc3 fe5 3. ed4",
          "cb4 bc5 bc3 fe5 ed4".some
        ),
        StartingPosition(
          "2-XV",
          "B:W17,18,21,22,24,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,12,14,16",
          "1. cb4 bc5 2. bc3 fg5 3. ed4",
          "cb4 bc5 bc3 fg5 ed4".some
        ),
        StartingPosition(
          "2-XVI",
          "W:W17,21,22,23,24,25,27,28,29,30,31,32:B1,2,3,4,6,7,8,9,10,11,12,14",
          "1. cb4 bc5 2. dc3 ab6",
          "cb4 bc5 dc3 ab6".some
        )
      )
    ),
    Category(
      "3",
      List(
        StartingPosition(
          "3-I",
          "W:W17,21,22,23,24,25,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,12,14,15",
          "1. cb4 bc5 2. dc3 fe5",
          "cb4 bc5 dc3 fe5".some
        ),
        StartingPosition(
          "3-II",
          "W:W17,19,21,22,24,25,27,28,29,30,31,32:B1,2,3,4,5,6,8,10,11,12,14,15",
          "1. cb4 bc5 2. dc3 fe5 3. ef4 ef6",
          "cb4 bc5 dc3 fe5 ef4 ef6".some
        ),
        StartingPosition(
          "3-III",
          "W:W17,20,21,22,23,25,27,28,29,30,31,32:B1,2,3,4,6,7,8,9,10,12,14,15",
          "1. cb4 bc5 2. dc3 fe5 3. gh4 ab6",
          "cb4 bc5 dc3 fe5 gh4 ab6".some
        ),
        StartingPosition(
          "3-IV",
          "W:W17,20,21,22,23,25,27,28,29,30,31,32:B1,2,3,4,5,6,7,10,11,12,14,15",
          "1. cb4 bc5 2. dc3 fe5 3. gh4 gf6",
          "cb4 bc5 dc3 fe5 gh4 gf6".some
        ),
        StartingPosition(
          "3-V",
          "W:W17,18,21,24,25,26,28,29,30,31,32:B1,2,3,4,5,7,8,9,10,11,12",
          "1. cb4 bc5 2. ed4 ce3 3. fd4 cb6",
          "cb4 bc5 ed4 ce3 fd4 cb6".some
        ),
        StartingPosition(
          "3-VI",
          "W:W17,18,21,24,25,26,28,29,30,31,32:B1,2,3,4,5,6,7,8,11,12,15",
          "1. cb4 bc5 2. ed4 ce3 3. fd4 de5",
          "cb4 bc5 ed4 ce3 fd4 de5".some
        ),
        StartingPosition(
          "3-VII",
          "W:W19,21,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,8,11,12,13,14",
          "1. cb4 dc5 2. bd6 ec5 3. gf4 ba5",
          "cb4 dc5 bd6 ec5 gf4 ba5".some
        ),
        StartingPosition(
          "3-VIII",
          "W:W20,21,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,7,8,11,12,13,15",
          "1. cb4 dc5 2. bd6 ce5 3. gh4 ba5",
          "cb4 dc5 bd6 ce5 gh4 ba5".some
        ),
        StartingPosition(
          "3-IX",
          "W:W17,21,22,23,24,25,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,12,15,16",
          "1. cb4 de5 2. dc3 fg5",
          "cb4 de5 dc3 fg5".some
        ),
        StartingPosition(
          "3-X",
          "W:W17,20,21,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,7,8,9,10,11,12,15",
          "1. cb4 de5 2. gh4 cd6",
          "cb4 de5 gh4 cd6".some
        ),
        StartingPosition(
          "3-XI",
          "W:W17,21,22,23,24,25,26,27,28,29,31,32:B1,2,3,4,5,6,7,9,10,12,15,16",
          "1. cb4 fe5 2. bc3 gf6 3. cb2 fg5",
          "cb4 fe5 bc3 gf6 cb2 fg5".some
        ),
        StartingPosition(
          "3-XII",
          "B:W17,18,21,22,24,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,9,10,11,12,15",
          "1. cb4 fe5 2. bc3 gf6 3. ed4",
          "cb4 fe5 bc3 gf6 ed4".some
        ),
        StartingPosition(
          "3-XIII",
          "W:W17,21,22,23,24,25,27,28,29,30,31,32:B1,2,3,4,5,6,7,9,10,11,12,15",
          "1. cb4 fe5 2. dc3 gf6",
          "cb4 fe5 dc3 gf6".some
        ),
        StartingPosition(
          "3-XIV",
          "W:W17,18,21,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,8,9,10,11,12,16",
          "1. cb4 fg5 2. ed4 ef6",
          "cb4 fg5 ed4 ef6".some
        ),
        StartingPosition(
          "3-XV",
          "W:W17,18,21,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,19",
          "1. cb4 fg5 2. ed4 gf4",
          "cb4 fg5 ed4 gf4".some
        ),
        StartingPosition(
          "3-XVI",
          "B:W13,18,21,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,20",
          "1. cb4 hg5 2. ba5 gh4 3. ed4",
          "cb4 hg5 ba5 gh4 ed4".some
        )
      )
    ),
    Category(
      "4",
      List(
        StartingPosition(
          "4-I",
          "W:W16,17,21,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,13,20",
          "1. cb4 hg5 2. gf4 gh4 3. fg5 ba5",
          "cb4 hg5 gf4 gh4 fg5 ba5".some
        ),
        StartingPosition(
          "4-II",
          "B:W18,19,21,22,24,26,27,28,29,30,31,32:B1,2,3,4,6,7,8,9,10,11,12,13",
          "1. cd4 ba5 2. bc3 ab6 3. ef4",
          "cd4 ba5 bc3 ab6 ef4".some
        ),
        StartingPosition(
          "4-III",
          "B:W18,19,21,22,23,26,27,28,29,30,31,32:B1,2,3,4,6,7,8,9,10,11,12,13",
          "1. cd4 ba5 2. bc3 ab6 3. gf4",
          "cd4 ba5 bc3 ab6 gf4".some
        ),
        StartingPosition(
          "4-IV",
          "W:W18,21,22,23,24,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,11,12,13,15",
          "1. cd4 ba5 2. bc3 de5",
          "cd4 ba5 bc3 de5".some
        ),
        StartingPosition(
          "4-V",
          "W:W18,21,22,23,24,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,12,13,15",
          "1. cd4 ba5 2. bc3 fe5",
          "cd4 ba5 bc3 fe5".some
        ),
        StartingPosition(
          "4-VI",
          "B:W18,21,22,23,24,25,26,27,28,30,31,32:B1,2,3,4,5,6,7,8,10,11,13,16",
          "1. cd4 ba5 2. bc3 hg5 3. ab2",
          "cd4 ba5 bc3 hg5 ab2".some
        ),
        StartingPosition(
          "4-VII",
          "B:W18,19,21,22,23,25,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,11,12,13,15",
          "1. cd4 ba5 2. dc3 de5 3. gf4",
          "cd4 ba5 dc3 de5 gf4".some
        ),
        StartingPosition(
          "4-VIII",
          "B:W18,20,21,22,23,25,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,11,12,13,15",
          "1. cd4 ba5 2. dc3 de5 3. gh4",
          "cd4 ba5 dc3 de5 gh4".some
        ),
        StartingPosition(
          "4-IX",
          "W:W18,20,21,22,23,25,27,28,29,30,31,32:B1,2,3,4,6,7,8,9,10,12,13,16",
          "1. cd4 ba5 2. dc3 fg5 3. gh4 ab6",
          "cd4 ba5 dc3 fg5 gh4 ab6".some
        ),
        StartingPosition(
          "4-X",
          "W:W18,19,21,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,11,12,13,14",
          "1. cd4 ba5 2. ef4 dc5",
          "cd4 ba5 ef4 dc5".some
        ),
        StartingPosition(
          "4-XI",
          "W:W18,19,21,23,24,25,27,28,29,30,31,32:B1,2,3,4,5,7,8,9,11,12,13,14",
          "1. cd4 ba5 2. ef4 cb6 3. de3 dc5",
          "cd4 ba5 ef4 cb6 de3 dc5".some
        ),
        StartingPosition(
          "4-XII",
          "W:W18,19,21,23,24,25,26,28,29,30,31,32:B1,2,3,4,5,7,8,9,10,11,12,17",
          "1. cd4 ba5 2. ef4 cb6 3. fe3 ab4",
          "cd4 ba5 ef4 cb6 fe3 ab4".some
        ),
        StartingPosition(
          "4-XIII",
          "W:W18,19,21,23,24,25,26,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,12,16,17",
          "1. cd4 ba5 2. ef4 fg5 3. fe3 ab4",
          "cd4 ba5 ef4 fg5 fe3 ab4".some
        ),
        StartingPosition(
          "4-XIV",
          "W:W18,19,21,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,11,12,13,15",
          "1. cd4 ba5 2. gf4 de5",
          "cd4 ba5 gf4 de5".some
        ),
        StartingPosition(
          "4-XV",
          "W:W19,21,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,8,10,12,13,16",
          "1. cd4 ba5 2. gf4 fe5 3. df6 eg5",
          "cd4 ba5 gf4 fe5 df6 eg5".some
        ),
        StartingPosition(
          "4-XVI",
          "W:W21,22,23,24,26,27,28,29,30,31,32:B1,2,3,4,6,7,8,11,12,14,15",
          "1. cd4 bc5 2. db6 ac5 3. bc3 de5",
          "cd4 bc5 db6 ac5 bc3 de5".some
        )
      )
    ),
    Category(
      "5",
      List(
        StartingPosition(
          "5-I",
          "W:W21,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,7,8,10,11,12,13",
          "1. cd4 bc5 2. db6 ca5",
          "cd4 bc5 db6 ca5".some
        ),
        StartingPosition(
          "5-II",
          "B:W18,21,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,7,8,10,11,12,13",
          "1. cd4 bc5 2. db6 ca5 3. ed4",
          "cd4 bc5 db6 ca5 ed4".some
        ),
        StartingPosition(
          "5-III",
          "W:W18,21,22,23,24,25,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,12,14,15",
          "1. cd4 dc5 2. dc3 fe5",
          "cd4 dc5 dc3 fe5".some
        ),
        StartingPosition(
          "5-IV",
          "W:W18,20,21,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,12,14,15",
          "1. cd4 dc5 2. gh4 fe5",
          "cd4 dc5 gh4 fe5".some
        ),
        StartingPosition(
          "5-V",
          "W:W18,21,22,24,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,12,20",
          "1. cd4 de5 2. bc3 ef4 3. eg5 fh4",
          "cd4 de5 bc3 ef4 eg5 fh4".some
        ),
        StartingPosition(
          "5-VI",
          "W:W18,20,21,23,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,12,15",
          "1. cd4 de5 2. gh4 ec3 3. bd4 fe5",
          "cd4 de5 gh4 ec3 bd4 fe5".some
        ),
        StartingPosition(
          "5-VII",
          "B:W20,21,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,9,10,12,15",
          "1. cd4 fe5 2. df6 ge5 3. gh4",
          "cd4 fe5 df6 ge5 gh4".some
        ),
        StartingPosition(
          "5-VIII",
          "B:W18,21,22,23,24,25,26,27,28,30,31,32:B1,2,3,4,5,6,8,9,10,11,12,16",
          "1. cd4 fg5 2. bc3 ef6 3. ab2",
          "cd4 fg5 bc3 ef6 ab2".some
        ),
        StartingPosition(
          "5-IX",
          "W:W18,21,22,23,24,25,26,27,28,30,31,32:B1,2,3,4,5,6,7,9,11,12,15,16",
          "1. cd4 fg5 2. bc3 gf6 3. ab2 de5",
          "cd4 fg5 bc3 gf6 ab2 de5".some
        ),
        StartingPosition(
          "5-X",
          "W:W18,21,22,23,24,25,26,27,28,29,30,32:B1,2,3,4,5,6,8,10,11,12,14,16",
          "1. cd4 fg5 2. dc3 ef6 3. ed2 bc5",
          "cd4 fg5 dc3 ef6 ed2 bc5".some
        ),
        StartingPosition(
          "5-XI",
          "W:W18,21,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,8,9,12,14,16",
          "1. cd4 fg5 2. gh4 dc5 3. hf6 eg5",
          "cd4 fg5 gh4 dc5 hf6 eg5".some
        ),
        StartingPosition(
          "5-XII",
          "W:W18,20,21,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,8,9,10,11,12,16",
          "1. cd4 fg5 2. gh4 ef6",
          "cd4 fg5 gh4 ef6".some
        ),
        StartingPosition(
          "5-XIII",
          "W:W18,21,22,23,24,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,20",
          "1. cd4 hg5 2. bc3 gh4",
          "cd4 hg5 bc3 gh4".some
        ),
        StartingPosition(
          "5-XIV",
          "W:W17,18,21,23,24,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,15,20",
          "1. cd4 hg5 2. bc3 gh4 3. cb4 de5",
          "cd4 hg5 bc3 gh4 cb4 de5".some
        ),
        StartingPosition(
          "5-XV",
          "W:W16,18,21,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,14,20",
          "1. cd4 hg5 2. gf4 gh4 3. fg5 dc5",
          "cd4 hg5 gf4 gh4 fg5 dc5".some
        ),
        StartingPosition(
          "5-XVI",
          "W:W16,18,21,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,15,20",
          "1. cd4 hg5 2. gf4 gh4 3. fg5 de5",
          "cd4 hg5 gf4 gh4 fg5 de5".some
        )
      )
    ),
    Category(
      "6",
      List(
        StartingPosition(
          "6-I",
          "W:W16,18,21,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,15,20",
          "1. cd4 hg5 2. gf4 gh4 3. fg5 fe5",
          "cd4 hg5 gf4 gh4 fg5 fe5".some
        ),
        StartingPosition(
          "6-II",
          "W:W18,21,22,23,24,25,26,28,29,30,31,32:B1,2,3,4,6,7,8,9,10,11,12,13",
          "1. ed4 ba5 2. fe3 ab6",
          "ed4 ba5 fe3 ab6".some
        ),
        StartingPosition(
          "6-III",
          "W:W18,20,21,22,23,25,26,28,29,30,31,32:B1,2,3,4,6,7,8,9,11,12,13,15",
          "1. ed4 ba5 2. fe3 de5 3. gh4 ab6",
          "ed4 ba5 fe3 de5 gh4 ab6".some
        ),
        StartingPosition(
          "6-IV",
          "W:W18,20,21,22,23,25,26,28,29,30,31,32:B1,2,3,4,5,7,8,9,11,12,13,15",
          "1. ed4 ba5 2. fe3 de5 3. gh4 cb6",
          "ed4 ba5 fe3 de5 gh4 cb6".some
        ),
        StartingPosition(
          "6-V",
          "W:W18,21,22,23,24,25,26,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,12,13,15",
          "1. ed4 ba5 2. fe3 fe5",
          "ed4 ba5 fe3 fe5".some
        ),
        StartingPosition(
          "6-VI",
          "W:W18,21,22,23,24,25,26,27,28,29,30,31:B1,2,3,4,5,6,7,8,10,12,13,20",
          "1. ed4 ba5 2. fe3 fg5 3. gf2 gh4",
          "ed4 ba5 fe3 fg5 gf2 gh4".some
        ),
        StartingPosition(
          "6-VII",
          "B:W18,21,22,23,24,25,26,27,28,29,30,31:B1,2,3,4,5,6,7,8,10,11,13,16",
          "1. ed4 ba5 2. fe3 hg5 3. gf2",
          "ed4 ba5 fe3 hg5 gf2".some
        ),
        StartingPosition(
          "6-VIII",
          "W:W18,19,21,22,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,12,13,16",
          "1. ed4 ba5 2. gf4 fg5",
          "ed4 ba5 gf4 fg5".some
        ),
        StartingPosition(
          "6-IX",
          "W:W21,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,7,8,10,11,12,13",
          "1. ed4 bc5 2. db6 ca5",
          "ed4 bc5 db6 ca5".some
        ),
        StartingPosition(
          "6-X",
          "W:W17,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,7,8,9,10,11,12,13",
          "1. ed4 bc5 2. db6 ca5 3. ab4 ab6",
          "ed4 bc5 db6 ca5 ab4 ab6".some
        ),
        StartingPosition(
          "6-XI",
          "W:W17,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,7,8,10,11,13,16",
          "1. ed4 bc5 2. db6 ca5 3. ab4 hg5",
          "ed4 bc5 db6 ca5 ab4 hg5".some
        ),
        StartingPosition(
          "6-XII",
          "W:W17,18,21,24,25,26,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,12,16",
          "1. ed4 dc5 2. cb4 ce3 3. fd4 fg5",
          "ed4 dc5 cb4 ce3 fd4 fg5".some
        ),
        StartingPosition(
          "6-XIII",
          "W:W17,19,21,24,25,27,28,29,30,31,32:B1,2,3,4,5,7,8,9,10,11,12",
          "1. ed4 dc5 2. cb4 ce3 3. df4 cd6",
          "ed4 dc5 cb4 ce3 df4 cd6".some
        ),
        StartingPosition(
          "6-XIV",
          "W:W18,20,21,22,25,26,28,29,30,31,32:B1,2,3,4,5,6,7,8,11,12,13",
          "1. ed4 dc5 2. gh4 ce3 3. fd4 ba5",
          "ed4 dc5 gh4 ce3 fd4 ba5".some
        ),
        StartingPosition(
          "6-XV",
          "W:W17,18,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,7,8,9,10,11,12,15",
          "1. ed4 de5 2. ab4 cd6",
          "ed4 de5 ab4 cd6".some
        ),
        StartingPosition(
          "6-XVI",
          "W:W17,18,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,8,9,10,11,12,15",
          "1. ed4 de5 2. ab4 ed6",
          "ed4 de5 ab4 ed6".some
        )
      )
    ),
    Category(
      "7",
      List(
        StartingPosition(
          "7-I",
          "W:W18,20,21,22,25,26,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,16",
          "1. ed4 de5 2. gf4 eg3 3. fh4 hg5",
          "ed4 de5 gf4 eg3 fh4 hg5".some
        ),
        StartingPosition(
          "7-II",
          "W:W17,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,9,10,15,16",
          "1. ed4 fe5 2. df6 ge5 3. ab4 hg5",
          "ed4 fe5 df6 ge5 ab4 hg5".some
        ),
        StartingPosition(
          "7-III",
          "B:W21,22,23,24,25,26,28,29,30,31,32:B1,2,3,4,5,6,8,9,10,12,16",
          "1. ed4 fe5 2. df6 eg5 3. fe3",
          "ed4 fe5 df6 eg5 fe3".some
        ),
        StartingPosition(
          "7-IV",
          "W:W18,21,22,23,24,25,26,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,12,14,16",
          "1. ed4 fg5 2. fe3 bc5",
          "ed4 fg5 fe3 bc5".some
        ),
        StartingPosition(
          "7-V",
          "W:W18,21,22,23,24,25,26,28,29,30,31,32:B1,2,3,4,5,6,8,9,10,11,12,16",
          "1. ed4 fg5 2. fe3 ef6",
          "ed4 fg5 fe3 ef6".some
        ),
        StartingPosition(
          "7-VI",
          "W:W18,21,22,23,24,25,26,27,28,29,30,31:B1,2,3,4,5,6,8,10,11,12,14,16",
          "1. ed4 fg5 2. fe3 ef6 3. gf2 bc5",
          "ed4 fg5 fe3 ef6 gf2 bc5".some
        ),
        StartingPosition(
          "7-VII",
          "W:W18,20,21,22,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,12,13,16",
          "1. ed4 fg5 2. gh4 ba5",
          "ed4 fg5 gh4 ba5".some
        ),
        StartingPosition(
          "7-VIII",
          "W:W18,20,21,22,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,8,9,10,11,12,16",
          "1. ed4 fg5 2. gh4 ef6",
          "ed4 fg5 gh4 ef6".some
        ),
        StartingPosition(
          "7-IX",
          "W:W18,20,21,22,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,9,10,11,12,16",
          "1. ed4 fg5 2. gh4 gf6",
          "ed4 fg5 gh4 gf6".some
        ),
        StartingPosition(
          "7-X",
          "W:W17,18,21,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,19",
          "1. ed4 hg5 2. cb4 gf4",
          "ed4 hg5 cb4 gf4".some
        ),
        StartingPosition(
          "7-XI",
          "W:W17,18,21,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,20",
          "1. ed4 hg5 2. cb4 gh4",
          "ed4 hg5 cb4 gh4".some
        ),
        StartingPosition(
          "7-XII",
          "W:W18,21,22,23,24,25,26,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,19",
          "1. ed4 hg5 2. fe3 gf4",
          "ed4 hg5 fe3 gf4".some
        ),
        StartingPosition(
          "7-XIII",
          "W:W18,19,21,23,24,25,27,28,29,30,31,32:B1,2,3,4,6,7,8,9,11,12,13,14",
          "1. ef4 ba5 2. de3 ab6 3. cd4 dc5",
          "ef4 ba5 de3 ab6 cd4 dc5".some
        ),
        StartingPosition(
          "7-XIV",
          "W:W19,21,22,23,24,25,26,27,28,29,31,32:B1,2,3,4,5,7,8,9,10,12,13,15",
          "1. ef4 ba5 2. de3 cb6 3. cd2 fe5",
          "ef4 ba5 de3 cb6 cd2 fe5".some
        ),
        StartingPosition(
          "7-XV",
          "W:W19,21,22,23,24,25,26,27,28,29,30,31:B1,2,3,4,6,7,8,9,10,12,13,16",
          "1. ef4 ba5 2. fe3 ab6 3. gf2 fg5",
          "ef4 ba5 fe3 ab6 gf2 fg5".some
        ),
        StartingPosition(
          "7-XVI",
          "B:W18,19,21,23,24,25,26,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,12,13,15",
          "1. ef4 ba5 2. fe3 fe5 3. cd4",
          "ef4 ba5 fe3 fe5 cd4".some
        )
      )
    ),
    Category(
      "8",
      List(
        StartingPosition(
          "8-I",
          "W:W18,19,21,23,24,25,27,28,29,30,31,32:B1,2,3,4,6,7,8,9,10,12,14,16",
          "1. ef4 bc5 2. de3 ab6 3. cd4 fg5",
          "ef4 bc5 de3 ab6 cd4 fg5".some
        ),
        StartingPosition(
          "8-II",
          "W:W18,19,21,23,24,25,26,28,29,30,31,32:B1,2,3,4,5,7,8,9,10,12,14,15",
          "1. ef4 bc5 2. fe3 cb6 3. cd4 fe5",
          "ef4 bc5 fe3 cb6 cd4 fe5".some
        ),
        StartingPosition(
          "8-III",
          "W:W17,19,21,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,8,9,10,11,12,14",
          "1. ef4 dc5 2. cb4 ed6",
          "ef4 dc5 cb4 ed6".some
        ),
        StartingPosition(
          "8-IV",
          "W:W17,19,20,21,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,8,9,11,12,14,15",
          "1. ef4 dc5 2. cb4 ed6 3. gh4 de5",
          "ef4 dc5 cb4 ed6 gh4 de5".some
        ),
        StartingPosition(
          "8-V",
          "B:W19,21,22,23,24,25,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,14",
          "1. ef4 dc5 2. de3",
          "ef4 dc5 de3".some
        ),
        StartingPosition(
          "8-VI",
          "B:W19,21,22,23,24,25,26,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,14",
          "1. ef4 dc5 2. fe3",
          "ef4 dc5 fe3".some
        ),
        StartingPosition(
          "8-VII",
          "W:W19,20,21,22,24,25,26,27,29,30,31,32:B1,2,3,4,5,6,8,9,10,12,14,15",
          "1. ef4 dc5 2. gh4 ed6 3. hg3 fe5",
          "ef4 dc5 gh4 ed6 hg3 fe5".some
        ),
        StartingPosition(
          "8-VIII",
          "W:W19,21,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,15",
          "1. ef4 de5",
          "ef4 de5".some
        ),
        StartingPosition(
          "8-IX",
          "B:W21,22,23,24,25,27,28,29,30,31,32:B1,2,3,4,5,6,8,9,11,12,14",
          "1. ef4 de5 2. fd6 ec5 3. de3",
          "ef4 de5 fd6 ec5 de3".some
        ),
        StartingPosition(
          "8-X",
          "W:W21,22,23,24,25,27,28,29,30,31,32:B1,2,3,4,5,7,8,9,12,15,16",
          "1. ef4 de5 2. fd6 ce5 3. de3 fg5",
          "ef4 de5 fd6 ce5 de3 fg5".some
        ),
        StartingPosition(
          "8-XI",
          "B:W18,19,21,24,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12",
          "1. ef4 fe5 2. cd4 ec3 3. bd4",
          "ef4 fe5 cd4 ec3 bd4".some
        ),
        StartingPosition(
          "8-XII",
          "B:W19,21,22,23,24,25,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,15",
          "1. ef4 fe5 2. de3",
          "ef4 fe5 de3".some
        ),
        StartingPosition(
          "8-XIII",
          "W:W17,19,22,23,24,25,27,28,29,30,31,32:B1,2,3,4,5,7,8,9,10,12,13,15",
          "1. ef4 fe5 2. de3 ba5 3. ab4 cb6",
          "ef4 fe5 de3 ba5 ab4 cb6".some
        ),
        StartingPosition(
          "8-XIV",
          "B:W17,18,21,24,25,26,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12",
          "1. ef4 fg5 2. cb4 ge3 3. fd4",
          "ef4 fg5 cb4 ge3 fd4".some
        ),
        StartingPosition(
          "8-XV",
          "W:W19,21,22,23,24,25,26,27,28,29,30,31:B1,2,3,4,5,6,7,8,10,12,13,20",
          "1. ef4 fg5 2. fe3 gh4 3. gf2 ba5",
          "ef4 fg5 fe3 gh4 gf2 ba5".some
        ),
        StartingPosition(
          "8-XVI",
          "W:W19,21,22,23,24,25,26,27,28,29,30,31:B1,2,3,4,5,6,7,8,9,12,15,20",
          "1. ef4 fg5 2. fe3 gh4 3. gf2 de5",
          "ef4 fg5 fe3 gh4 gf2 de5".some
        )
      )
    ),
    Category(
      "9",
      List(
        StartingPosition(
          "9-I",
          "W:W19,20,21,22,23,25,26,27,29,30,31,32:B1,2,3,4,6,7,8,9,11,12,13,15",
          "1. gf4 ba5 2. hg3 ab6 3. gh4 de5",
          "gf4 ba5 hg3 ab6 gh4 de5".some
        ),
        StartingPosition(
          "9-II",
          "W:W17,19,21,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,11,12,13,14",
          "1. gf4 dc5 2. cb4 ba5",
          "gf4 dc5 cb4 ba5".some
        ),
        StartingPosition(
          "9-III",
          "W:W18,19,21,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,8,9,10,11,12,14",
          "1. gf4 dc5 2. cd4 ed6",
          "gf4 dc5 cd4 ed6".some
        ),
        StartingPosition(
          "9-IV",
          "W:W19,20,21,22,23,25,26,27,29,30,31,32:B1,2,3,4,5,6,8,9,10,12,14,15",
          "1. gf4 dc5 2. hg3 ed6 3. gh4 fe5",
          "gf4 dc5 hg3 ed6 gh4 fe5".some
        ),
        StartingPosition(
          "9-V",
          "W:W19,21,22,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,15",
          "1. gf4 de5",
          "gf4 de5".some
        ),
        StartingPosition(
          "9-VI",
          "W:W21,22,23,24,25,26,28,29,30,31,32:B2,3,4,5,6,7,8,9,11,12,15",
          "1. gf4 de5 2. fd6 ce5 3. fg3 bc7",
          "gf4 de5 fd6 ce5 fg3 bc7".some
        ),
        StartingPosition(
          "9-VII",
          "B:W17,22,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,7,8,9,11,12,15",
          "1. gf4 de5 2. fd6 ce5 3. ab4",
          "gf4 de5 fd6 ce5 ab4".some
        ),
        StartingPosition(
          "9-VIII",
          "B:W21,22,23,24,25,26,27,29,30,31,32:B1,2,3,4,5,6,8,9,11,12,14",
          "1. gf4 de5 2. fd6 ec5 3. hg3",
          "gf4 de5 fd6 ec5 hg3".some
        ),
        StartingPosition(
          "9-IX",
          "W:W17,20,22,23,25,26,28,29,30,31,32:B1,2,3,4,5,6,7,9,10,11,12",
          "1. gf4 fe5 2. ab4 eg3 3. fh4 gf6",
          "gf4 fe5 ab4 eg3 fh4 gf6".some
        ),
        StartingPosition(
          "9-X",
          "B:W18,19,21,22,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,15",
          "1. gf4 fe5 2. ed4",
          "gf4 fe5 ed4".some
        ),
        StartingPosition(
          "9-XI",
          "B:W17,19,22,23,24,25,26,28,29,30,31,32:B1,2,3,4,5,6,8,9,10,11,12,15",
          "1. gf4 fe5 2. fg3 ef6 3. ab4",
          "gf4 fe5 fg3 ef6 ab4".some
        ),
        StartingPosition(
          "9-XII",
          "W:W20,21,22,23,24,25,26,27,28,29,30,31:B1,2,3,4,6,7,8,9,10,12,13,16",
          "1. gh4 ba5 2. fg3 ab6 3. gf2 fg5",
          "gh4 ba5 fg3 ab6 gf2 fg5".some
        ),
        StartingPosition(
          "9-XIII",
          "B:W18,20,21,22,24,25,26,28,29,30,31,32:B1,2,3,4,5,6,7,8,11,12,13,15",
          "1. gh4 ba5 2. fg3 de5 3. ed4",
          "gh4 ba5 fg3 de5 ed4".some
        ),
        StartingPosition(
          "9-XIV",
          "B:W18,20,21,22,24,25,26,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,12,13,15",
          "1. gh4 ba5 2. fg3 fe5 3. ed4",
          "gh4 ba5 fg3 fe5 ed4".some
        ),
        StartingPosition(
          "9-XV",
          "B:W20,21,22,23,24,25,26,27,28,29,30,31:B1,2,3,4,5,6,7,8,10,11,13,16",
          "1. gh4 ba5 2. fg3 hg5 3. gf2",
          "gh4 ba5 fg3 hg5 gf2".some
        ),
        StartingPosition(
          "9-XVI",
          "B:W18,20,21,22,24,25,26,27,29,30,31,32:B1,2,3,4,5,6,7,8,10,12,13,15",
          "1. gh4 ba5 2. hg3 fe5 3. ed4",
          "gh4 ba5 hg3 fe5 ed4".some
        )
      )
    ),
    Category(
      "10",
      List(
        StartingPosition(
          "10-I",
          "W:W18,20,21,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,12,14,15",
          "1. gh4 bc5 2. cd4 fe5",
          "gh4 bc5 cd4 fe5".some
        ),
        StartingPosition(
          "10-II",
          "W:W19,20,21,22,25,27,28,29,30,31,32:B1,2,3,4,5,7,8,9,10,11,12",
          "1. gh4 bc5 2. ed4 ce3 3. df4 cb6",
          "gh4 bc5 ed4 ce3 df4 cb6".some
        ),
        StartingPosition(
          "10-III",
          "W:W18,20,21,22,25,26,28,29,30,31,32:B1,2,3,4,5,6,7,8,11,12,15",
          "1. gh4 bc5 2. ed4 ce3 3. fd4 de5",
          "gh4 bc5 ed4 ce3 fd4 de5".some
        ),
        StartingPosition(
          "10-IV",
          "B:W18,20,21,22,24,25,26,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,12,14,15",
          "1. gh4 dc5 2. fg3 fe5 3. ed4",
          "gh4 dc5 fg3 fe5 ed4".some
        ),
        StartingPosition(
          "10-V",
          "W:W20,21,22,23,24,25,26,27,29,30,31,32:B1,2,3,4,5,6,7,8,9,12,14,15",
          "1. gh4 dc5 2. hg3 fe5",
          "gh4 dc5 hg3 fe5".some
        ),
        StartingPosition(
          "10-VI",
          "W:W17,20,22,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,7,8,9,10,11,12,15",
          "1. gh4 de5 2. ab4 cd6",
          "gh4 de5 ab4 cd6".some
        ),
        StartingPosition(
          "10-VII",
          "W:W20,21,22,23,24,25,26,27,29,30,31,32:B1,2,3,4,5,6,7,8,11,12,14,15",
          "1. gh4 de5 2. hg3 bc5",
          "gh4 de5 hg3 bc5".some
        ),
        StartingPosition(
          "10-VIII",
          "W:W20,21,22,23,24,25,26,27,29,30,31,32:B1,2,3,4,5,7,8,9,10,11,12,15",
          "1. gh4 de5 2. hg3 cd6",
          "gh4 de5 hg3 cd6".some
        ),
        StartingPosition(
          "10-IX",
          "W:W18,20,21,23,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,12,13",
          "1. gh4 fe5 2. cd4 ec3 3. bd4 ba5",
          "gh4 fe5 cd4 ec3 bd4 ba5".some
        ),
        StartingPosition(
          "10-X",
          "W:W18,20,21,22,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,12,14,15",
          "1. gh4 fe5 2. ed4 dc5",
          "gh4 fe5 ed4 dc5".some
        ),
        StartingPosition(
          "10-XI",
          "W:W19,20,21,22,25,26,27,29,30,31,32:B1,2,3,4,5,6,7,8,10,12,13",
          "1. gh4 fe5 2. ef4 eg3 3. hf4 ba5",
          "gh4 fe5 ef4 eg3 hf4 ba5".some
        ),
        StartingPosition(
          "10-XII",
          "W:W20,21,22,23,24,25,26,28,29,30,31,32:B1,2,3,4,5,6,7,9,10,11,12,15",
          "1. gh4 fe5 2. fg3 gf6",
          "gh4 fe5 fg3 gf6".some
        ),
        StartingPosition(
          "10-XIII",
          "W:W17,22,23,25,26,27,28,29,30,31,32:B1,2,4,5,6,7,8,9,10,12,15",
          "1. gh4 fg5 2. hf6 ge5 3. ab4 fg7",
          "gh4 fg5 hf6 ge5 ab4 fg7".some
        ),
        StartingPosition(
          "10-XIV",
          "W:W18,21,22,25,26,27,28,29,30,31,32:B1,2,4,5,6,7,8,9,10,12,15",
          "1. gh4 fg5 2. hf6 ge5 3. ed4 fg7",
          "gh4 fg5 hf6 ge5 ed4 fg7".some
        ),
        StartingPosition(
          "10-XV",
          "B:W18,21,22,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,8,9,10,12,16",
          "1. gh4 fg5 2. hf6 eg5 3. ed4",
          "gh4 fg5 hf6 eg5 ed4".some
        ),
        StartingPosition(
          "10-XVI",
          "W:W19,20,21,22,25,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,13",
          "1. gh4 hg5 2. ef4 ge3 3. df4 ba5",
          "gh4 hg5 ef4 ge3 df4 ba5".some
        )
      )
    ),
    Category(
      "11",
      List(
        StartingPosition(
          "11-I",
          "W:W19,20,21,22,25,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,14",
          "1. gh4 hg5 2. ef4 ge3 3. df4 bc5",
          "gh4 hg5 ef4 ge3 df4 bc5".some
        ),
        StartingPosition(
          "11-II",
          "W:W20,21,22,23,24,25,26,27,29,30,31,32:B1,2,3,4,5,6,7,9,10,11,12,16",
          "1. gh4 hg5 2. hg3 gh6",
          "gh4 hg5 hg3 gh6".some
        ),
        StartingPosition(
          "11-III",
          "W:W17,21,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,14",
          "1. cb4 dc5",
          "cb4 dc5".some
        ),
        StartingPosition(
          "11-IV",
          "W:W13,21,22,23,24,25,26,27,28,30,31,32:B1,2,3,4,6,7,8,9,10,11,12,20",
          "",
          "a1-a5 a7-h4".some
        ),
        StartingPosition(
          "11-V",
          "W:W13,21,22,23,24,25,26,27,28,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,16",
          "",
          "a1-a5 b6-g5".some
        ),
        StartingPosition(
          "11-VI",
          "W:W13,21,22,23,24,25,26,27,28,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,20",
          "",
          "a1-a5 b6-h4".some
        ),
        StartingPosition(
          "11-VII",
          "W:W13,21,22,23,24,25,26,27,28,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,20",
          "",
          "a1-a5 f6-h4".some
        ),
        StartingPosition(
          "11-VIII",
          "W:W13,21,22,23,24,25,26,27,28,30,31,32:B1,2,3,4,5,6,7,9,10,11,12,15",
          "",
          "a1-a5 g7-e5".some
        ),
        StartingPosition(
          "11-IX",
          "W:W13,21,22,23,24,25,26,27,28,30,31,32:B1,2,3,4,5,6,7,9,10,11,12,16",
          "",
          "a1-a5 g7-g5".some
        ),
        StartingPosition(
          "11-X",
          "W:W13,21,22,23,24,25,26,27,28,30,31,32:B1,2,3,4,5,6,7,9,10,11,12,20",
          "",
          "a1-a5 g7-h4".some
        ),
        StartingPosition(
          "11-XI",
          "W:W13,21,22,23,24,25,26,27,28,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,17",
          "",
          "a1-a5 h6-b4".some
        ),
        StartingPosition(
          "11-XII",
          "W:W17,21,22,23,24,25,26,27,28,30,31,32:B1,2,3,4,6,7,8,9,10,11,12,13",
          "",
          "a1-b4 a7-a5".some
        ),
        StartingPosition(
          "11-XIII",
          "W:W17,21,22,23,24,25,26,27,28,30,31,32:B1,2,3,4,6,7,8,9,10,11,12,16",
          "",
          "a1-b4 a7-g5".some
        ),
        StartingPosition(
          "11-XIV",
          "W:W17,21,22,23,24,25,26,27,28,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,16",
          "",
          "a1-b4 d6-g5".some
        ),
        StartingPosition(
          "11-XV",
          "W:W17,21,22,23,24,25,26,27,28,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,16",
          "",
          "a1-b4 h6-g5".some
        ),
        StartingPosition(
          "11-XVI",
          "W:W18,21,22,23,24,25,26,27,28,30,31,32:B1,2,3,4,6,7,8,9,10,11,12,14",
          "",
          "a1-d4 a7-c5".some
        )
      )
    ),
    Category(
      "12",
      List(
        StartingPosition(
          "12-I",
          "W:W18,21,22,23,24,25,26,27,28,30,31,32:B1,2,3,4,6,7,8,9,10,11,12,19",
          "",
          "a1-d4 a7-f4".some
        ),
        StartingPosition(
          "12-II",
          "W:W18,21,22,23,24,25,26,27,28,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,14",
          "",
          "a1-d4 b6-c5".some
        ),
        StartingPosition(
          "12-III",
          "W:W18,21,22,23,24,25,26,27,28,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,15",
          "",
          "a1-d4 f6-e5".some
        ),
        StartingPosition(
          "12-IV",
          "W:W18,21,22,23,24,25,26,27,28,30,31,32:B1,2,3,5,6,7,8,9,10,11,12,15",
          "",
          "a1-d4 h8-e5".some
        ),
        StartingPosition(
          "12-V",
          "W:W18,21,22,23,24,25,26,27,28,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,16",
          "",
          "a1-d4 h6-g5".some
        ),
        StartingPosition(
          "12-VI",
          "W:W14,21,22,23,24,25,26,27,28,30,31,32:B1,2,3,4,5,6,7,9,10,11,12,16",
          "",
          "a1-c5 g7-g5".some
        ),
        StartingPosition(
          "12-VII",
          "W:W15,21,22,23,24,25,26,27,28,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,16",
          "",
          "a1-e5 d6-g5".some
        ),
        StartingPosition(
          "12-VIII",
          "W:W19,21,22,23,24,25,26,27,28,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,14",
          "",
          "a1-f4 b6-c5".some
        ),
        StartingPosition(
          "12-IX",
          "W:W19,21,22,23,24,25,26,27,28,30,31,32:B1,2,3,4,5,7,8,9,10,11,12,13",
          "",
          "a1-f4 c7-a5".some
        ),
        StartingPosition(
          "12-X",
          "W:W19,21,22,23,24,25,26,27,28,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,14",
          "",
          "a1-f4 d6-c5".some
        ),
        StartingPosition(
          "12-XI",
          "W:W19,21,22,23,24,25,26,27,28,30,31,32:B1,2,3,4,5,6,8,9,10,11,12,15",
          "",
          "a1-f4 e7-e5".some
        ),
        StartingPosition(
          "12-XII",
          "W:W19,21,22,23,24,25,26,27,28,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,15",
          "",
          "a1-f4 f6-e5".some
        ),
        StartingPosition(
          "12-XIII",
          "W:W16,21,22,23,24,25,26,27,28,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,15",
          "",
          "a1-g5 d6-e5".some
        ),
        StartingPosition(
          "12-XIV",
          "W:W13,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,6,7,8,9,10,11,12,21",
          "",
          "a3-a5 a7-a3".some
        ),
        StartingPosition(
          "12-XV",
          "W:W13,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,21",
          "",
          "a3-a5 b6-a3".some
        ),
        StartingPosition(
          "12-XVI",
          "W:W13,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,17",
          "",
          "a3-a5 d6-b4".some
        )
      )
    ),
    Category(
      "13",
      List(
        StartingPosition(
          "13-I",
          "W:W13,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,21",
          "",
          "a3-a5 f6-a3".some
        ),
        StartingPosition(
          "13-II",
          "W:W13,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,21",
          "",
          "a3-a5 h6-a3".some
        ),
        StartingPosition(
          "13-III",
          "W:W13,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,17",
          "",
          "a3-a5 h6-b4".some
        ),
        StartingPosition(
          "13-IV",
          "W:W13,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,15",
          "",
          "a3-a5 h6-e5".some
        ),
        StartingPosition(
          "13-V",
          "W:W17,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,21",
          "",
          "a3-b4 b6-a3".some
        ),
        StartingPosition(
          "13-VI",
          "W:W17,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,21",
          "",
          "a3-b4 d6-a3".some
        ),
        StartingPosition(
          "13-VII",
          "W:W17,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,8,9,10,11,12,13",
          "",
          "a3-b4 e7-a5".some
        ),
        StartingPosition(
          "13-VIII",
          "W:W17,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,21",
          "",
          "a3-b4 h6-a3".some
        ),
        StartingPosition(
          "13-IX",
          "W:W17,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,5,6,7,8,9,10,11,12,21",
          "",
          "a3-b4 h8-a3".some
        ),
        StartingPosition(
          "13-X",
          "W:W14,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,21",
          "",
          "a3-c5 b6-a3".some
        ),
        StartingPosition(
          "13-XI",
          "W:W14,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,21",
          "",
          "a3-c5 d6-a3".some
        ),
        StartingPosition(
          "13-XII",
          "W:W14,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,21",
          "",
          "a3-c5 f6-a3".some
        ),
        StartingPosition(
          "13-XIII",
          "W:W14,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,9,10,11,12,21",
          "",
          "a3-c5 g7-a3".some
        ),
        StartingPosition(
          "13-XIV",
          "W:W14,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,5,6,7,8,9,10,11,12,21",
          "",
          "a3-c5 h8-a3".some
        ),
        StartingPosition(
          "13-XV",
          "W:W18,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,21",
          "",
          "a3-d4 b6-a3".some
        ),
        StartingPosition(
          "13-XVI",
          "W:W18,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,8,9,10,11,12,14",
          "",
          "a3-d4 e7-c5".some
        )
      )
    ),
    Category(
      "14",
      List(
        StartingPosition(
          "14-I",
          "W:W18,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,21",
          "",
          "a3-d4 f6-a3".some
        ),
        StartingPosition(
          "14-II",
          "W:W18,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,9,10,11,12,21",
          "",
          "a3-d4 g7-a3".some
        ),
        StartingPosition(
          "14-III",
          "W:W18,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,9,10,11,12,13",
          "",
          "a3-d4 g7-a5".some
        ),
        StartingPosition(
          "14-IV",
          "W:W18,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,5,6,7,8,9,10,11,12,21",
          "",
          "a3-d4 h8-a3".some
        ),
        StartingPosition(
          "14-V",
          "W:W18,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,5,6,7,8,9,10,11,12,20",
          "",
          "a3-d4 h8-h4".some
        ),
        StartingPosition(
          "14-VI",
          "W:W15,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,6,7,8,9,10,11,12,14",
          "",
          "a3-e5 a7-c5".some
        ),
        StartingPosition(
          "14-VII",
          "W:W15,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,21",
          "",
          "a3-e5 b6-a3".some
        ),
        StartingPosition(
          "14-VIII",
          "W:W15,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,9,10,11,12,21",
          "",
          "a3-e5 g7-a3".some
        ),
        StartingPosition(
          "14-IX",
          "W:W15,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,5,6,7,8,9,10,11,12,21",
          "",
          "a3-e5 h8-a3".some
        ),
        StartingPosition(
          "14-X",
          "W:W19,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,6,7,8,9,10,11,12,14",
          "",
          "a3-f4 a7-c5".some
        ),
        StartingPosition(
          "14-XI",
          "W:W19,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,7,8,9,10,11,12,14",
          "",
          "a3-f4 c7-c5".some
        ),
        StartingPosition(
          "14-XII",
          "W:W16,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,6,7,8,9,10,11,12,21",
          "",
          "a3-g5 a7-a3".some
        ),
        StartingPosition(
          "14-XIII",
          "W:W16,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,21",
          "",
          "a3-g5 b6-a3".some
        ),
        StartingPosition(
          "14-XIV",
          "W:W16,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,7,8,9,10,11,12,21",
          "",
          "a3-g5 c7-a3".some
        ),
        StartingPosition(
          "14-XV",
          "W:W16,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,21",
          "",
          "a3-g5 d6-a3".some
        ),
        StartingPosition(
          "14-XVI",
          "W:W16,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,14",
          "",
          "a3-g5 d6-c5".some
        )
      )
    ),
    Category(
      "15",
      List(
        StartingPosition(
          "15-I",
          "W:W16,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,21",
          "",
          "a3-g5 f6-a3".some
        ),
        StartingPosition(
          "15-II",
          "W:W16,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,9,10,11,12,21",
          "",
          "a3-g5 g7-a3".some
        ),
        StartingPosition(
          "15-III",
          "W:W16,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,5,6,7,8,9,10,11,12,21",
          "",
          "a3-g5 h8-a3".some
        ),
        StartingPosition(
          "15-IV",
          "W:W20,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,6,7,8,9,10,11,12,21",
          "",
          "a3-h4 a7-a3".some
        ),
        StartingPosition(
          "15-V",
          "W:W20,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,7,8,9,10,11,12,21",
          "",
          "a3-h4 c7-a3".some
        ),
        StartingPosition(
          "15-VI",
          "W:W20,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,21",
          "",
          "a3-h4 d6-a3".some
        ),
        StartingPosition(
          "15-VII",
          "W:W20,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,8,9,10,11,12,21",
          "",
          "a3-h4 e7-a3".some
        ),
        StartingPosition(
          "15-VIII",
          "W:W20,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,8,9,10,11,12,13",
          "",
          "a3-h4 e7-a5".some
        ),
        StartingPosition(
          "15-IX",
          "W:W20,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,21",
          "",
          "a3-h4 f6-a3".some
        ),
        StartingPosition(
          "15-X",
          "W:W20,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,14",
          "",
          "a3-h4 f6-c5".some
        ),
        StartingPosition(
          "15-XI",
          "W:W20,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,15",
          "",
          "a3-h4 f6-e5".some
        ),
        StartingPosition(
          "15-XII",
          "W:W20,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,9,10,11,12,21",
          "",
          "a3-h4 g7-a3".some
        ),
        StartingPosition(
          "15-XIII",
          "W:W20,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,9,10,11,12,13",
          "",
          "a3-h4 g7-a5".some
        ),
        StartingPosition(
          "15-XIV",
          "W:W20,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,9,10,11,12,14",
          "",
          "a3-h4 g7-c5".some
        ),
        StartingPosition(
          "15-XV",
          "W:W20,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,9,10,11,12,15",
          "",
          "a3-h4 g7-e5".some
        ),
        StartingPosition(
          "15-XVI",
          "W:W20,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,21",
          "",
          "a3-h4 h6-a3".some
        )
      )
    ),
    Category(
      "16",
      List(
        StartingPosition(
          "16-I",
          "W:W20,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,9,10,11,12,15",
          "",
          "a3-h4 g7-e5".some
        ),
        StartingPosition(
          "16-II",
          "W:W20,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,21",
          "",
          "a3-h4 h6-a3".some
        ),
        StartingPosition(
          "16-III",
          "W:W21,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,6,7,8,9,10,11,12,13",
          "",
          "----- a7-a5".some
        ),
        StartingPosition(
          "16-IV",
          "W:W21,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,6,7,8,9,10,11,12,20",
          "",
          "----- a7-h4".some
        ),
        StartingPosition(
          "16-V",
          "W:W21,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,6,7,8,9,10,11,12,14",
          "",
          "----- a7-c5".some
        ),
        StartingPosition(
          "16-VI",
          "W:W13,21,22,23,24,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,12",
          "",
          "b2-a5 -----".some
        ),
        StartingPosition(
          "16-VII",
          "W:W13,21,22,23,24,26,27,28,29,30,31,32:B1,2,3,4,6,7,8,9,10,11,12,15",
          "",
          "b2-a5 a7-e5".some
        ),
        StartingPosition(
          "16-VIII",
          "W:W13,21,22,23,24,26,27,28,29,30,31,32:B1,2,3,4,6,7,8,9,10,11,12,20",
          "",
          "b2-a5 a7-h4".some
        ),
        StartingPosition(
          "16-IX",
          "W:W13,21,22,23,24,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,17",
          "",
          "b2-a5 b6-b4".some
        ),
        StartingPosition(
          "16-X",
          "W:W13,21,22,23,24,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,15",
          "",
          "b2-a5 f6-e5".some
        ),
        StartingPosition(
          "16-XI",
          "W:W17,21,22,23,24,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,16",
          "",
          "b2-b4 d6-g5".some
        ),
        StartingPosition(
          "16-XII",
          "W:W17,21,22,23,24,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,15",
          "",
          "b2-b4 h6-e5".some
        ),
        StartingPosition(
          "16-XIII",
          "W:W14,21,22,23,24,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,9,10,11,12,16",
          "",
          "b2-c5 g7-g5".some
        ),
        StartingPosition(
          "16-XIV",
          "W:W18,21,22,23,24,26,27,28,29,30,31,32:B1,2,3,4,6,7,8,9,10,11,12,16",
          "",
          "b2-d4 a7-g5".some
        ),
        StartingPosition(
          "16-XV",
          "W:W18,21,22,23,24,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,14",
          "",
          "b2-d4 b6-c5".some
        ),
        StartingPosition(
          "16-XVI",
          "W:W18,21,22,23,24,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,14",
          "",
          "b2-d4 f6-c5".some
        )
      )
    ),
    Category(
      "17",
      List(
        StartingPosition(
          "17-I",
          "W:W18,21,22,23,24,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,15",
          "",
          "b2-d4 f6-e5".some
        ),
        StartingPosition(
          "17-II",
          "W:W15,21,22,23,24,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,9,10,11,12,13",
          "",
          "b2-e5 g7-a5".some
        ),
        StartingPosition(
          "17-III",
          "W:W15,21,22,23,24,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,9,10,11,12,20",
          "",
          "b2-e5 g7-h4".some
        ),
        StartingPosition(
          "17-IV",
          "W:W19,21,22,23,24,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,14",
          "",
          "b2-f4 d6-c5".some
        ),
        StartingPosition(
          "17-V",
          "W:W16,21,22,23,24,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,15",
          "",
          "b2-g5 d6-e5".some
        ),
        StartingPosition(
          "17-VI",
          "W:W16,21,22,23,24,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,9,10,11,12,13",
          "",
          "b2-g5 g7-a5".some
        ),
        StartingPosition(
          "17-VII",
          "W:W16,21,22,23,24,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,15",
          "",
          "b2-g5 h6-e5".some
        ),
        StartingPosition(
          "17-VIII",
          "W:W16,21,22,23,24,26,27,28,29,30,31,32:B1,2,3,5,6,7,8,9,10,11,12,13",
          "",
          "b2-g5 h8-a5".some
        ),
        StartingPosition(
          "17-IX",
          "W:W20,21,22,23,24,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,13",
          "",
          "b2-h4 b6-a5".some
        ),
        StartingPosition(
          "17-X",
          "W:W20,21,22,23,24,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,9,10,11,12,15",
          "",
          "b2-h4 g7-e5".some
        ),
        StartingPosition(
          "17-XI",
          "W:W12,21,22,23,24,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,13",
          "",
          "b2-h6 h6-a5".some
        ),
        StartingPosition(
          "17-XII",
          "W:W12,21,22,23,24,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,15",
          "",
          "b2-h6 h6-e5".some
        ),
        StartingPosition(
          "17-XIII",
          "W:W12,21,22,23,24,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,19",
          "",
          "b2-h6 h6-f4".some
        ),
        StartingPosition(
          "17-XIV",
          "W:W21,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,20",
          "",
          "----- b6-h4".some
        ),
        StartingPosition(
          "17-XV",
          "W:W13,21,22,23,24,25,26,27,28,29,31,32:B1,2,3,4,6,7,8,9,10,11,12,14",
          "",
          "c1-a5 a7-c5".some
        ),
        StartingPosition(
          "17-XVI",
          "W:W13,21,22,23,24,25,26,27,28,29,31,32:B1,2,4,5,6,7,8,9,10,11,12,20",
          "",
          "c1-a5 f8-h4".some
        )
      )
    ),
    Category(
      "18",
      List(
        StartingPosition(
          "18-I",
          "W:W20,21,22,23,24,25,26,27,28,29,31,32:B1,2,3,4,5,6,7,8,9,10,12,16",
          "",
          "c1-h4 f6-g5".some
        ),
        StartingPosition(
          "18-II",
          "W:W13,21,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,6,7,8,9,10,11,12,16",
          "",
          "c3-a5 a7-g5".some
        ),
        StartingPosition(
          "18-III",
          "W:W13,21,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,16",
          "",
          "c3-a5 d6-g5".some
        ),
        StartingPosition(
          "18-IV",
          "W:W17,21,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,6,7,8,9,10,11,12,13",
          "",
          "c3-b4 a7-a5".some
        ),
        StartingPosition(
          "18-V",
          "W:W17,21,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,6,7,8,9,10,11,12,15",
          "",
          "c3-b4 a7-e5".some
        ),
        StartingPosition(
          "18-VI",
          "W:W17,21,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,6,7,8,9,10,11,12,20",
          "",
          "c3-b4 a7-h4".some
        ),
        StartingPosition(
          "18-VII",
          "W:W14,21,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,16",
          "",
          "c3-c5 f6-g5".some
        ),
        StartingPosition(
          "18-VIII",
          "W:W14,21,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,9,10,11,12,15",
          "",
          "c3-c5 g7-e5".some
        ),
        StartingPosition(
          "18-IX",
          "W:W14,21,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,9,10,11,12,20",
          "",
          "c3-c5 g7-h4".some
        ),
        StartingPosition(
          "18-X",
          "W:W18,21,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,6,7,8,9,10,11,12,15",
          "",
          "c3-d4 a7-e5".some
        ),
        StartingPosition(
          "18-XI",
          "W:W13,21,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,15",
          "",
          "c3-a5 h6-e5".some
        ),
        StartingPosition(
          "18-XII",
          "W:W18,21,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,8,9,10,11,12,15",
          "",
          "c3-d4 e7-e5".some
        ),
        StartingPosition(
          "18-XIII",
          "W:W18,21,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,14",
          "",
          "c3-d4 f6-c5".some
        ),
        StartingPosition(
          "18-XIV",
          "W:W18,21,23,24,25,26,27,28,29,30,31,32:B1,2,3,5,6,7,8,9,10,11,12,16",
          "",
          "c3-d4 h8-g5".some
        ),
        StartingPosition(
          "18-XV",
          "W:W18,21,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,15",
          "",
          "c3-d4 h6-e5".some
        ),
        StartingPosition(
          "18-XVI",
          "W:W15,21,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,9,10,11,12,13",
          "",
          "c3-e5 g7-a5".some
        )
      )
    ),
    Category(
      "19",
      List(
        StartingPosition(
          "19-I",
          "W:W15,21,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,9,10,11,12,16",
          "",
          "c3-e5 g7-g5".some
        ),
        StartingPosition(
          "19-II",
          "W:W19,21,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,6,7,8,9,10,11,12,15",
          "",
          "c3-f4 a7-e5".some
        ),
        StartingPosition(
          "19-III",
          "W:W19,21,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,17",
          "",
          "c3-f4 f6-b4".some
        ),
        StartingPosition(
          "19-IV",
          "W:W19,21,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,15",
          "",
          "c3-f4 f6-e5".some
        ),
        StartingPosition(
          "19-V",
          "W:W19,21,23,24,25,26,27,28,29,30,31,32:B1,2,3,5,6,7,8,9,10,11,12,20",
          "",
          "c3-f4 h8-h4".some
        ),
        StartingPosition(
          "19-VI",
          "W:W19,21,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,14",
          "",
          "c3-f4 h6-c5".some
        ),
        StartingPosition(
          "19-VII",
          "W:W16,21,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,6,7,8,9,10,11,12,13",
          "",
          "c3-g5 a7-a5".some
        ),
        StartingPosition(
          "19-VIII",
          "W:W16,21,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,6,7,8,9,10,11,12,15",
          "",
          "c3-g5 a7-e5".some
        ),
        StartingPosition(
          "19-IX",
          "W:W16,21,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,14",
          "",
          "c3-g5 b6-c5".some
        ),
        StartingPosition(
          "19-X",
          "W:W16,21,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,7,8,9,10,11,12,14",
          "",
          "c3-g5 c7-c5".some
        ),
        StartingPosition(
          "19-XI",
          "W:W16,21,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,7,8,9,10,11,12,15",
          "",
          "c3-g5 c7-e5".some
        ),
        StartingPosition(
          "19-XII",
          "W:W16,21,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,9,10,11,12,14",
          "",
          "c3-g5 g7-c5".some
        ),
        StartingPosition(
          "19-XIII",
          "W:W16,21,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,9,10,11,12,15",
          "",
          "c3-g5 g7-e5".some
        ),
        StartingPosition(
          "19-XIV",
          "W:W16,21,23,24,25,26,27,28,29,30,31,32:B1,2,3,5,6,7,8,9,10,11,12,13",
          "",
          "c3-g5 h8-a5".some
        ),
        StartingPosition(
          "19-XV",
          "W:W20,21,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,12",
          "",
          "c3-h4 -----".some
        ),
        StartingPosition(
          "19-XVI",
          "W:W20,21,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,6,7,8,9,10,11,12,16",
          "",
          "c3-h4 a7-g5".some
        )
      )
    ),
    Category(
      "20",
      List(
        StartingPosition(
          "20-I",
          "W:W20,21,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,15",
          "",
          "c3-h4 f6-e5".some
        ),
        StartingPosition(
          "20-II",
          "W:W20,21,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,9,10,11,12,14",
          "",
          "c3-h4 g7-c5".some
        ),
        StartingPosition(
          "20-III",
          "W:W20,21,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,14",
          "",
          "c3-h4 h6-c5".some
        ),
        StartingPosition(
          "20-IV",
          "W:W12,21,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,13",
          "",
          "c3-h6 h6-a5".some
        ),
        StartingPosition(
          "20-V",
          "W:W21,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,7,8,9,10,11,12,13",
          "",
          "----- c7-a5".some
        ),
        StartingPosition(
          "20-VI",
          "W:W21,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,7,8,9,10,11,12,16",
          "",
          "----- c7-g5".some
        ),
        StartingPosition(
          "20-VII",
          "W:W13,21,22,23,24,25,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,12",
          "",
          "d2-a5 -----".some
        ),
        StartingPosition(
          "20-VIII",
          "W:W13,21,22,23,24,25,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,20",
          "",
          "d2-a5 b6-h4".some
        ),
        StartingPosition(
          "20-IX",
          "W:W13,21,22,23,24,25,27,28,29,30,31,32:B1,2,3,4,5,6,8,9,10,11,12,16",
          "",
          "d2-a5 e7-g5".some
        ),
        StartingPosition(
          "20-X",
          "W:W13,21,22,23,24,25,27,28,29,30,31,32:B1,2,3,4,5,6,7,9,10,11,12,15",
          "",
          "d2-a5 g7-e5".some
        ),
        StartingPosition(
          "20-XI",
          "W:W17,21,22,23,24,25,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,12",
          "",
          "d2-b4 -----".some
        ),
        StartingPosition(
          "20-XII",
          "W:W18,21,22,23,24,25,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,16",
          "",
          "d2-d4 b6-g5".some
        ),
        StartingPosition(
          "20-XIII",
          "W:W17,21,22,23,24,25,27,28,29,30,31,32:B1,2,3,4,6,7,8,9,10,11,12,16",
          "",
          "d2-b4 a7-g5".some
        ),
        StartingPosition(
          "20-XIV",
          "W:W17,21,22,23,24,25,27,28,29,30,31,32:B1,2,3,4,6,7,8,9,10,11,12,20",
          "",
          "d2-b4 a7-h4".some
        ),
        StartingPosition(
          "20-XV",
          "W:W17,21,22,23,24,25,27,28,29,30,31,32:B1,2,3,5,6,7,8,9,10,11,12,16",
          "",
          "d2-b4 h8-g5".some
        ),
        StartingPosition(
          "20-XVI",
          "W:W17,21,22,23,24,25,27,28,29,30,31,32:B1,2,3,5,6,7,8,9,10,11,12,20",
          "",
          "d2-b4 h8-h4".some
        )
      )
    ),
    Category(
      "21",
      List(
        StartingPosition(
          "21-I",
          "W:W14,21,22,23,24,25,27,28,29,30,31,32:B1,2,3,4,6,7,8,9,10,11,12,26",
          "",
          "d2-c5 a7-d2".some
        ),
        StartingPosition(
          "21-II",
          "W:W14,21,22,23,24,25,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,16",
          "",
          "d2-c5 b6-g5".some
        ),
        StartingPosition(
          "21-III",
          "W:W14,21,22,23,24,25,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,20",
          "",
          "d2-c5 d6-h4".some
        ),
        StartingPosition(
          "21-IV",
          "W:W18,21,22,23,24,25,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,12",
          "",
          "d2-d4 -----".some
        ),
        StartingPosition(
          "21-V",
          "W:W18,21,22,23,24,25,27,28,29,30,31,32:B1,2,3,4,6,7,8,9,10,11,12,16",
          "",
          "d2-d4 a7-g5".some
        ),
        StartingPosition(
          "21-VI",
          "W:W18,21,22,23,24,25,27,28,29,30,31,32:B1,2,3,4,6,7,8,9,10,11,12,20",
          "",
          "d2-d4 a7-h4".some
        ),
        StartingPosition(
          "21-VII",
          "W:W18,21,22,23,24,25,27,28,29,30,31,32:B1,2,3,4,5,7,8,9,10,11,12,20",
          "",
          "d2-d4 c7-h4".some
        ),
        StartingPosition(
          "21-VIII",
          "W:W18,21,22,23,24,25,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,20",
          "",
          "d2-d4 d6-h4".some
        ),
        StartingPosition(
          "21-IX",
          "W:W18,21,22,23,24,25,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,16",
          "",
          "d2-d4 f6-g5".some
        ),
        StartingPosition(
          "21-X",
          "W:W18,21,22,23,24,25,27,28,29,30,31,32:B1,2,3,5,6,7,8,9,10,11,12,20",
          "",
          "d2-d4 h8-h4".some
        ),
        StartingPosition(
          "21-XI",
          "W:W18,21,22,23,24,25,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,16",
          "",
          "d2-d4 h6-g5".some
        ),
        StartingPosition(
          "21-XII",
          "W:W15,21,22,23,24,25,27,28,29,30,31,32:B1,2,3,4,5,6,7,9,10,11,12,20",
          "",
          "d2-e5 g7-h4".some
        ),
        StartingPosition(
          "21-XIII",
          "W:W19,21,22,23,24,25,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,12",
          "",
          "d2-f4 -----".some
        ),
        StartingPosition(
          "21-XIV",
          "W:W19,21,22,23,24,25,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,14",
          "",
          "d2-f4 b6-c5".some
        ),
        StartingPosition(
          "21-XV",
          "W:W19,21,22,23,24,25,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,13",
          "",
          "d2-f4 d6-a5".some
        ),
        StartingPosition(
          "21-XVI",
          "W:W19,21,22,23,24,25,27,28,29,30,31,32:B1,2,3,4,5,6,8,9,10,11,12,13",
          "",
          "d2-f4 e7-a5".some
        )
      )
    ),
    Category(
      "22",
      List(
        StartingPosition(
          "22-I",
          "W:W16,21,22,23,24,25,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,20",
          "",
          "d2-g5 h6-h4".some
        ),
        StartingPosition(
          "22-II",
          "W:W20,21,22,23,24,25,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,14",
          "",
          "d2-h4 b6-c5".some
        ),
        StartingPosition(
          "22-III",
          "W:W20,21,22,23,24,25,27,28,29,30,31,32:B1,2,3,4,5,6,8,9,10,11,12,14",
          "",
          "d2-h4 e7-c5".some
        ),
        StartingPosition(
          "22-IV",
          "W:W20,21,22,23,24,25,27,28,29,30,31,32:B1,2,3,4,5,6,7,9,10,11,12,15",
          "",
          "d2-h4 g7-e5".some
        ),
        StartingPosition(
          "22-V",
          "W:W20,21,22,23,24,25,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,15",
          "",
          "d2-h4 h6-e5".some
        ),
        StartingPosition(
          "22-VI",
          "W:W21,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,13",
          "",
          "----- d6-a5".some
        ),
        StartingPosition(
          "22-VII",
          "W:W21,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,16",
          "",
          "----- d6-g5".some
        ),
        StartingPosition(
          "22-VIII",
          "W:W21,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,20",
          "",
          "----- d6-h4".some
        ),
        StartingPosition(
          "22-IX",
          "W:W13,21,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,12",
          "",
          "e3-a5 -----".some
        ),
        StartingPosition(
          "22-X",
          "W:W13,21,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,6,7,8,9,10,11,12,15",
          "",
          "e3-a5 a7-e5".some
        ),
        StartingPosition(
          "22-XI",
          "W:W13,21,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,6,7,8,9,10,11,12,16",
          "",
          "e3-a5 a7-g5".some
        ),
        StartingPosition(
          "22-XII",
          "W:W13,21,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,14",
          "",
          "e3-a5 b6-c5".some
        ),
        StartingPosition(
          "22-XIII",
          "W:W18,21,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,20",
          "",
          "e3-d4 d6-h4".some
        ),
        StartingPosition(
          "22-XIV",
          "W:W13,21,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,15",
          "",
          "e3-a5 d6-e5".some
        ),
        StartingPosition(
          "22-XV",
          "B:W13,21,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,8,9,10,11,12,16",
          "",
          "e3-a5 e7-g5".some
        ),
        StartingPosition(
          "22-XVI",
          "W:W13,21,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,17",
          "",
          "e3-a5 f6-b4".some
        )
      )
    ),
    Category(
      "23",
      List(
        StartingPosition(
          "23-I",
          "W:W13,21,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,9,10,11,12,17",
          "",
          "e3-a5 g7-b4".some
        ),
        StartingPosition(
          "23-II",
          "W:W13,21,22,24,25,26,27,28,29,30,31,32:B1,2,3,5,6,7,8,9,10,11,12,15",
          "",
          "e3-a5 h8-e5".some
        ),
        StartingPosition(
          "23-III",
          "W:W13,21,22,24,25,26,27,28,29,30,31,32:B1,2,3,5,6,7,8,9,10,11,12,20",
          "",
          "e3-a5 h8-h4".some
        ),
        StartingPosition(
          "23-IV",
          "W:W17,21,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,12",
          "",
          "e3-b4 -----".some
        ),
        StartingPosition(
          "23-V",
          "W:W17,21,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,6,7,8,9,10,11,12,14",
          "",
          "e3-b4 a7-c5".some
        ),
        StartingPosition(
          "23-VI",
          "W:W17,21,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,6,7,8,9,10,11,12,15",
          "",
          "e3-b4 a7-e5".some
        ),
        StartingPosition(
          "23-VII",
          "W:W17,21,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,6,7,8,9,10,11,12,20",
          "",
          "e3-b4 a7-h4".some
        ),
        StartingPosition(
          "23-VIII",
          "W:W17,21,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,16",
          "",
          "e3-b4 b6-g5".some
        ),
        StartingPosition(
          "23-IX",
          "W:W17,21,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,15",
          "",
          "e3-b4 f6-e5".some
        ),
        StartingPosition(
          "23-X",
          "W:W17,21,22,24,25,26,27,28,29,30,31,32:B1,2,3,5,6,7,8,9,10,11,12,15",
          "",
          "e3-b4 h8-e5".some
        ),
        StartingPosition(
          "23-XI",
          "W:W17,21,22,24,25,26,27,28,29,30,31,32:B1,2,3,5,6,7,8,9,10,11,12,20",
          "",
          "e3-b4 h8-h4".some
        ),
        StartingPosition(
          "23-XII",
          "W:W9,21,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,17",
          "",
          "e3-b6 b6-b4".some
        ),
        StartingPosition(
          "23-XIII",
          "W:W14,21,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,16",
          "",
          "e3-c5 d6-g5".some
        ),
        StartingPosition(
          "23-XIV",
          "W:W18,21,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,12",
          "",
          "e3-d4 -----".some
        ),
        StartingPosition(
          "23-XV",
          "W:W18,21,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,6,7,8,9,10,11,12,15",
          "",
          "e3-d4 a7-e5".some
        ),
        StartingPosition(
          "23-XVI",
          "W:W18,21,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,6,7,8,9,10,11,12,16",
          "",
          "e3-d4 a7-g5".some
        )
      )
    ),
    Category(
      "24",
      List(
        StartingPosition(
          "24-I",
          "W:W18,21,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,13",
          "",
          "e3-d4 d6-a5".some
        ),
        StartingPosition(
          "24-II",
          "W:W19,21,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,12",
          "",
          "e3-f4 -----".some
        ),
        StartingPosition(
          "24-III",
          "W:W19,21,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,13",
          "",
          "e3-f4 d6-a5".some
        ),
        StartingPosition(
          "24-IV",
          "W:W19,21,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,9,10,11,12,13",
          "",
          "e3-f4 g7-a5".some
        ),
        StartingPosition(
          "24-V",
          "W:W11,21,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,17",
          "",
          "e3-f6 f6-b4".some
        ),
        StartingPosition(
          "24-VI",
          "W:W16,21,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,15",
          "",
          "e3-g5 h6-e5".some
        ),
        StartingPosition(
          "24-VII",
          "W:W20,21,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,9,10,11,12,16",
          "",
          "e3-h4 g7-g5".some
        ),
        StartingPosition(
          "24-VIII",
          "W:W20,21,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,13",
          "",
          "e3-h4 h6-a5".some
        ),
        StartingPosition(
          "24-IX",
          "W:W20,21,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,16",
          "",
          "e3-h4 h6-g5".some
        ),
        StartingPosition(
          "24-X",
          "W:W12,21,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,13",
          "",
          "e3-h6 h6-a5".some
        ),
        StartingPosition(
          "24-XI",
          "W:W12,21,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,15",
          "",
          "e3-h6 h6-e5".some
        ),
        StartingPosition(
          "24-XII",
          "W:W21,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,8,9,10,11,12,20",
          "",
          "----- e7-h4".some
        ),
        StartingPosition(
          "24-XIII",
          "W:W13,21,22,23,24,25,26,28,29,30,31,32:B1,3,4,5,6,7,8,9,10,11,12,19",
          "",
          "f2-a5 d8-f4".some
        ),
        StartingPosition(
          "24-XIV",
          "W:W13,21,22,23,24,25,26,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,15",
          "",
          "f2-a5 d6-e5".some
        ),
        StartingPosition(
          "24-XV",
          "W:W13,21,22,23,24,25,26,28,29,30,31,32:B1,2,3,4,5,6,8,9,10,11,12,15",
          "",
          "f2-a5 e7-e5".some
        ),
        StartingPosition(
          "24-XVI",
          "W:W13,21,22,23,24,25,26,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,15",
          "",
          "f2-a5 f6-e5".some
        )
      )
    ),
    Category(
      "25",
      List(
        StartingPosition(
          "25-I",
          "W:W13,21,22,23,24,25,26,28,29,30,31,32:B1,2,3,4,5,6,7,9,10,11,12,15",
          "",
          "f2-a5 g7-e5".some
        ),
        StartingPosition(
          "25-II",
          "W:W17,21,22,23,24,25,26,28,29,30,31,32:B1,3,4,5,6,7,8,9,10,11,12,20",
          "",
          "f2-b4 d8-h4".some
        ),
        StartingPosition(
          "25-III",
          "W:W17,21,22,23,24,25,26,28,29,30,31,32:B1,2,3,4,5,6,8,9,10,11,12,15",
          "",
          "f2-b4 e7-e5".some
        ),
        StartingPosition(
          "25-IV",
          "W:W17,21,22,23,24,25,26,28,29,30,31,32:B1,2,3,4,5,6,7,9,10,11,12,15",
          "",
          "f2-b4 g7-e5".some
        ),
        StartingPosition(
          "25-V",
          "W:W17,21,22,23,24,25,26,28,29,30,31,32:B1,2,3,5,6,7,8,9,10,11,12,14",
          "",
          "f2-b4 h8-c5".some
        ),
        StartingPosition(
          "25-VI",
          "W:W17,21,22,23,24,25,26,28,29,30,31,32:B1,2,3,5,6,7,8,9,10,11,12,16",
          "",
          "f2-b4 h8-g5".some
        ),
        StartingPosition(
          "25-VII",
          "W:W14,21,22,23,24,25,26,28,29,30,31,32:B1,2,3,4,6,7,8,9,10,11,12,27",
          "",
          "f2-c5 a7-f2".some
        ),
        StartingPosition(
          "25-VIII",
          "W:W14,21,22,23,24,25,26,28,29,30,31,32:B1,2,3,5,6,7,8,9,10,11,12,15",
          "",
          "f2-c5 h8-e5".some
        ),
        StartingPosition(
          "25-IX",
          "W:W18,21,22,23,24,25,26,28,29,30,31,32:B1,2,3,4,5,7,8,9,10,11,12,16",
          "",
          "f2-d4 c7-g5".some
        ),
        StartingPosition(
          "25-X",
          "W:W18,21,22,23,24,25,26,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,16",
          "",
          "f2-d4 h6-g5".some
        ),
        StartingPosition(
          "25-XI",
          "W:W19,21,22,23,24,25,26,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,15",
          "",
          "f2-f4 d6-e5".some
        ),
        StartingPosition(
          "25-XII",
          "W:W19,21,22,23,24,25,26,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,13",
          "",
          "f2-f4 h6-a5".some
        ),
        StartingPosition(
          "25-XIII",
          "W:W19,21,22,23,24,25,26,28,29,30,31,32:B1,2,3,5,6,7,8,9,10,11,12,15",
          "",
          "f2-f4 h8-e5".some
        ),
        StartingPosition(
          "25-XIV",
          "W:W16,21,22,23,24,25,26,28,29,30,31,32:B1,2,3,4,5,6,7,9,10,11,12,13",
          "",
          "f2-g5 g7-a5".some
        ),
        StartingPosition(
          "25-XV",
          "W:W16,21,22,23,24,25,26,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,13",
          "",
          "f2-g5 h6-a5".some
        ),
        StartingPosition(
          "25-XVI",
          "W:W16,21,22,23,24,25,26,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,14",
          "",
          "f2-g5 h6-c5".some
        )
      )
    ),
    Category(
      "26",
      List(
        StartingPosition(
          "26-I",
          "W:W20,21,22,23,24,25,26,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,12",
          "",
          "f2-h4 -----".some
        ),
        StartingPosition(
          "26-II",
          "W:W20,21,22,23,24,25,26,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,14",
          "",
          "f2-h4 f6-c5".some
        ),
        StartingPosition(
          "26-III",
          "W:W21,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,13",
          "",
          "----- f6-a5".some
        ),
        StartingPosition(
          "26-IV",
          "W:W13,21,22,23,24,25,26,27,28,29,30,31:B1,2,3,4,5,6,7,8,9,10,12,17",
          "",
          "g1-a5 f6-b4".some
        ),
        StartingPosition(
          "26-V",
          "W:W13,21,22,23,24,25,26,27,28,29,30,31:B1,2,3,4,5,6,7,9,10,11,12,14",
          "",
          "g1-a5 g7-c5".some
        ),
        StartingPosition(
          "26-VI",
          "W:W17,21,22,23,24,25,26,27,28,29,30,31:B1,2,3,4,5,6,8,9,10,11,12,14",
          "",
          "g1-b4 e7-c5".some
        ),
        StartingPosition(
          "26-VII",
          "W:W17,21,22,23,24,25,26,27,28,29,30,31:B1,2,3,5,6,7,8,9,10,11,12,14",
          "",
          "g1-b4 h8-c5".some
        ),
        StartingPosition(
          "26-VIII",
          "W:W18,21,22,23,24,25,26,27,28,29,30,31:B1,2,3,4,5,6,7,8,9,11,12,16",
          "",
          "g1-d4 d6-g5".some
        ),
        StartingPosition(
          "26-IX",
          "W:W18,21,22,23,24,25,26,27,28,29,30,31:B1,2,3,4,5,6,7,8,9,10,11,13",
          "",
          "g1-d4 h6-a5".some
        ),
        StartingPosition(
          "26-X",
          "W:W16,21,22,23,24,25,26,27,28,29,30,31:B1,2,3,4,5,6,7,9,10,11,12,13",
          "",
          "g1-g5 g7-a5".some
        ),
        StartingPosition(
          "26-XI",
          "W:W16,21,22,23,24,25,26,27,28,29,30,31:B1,2,3,4,5,6,7,9,10,11,12,15",
          "",
          "g1-g5 g7-e5".some
        ),
        StartingPosition(
          "26-XII",
          "W:W16,21,22,23,24,25,26,27,28,29,30,31:B1,2,3,5,6,7,8,9,10,11,12,13",
          "",
          "g1-g5 h8-a5".some
        ),
        StartingPosition(
          "26-XIII",
          "W:W20,21,22,23,24,25,26,27,28,29,30,31:B1,2,3,4,5,6,7,8,9,11,12,14",
          "",
          "g1-h4 d6-c5".some
        ),
        StartingPosition(
          "26-XIV",
          "W:W20,21,22,23,24,25,26,27,28,29,30,31:B1,2,3,4,5,6,8,9,10,11,12,16",
          "",
          "g1-h4 e7-g5".some
        ),
        StartingPosition(
          "26-XV",
          "W:W13,21,22,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,12",
          "",
          "g3-a5 -----".some
        ),
        StartingPosition(
          "26-XVI",
          "W:W13,21,22,23,25,26,27,28,29,30,31,32:B1,2,3,4,6,7,8,9,10,11,12,15",
          "",
          "g3-a5 a7-e5".some
        )
      )
    ),
    Category(
      "27",
      List(
        StartingPosition(
          "27-I",
          "W:W13,21,22,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,15",
          "",
          "g3-a5 b6-e5".some
        ),
        StartingPosition(
          "27-II",
          "W:W13,21,22,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,16",
          "",
          "g3-a5 h6-g5".some
        ),
        StartingPosition(
          "27-III",
          "W:W17,21,22,23,25,26,27,28,29,30,31,32:B1,2,3,4,6,7,8,9,10,11,12,15",
          "",
          "g3-b4 a7-e5".some
        ),
        StartingPosition(
          "27-IV",
          "W:W17,21,22,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,15",
          "",
          "g3-b4 b6-e5".some
        ),
        StartingPosition(
          "27-V",
          "W:W17,21,22,23,25,26,27,28,29,30,31,32:B1,2,4,5,6,7,8,9,10,11,12,19",
          "",
          "g3-b4 f8-f4".some
        ),
        StartingPosition(
          "27-VI",
          "W:W17,21,22,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,9,10,11,12,16",
          "",
          "g3-b4 g7-g5".some
        ),
        StartingPosition(
          "27-VII",
          "W:W17,21,22,23,25,26,27,28,29,30,31,32:B1,2,3,5,6,7,8,9,10,11,12,20",
          "",
          "g3-b4 h8-h4".some
        ),
        StartingPosition(
          "27-VIII",
          "W:W17,21,22,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,16",
          "",
          "g3-b4 h6-g5".some
        ),
        StartingPosition(
          "27-IX",
          "W:W14,21,22,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,16",
          "",
          "g3-c5 b6-g5".some
        ),
        StartingPosition(
          "27-X",
          "W:W18,21,22,23,25,26,27,28,29,30,31,32:B1,2,3,4,6,7,8,9,10,11,12,13",
          "",
          "g3-d4 a7-a5".some
        ),
        StartingPosition(
          "27-XI",
          "W:W18,21,22,23,25,26,27,28,29,30,31,32:B1,2,3,4,6,7,8,9,10,11,12,15",
          "",
          "g3-d4 a7-e5".some
        ),
        StartingPosition(
          "27-XII",
          "W:W18,21,22,23,25,26,27,28,29,30,31,32:B1,2,3,4,6,7,8,9,10,11,12,19",
          "",
          "g3-d4 a7-f4".some
        ),
        StartingPosition(
          "27-XIII",
          "W:W18,21,22,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,14",
          "",
          "g3-d4 b6-c5".some
        ),
        StartingPosition(
          "27-XIV",
          "W:W18,21,22,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,13",
          "",
          "g3-d4 d6-a5".some
        ),
        StartingPosition(
          "27-XV",
          "W:W18,21,22,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,15",
          "",
          "g3-d4 d6-e5".some
        ),
        StartingPosition(
          "27-XVI",
          "W:W18,21,22,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,9,10,11,12,13",
          "",
          "g3-d4 g7-a5".some
        )
      )
    ),
    Category(
      "28",
      List(
        StartingPosition(
          "28-I",
          "W:W15,21,22,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,9,10,11,12,20",
          "",
          "g3-e5 g7-h4".some
        ),
        StartingPosition(
          "28-II",
          "W:W15,21,22,23,25,26,27,28,29,30,31,32:B1,2,3,5,6,7,8,9,10,11,12,13",
          "",
          "g3-e5 h8-a5".some
        ),
        StartingPosition(
          "28-III",
          "W:W19,21,22,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,16",
          "",
          "g3-f4 d6-g5".some
        ),
        StartingPosition(
          "28-IV",
          "W:W19,21,22,23,25,26,27,28,29,30,31,32:B1,2,3,5,6,7,8,9,10,11,12,16",
          "",
          "g3-f4 h8-g5".some
        ),
        StartingPosition(
          "28-V",
          "W:W16,21,22,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,13",
          "",
          "g3-g5 h6-a5".some
        ),
        StartingPosition(
          "28-VI",
          "W:W20,21,22,23,25,26,27,28,29,30,31,32:B1,2,3,4,6,7,8,9,10,11,12,16",
          "",
          "g3-h4 a7-g5".some
        ),
        StartingPosition(
          "28-VII",
          "W:W20,21,22,23,25,26,27,28,29,30,31,32:B1,2,3,5,6,7,8,9,10,11,12,15",
          "",
          "g3-h4 h8-e5".some
        ),
        StartingPosition(
          "28-VIII",
          "W:W12,21,22,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,13",
          "",
          "g3-h6 h6-a5".some
        ),
        StartingPosition(
          "28-IX",
          "W:W12,21,22,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,15",
          "",
          "g3-h6 h6-e5".some
        ),
        StartingPosition(
          "28-X",
          "W:W12,21,22,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,20",
          "",
          "g3-h6 h6-h4".some
        ),
        StartingPosition(
          "28-XI",
          "W:W21,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,9,10,11,12,13",
          "",
          "----- g7-a5".some
        ),
        StartingPosition(
          "28-XII",
          "W:W21,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,9,10,11,12,15",
          "",
          "----- g7-e5".some
        ),
        StartingPosition(
          "28-XIII",
          "W:W21,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,9,10,11,12,16",
          "",
          "----- g7-g5".some
        ),
        StartingPosition(
          "28-XIV",
          "W:W13,21,22,23,24,25,26,27,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,12",
          "",
          "h2-a5 -----".some
        ),
        StartingPosition(
          "28-XV",
          "W:W13,21,22,23,24,25,26,27,29,30,31,32:B1,2,3,4,6,7,8,9,10,11,12,16",
          "",
          "h2-a5 a7-g5".some
        ),
        StartingPosition(
          "28-XVI",
          "W:W13,21,22,23,24,25,26,27,29,30,31,32:B1,2,3,4,6,7,8,9,10,11,12,28",
          "",
          "h2-a5 a7-h2".some
        )
      )
    ),
    Category(
      "29",
      List(
        StartingPosition(
          "29-I",
          "W:W13,21,22,23,24,25,26,27,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,28",
          "",
          "h2-a5 b6-h2".some
        ),
        StartingPosition(
          "29-II",
          "W:W13,21,22,23,24,25,26,27,29,30,31,32:B1,2,3,4,5,6,8,9,10,11,12,16",
          "",
          "h2-a5 e7-g5".some
        ),
        StartingPosition(
          "29-III",
          "W:W13,21,22,23,24,25,26,27,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,20",
          "",
          "h2-a5 f6-h4".some
        ),
        StartingPosition(
          "29-IV",
          "W:W13,21,22,23,24,25,26,27,29,30,31,32:B1,2,3,4,5,6,7,9,10,11,12,17",
          "",
          "h2-a5 g7-b4".some
        ),
        StartingPosition(
          "29-V",
          "W:W13,21,22,23,24,25,26,27,29,30,31,32:B1,2,3,5,6,7,8,9,10,11,12,28",
          "",
          "h2-a5 h8-h2".some
        ),
        StartingPosition(
          "29-VI",
          "W:W13,21,22,23,24,25,26,27,29,30,31,32:B1,2,3,5,6,7,8,9,10,11,12,20",
          "",
          "h2-a5 h8-h4".some
        ),
        StartingPosition(
          "29-VII",
          "W:W17,21,22,23,24,25,26,27,29,30,31,32:B1,2,3,4,6,7,8,9,10,11,12,15",
          "",
          "h2-b4 a7-e5".some
        ),
        StartingPosition(
          "29-VIII",
          "W:W17,21,22,23,24,25,26,27,29,30,31,32:B1,2,3,4,6,7,8,9,10,11,12,16",
          "",
          "h2-b4 a7-g5".some
        ),
        StartingPosition(
          "29-IX",
          "W:W17,21,22,23,24,25,26,27,29,30,31,32:B1,2,3,4,6,7,8,9,10,11,12,28",
          "",
          "h2-b4 a7-h2".some
        ),
        StartingPosition(
          "29-X",
          "W:W17,21,22,23,24,25,26,27,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,14",
          "",
          "h2-b4 b6-c5".some
        ),
        StartingPosition(
          "29-XI",
          "W:W17,21,22,23,24,25,26,27,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,28",
          "",
          "h2-b4 b6-h2".some
        ),
        StartingPosition(
          "29-XII",
          "W:W17,21,22,23,24,25,26,27,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,16",
          "",
          "h2-b4 d6-g5".some
        ),
        StartingPosition(
          "29-XIII",
          "W:W14,21,22,23,24,25,26,27,29,30,31,32:B1,2,3,4,6,7,8,9,10,11,12,19",
          "",
          "h2-c5 a7-f4".some
        ),
        StartingPosition(
          "29-XIV",
          "W:W14,21,22,23,24,25,26,27,29,30,31,32:B1,2,3,4,6,7,8,9,10,11,12,28",
          "",
          "h2-c5 a7-h2".some
        ),
        StartingPosition(
          "29-XV",
          "W:W14,21,22,23,24,25,26,27,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,28",
          "",
          "h2-c5 b6-h2".some
        ),
        StartingPosition(
          "29-XVI",
          "W:W14,21,22,23,24,25,26,27,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,28",
          "",
          "h2-c5 d6-h2".some
        )
      )
    ),
    Category(
      "30",
      List(
        StartingPosition(
          "30-I",
          "W:W14,21,22,23,24,25,26,27,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,28",
          "",
          "h2-c5 f6-h2".some
        ),
        StartingPosition(
          "30-II",
          "W:W14,21,22,23,24,25,26,27,29,30,31,32:B1,2,3,5,6,7,8,9,10,11,12,28",
          "",
          "h2-c5 h8-h2".some
        ),
        StartingPosition(
          "30-III",
          "W:W18,21,22,23,24,25,26,27,29,30,31,32:B1,2,3,4,6,7,8,9,10,11,12,28",
          "",
          "h2-d4 a7-h2".some
        ),
        StartingPosition(
          "30-IV",
          "W:W18,21,22,23,24,25,26,27,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,14",
          "",
          "h2-d4 b6-c5".some
        ),
        StartingPosition(
          "30-V",
          "W:W18,21,22,23,24,25,26,27,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,28",
          "",
          "h2-d4 b6-h2".some
        ),
        StartingPosition(
          "30-VI",
          "W:W18,21,22,23,24,25,26,27,29,30,31,32:B1,2,3,4,5,7,8,9,10,11,12,28",
          "",
          "h2-d4 c7-h2".some
        ),
        StartingPosition(
          "30-VII",
          "W:W18,21,22,23,24,25,26,27,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,28",
          "",
          "h2-d4 d6-h2".some
        ),
        StartingPosition(
          "30-VIII",
          "W:W18,21,22,23,24,25,26,27,29,30,31,32:B1,2,3,4,5,6,7,9,10,11,12,28",
          "",
          "h2-d4 g7-h2".some
        ),
        StartingPosition(
          "30-IX",
          "W:W15,21,22,23,24,25,26,27,29,30,31,32:B1,2,3,4,6,7,8,9,10,11,12,17",
          "",
          "h2-e5 a7-b4".some
        ),
        StartingPosition(
          "30-X",
          "W:W15,21,22,23,24,25,26,27,29,30,31,32:B1,2,3,4,6,7,8,9,10,11,12,18",
          "",
          "h2-e5 a7-d4".some
        ),
        StartingPosition(
          "30-XI",
          "W:W15,21,22,23,24,25,26,27,29,30,31,32:B1,2,3,4,5,6,8,9,10,11,12,28",
          "",
          "h2-e5 e7-h2".some
        ),
        StartingPosition(
          "30-XII",
          "W:W15,21,22,23,24,25,26,27,29,30,31,32:B1,2,3,5,6,7,8,9,10,11,12,28",
          "",
          "h2-e5 h8-h2".some
        ),
        StartingPosition(
          "30-XIII",
          "W:W19,21,22,23,24,25,26,27,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,12",
          "",
          "h2-f4 -----".some
        ),
        StartingPosition(
          "30-XIV",
          "W:W19,21,22,23,24,25,26,27,29,30,31,32:B1,2,3,4,6,7,8,9,10,11,12,28",
          "",
          "h2-f4 a7-h2".some
        ),
        StartingPosition(
          "30-XV",
          "W:W19,21,22,23,24,25,26,27,29,30,31,32:B1,2,3,4,5,7,8,9,10,11,12,28",
          "",
          "h2-f4 c7-h2".some
        ),
        StartingPosition(
          "30-XVI",
          "W:W19,21,22,23,24,25,26,27,29,30,31,32:B1,2,3,4,5,6,8,9,10,11,12,28",
          "",
          "h2-f4 e7-h2".some
        )
      )
    ),
    Category(
      "31",
      List(
        StartingPosition(
          "31-I",
          "W:W19,21,22,23,24,25,26,27,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,15",
          "",
          "h2-f4 f6-e5".some
        ),
        StartingPosition(
          "31-II",
          "W:W19,21,22,23,24,25,26,27,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,14",
          "",
          "h2-f4 h6-c5".some
        ),
        StartingPosition(
          "31-III",
          "W:W16,21,22,23,24,25,26,27,29,30,31,32:B1,2,3,4,5,7,8,9,10,11,12,13",
          "",
          "h2-g5 c7-a5".some
        ),
        StartingPosition(
          "31-IV",
          "W:W16,21,22,23,24,25,26,27,29,30,31,32:B1,2,3,4,5,7,8,9,10,11,12,15",
          "",
          "h2-g5 c7-e5".some
        ),
        StartingPosition(
          "31-V",
          "W:W16,21,22,23,24,25,26,27,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,28",
          "",
          "h2-g5 d6-h2".some
        ),
        StartingPosition(
          "31-VI",
          "W:W20,21,22,23,24,25,26,27,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,12",
          "",
          "h2-h4 -----".some
        ),
        StartingPosition(
          "31-VII",
          "W:W20,21,22,23,24,25,26,27,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,28",
          "",
          "h2-h4 h6-h2".some
        ),
        StartingPosition(
          "31-VIII",
          "W:W20,21,22,23,24,25,26,27,29,30,31,32:B1,2,3,4,6,7,8,9,10,11,12,15",
          "",
          "h2-h4 a7-e5".some
        ),
        StartingPosition(
          "31-IX",
          "W:W20,21,22,23,24,25,26,27,29,30,31,32:B1,2,3,4,6,7,8,9,10,11,12,28",
          "",
          "h2-h4 a7-h2".some
        ),
        StartingPosition(
          "31-X",
          "W:W20,21,22,23,24,25,26,27,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,15",
          "",
          "h2-h4 d6-e5".some
        ),
        StartingPosition(
          "31-XI",
          "W:W20,21,22,23,24,25,26,27,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,28",
          "",
          "h2-h4 d6-h2".some
        ),
        StartingPosition(
          "31-XII",
          "W:W12,21,22,23,24,25,26,27,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,19",
          "",
          "h2-h6 h6-f4".some
        ),
        StartingPosition(
          "31-XIII",
          "W:W12,21,22,23,24,25,26,27,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,20",
          "",
          "h2-h6 h6-h4".some
        ),
        StartingPosition(
          "31-XIV",
          "W:W19,21,22,23,24,25,26,27,29,30,31,32:B1,2,3,4,5,7,8,9,10,11,12,20",
          "",
          "h2-f4 c7-h4".some
        ),
        StartingPosition(
          "31-XV",
          "W:W21,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,20",
          "",
          "----- h6-h4".some
        ),
        StartingPosition(
          "31-XVI",
          "W:W21,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,5,6,7,8,9,10,11,12,20",
          "",
          "----- h8-h4".some
        )
      )
    ),
    Category(
      "32",
      List(
        StartingPosition(
          "32-I",
          "W:W17,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,8,10,12,13,16",
          "1. ab4 ba5 2. ed4 fe5 3. df6 eg5",
          "ab4 ba5 ed4 fe5 df6 eg5".some
        ),
        StartingPosition(
          "32-II",
          "W:W13,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,17",
          "1. ab4 bc5 2. ba5 cb4",
          "ab4 bc5 ba5 cb4".some
        ),
        StartingPosition(
          "32-III",
          "W:W17,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,14",
          "1. ab4 dc5",
          "ab4 dc5".some
        ),
        StartingPosition(
          "32-IV",
          "W:W17,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,15",
          "1. ab4 de5",
          "ab4 de5".some
        ),
        StartingPosition(
          "32-V",
          "B:W13,20,22,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,12,15,16",
          "1. ab4 de5 2. ba5 fg5 3. gh4",
          "ab4 de5 ba5 fg5 gh4".some
        ),
        StartingPosition(
          "32-VI",
          "W:W17,20,22,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,9,10,11,12,15",
          "1. ab4 fe5 2. gh4 gf6",
          "ab4 fe5 gh4 gf6".some
        ),
        StartingPosition(
          "32-VII",
          "W:W17,19,22,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,9,10,11,12,16",
          "1. ab4 hg5 2. gf4 gh6",
          "ab4 hg5 gf4 gh6".some
        ),
        StartingPosition(
          "32-VIII",
          "W:W17,20,22,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,13,16",
          "1. ab4 hg5 2. gh4 ba5",
          "ab4 hg5 gh4 ba5".some
        ),
        StartingPosition(
          "32-IX",
          "W:W17,20,22,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,19",
          "1. ab4 hg5 2. gh4 gf4",
          "ab4 hg5 gh4 gf4".some
        ),
        StartingPosition(
          "32-X",
          "W:W17,19,21,23,24,25,26,27,29,30,31,32:B1,2,3,4,6,7,8,9,10,12,14,16",
          "1. cb4 bc5 2. gf4 ab6 3. hg3 fg5",
          "cb4 bc5 gf4 ab6 hg3 fg5".some
        ),
        StartingPosition(
          "32-XI",
          "B:W17,21,22,23,24,25,26,27,28,30,31,32:B1,2,3,4,5,6,7,9,10,11,12,15",
          "1. cb4 fe5 2. bc3 gf6 3. ab2",
          "cb4 fe5 bc3 gf6 ab2".some
        ),
        StartingPosition(
          "32-XII",
          "W:W17,20,21,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,13,16",
          "1. cb4 hg5 2. gh4 ba5",
          "cb4 hg5 gh4 ba5".some
        ),
        StartingPosition(
          "32-XIII",
          "B:W14,20,21,23,25,26,27,28,29,30,31,32:B1,2,3,4,6,7,8,9,10,11,12,13",
          "1. cd4 ba5 2. gh4 ab6 3. dc5",
          "cd4 ba5 gh4 ab6 dc5".some
        ),
        StartingPosition(
          "32-XIV",
          "B:W18,20,21,22,23,26,27,28,29,30,31,32:B1,2,3,4,5,6,8,9,10,11,12,14",
          "1. cd4 dc5 2. bc3 ed6 3. gh4",
          "cd4 dc5 bc3 ed6 gh4".some
        ),
        StartingPosition(
          "32-XV",
          "W:W18,21,22,23,24,25,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,14,16",
          "1. cd4 dc5 2. dc3 hg5",
          "cd4 dc5 dc3 hg5".some
        ),
        StartingPosition(
          "32-XVI",
          "B:W15,19,21,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,7,8,9,10,11,12,14",
          "1. cd4 dc5 2. gf4 cd6 3. de5",
          "cd4 dc5 gf4 cd6 de5".some
        )
      )
    ),
    Category(
      "33",
      List(
        StartingPosition(
          "33-I",
          "W:W18,21,22,23,24,25,26,27,28,29,31,32:B1,2,4,5,6,7,8,9,10,11,12,15",
          "1. cd4 de5 2. bc3 ed6 3. cb2 fe7",
          "cd4 de5 bc3 ed6 cb2 fe7".some
        ),
        StartingPosition(
          "33-II",
          "B:W21,22,23,24,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,9,10,12,15",
          "1. cd4 fe5 2. df6 ge5 3. bc3",
          "cd4 fe5 df6 ge5 bc3".some
        ),
        StartingPosition(
          "33-III",
          "W:W18,19,21,22,23,25,27,28,29,30,31,32:B1,2,3,4,5,6,7,9,11,12,15,16",
          "1. cd4 fg5 2. dc3 gf6 3. gf4 de5",
          "cd4 fg5 dc3 gf6 gf4 de5".some
        ),
        StartingPosition(
          "33-IV",
          "B:W17,18,21,23,24,25,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,20",
          "1. cd4 fg5 2. dc3 gh4 3. cb4",
          "cd4 fg5 dc3 gh4 cb4".some
        ),
        StartingPosition(
          "33-V",
          "W:W18,21,22,23,24,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,19",
          "1. cd4 hg5 2. bc3 gf4",
          "cd4 hg5 bc3 gf4".some
        ),
        StartingPosition(
          "33-VI",
          "W:W18,21,22,23,24,25,26,28,29,30,31,32:B1,2,3,4,5,7,8,9,10,11,12,13",
          "1. ed4 ba5 2. fe3 cb6",
          "ed4 ba5 fe3 cb6".some
        ),
        StartingPosition(
          "33-VII",
          "W:W18,21,22,23,24,25,26,28,29,30,31,32:B1,2,3,4,5,6,8,9,10,11,12,14",
          "1. ed4 dc5 2. fe3 ed6",
          "ed4 dc5 fe3 ed6".some
        ),
        StartingPosition(
          "33-VIII",
          "B:W18,20,21,22,23,25,26,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,15,16",
          "1. ed4 de5 2. gh4 hg5 3. fe3",
          "ed4 de5 gh4 hg5 fe3".some
        ),
        StartingPosition(
          "33-IX",
          "W:W17,22,24,25,26,27,28,29,30,31,32:B1,2,3,5,6,7,8,9,10,12,15",
          "1. ed4 fe5 2. df6 ge5 3. ab4 hg7",
          "ed4 fe5 df6 ge5 ab4 hg7".some
        ),
        StartingPosition(
          "33-X",
          "W:W20,21,22,25,26,27,28,29,30,31,32:B1,2,3,5,6,7,8,9,10,12,15",
          "1. ed4 fe5 2. df6 ge5 3. gh4 hg7",
          "ed4 fe5 df6 ge5 gh4 hg7".some
        ),
        StartingPosition(
          "33-XI",
          "W:W18,21,22,23,24,25,26,28,29,30,31,32:B1,2,3,4,5,6,7,9,10,11,12,16",
          "1. ed4 fg5 2. fe3 gf6",
          "ed4 fg5 fe3 gf6".some
        ),
        StartingPosition(
          "33-XII",
          "B:W19,20,21,22,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,13",
          "1. ef4 ba5 2. gh4",
          "ef4 ba5 gh4".some
        ),
        StartingPosition(
          "33-XIII",
          "W:W19,20,21,22,24,25,26,27,29,30,31,32:B1,2,3,4,6,7,8,9,10,12,14,15",
          "1. ef4 bc5 2. gh4 ab6 3. hg3 fe5",
          "ef4 bc5 gh4 ab6 hg3 fe5".some
        ),
        StartingPosition(
          "33-XIV",
          "W:W19,21,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,22",
          "1. gf4 fe5 2. cd4 ec3",
          "gf4 fe5 cd4 ec3".some
        ),
        StartingPosition(
          "33-XV",
          "W:W18,21,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,24",
          "1. gf4 fe5 2. cd4 eg3",
          "gf4 fe5 cd4 eg3".some
        ),
        StartingPosition(
          "33-XVI",
          "W:W18,19,21,22,25,26,27,29,30,31,32:B1,2,3,4,5,6,7,8,10,12,13",
          "1. gf4 fe5 2. ed4 eg3 3. hf4 ba5",
          "gf4 fe5 ed4 eg3 hf4 ba5".some
        )
      )
    ),
    Category(
      "34",
      List(
        StartingPosition(
          "34-I",
          "W:W18,20,21,23,25,26,27,28,29,30,31,32:B1,2,3,4,6,7,8,9,10,11,12,14",
          "1. gh4 bc5 2. cd4 ab6",
          "gh4 bc5 cd4 ab6".some
        ),
        StartingPosition(
          "34-II",
          "W:W18,20,21,23,24,25,26,28,29,30,31,32:B1,2,3,4,6,7,8,9,10,12,14,16",
          "1. gh4 bc5 2. fg3 ab6 3. cd4 fg5",
          "gh4 bc5 fg3 ab6 cd4 fg5".some
        ),
        StartingPosition(
          "34-III",
          "W:W17,20,21,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,18",
          "1. gh4 dc5 1. cb4 cd4",
          "gh4 dc5 cb4 cd4".some
        ),
        StartingPosition(
          "34-IV",
          "W:W17,20,21,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,12,14,16",
          "1. gh4 dc5 2. cb4 fg5",
          "gh4 dc5 cb4 fg5".some
        ),
        StartingPosition(
          "34-V",
          "B:W18,20,21,23,24,25,26,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,12,14,15",
          "1. gh4 dc5 2. fg3 fe5 3. cd4",
          "gh4 dc5 fg3 fe5 cd4".some
        ),
        StartingPosition(
          "34-VI",
          "W:W13,21,22,23,24,25,26,27,28,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,12",
          "",
          "a1-a5 -----".some
        ),
        StartingPosition(
          "34-VII",
          "W:W17,21,22,23,24,25,26,27,28,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,13",
          "",
          "a1-b4 b6-a5".some
        ),
        StartingPosition(
          "34-VIII",
          "W:W17,21,22,23,24,25,26,27,28,30,31,32:B1,2,3,5,6,7,8,9,10,11,12,15",
          "",
          "a1-b4 h8-e5".some
        ),
        StartingPosition(
          "34-IX",
          "W:W18,21,22,23,24,25,26,27,28,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,13",
          "",
          "a1-d4 b6-a5".some
        ),
        StartingPosition(
          "34-X",
          "W:W15,21,22,23,24,25,26,27,28,30,31,32:B1,2,3,4,5,6,7,9,10,11,12,20",
          "",
          "a1-e5 g7-h4".some
        ),
        StartingPosition(
          "34-XI",
          "W:W17,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,15",
          "",
          "a3-b4 h6-e5".some
        ),
        StartingPosition(
          "34-XII",
          "W:W18,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,7,8,9,10,11,12,13",
          "",
          "a3-d4 c7-a5".some
        ),
        StartingPosition(
          "34-XIII",
          "W:W19,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,12",
          "",
          "a3-f4 -----".some
        ),
        StartingPosition(
          "34-XIV",
          "W:W20,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,6,7,8,9,10,11,12,13",
          "",
          "a3-h4 a7-a5".some
        ),
        StartingPosition(
          "34-XV",
          "W:W20,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,14",
          "",
          "a3-h4 b6-c5".some
        ),
        StartingPosition(
          "34-XVI",
          "W:W20,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,14",
          "",
          "a3-h4 d6-c5".some
        )
      )
    ),
    Category(
      "35",
      List(
        StartingPosition(
          "35-I",
          "W:W21,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,6,7,8,9,10,11,12,16",
          "",
          "----- a7-g5".some
        ),
        StartingPosition(
          "35-II",
          "W:W17,21,22,23,24,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,15",
          "",
          "b2-b4 d6-e5".some
        ),
        StartingPosition(
          "35-III",
          "W:W17,21,22,23,24,26,27,28,29,30,31,32:B1,2,3,5,6,7,8,9,10,11,12,13",
          "",
          "b2-b4 h8-a5".some
        ),
        StartingPosition(
          "35-IV",
          "W:W14,21,22,23,24,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,12",
          "",
          "b2-c5 -----".some
        ),
        StartingPosition(
          "35-V",
          "W:W18,21,22,23,24,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,12",
          "",
          "b2-d4 -----".some
        ),
        StartingPosition(
          "35-VI",
          "W:W18,21,22,23,24,26,27,28,29,30,31,32:B1,2,3,5,6,7,8,9,10,11,12,20",
          "",
          "b2-d4 h8-h4".some
        ),
        StartingPosition(
          "35-VII",
          "W:W16,21,22,23,24,26,27,28,29,30,31,32:B1,2,3,4,6,7,8,9,10,11,12,13",
          "",
          "b2-g5 a7-a5".some
        ),
        StartingPosition(
          "35-VIII",
          "W:W16,21,22,23,24,26,27,28,29,30,31,32:B1,2,3,4,5,7,8,9,10,11,12,13",
          "",
          "b2-g5 c7-a5".some
        ),
        StartingPosition(
          "35-IX",
          "W:W20,21,22,23,24,26,27,28,29,30,31,32:B1,2,3,4,6,7,8,9,10,11,12,13",
          "",
          "b2-h4 a7-a5".some
        ),
        StartingPosition(
          "35-X",
          "W:W13,21,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,20",
          "",
          "c3-a5 d6-h4".some
        ),
        StartingPosition(
          "35-XI",
          "W:W13,21,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,15",
          "",
          "c3-a5 f6-e5".some
        ),
        StartingPosition(
          "35-XII",
          "W:W13,21,23,24,25,26,27,28,29,30,31,32:B1,2,3,5,6,7,8,9,10,11,12,16",
          "",
          "c3-a5 h8-g5".some
        ),
        StartingPosition(
          "35-XIII",
          "W:W17,21,23,24,25,26,27,28,29,30,31,32:B1,2,3,5,6,7,8,9,10,11,12,16",
          "",
          "c3-b4 h8-g5".some
        ),
        StartingPosition(
          "35-XIV",
          "W:W17,21,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,15",
          "",
          "c3-b4 h6-e5".some
        ),
        StartingPosition(
          "35-XV",
          "W:W18,21,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,13",
          "",
          "c3-d4 d6-a5".some
        ),
        StartingPosition(
          "35-XVI",
          "W:W15,21,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,13",
          "",
          "c3-e5 b6-a5".some
        )
      )
    ),
    Category(
      "36",
      List(
        StartingPosition(
          "36-I",
          "W:W15,21,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,9,10,11,12,20",
          "",
          "c3-e5 g7-h4".some
        ),
        StartingPosition(
          "36-II",
          "W:W19,21,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,12",
          "",
          "c3-f4 -----".some
        ),
        StartingPosition(
          "36-III",
          "W:W20,21,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,13",
          "",
          "c3-h4 f6-a5".some
        ),
        StartingPosition(
          "36-IV",
          "W:W13,21,22,23,24,25,27,28,29,30,31,32:B1,2,3,4,5,6,7,9,10,11,12,26",
          "",
          "d2-a5 g7-d2".some
        ),
        StartingPosition(
          "36-V",
          "W:W17,21,22,23,24,25,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,15",
          "",
          "d2-b4 d6-e5".some
        ),
        StartingPosition(
          "36-VI",
          "W:W17,21,22,23,24,25,27,28,29,30,31,32:B1,2,3,5,6,7,8,9,10,11,12,13",
          "",
          "d2-b4 h8-a5".some
        ),
        StartingPosition(
          "36-VII",
          "W:W14,21,22,23,24,25,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,12",
          "",
          "d2-c5 -----".some
        ),
        StartingPosition(
          "36-VIII",
          "W:W18,21,22,23,24,25,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,13",
          "",
          "d2-d4 b6-a5".some
        ),
        StartingPosition(
          "36-IX",
          "W:W18,21,22,23,24,25,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,15",
          "",
          "d2-d4 f6-e5".some
        ),
        StartingPosition(
          "36-X",
          "W:W20,21,22,23,24,25,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,16",
          "",
          "d2-h4 f6-g5".some
        ),
        StartingPosition(
          "36-XI",
          "W:W17,21,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,19",
          "",
          "e3-b4 b6-f4".some
        ),
        StartingPosition(
          "36-XII",
          "W:W14,21,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,9,10,11,12,20",
          "",
          "e3-c5 g7-h4".some
        ),
        StartingPosition(
          "36-XIII",
          "W:W19,21,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,6,7,8,9,10,11,12,15",
          "",
          "e3-f4 a7-e5".some
        ),
        StartingPosition(
          "36-XIV",
          "W:W19,21,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,14",
          "",
          "e3-f4 f6-c5".some
        ),
        StartingPosition(
          "36-XV",
          "W:W20,21,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,13",
          "",
          "e3-h4 f6-a5".some
        ),
        StartingPosition(
          "36-XVI",
          "W:W17,21,22,23,24,25,26,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,13",
          "",
          "f2-b4 d6-a5".some
        )
      )
    ),
    Category(
      "37",
      List(
        StartingPosition(
          "37-I",
          "W:W18,21,22,23,24,25,26,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,14",
          "",
          "f2-d4 b6-c5".some
        ),
        StartingPosition(
          "37-II",
          "W:W18,21,22,23,24,25,26,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,13",
          "",
          "f2-d4 d6-a5".some
        ),
        StartingPosition(
          "37-III",
          "W:W19,21,22,23,24,25,26,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,15",
          "",
          "f2-f4 f6-e5".some
        ),
        StartingPosition(
          "37-IV",
          "W:W21,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,20",
          "",
          "----- f6-h4".some
        ),
        StartingPosition(
          "37-V",
          "W:W19,21,22,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,12",
          "",
          "g3-f4 -----".some
        ),
        StartingPosition(
          "37-VI",
          "W:W19,21,22,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,9,10,11,12,13",
          "",
          "g3-f4 g7-a5".some
        ),
        StartingPosition(
          "37-VII",
          "W:W16,21,22,23,25,26,27,28,29,30,31,32:B1,2,3,4,6,7,8,9,10,11,12,20",
          "",
          "g3-g5 a7-h4".some
        ),
        StartingPosition(
          "37-VIII",
          "W:W16,21,22,23,25,26,27,28,29,30,31,32:B1,2,3,5,6,7,8,9,10,11,12,17",
          "",
          "g3-g5 h8-b4".some
        ),
        StartingPosition(
          "37-IX",
          "W:W20,21,22,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,12",
          "",
          "g3-h4 -----".some
        ),
        StartingPosition(
          "37-X",
          "W:W20,21,22,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,7,8,9,10,11,12,19",
          "",
          "g3-h4 c7-f4".some
        ),
        StartingPosition(
          "37-XI",
          "W:W20,21,22,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,9,10,11,12,14",
          "",
          "g3-h4 g7-c5".some
        ),
        StartingPosition(
          "37-XII",
          "W:W21,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,9,10,11,12,20",
          "",
          "----- g7-h4".some
        ),
        StartingPosition(
          "37-XIII",
          "W:W13,21,22,23,24,25,26,27,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,28",
          "",
          "h2-a5 d6-h2".some
        ),
        StartingPosition(
          "37-XIV",
          "W:W17,21,22,23,24,25,26,27,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,28",
          "",
          "h2-b4 d6-h2".some
        ),
        StartingPosition(
          "37-XV",
          "W:W17,21,22,23,24,25,26,27,29,30,31,32:B1,2,3,4,5,6,7,9,10,11,12,28",
          "",
          "h2-b4 g7-h2".some
        ),
        StartingPosition(
          "37-XVI",
          "W:W17,21,22,23,24,25,26,27,29,30,31,32:B1,2,3,5,6,7,8,9,10,11,12,28",
          "",
          "h2-b4 h8-h2".some
        )
      )
    ),
    Category(
      "38",
      List(
        StartingPosition(
          "38-I",
          "B:W17,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,12",
          "1. ab4",
          "ab4".some
        ),
        StartingPosition(
          "38-II",
          "W:W17,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,13",
          "1. ab4 ba5",
          "ab4 ba5".some
        ),
        StartingPosition(
          "38-III",
          "W:W17,21,22,23,24,26,27,28,29,30,31,32:B1,2,3,4,6,7,8,9,10,11,12,13",
          "1. ab4 ba5 2. ba3 ab6",
          "ab4 ba5 ba3 ab6".some
        ),
        StartingPosition(
          "38-IV",
          "W:W17,21,22,23,24,25,26,27,28,30,31,32:B1,2,3,4,6,7,8,9,11,12,13,15",
          "1. ab4 ba5 2. ba3 ab6 3. ab2 de5",
          "ab4 ba5 ba3 ab6 ab2 de5".some
        ),
        StartingPosition(
          "38-V",
          "W:W17,21,22,23,24,25,26,27,28,30,31,32:B1,2,3,4,5,7,8,10,11,12,13,14",
          "1. ab4 ba5 2. ba3 cb6 3. ab2 bc5",
          "ab4 ba5 ba3 cb6 ab2 bc5".some
        ),
        StartingPosition(
          "38-VI",
          "B:W17,19,21,22,23,26,27,28,29,30,31,32:B1,2,3,4,5,7,8,9,10,11,12,13",
          "1. ab4 ba5 2. ba3 cb6 3. gf4",
          "ab4 ba5 ba3 cb6 gf4".some
        ),
        StartingPosition(
          "38-VII",
          "W:W17,21,22,23,24,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,11,12,13,15",
          "1. ab4 ba5 2. ba3 de5",
          "ab4 ba5 ba3 de5".some
        ),
        StartingPosition(
          "38-VIII",
          "W:W17,18,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,11,12,13,14",
          "1. ab4 ba5 2. ed4 dc5",
          "ab4 ba5 ed4 dc5".some
        ),
        StartingPosition(
          "38-IX",
          "W:W17,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,10,12,13,15",
          "1. ab4 ba5 2. ed4 fe5 3. df6 ge5",
          "ab4 ba5 ed4 fe5 df6 ge5".some
        ),
        StartingPosition(
          "38-X",
          "W:W17,18,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,12,13,16",
          "1. ab4 ba5 2. ed4 fg5",
          "ab4 ba5 ed4 fg5".some
        ),
        StartingPosition(
          "38-XI",
          "W:W17,18,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,13,16",
          "1. ab4 ba5 2. ed4 hg5",
          "ab4 ba5 ed4 hg5".some
        ),
        StartingPosition(
          "38-XII",
          "W:W17,20,22,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,12,13,16",
          "1. ab4 ba5 2. gh4 fg5",
          "ab4 ba5 gh4 fg5".some
        ),
        StartingPosition(
          "38-XIII",
          "W:W17,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,14",
          "1. ab4 bc5",
          "ab4 bc5".some
        ),
        StartingPosition(
          "38-XIV",
          "W:W13,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,12,14,16",
          "1. ab4 bc5 2. ba5 fg5",
          "ab4 bc5 ba5 fg5".some
        ),
        StartingPosition(
          "38-XV",
          "B:W17,20,22,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,15",
          "1. ab4 de5 2. gh4",
          "ab4 de5 gh4".some
        ),
        StartingPosition(
          "38-XVI",
          "W:W17,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,15",
          "1. ab4 fe5",
          "ab4 fe5".some
        )
      )
    ),
    Category(
      "39",
      List(
        StartingPosition(
          "39-I",
          "W:W13,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,19",
          "1. ab4 fe5 2. ba5 ef4",
          "ab4 fe5 ba5 ef4".some
        ),
        StartingPosition(
          "39-II",
          "W:W13,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,9,10,11,12,15",
          "1. ab4 fe5 2. ba5 gf6",
          "ab4 fe5 ba5 gf6".some
        ),
        StartingPosition(
          "39-III",
          "B:W17,19,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,15",
          "1. ab4 fe5 2. ef4",
          "ab4 fe5 ef4".some
        ),
        StartingPosition(
          "39-IV",
          "W:W17,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,16",
          "1. ab4 fg5",
          "ab4 fg5".some
        ),
        StartingPosition(
          "39-V",
          "W:W22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,12,16,17",
          "1. ab4 fg5 2. bc5 db4",
          "ab4 fg5 bc5 db4".some
        ),
        StartingPosition(
          "39-VI",
          "W:W17,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,16",
          "1. ab4 hg5",
          "ab4 hg5".some
        ),
        StartingPosition(
          "39-VII",
          "W:W17,19,22,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,14,16",
          "1. ab4 hg5 2. gf4 bc5",
          "ab4 hg5 gf4 bc5".some
        ),
        StartingPosition(
          "39-VIII",
          "B:W17,21,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,12",
          "1. cb4",
          "cb4".some
        ),
        StartingPosition(
          "39-IX",
          "W:W14,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,7,8,9,11,12,13",
          "1. cb4 ba5 2. bc5 db4 3. ac5 cb6",
          "cb4 ba5 bc5 db4 ac5 cb6".some
        ),
        StartingPosition(
          "39-X",
          "W:W14,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,12,13,15",
          "1. cb4 ba5 2. bc5 db4 3. ac5 fe5",
          "cb4 ba5 bc5 db4 ac5 fe5".some
        ),
        StartingPosition(
          "39-XI",
          "B:W17,19,21,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,13",
          "1. cb4 ba5 2. gf4",
          "cb4 ba5 gf4".some
        ),
        StartingPosition(
          "39-XII",
          "W:W17,21,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,14",
          "1. cb4 bc5",
          "cb4 bc5".some
        ),
        StartingPosition(
          "39-XIII",
          "W:W17,18,21,23,24,26,27,28,29,30,31,32:B1,2,3,4,6,7,8,9,10,12,14,16",
          "1. cb4 bc5 2. bc3 fg5 3. cd4 ab6",
          "cb4 bc5 bc3 fg5 cd4 ab6".some
        ),
        StartingPosition(
          "39-XIV",
          "W:W17,21,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,15",
          "1. cb4 de5",
          "cb4 de5".some
        ),
        StartingPosition(
          "39-XV",
          "W:W13,21,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,11,12,14,15",
          "1. cb4 de5 2. ba5 bc5",
          "cb4 de5 ba5 bc5".some
        ),
        StartingPosition(
          "39-XVI",
          "W:W17,19,21,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,11,12,13,15",
          "1. cb4 de5 2. ef4 ba5",
          "cb4 de5 ef4 ba5".some
        )
      )
    ),
    Category(
      "40",
      List(
        StartingPosition(
          "40-I",
          "B:W17,19,21,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,15",
          "1. cb4 de5 2. gf4",
          "cb4 de5 gf4".some
        ),
        StartingPosition(
          "40-II",
          "W:W17,19,21,23,25,26,27,29,30,31,32:B1,2,3,4,5,6,7,8,9,12,15",
          "1. cb4 de5 2. gf4 eg3 3. hf4 fe5",
          "cb4 de5 gf4 eg3 hf4 fe5".some
        ),
        StartingPosition(
          "40-III",
          "W:W13,21,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,12,14,15",
          "1. cb4 fe5 2. ba5 bc5",
          "cb4 fe5 ba5 bc5".some
        ),
        StartingPosition(
          "40-IV",
          "W:W17,20,21,22,23,26,27,28,29,30,31,32:B1,2,3,5,6,7,8,9,10,11,12,15",
          "1. cb4 fe5 2. bc3 gf6 3. gh4 hg7",
          "cb4 fe5 bc3 gf6 gh4 hg7".some
        ),
        StartingPosition(
          "40-V",
          "W:W17,19,21,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,12,13,15",
          "1. cb4 fe5 2. ef4 ba5",
          "cb4 fe5 ef4 ba5".some
        ),
        StartingPosition(
          "40-VI",
          "B:W14,19,21,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,12,13,15",
          "1. cb4 fe5 2. ef4 ba5 3. bc5",
          "cb4 fe5 ef4 ba5 bc5".some
        ),
        StartingPosition(
          "40-VII",
          "B:W17,19,21,22,24,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,9,10,11,12,15",
          "1. cb4 fe5 2. ef4 gf6 3. bc3",
          "cb4 fe5 ef4 gf6 bc3".some
        ),
        StartingPosition(
          "40-VIII",
          "W:W17,19,21,23,24,25,27,28,29,30,31,32:B1,2,3,4,5,6,7,10,11,12,14,15",
          "1. cb4 fe5 2. ef4 gf6 3. de3 bc5",
          "cb4 fe5 ef4 gf6 de3 bc5".some
        ),
        StartingPosition(
          "40-IX",
          "B:W16,17,21,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,12,13,15",
          "1. cb4 fe5 2. gh4 ba5 3. hg5",
          "cb4 fe5 gh4 ba5 hg5".some
        ),
        StartingPosition(
          "40-X",
          "B:W16,17,21,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,12,14,15",
          "1. cb4 fe5 2. gh4 bc5 3. hg5",
          "cb4 fe5 gh4 bc5 hg5".some
        ),
        StartingPosition(
          "40-XI",
          "W:W17,18,21,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,12,13,16",
          "1. cb4 fg5 2. ed4 ba5",
          "cb4 fg5 ed4 ba5".some
        ),
        StartingPosition(
          "40-XII",
          "W:W17,18,21,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,12,14,16",
          "1. cb4 fg5 2. ed4 dc5",
          "cb4 fg5 ed4 dc5".some
        ),
        StartingPosition(
          "40-XIII",
          "W:W17,21,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,16",
          "1. cb4 hg5",
          "cb4 hg5".some
        ),
        StartingPosition(
          "40-XIV",
          "W:W17,20,21,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,15,16",
          "1. cb4 hg5 2. gh4 de5",
          "cb4 hg5 gh4 de5".some
        ),
        StartingPosition(
          "40-XV",
          "B:W14,20,21,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,9,10,11,12,16",
          "1. cb4 hg5 2. gh4 gh6 3. bc5",
          "cb4 hg5 gh4 gh6 bc5".some
        ),
        StartingPosition(
          "40-XVI",
          "B:W17,20,21,23,24,25,26,27,29,30,31,32:B1,2,3,4,5,6,7,9,10,11,12,16",
          "1. cb4 hg5 2. gh4 gh6 3. hg3",
          "cb4 hg5 gh4 gh6 hg3".some
        )
      )
    ),
    Category(
      "41",
      List(
        StartingPosition(
          "41-I",
          "B:W18,21,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,12",
          "1. cd4",
          "cd4".some
        ),
        StartingPosition(
          "41-II",
          "B:W18,21,22,23,24,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,13",
          "1. cd4 ba5 2. bc3",
          "cd4 ba5 bc3".some
        ),
        StartingPosition(
          "41-III",
          "W:W18,21,22,23,24,26,27,28,29,30,31,32:B1,2,3,4,6,7,8,9,10,11,12,13",
          "1. cd4 ba5 2. bc3 ab6",
          "cd4 ba5 bc3 ab6".some
        ),
        StartingPosition(
          "41-IV",
          "W:W18,21,22,23,24,25,26,27,28,30,31,32:B1,2,3,4,6,7,8,9,11,12,13,14",
          "1. cd4 ba5 2. bc3 ab6 3. ab2 dc5",
          "cd4 ba5 bc3 ab6 ab2 dc5".some
        ),
        StartingPosition(
          "41-V",
          "B:W18,19,21,22,24,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,11,12,13,15",
          "1. cd4 ba5 2. bc3 de5 3. ef4",
          "cd4 ba5 bc3 de5 ef4".some
        ),
        StartingPosition(
          "41-VI",
          "B:W18,19,21,22,23,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,11,12,13,15",
          "1. cd4 ba5 2. bc3 de5 3. gf4",
          "cd4 ba5 bc3 de5 gf4".some
        ),
        StartingPosition(
          "41-VII",
          "W:W18,21,22,23,24,25,26,27,28,29,31,32:B1,2,3,4,5,6,7,8,10,11,13,19",
          "1. cd4 ba5 2. bc3 hg5 3. cb2 gf4",
          "cd4 ba5 bc3 hg5 cb2 gf4".some
        ),
        StartingPosition(
          "41-VIII",
          "W:W21,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,11,12,13,19",
          "1. cd4 ba5 2. de5 df4",
          "cd4 ba5 de5 df4".some
        ),
        StartingPosition(
          "41-IX",
          "W:W18,19,21,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,11,12,13,15",
          "1. cd4 ba5 2. ef4 de5",
          "cd4 ba5 ef4 de5".some
        ),
        StartingPosition(
          "41-X",
          "W:W18,21,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,14",
          "1. cd4 bc5",
          "cd4 bc5".some
        ),
        StartingPosition(
          "41-XI",
          "W:W21,22,23,24,26,27,28,29,30,31,32:B1,2,3,4,6,7,8,10,12,14,15",
          "1. cd4 bc5 2. db6 ac5 3. bc3 fe5",
          "cd4 bc5 db6 ac5 bc3 fe5".some
        ),
        StartingPosition(
          "41-XII",
          "W:W18,21,22,23,24,26,27,28,29,30,31,32:B1,2,3,4,5,7,8,9,10,11,12,14",
          "1. cd4 dc5 2. bc3 cd6",
          "cd4 dc5 bc3 cd6".some
        ),
        StartingPosition(
          "41-XIII",
          "W:W18,21,22,23,24,25,26,27,28,30,31,32:B1,2,3,4,5,7,8,9,11,12,14,15",
          "1. cd4 dc5 2. bc3 cd6 3. ab2 de5",
          "cd4 dc5 bc3 cd6 ab2 de5".some
        ),
        StartingPosition(
          "41-XIV",
          "W:W17,18,21,23,24,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,12,13,14,16",
          "1. cd4 dc5 2. bc3 fg5 3. cb4 ba5",
          "cd4 dc5 bc3 fg5 cb4 ba5".some
        ),
        StartingPosition(
          "41-XV",
          "W:W17,18,21,23,24,25,27,28,29,30,31,32:B1,2,3,4,5,6,8,9,10,12,14,16",
          "1. cd4 dc5 2. dc3 ed6 3. cb4 fg5",
          "cd4 dc5 dc3 ed6 cb4 fg5".some
        ),
        StartingPosition(
          "41-XVI",
          "W:W18,21,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,15",
          "1. cd4 de5",
          "cd4 de5".some
        )
      )
    ),
    Category(
      "42",
      List(
        StartingPosition(
          "42-I",
          "W:W18,19,21,22,24,26,27,28,29,30,31,32:B1,2,4,5,6,7,8,9,10,11,12,15",
          "1. cd4 de5 2. bc3 ed6 3. ef4 fe7",
          "cd4 de5 bc3 ed6 ef4 fe7".some
        ),
        StartingPosition(
          "42-II",
          "B:W18,19,21,22,23,26,27,28,29,30,31,32:B1,2,3,4,5,6,8,9,10,11,12,15",
          "1. cd4 de5 2. bc3 ed6 3. gf4",
          "cd4 de5 bc3 ed6 gf4".some
        ),
        StartingPosition(
          "42-III",
          "B:W18,21,22,23,24,25,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,15",
          "1. cd4 de5 2. dc3",
          "cd4 de5 dc3".some
        ),
        StartingPosition(
          "42-IV",
          "W:W19,21,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,22",
          "1. cd4 de5 2. gf4 ec3",
          "cd4 de5 gf4 ec3".some
        ),
        StartingPosition(
          "42-V",
          "W:W18,21,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,24",
          "1. cd4 de5 2. gf4 eg3",
          "cd4 de5 gf4 eg3".some
        ),
        StartingPosition(
          "42-VI",
          "W:W18,21,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,15",
          "1. cd4 fe5",
          "cd4 fe5".some
        ),
        StartingPosition(
          "42-VII",
          "W:W19,21,23,25,26,27,28,29,30,31,32:B1,3,4,5,6,7,8,9,10,12,16",
          "1. cd4 fe5 2. df6 eg5 3. gf4 de7",
          "cd4 fe5 df6 eg5 gf4 de7".some
        ),
        StartingPosition(
          "42-VIII",
          "W:W18,21,22,23,24,26,27,28,29,30,31,32:B1,2,3,4,5,6,8,9,10,11,12,16",
          "1. cd4 fg5 2. bc3 ef6",
          "cd4 fg5 bc3 ef6".some
        ),
        StartingPosition(
          "42-IX",
          "W:W18,21,22,23,24,25,26,27,28,30,31,32:B1,2,3,4,5,6,7,9,10,11,12,20",
          "1. cd4 fg5 2. bc3 gf6 3. ab2 gh4",
          "cd4 fg5 bc3 gf6 ab2 gh4".some
        ),
        StartingPosition(
          "42-X",
          "W:W18,21,22,23,24,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,19",
          "1. cd4 fg5 2. bc3 gf4",
          "cd4 fg5 bc3 gf4".some
        ),
        StartingPosition(
          "42-XI",
          "W:W17,18,21,23,24,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,12,15,20",
          "1. cd4 fg5 2. bc3 gh4 3. cb4 de5",
          "cd4 fg5 bc3 gh4 cb4 de5".some
        ),
        StartingPosition(
          "42-XII",
          "W:W17,18,21,23,24,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,16,20",
          "1. cd4 fg5 2. bc3 gh4 3. cb4 hg5",
          "cd4 fg5 bc3 gh4 cb4 hg5".some
        ),
        StartingPosition(
          "42-XIII",
          "B:W14,21,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,16",
          "1. cd4 fg5 2. dc5",
          "cd4 fg5 dc5".some
        ),
        StartingPosition(
          "42-XIV",
          "W:W18,19,21,22,23,26,27,28,29,30,31,32:B1,2,3,5,6,7,8,9,10,11,12,16",
          "1. cd4 fg5 2. gf4 gf6 3. bc3 hg7",
          "cd4 fg5 gf4 gf6 bc3 hg7".some
        ),
        StartingPosition(
          "42-XV",
          "W:W18,19,21,22,23,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,9,10,11,12,20",
          "1. cd4 fg5 2. gf4 gf6 3. bc3 gh4",
          "cd4 fg5 gf4 gf6 bc3 gh4".some
        ),
        StartingPosition(
          "42-XVI",
          "W:W18,20,21,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,12,13,16",
          "1. cd4 fg5 2. gh4 ba5",
          "cd4 fg5 gh4 ba5".some
        )
      )
    ),
    Category(
      "43",
      List(
        StartingPosition(
          "43-I",
          "W:W18,20,21,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,12,14,16",
          "1. cd4 fg5 2. gh4 dc5",
          "cd4 fg5 gh4 dc5".some
        ),
        StartingPosition(
          "43-II",
          "W:W18,20,21,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,19",
          "1. cd4 fg5 2. gh4 gf4",
          "cd4 fg5 gh4 gf4".some
        ),
        StartingPosition(
          "43-III",
          "W:W18,21,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,16",
          "1. cd4 hg5",
          "cd4 hg5".some
        ),
        StartingPosition(
          "43-IV",
          "B:W15,18,21,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,20",
          "1. cd4 hg5 2. gf4 gh4 3. fe5",
          "cd4 hg5 gf4 gh4 fe5".some
        ),
        StartingPosition(
          "43-V",
          "B:W18,21,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,12",
          "1. ed4",
          "ed4".some
        ),
        StartingPosition(
          "43-VI",
          "W:W18,21,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,13",
          "1. ed4 ba5",
          "ed4 ba5".some
        ),
        StartingPosition(
          "43-VII",
          "W:W14,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,12,13,16",
          "1. ed4 ba5 2. dc5 db4 3. ac5 fg5",
          "ed4 ba5 dc5 db4 ac5 fg5".some
        ),
        StartingPosition(
          "43-VIII",
          "W:W18,21,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,14",
          "1. ed4 bc5",
          "ed4 bc5".some
        ),
        StartingPosition(
          "43-IX",
          "W:W21,22,23,24,25,27,28,29,30,31,32:B1,2,3,4,6,7,8,10,11,14,16",
          "1. ed4 bc5 2. db6 ac5 3. de3 hg5",
          "ed4 bc5 db6 ac5 de3 hg5".some
        ),
        StartingPosition(
          "43-X",
          "W:W19,21,22,25,26,27,28,29,30,31,32:B1,2,3,4,6,7,8,10,12,14,15",
          "1. ed4 bc5 2. db6 ac5 3. gf4 fe5",
          "ed4 bc5 db6 ac5 gf4 fe5".some
        ),
        StartingPosition(
          "43-XI",
          "W:W20,21,22,25,26,27,28,29,30,31,32:B1,2,3,4,6,7,8,11,12,14,15",
          "1. ed4 bc5 2. db6 ac5 3. gh4 de5",
          "ed4 bc5 db6 ac5 gh4 de5".some
        ),
        StartingPosition(
          "43-XII",
          "W:W18,21,22,23,24,25,26,28,29,30,31,32:B1,2,3,4,5,7,8,9,10,11,12,14",
          "1. ed4 dc5 2. fe3 cd6",
          "ed4 dc5 fe3 cd6".some
        ),
        StartingPosition(
          "43-XIII",
          "W:W18,21,22,23,24,25,26,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,12,14,16",
          "1. ed4 dc5 2. fe3 fg5",
          "ed4 dc5 fe3 fg5".some
        ),
        StartingPosition(
          "43-XIV",
          "W:W18,21,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,15",
          "1. ed4 de5",
          "ed4 de5".some
        ),
        StartingPosition(
          "43-XV",
          "W:W18,21,22,23,24,25,27,28,29,30,31,32:B1,2,3,4,5,6,8,9,10,11,12,15",
          "1. ed4 de5 2. de3 ed6",
          "ed4 de5 de3 ed6".some
        ),
        StartingPosition(
          "43-XVI",
          "B:W18,21,22,23,24,25,26,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,15",
          "1. ed4 de5 2. fe3",
          "ed4 de5 fe3".some
        )
      )
    ),
    Category(
      "44",
      List(
        StartingPosition(
          "44-I",
          "W:W18,21,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,15",
          "1. ed4 fe5",
          "ed4 fe5".some
        ),
        StartingPosition(
          "44-II",
          "W:W18,21,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,16",
          "1. ed4 fg5",
          "ed4 fg5".some
        ),
        StartingPosition(
          "44-III",
          "W:W18,20,21,22,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,12,14,16",
          "1. ed4 fg5 2. gh4 dc5",
          "ed4 fg5 gh4 dc5".some
        ),
        StartingPosition(
          "44-IV",
          "W:W18,21,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,16",
          "1. ed4 hg5",
          "ed4 hg5".some
        ),
        StartingPosition(
          "44-V",
          "W:W17,18,21,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,15,16",
          "1. ed4 hg5 2. cb4 de5",
          "ed4 hg5 cb4 de5".some
        ),
        StartingPosition(
          "44-VI",
          "B:W19,21,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,12",
          "1. ef4",
          "ef4".some
        ),
        StartingPosition(
          "44-VII",
          "B:W17,19,21,23,24,25,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,12,14,16",
          "1. ef4 bc5 2. cb4 fg5 3. de3",
          "ef4 bc5 cb4 fg5 de3".some
        ),
        StartingPosition(
          "44-VIII",
          "W:W19,21,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,16",
          "1. ef4 fg5",
          "ef4 fg5".some
        ),
        StartingPosition(
          "44-IX",
          "W:W17,19,21,24,25,27,28,29,30,31,32:B1,2,3,4,5,6,8,9,10,11,12",
          "1. ef4 fg5 2. cb4 ge3 3. df4 ef6",
          "ef4 fg5 cb4 ge3 df4 ef6".some
        ),
        StartingPosition(
          "44-X",
          "B:W19,21,22,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,12",
          "1. gf4",
          "gf4".some
        ),
        StartingPosition(
          "44-XI",
          "W:W19,21,22,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,13",
          "1. gf4 ba5",
          "gf4 ba5".some
        ),
        StartingPosition(
          "44-XII",
          "W:W18,19,21,23,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,12,16",
          "1. gf4 ba5 2. cb4 ac3 3. bd4 fg5",
          "gf4 ba5 cb4 ac3 bd4 fg5".some
        ),
        StartingPosition(
          "44-XIII",
          "W:W17,19,21,23,25,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,11,12,15",
          "1. gf4 ba5 2. cb4 ac3 3. db4 de5",
          "gf4 ba5 cb4 ac3 db4 de5".some
        ),
        StartingPosition(
          "44-XIV",
          "W:W17,19,21,23,25,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,12,16",
          "1. gf4 ba5 2. cb4 ac3 3. db4 fg5",
          "gf4 ba5 cb4 ac3 db4 fg5".some
        ),
        StartingPosition(
          "44-XV",
          "W:W19,21,22,23,24,25,26,27,29,30,31,32:B1,2,3,4,5,6,7,8,10,12,13,16",
          "1. gf4 ba5 2. hg3 fg5",
          "gf4 ba5 hg3 fg5".some
        ),
        StartingPosition(
          "44-XVI",
          "W:W19,20,21,22,23,25,26,27,29,30,31,32:B1,2,3,4,6,7,8,10,11,12,13,14",
          "1. gf4 bc5 2. hg3 ab6 3. gh4 ba5",
          "gf4 bc5 hg3 ab6 gh4 ba5".some
        )
      )
    ),
    Category(
      "45",
      List(
        StartingPosition(
          "45-I",
          "B:W18,19,21,23,24,25,26,27,29,30,31,32:B1,2,3,4,5,6,7,8,10,12,14,15",
          "1. gf4 bc5 2. hg3 fe5 3. cd4",
          "gf4 bc5 hg3 fe5 cd4".some
        ),
        StartingPosition(
          "45-II",
          "W:W19,21,22,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,14",
          "1. gf4 dc5",
          "gf4 dc5".some
        ),
        StartingPosition(
          "45-III",
          "W:W17,19,21,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,12,14,16",
          "1. gf4 dc5 2. cb4 fg5",
          "gf4 dc5 cb4 fg5".some
        ),
        StartingPosition(
          "45-IV",
          "W:W19,21,22,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,15",
          "1. gf4 fe5",
          "gf4 fe5".some
        ),
        StartingPosition(
          "45-V",
          "B:W19,21,22,23,24,25,26,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,15",
          "1. gf4 fe5 2. fg3",
          "gf4 fe5 fg3".some
        ),
        StartingPosition(
          "45-VI",
          "W:W17,19,21,23,24,25,26,27,29,30,31,32:B1,2,3,4,6,7,8,9,10,12,14,15",
          "1. gf4 fe5 2. hg3 bc5 3. cb4 ab6",
          "gf4 fe5 hg3 bc5 cb4 ab6".some
        ),
        StartingPosition(
          "45-VII",
          "B:W14,19,21,22,24,25,26,27,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12",
          "1. gf4 fe5 2. hg3 ed4 3. ec5",
          "gf4 fe5 hg3 ed4 ec5".some
        ),
        StartingPosition(
          "45-VIII",
          "W:W20,21,22,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,13",
          "1. gh4 ba5",
          "gh4 ba5".some
        ),
        StartingPosition(
          "45-IX",
          "B:W19,20,21,22,24,25,26,27,29,30,31,32:B1,2,3,4,6,7,8,9,11,12,13,14",
          "1. gh4 ba5 2. hg3 ab6 3. ef4 dc5",
          "gh4 ba5 hg3 ab6 ef4 dc5".some
        ),
        StartingPosition(
          "45-X",
          "W:W20,21,22,23,24,25,26,27,28,29,30,31:B2,3,4,5,6,7,8,9,10,11,12,13",
          "1. gh4 ba5 2. hg3 ab6 3. gh2 ba7",
          "gh4 ba5 hg3 ab6 gh2 ba7".some
        ),
        StartingPosition(
          "45-XI",
          "W:W20,21,22,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,14",
          "1. gh4 bc5",
          "gh4 bc5".some
        ),
        StartingPosition(
          "45-XII",
          "B:W17,19,21,23,24,25,26,27,29,30,31,32:B1,2,3,4,5,6,7,9,10,11,12,15",
          "1. gf4 fe5 2. hg3 gf6 3. cb4",
          "gf4 fe5 hg3 gf6 cb4".some
        ),
        StartingPosition(
          "45-XIII",
          "B:W18,19,21,22,24,25,26,27,29,30,31,32:B1,2,3,4,5,6,7,9,10,11,12,15",
          "1. gf4 fe5 2. hg3 gf6 3. ed4",
          "gf4 fe5 hg3 gf6 ed4".some
        ),
        StartingPosition(
          "45-XIV",
          "W:W17,19,21,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,12,15,16",
          "1. gf4 fg5 2. cb4 de5",
          "gf4 fg5  cb4 de5".some
        ),
        StartingPosition(
          "45-XV",
          "W:W17,19,21,22,23,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,9,11,12,15,16",
          "1. gf4 fg5 2. cb4 gf6 3. bc3 de5",
          "gf4 fg5 cb4 gf6 bc3 de5".some
        ),
        StartingPosition(
          "45-XVI",
          "B:W20,21,22,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,12",
          "1. gh4",
          "gh4".some
        )
      )
    ),
    Category(
      "46",
      List(
        StartingPosition(
          "46-I",
          "W:W20,21,22,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,14",
          "1. gh4 dc5",
          "gh4 dc5".some
        ),
        StartingPosition(
          "46-II",
          "W:W19,20,21,22,25,26,27,28,29,30,31,32:B1,2,3,4,5,7,8,9,10,11,12,14",
          "1. gh4 dc5 2. ef4 cd6",
          "gh4 dc5 ef4 cd6".some
        ),
        StartingPosition(
          "46-III",
          "W:W20,21,22,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,15",
          "1. gh4 de5",
          "gh4 de5".some
        ),
        StartingPosition(
          "46-IV",
          "B:W20,21,22,23,24,25,26,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,15",
          "1. gh4 de5 2. fg3",
          "gh4 de5 fg3".some
        ),
        StartingPosition(
          "46-V",
          "W:W20,21,22,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,15",
          "1. gh4 fe5",
          "gh4 fe5".some
        ),
        StartingPosition(
          "46-VI",
          "W:W17,20,22,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,18",
          "1. gh4 fe5 2. ab4 ed4",
          "gh4 fe5 ab4 ed4".some
        ),
        StartingPosition(
          "46-VII",
          "W:W17,20,21,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,19",
          "1. gh4 fe5 2. cb4 ef4",
          "gh4 fe5 cb4 ef4".some
        ),
        StartingPosition(
          "46-VIII",
          "W:W18,20,21,22,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,12,14,15",
          "1. gh4 fe5 2. ed4 bc5",
          "gh4 fe5  ed4 bc5".some
        ),
        StartingPosition(
          "46-IX",
          "W:W18,20,21,22,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,8,9,10,11,12,15",
          "1. gh4 fe5 2. ed4 ef6",
          "gh4 fe5 ed4 ef6".some
        ),
        StartingPosition(
          "46-X",
          "W:W18,20,21,22,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,9,10,11,12,15",
          "1. gh4 fe5 2. ed4 gf6",
          "gh4 fe5 ed4 gf6".some
        ),
        StartingPosition(
          "46-XI",
          "W:W18,20,21,22,24,25,26,28,29,30,31,32:B1,2,3,4,5,6,7,9,11,12,14,15",
          "1. gh4 fe5 2. ed4 gf6 3. fg3 dc5",
          "gh4 fe5 ed4 gf6 fg3 dc5".some
        ),
        StartingPosition(
          "46-XII",
          "B:W20,21,22,23,24,25,26,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,15",
          "1. gh4 fe5 2. fg3",
          "gh4 fe5 fg3".some
        ),
        StartingPosition(
          "46-XIII",
          "W:W20,21,22,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,16",
          "1. gh4 fg5",
          "gh4 fg5".some
        ),
        StartingPosition(
          "46-XIV",
          "W:W18,20,21,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,14,16",
          "1. gh4 hg5 2. cd4 dc5",
          "gh4 hg5 cd4 dc5".some
        ),
        StartingPosition(
          "46-XV",
          "B:W18,20,21,23,24,25,26,28,29,30,31,32:B1,2,3,4,5,6,7,9,10,11,12,16",
          "1. gh4 hg5 2. cd4 gh6 3. fg3",
          "gh4 hg5  cd4 gh6 fg3".some
        ),
        StartingPosition(
          "46-XVI",
          "W:W20,21,22,23,24,25,26,27,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,15,16",
          "1. gh4 hg5 2. hg3 de5",
          "gh4 hg5 hg3 de5".some
        )
      )
    )
  )

  private val categoriesFMJDBrazilian = categoriesFMJD.map {
    case cat if cat.name == "1" =>
      cat.copy(positions = cat.positions.filterNot(_.code == "1-IX"))
    case cat if cat.name == "4" =>
      cat.copy(positions = cat.positions.filterNot(_.code == "4-XV"))
    case cat if cat.name == "5" =>
      cat.copy(positions = cat.positions.filterNot(p => p.code == "5-IX" || p.code == "5-XI"))
    case cat if cat.name == "19" =>
      cat.copy(positions = cat.positions.filterNot(_.code == "19-V"))
    case cat if cat.name == "25" =>
      cat.copy(positions = cat.positions.filterNot(_.code == "25-XIII"))
    case cat if cat.name == "30" =>
      cat.copy(positions = cat.positions.filterNot(_.code == "30-XVI"))
    case cat if cat.name == "40" =>
      cat.copy(positions = cat.positions.filterNot(_.code == "40-XIV"))
    case cat if cat.name == "41" =>
      cat.copy(positions = cat.positions.filterNot(_.code == "41-XIII"))
    case cat if cat.name == "42" =>
      cat.copy(positions = cat.positions.filterNot(_.code == "42-XIV"))
    case cat if cat.name == "43" =>
      cat.copy(positions = cat.positions.filterNot(_.code == "43-II"))
    case cat => cat
  }

  private val categoriesIDFBasic = List(
    Category(
      "1",
      List(
        StartingPosition(
          "1-I",
          "B:W14,21,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,13",
          "1. cd4 ba5 2. dc5",
          "cd4 ba5 dc5".some
        ),
        StartingPosition(
          "1-II",
          "B:W19,21,22,23,24,25,26,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,14",
          "1. ef4 dc5 2. fe3",
          "ef4 dc5 fe3".some
        ),
        StartingPosition(
          "1-III",
          "W:W20,21,22,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,16",
          "1. gh4 hg5",
          "gh4 hg5".some
        ),
        StartingPosition(
          "1-IV",
          "W:W19,21,22,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,15",
          "1. gf4 de5",
          "gf4 de5".some
        ),
        StartingPosition(
          "1-V",
          "B:W18,21,22,23,24,25,26,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,16",
          "1. ed4 fg5 2. fe3",
          "ed4 fg5 fe3".some
        ),
        StartingPosition(
          "1-VI",
          "W:W17,21,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,15",
          "1. cb4 de5",
          "cb4 de5".some
        ),
        StartingPosition(
          "1-VII",
          "W:W20,21,22,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,16",
          "1. gh4 fg5",
          "gh4 fg5".some
        ),
        StartingPosition(
          "1-VIII",
          "B:W18,19,21,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,14",
          "1. gf4 bc5 2. cd4",
          "gf4 bc5 cd4".some
        ),
        StartingPosition(
          "1-IX",
          "B:W13,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,16",
          "1. ab4 hg5 2. ba5",
          "ab4 hg5 ba5".some
        ),
        StartingPosition(
          "1-X",
          "B:W17,20,21,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,16",
          "1. gh4 hg5 2. cb4",
          "gh4 hg5 cb4".some
        )
      )
    ),
    Category(
      "2",
      List(
        StartingPosition(
          "2-I",
          "B:W20,21,22,23,24,25,26,27,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,13",
          "1. gh4 ba5 2. hg3",
          "gh4 ba5 hg3".some
        ),
        StartingPosition(
          "2-II",
          "B:W18,20,21,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,16",
          "1. cd4 hg5 2. gh4",
          "cd4 hg5 gh4".some
        ),
        StartingPosition(
          "2-III",
          "B:W17,19,21,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,16",
          "1. ef4 fg5 2. cb4",
          "ef4 fg5 cb4".some
        ),
        StartingPosition(
          "2-IV",
          "B:W13,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,15",
          "1. ef4 fe5 2. ab4",
          "ef4 fe5 ab4".some
        ),
        StartingPosition(
          "2-V",
          "B:W13,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,15",
          "1. ab4 fe5 2. ba5",
          "ab4 fe5 ba5".some
        ),
        StartingPosition(
          "2-VI",
          "W:W19,21,22,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,14",
          "1. gf4 bc5",
          "gf4 bc5".some
        ),
        StartingPosition(
          "2-VII",
          "B:W17,19,22,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,16",
          "1. ab4 hg5 2. gf4",
          "ab4 hg5 gf4".some
        ),
        StartingPosition(
          "2-VIII",
          "W:W18,21,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,15",
          "1. ed4 fe5",
          "ed4 fe5".some
        ),
        StartingPosition(
          "2-IX",
          "B:W17,20,21,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,14",
          "1. gh4 dc5 2. cb4",
          "gh4 dc5 cb4".some
        ),
        StartingPosition(
          "2-X",
          "B:W18,19,21,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,13",
          "1. gf4 ba5 2. cd4",
          "gf4 ba5 cd4".some
        )
      )
    ),
    Category(
      "3",
      List(
        StartingPosition(
          "3-I",
          "B:W14,21,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,16",
          "1. cd4 fg5 2. dc5",
          "cd4 fg5 dc5".some
        ),
        StartingPosition(
          "3-II",
          "B:W20,21,22,23,24,25,26,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,14",
          "1. gh4 dc5 2. fg3",
          "gh4 dc5 fg3".some
        ),
        StartingPosition(
          "3-III",
          "B:W19,21,22,23,24,25,26,27,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,15",
          "1. gf4 fe5 2. hg3",
          "gf4 fe5 hg3".some
        ),
        StartingPosition(
          "3-IV",
          "W:W17,21,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,16",
          "1. cb4 hg5",
          "cb4 hg5".some
        ),
        StartingPosition(
          "3-V",
          "W:W17,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,15",
          "1. ab4 de5",
          "ab4 de5".some
        ),
        StartingPosition(
          "3-VI",
          "B:W17,20,21,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,15",
          "1. gh4 de5 2. cb4",
          "gh4 de5 cb4".some
        ),
        StartingPosition(
          "3-VII",
          "W:W19,21,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,13",
          "1. ef4 ba5",
          "ef4 ba5".some
        ),
        StartingPosition(
          "3-VIII",
          "W:W17,21,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,16",
          "1. cb4 fg5",
          "cb4 fg5".some
        ),
        StartingPosition(
          "3-IX",
          "B:W20,21,22,23,24,25,26,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,14",
          "1. gh4 bc5 2. fg3",
          "gh4 bc5 fg3".some
        ),
        StartingPosition(
          "3-X",
          "B:W19,21,22,23,24,25,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,14",
          "1. ef4 dc5 2. de3",
          "ef4 dc5 de3".some
        )
      )
    ),
    Category(
      "4",
      List(
        StartingPosition(
          "4-I",
          "B:W17,21,22,23,24,25,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,15",
          "1. cb4 fe5 2. dc3",
          "cb4 fe5 dc3".some
        ),
        StartingPosition(
          "4-II",
          "B:W18,20,21,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,14",
          "1. gh4 bc5 2. cd4",
          "gh4 bc5 cd4".some
        ),
        StartingPosition(
          "4-III",
          "B:W14,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,16",
          "1. ab4 fg5 2. bc5",
          "ab4 fg5 bc5".some
        ),
        StartingPosition(
          "4-IV",
          "B:W18,20,21,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,14",
          "1. cd4 dc5 2. gh4",
          "cd4 dc5 gh4".some
        ),
        StartingPosition(
          "4-V",
          "B:W17,20,22,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,15",
          "1. ab4 fe5 2. gh4",
          "ab4 fe5 gh4".some
        ),
        StartingPosition(
          "4-VI",
          "W:W20,21,22,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,13",
          "1. gh4 ba5",
          "gh4 ba5".some
        ),
        StartingPosition(
          "4-VII",
          "B:W14,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,15",
          "1. ab4 fe5 2. bc5",
          "ab4 fe5 bc5".some
        ),
        StartingPosition(
          "4-VIII",
          "B:W19,21,22,23,24,25,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,15",
          "1. ef4 fe5 2. de3",
          "ef4 fe5 de3".some
        ),
        StartingPosition(
          "4-IX",
          "B:W17,20,22,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,13",
          "1. ab4 ba5 2. gh4",
          "ab4 ba5 gh4".some
        ),
        StartingPosition(
          "4-X",
          "B:W20,21,22,23,24,25,26,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,15",
          "1. gh4 de5 2. fg3",
          "gh4 de5 fg3".some
        )
      )
    ),
    Category(
      "5",
      List(
        StartingPosition(
          "5-I",
          "W:W19,21,22,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,16",
          "1. gf4 fg5",
          "gf4 fg5".some
        ),
        StartingPosition(
          "5-II",
          "W:W20,21,22,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,14",
          "1. gh4 bc5",
          "gh4 bc5".some
        ),
        StartingPosition(
          "5-III",
          "B:W17,21,22,23,24,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,13",
          "1. ab4 ba5 2. ba3",
          "ab4 ba5 ba3".some
        ),
        StartingPosition(
          "5-IV",
          "B:W13,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,15",
          "1. ab4 de5 2. ba5",
          "ab4 de5 ba5".some
        ),
        StartingPosition(
          "5-V",
          "W:W18,21,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,16",
          "1. cd4 hg5",
          "cd4 hg5".some
        ),
        StartingPosition(
          "5-VI",
          "W:W18,21,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,16",
          "1. cd4 fg5",
          "cd4 fg5".some
        ),
        StartingPosition(
          "5-VII",
          "W:W21,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,12",
          "1. Free",
          "Free".some
        ),
        StartingPosition(
          "5-VIII",
          "B:W20,21,22,23,24,25,26,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,16",
          "1. gh4 hg5 2. fg3",
          "gh4 hg5 fg3".some
        ),
        StartingPosition(
          "5-IX",
          "B:W17,21,22,23,24,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,16",
          "1. ab4 fg5 2. ba3",
          "ab4 fg5 ba3".some
        ),
        StartingPosition(
          "5-X",
          "B:W20,21,22,23,24,25,26,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,13",
          "1. gh4 ba5 2. fg3",
          "gh4 ba5 fg3".some
        )
      )
    ),
    Category(
      "6",
      List(
        StartingPosition(
          "6-I",
          "W:W17,21,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,14",
          "1. cb4 bc5",
          "cb4 bc5".some
        ),
        StartingPosition(
          "6-II",
          "B:W19,21,22,23,24,25,26,27,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,13",
          "1. gf4 ba5 2. hg3",
          "gf4 ba5 hg3".some
        ),
        StartingPosition(
          "6-III",
          "B:W17,21,22,23,24,25,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,13",
          "1. cb4 ba5 2. dc3",
          "cb4 ba5 dc3".some
        ),
        StartingPosition(
          "6-IV",
          "W:W18,21,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,16",
          "1. ed4 fg5",
          "ed4 fg5".some
        ),
        StartingPosition(
          "6-V",
          "B:W17,19,21,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,14",
          "1. ef4 dc5 2. cb4",
          "ef4 dc5 cb4".some
        ),
        StartingPosition(
          "6-VI",
          "B:W18,21,22,23,24,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,15",
          "1. cd4 de5 2. bc3",
          "cd4 de5 bc3".some
        ),
        StartingPosition(
          "6-VII",
          "W:W19,21,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,16",
          "1. ef4 fg5",
          "ef4 fg5".some
        ),
        StartingPosition(
          "6-VIII",
          "B:W17,20,22,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,15",
          "1. ab4 de5 2. gh4",
          "ab4 de5 gh4".some
        ),
        StartingPosition(
          "6-IX",
          "B:W17,18,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,15",
          "1. ed4 de5 2. ab4",
          "ed4 de5 ab4".some
        ),
        StartingPosition(
          "6-X",
          "B:W17,21,22,23,24,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,15",
          "1. cb4 fe5 2. bc3",
          "cb4 fe5 bc3".some
        )
      )
    ),
    Category(
      "7",
      List(
        StartingPosition(
          "7-I",
          "B:W18,21,22,23,24,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,16",
          "1. cd4 fg5 2. bc3",
          "cd4 fg5 bc3".some
        ),
        StartingPosition(
          "7-II",
          "W:W18,21,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,15",
          "1. cd4 fe5",
          "cd4 fe5".some
        ),
        StartingPosition(
          "7-III",
          "B:W18,21,22,23,24,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,16",
          "1. cd4 hg5 2. bc3",
          "cd4 hg5 bc3".some
        ),
        StartingPosition(
          "7-IV",
          "W:W19,21,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,14",
          "1. ef4 bc5",
          "ef4 bc5".some
        ),
        StartingPosition(
          "7-V",
          "B:W20,21,22,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,12",
          "1. gh4",
          "gh4".some
        ),
        StartingPosition(
          "7-VI",
          "W:W17,21,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,14",
          "1. cb4 dc5",
          "cb4 dc5".some
        ),
        StartingPosition(
          "7-VII",
          "B:W14,21,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,15",
          "1. cb4 fe5 2. bc5",
          "cb4 fe5 bc5".some
        ),
        StartingPosition(
          "7-VIII",
          "B:W18,21,22,23,24,25,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,16",
          "1. ed4 fg5 2. de3",
          "ed4 fg5 de3".some
        ),
        StartingPosition(
          "7-IX",
          "W:W18,21,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,16",
          "1. ed4 hg5",
          "ed4 hg5".some
        ),
        StartingPosition(
          "7-X",
          "B:W18,21,22,23,24,25,26,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,14",
          "1. ed4 dc5 2. fe3",
          "ed4 dc5 fe3".some
        )
      )
    ),
    Category(
      "8",
      List(
        StartingPosition(
          "8-I",
          "W:W17,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,16",
          "1. ab4 fg5",
          "ab4 fg5".some
        ),
        StartingPosition(
          "8-II",
          "W:W18,21,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,15",
          "1. cd4 de5",
          "cd4 de5".some
        ),
        StartingPosition(
          "8-III",
          "B:W17,21,22,23,24,25,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,16",
          "1. cb4 fg5 2. dc3",
          "cb4 fg5 dc3".some
        ),
        StartingPosition(
          "8-IV",
          "B:W19,21,22,23,24,25,26,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,15",
          "1. ef4 fe5 2. fe3",
          "ef4 fe5 fe3".some
        ),
        StartingPosition(
          "8-V",
          "B:W18,21,22,23,24,25,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,14",
          "1. ed4 dc5 2. de3",
          "ed4 dc5 de3".some
        ),
        StartingPosition(
          "8-VI",
          "B:W19,21,22,23,24,25,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,14",
          "1. ef4 bc5 2. de3",
          "ef4 bc5 de3".some
        ),
        StartingPosition(
          "8-VII",
          "W:W18,21,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,14",
          "1. ed4 bc5",
          "ed4 bc5".some
        ),
        StartingPosition(
          "8-VIII",
          "W:W18,21,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,14",
          "1. cd4 dc5",
          "cd4 dc5".some
        ),
        StartingPosition(
          "8-IX",
          "B:W13,21,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,14",
          "1. cb4 bc5 2. ba5",
          "cb4 bc5 ba5".some
        ),
        StartingPosition(
          "8-X",
          "B:W17,19,21,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,16",
          "1. gf4 fg5 2. cb4",
          "gf4 fg5 cb4".some
        )
      )
    ),
    Category(
      "9",
      List(
        StartingPosition(
          "9-I",
          "W:W17,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,15",
          "1. ab4 fe5",
          "ab4 fe5".some
        ),
        StartingPosition(
          "9-II",
          "B:W17,19,21,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,15",
          "1. ef4 fe5 2. cb4",
          "ef4 fe5 cb4".some
        ),
        StartingPosition(
          "9-III",
          "B:W13,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,16",
          "1. ab4 fg5 2. ba5",
          "ab4 fg5 ba5".some
        ),
        StartingPosition(
          "9-IV",
          "B:W17,21,22,23,24,25,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,14",
          "1. cb4 bc5 2. dc3",
          "cb4 bc5 dc3".some
        ),
        StartingPosition(
          "9-V",
          "W:W18,21,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,14",
          "1. ed4 dc5",
          "ed4 dc5".some
        ),
        StartingPosition(
          "9-VI",
          "B:W13,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,14",
          "1. ab4 bc5 2. ba5",
          "ab4 bc5 ba5".some
        ),
        StartingPosition(
          "9-VII",
          "W:W19,21,22,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,15",
          "1. gf4 fe5",
          "gf4 fe5".some
        ),
        StartingPosition(
          "9-VIII",
          "B:W19,21,22,23,24,25,26,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,14",
          "1. gf4 bc5 2. fg3",
          "gf4 bc5 fg3".some
        ),
        StartingPosition(
          "9-IX",
          "W:W20,21,22,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,14",
          "1. gh4 dc5",
          "gh4 dc5".some
        ),
        StartingPosition(
          "9-X",
          "B:W19,21,22,23,24,25,26,27,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,14",
          "1. gf4 bc5 2. hg3",
          "gf4 bc5 hg3".some
        )
      )
    ),
    Category(
      "10",
      List(
        StartingPosition(
          "10-I",
          "W:W18,21,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,13",
          "1. cd4 ba5",
          "cd4 ba5".some
        ),
        StartingPosition(
          "10-II",
          "B:W19,21,22,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,12",
          "1. gf4",
          "gf4".some
        ),
        StartingPosition(
          "10-III",
          "W:W17,21,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,15",
          "1. cb4 fe5",
          "cb4 fe5".some
        ),
        StartingPosition(
          "10-IV",
          "B:W18,19,21,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,15",
          "1. cd4 de5 2. gf4",
          "cd4 de5 gf4".some
        ),
        StartingPosition(
          "10-V",
          "B:W18,20,21,22,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,16",
          "1. ed4 hg5 2. gh4",
          "ed4 hg5 gh4".some
        ),
        StartingPosition(
          "10-VI",
          "W:W19,21,22,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,13",
          "1. gf4 ba5",
          "gf4 ba5".some
        ),
        StartingPosition(
          "10-VII",
          "B:W18,19,21,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,16",
          "1. cd4 hg5 2. gf4",
          "cd4 hg5 gf4".some
        ),
        StartingPosition(
          "10-VIII",
          "B:W17,19,21,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,15",
          "1. cb4 de5 2. ef4",
          "cb4 de5 ef4".some
        ),
        StartingPosition(
          "10-IX",
          "B:W17,20,21,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,14",
          "1. gh4 bc5 2. cb4",
          "gh4 bc5 cb4".some
        ),
        StartingPosition(
          "10-X",
          "W:W18,21,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,14",
          "1. cd4 bc5",
          "cd4 bc5".some
        )
      )
    ),
    Category(
      "11",
      List(
        StartingPosition(
          "11-I",
          "B:W19,21,22,23,24,25,26,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,13",
          "1. ef4 ba5 2. fe3",
          "ef4 ba5 fe3".some
        ),
        StartingPosition(
          "11-II",
          "W:W19,21,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,15",
          "1. ef4 de5",
          "ef4 de5".some
        ),
        StartingPosition(
          "11-III",
          "B:W17,18,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,13",
          "1. ed4 ba5 2. ab4",
          "ed4 ba5 ab4".some
        ),
        StartingPosition(
          "11-IV",
          "B:W13,21,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,16",
          "1. cb4 hg5 2. ba5",
          "cb4 hg5 ba5".some
        ),
        StartingPosition(
          "11-V",
          "B:W17,21,22,23,24,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,16",
          "1. ab4 hg5 2. ba3",
          "ab4 hg5 ba3".some
        ),
        StartingPosition(
          "11-VI",
          "B:W17,20,22,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,16",
          "1. ab4 hg5 2. gh4",
          "ab4 hg5 gh4".some
        ),
        StartingPosition(
          "11-VII",
          "B:W14,21,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,13",
          "1. ed4 ba5 2. dc5",
          "ed4 ba5 dc5".some
        ),
        StartingPosition(
          "11-VIII",
          "W:W17,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,13",
          "1. ab4 ba5",
          "ab4 ba5".some
        ),
        StartingPosition(
          "11-IX",
          "B:W18,19,21,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,13",
          "1. ef4 ba5 2. cd4",
          "ef4 ba5 cd4".some
        ),
        StartingPosition(
          "11-X",
          "B:W19,21,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,12",
          "1. ef4",
          "ef4".some
        )
      )
    ),
    Category(
      "12",
      List(
        StartingPosition(
          "12-I",
          "B:W18,20,21,22,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,15",
          "1. gh4 fe5 2. ed4",
          "gh4 fe5 ed4".some
        ),
        StartingPosition(
          "12-II",
          "B:W17,19,21,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,13",
          "1. gf4 ba5 2. cb4",
          "gf4 ba5 cb4".some
        ),
        StartingPosition(
          "12-III",
          "W:W17,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,16",
          "1. ab4 hg5",
          "ab4 hg5".some
        ),
        StartingPosition(
          "12-IV",
          "W:W17,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,14",
          "1. ab4 bc5",
          "ab4 bc5".some
        ),
        StartingPosition(
          "12-V",
          "B:W18,21,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,12",
          "1. cd4",
          "cd4".some
        ),
        StartingPosition(
          "12-VI",
          "B:W17,20,21,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,16",
          "1. cb4 fg5 2. gh4",
          "cb4 fg5 gh4".some
        ),
        StartingPosition(
          "12-VII",
          "B:W20,21,22,23,24,25,26,27,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,14",
          "1. gh4 dc5 2. hg3",
          "gh4 dc5 hg3".some
        ),
        StartingPosition(
          "12-VIII",
          "B:W18,21,22,23,24,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,13",
          "1. cd4 ba5 2. bc3",
          "cd4 ba5 bc3".some
        ),
        StartingPosition(
          "12-IX",
          "B:W17,21,22,23,24,25,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,15",
          "1. cb4 de5 2. dc3",
          "cb4 de5 dc3".some
        ),
        StartingPosition(
          "12-X",
          "B:W14,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,16",
          "1. ab4 hg5 2. bc5",
          "ab4 hg5 bc5".some
        )
      )
    ),
    Category(
      "13",
      List(
        StartingPosition(
          "13-I",
          "B:W18,21,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,12",
          "1. ed4",
          "ed4".some
        ),
        StartingPosition(
          "13-II",
          "B:W17,19,21,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,14",
          "1. ef4 bc5 2. cb4",
          "ef4 bc5 cb4".some
        ),
        StartingPosition(
          "13-III",
          "W:W19,21,22,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,14",
          "1. gf4 dc5",
          "gf4 dc5".some
        ),
        StartingPosition(
          "13-IV",
          "B:W18,19,21,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,16",
          "1. gf4 fg5 2. cd4",
          "gf4 fg5 cd4".some
        ),
        StartingPosition(
          "13-V",
          "W:W19,21,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,15",
          "1. ef4 fe5",
          "ef4 fe5".some
        ),
        StartingPosition(
          "13-VI",
          "B:W13,21,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,15",
          "1. cb4 fe5 2. ba5",
          "cb4 fe5 ba5".some
        ),
        StartingPosition(
          "13-VII",
          "W:W18,21,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,13",
          "1. ed4 ba5",
          "ed4 ba5".some
        ),
        StartingPosition(
          "13-VIII",
          "W:W17,21,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,13",
          "1. cb4 ba5",
          "cb4 ba5".some
        ),
        StartingPosition(
          "13-IX",
          "B:W20,21,22,23,24,25,26,27,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,16",
          "1. gh4 hg5 2. hg3",
          "gh4 hg5 hg3".some
        ),
        StartingPosition(
          "13-X",
          "B:W20,21,22,23,24,25,26,27,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,15",
          "1. gh4 de5 2. hg3",
          "gh4 de5 hg3".some
        )
      )
    ),
    Category(
      "14",
      List(
        StartingPosition(
          "14-I",
          "B:W17,19,21,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,14",
          "1. gf4 dc5 2. cb4",
          "gf4 dc5 cb4".some
        ),
        StartingPosition(
          "14-II",
          "B:W17,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,12",
          "1. ab4",
          "ab4".some
        ),
        StartingPosition(
          "14-III",
          "B:W18,21,22,23,24,25,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,15",
          "1. cd4 de5 2. dc3",
          "cd4 de5 dc3".some
        ),
        StartingPosition(
          "14-IV",
          "B:W17,18,21,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,16",
          "1. ed4 fg5 2. cb4",
          "ed4 fg5 cb4".some
        ),
        StartingPosition(
          "14-V",
          "B:W17,21,22,23,24,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,15",
          "1. cb4 de5 2. bc3",
          "cb4 de5 bc3".some
        ),
        StartingPosition(
          "14-VI",
          "B:W18,19,21,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,14",
          "1. gf4 dc5 2. cd4",
          "gf4 dc5 cd4".some
        ),
        StartingPosition(
          "14-VII",
          "W:W18,21,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,15",
          "1. ed4 de5",
          "ed4 de5".some
        ),
        StartingPosition(
          "14-VIII",
          "B:W20,21,22,23,24,25,26,27,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,14",
          "1. gh4 bc5 2. hg3",
          "gh4 bc5 hg3".some
        ),
        StartingPosition(
          "14-IX",
          "W:W19,21,22,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,14",
          "1. ef4 dc5",
          "ef4 dc5".some
        ),
        StartingPosition(
          "14-X",
          "B:W17,19,21,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,14",
          "1. gf4 bc5 2. cb4",
          "gf4 bc5 cb4".some
        )
      )
    ),
    Category(
      "15",
      List(
        StartingPosition(
          "15-I",
          "W:W17,22,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,14",
          "1. ab4 dc5",
          "ab4 dc5".some
        ),
        StartingPosition(
          "15-II",
          "W:W20,21,22,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,15",
          "1. gh4 fe5",
          "gh4 fe5".some
        ),
        StartingPosition(
          "15-III",
          "B:W13,21,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,15",
          "1. cb4 de5 2. ba5",
          "cb4 de5 ba5".some
        ),
        StartingPosition(
          "15-IV",
          "B:W18,20,21,22,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,16",
          "1. ed4 fg5 2. gh4",
          "ed4 fg5 gh4".some
        ),
        StartingPosition(
          "15-V",
          "W:W20,21,22,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,15",
          "1. gh4 de5",
          "gh4 de5".some
        ),
        StartingPosition(
          "15-VI",
          "B:W18,20,21,22,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,11,12,15",
          "1. ed4 de5 2. gh4",
          "ed4 de5 gh4".some
        ),
        StartingPosition(
          "15-VII",
          "B:W17,21,23,24,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,12",
          "1. cb4",
          "cb4".some
        ),
        StartingPosition(
          "15-VIII",
          "B:W17,21,22,23,24,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,10,11,12,14",
          "1. cb4 bc5 2. bc3",
          "cb4 bc5 bc3".some
        ),
        StartingPosition(
          "15-IX",
          "B:W17,19,21,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,11,16",
          "1. cb4 hg5 2. gf4",
          "cb4 hg5 gf4".some
        ),
        StartingPosition(
          "15-X",
          "B:W17,20,21,23,25,26,27,28,29,30,31,32:B1,2,3,4,5,6,7,8,9,10,12,15",
          "1. gh4 fe5 2. cb4",
          "gh4 fe5 cb4".some
        )
      )
    )
  )

  val tableFMJD = OpeningTable(
    key = "fmjd",
    name = "FMJD Drawing Table 64",
    url = "https://results.fmjd.org/viewpage.php?page_id=2",
    categories = categoriesFMJD
  )

  val tableFMJDBrazilian = OpeningTable(
    key = "fmjdBrazilian",
    name = "FMJD Drawing Table 64 - Brazilian",
    url = "https://results.fmjd.org/viewpage.php?page_id=2",
    categories = categoriesFMJDBrazilian
  )

  val tableIDFBasic = OpeningTable(
    key = "idfBasic",
    name = "IDF Drawing Table 64 - Basic Positions",
    url = "https://idf64.org/tables-of-draw/",
    categories = categoriesIDFBasic
  )

  private val allTables = List(tableFMJD, tableFMJDBrazilian, tableIDFBasic)
  private val key2table: Map[String, OpeningTable] = allTables
    .map { p =>
      p.key -> p
    }
    .to(Map)

  def byKey = key2table.get _

}
