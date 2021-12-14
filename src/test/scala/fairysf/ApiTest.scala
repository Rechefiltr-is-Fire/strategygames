package strategygames.fairysf

import org.specs2.matcher.ValidatedMatchers
import org.specs2.mutable.Specification

class FairyStockfishApiTest extends Specification with ValidatedMatchers {

  "Shogi initial fen" should {
    "be valid" in {
      Api.validateFEN(
        variant.Shogi.fairysfName.name,
        variant.Shogi.initialFen.value
      ) must_== true
    }
    "return confirm sufficient material for both sides" in {
      Api.insufficientMaterial(
        variant.Shogi.fairysfName.name,
        variant.Shogi.initialFen.value
      ) must_== ((false, false))
    }
    "have legal moves" in {
      Api.legalMoves(
        variant.Shogi.fairysfName.name,
        variant.Shogi.initialFen.value
      ).size must_!= 0L
    }
    "have 40 pieces" in {
      Api.pieceMapFromFen(
        variant.Shogi.fairysfName.name,
        variant.Shogi.gameFamily,
        variant.Shogi.initialFen.value
      ).size must_== 40
    }
    "not be game end" in {
      Api.gameEnd(
        variant.Shogi.fairysfName.name,
        variant.Shogi.initialFen.value
      ) must_== false
    }
    "not be optional game end" in {
      Api.optionalGameEnd(
        variant.Shogi.fairysfName.name,
        variant.Shogi.initialFen.value
      ) must_== false
    }
  }

  "Xiangqi initial fen" should {
    "equal expected initialfen" in {
      variant.Xiangqi.initialFen.value must_== "rnbakabnr/9/1c5c1/p1p1p1p1p/9/9/P1P1P1P1P/1C5C1/9/RNBAKABNR w - - 0 1"
    }
    "be valid" in {
      Api.validateFEN(
        variant.Xiangqi.fairysfName.name,
        variant.Xiangqi.initialFen.value
      ) must_== true
    }
    "return confirm sufficient material for both sides" in {
      Api.insufficientMaterial(
        variant.Xiangqi.fairysfName.name,
        variant.Xiangqi.initialFen.value
      ) must_== ((false, false))
    }
    "have legal moves" in {
      Api.legalMoves(
        variant.Xiangqi.fairysfName.name,
        variant.Xiangqi.initialFen.value
      ).size must_!= 0L
    }
    "have 32 pieces" in {
      Api.pieceMapFromFen(
        variant.Xiangqi.fairysfName.name,
        variant.Xiangqi.gameFamily,
        variant.Xiangqi.initialFen.value
      ).size must_== 32
    }
    "have 32 pieces again" in {
      Api.pieceMapFromFen(
        variant.Xiangqi.fairysfName.name,
        variant.Xiangqi.gameFamily,
        variant.Xiangqi.initialFen.value
      ).size must_== 32
    }
    "not be game end" in {
      Api.gameEnd(
        variant.Xiangqi.fairysfName.name,
        variant.Xiangqi.initialFen.value
      ) must_== false
    }
    "not be optional game end" in {
      Api.optionalGameEnd(
        variant.Xiangqi.fairysfName.name,
        variant.Xiangqi.initialFen.value
      ) must_== false
    }
    "e1e2 opening provide valid piece map" in {
      Api.pieceMapFromFen(
        variant.Xiangqi.fairysfName.name,
        variant.Xiangqi.gameFamily,
        Api.fenFromMoves(
          variant.Xiangqi.fairysfName.name,
          variant.Xiangqi.initialFen.value,
          Option(List("e1e2"))
        ).value
      ).size must_== 32
    }
  }

  "Shogi initial fen minus middle rank" should {
    "be invalid" in {
      Api.validateFEN(
        variant.Shogi.fairysfName.name,
        "lnsgkgsnl/1r5b1/ppppppppp/9/9/PPPPPPPPP/1B5R1/LNSGKGSNL[-] w 0 1"
      ) must_== false
    }
  }

  "Random string" should {
    "be invalid fen" in {
      Api.validateFEN(
        variant.Shogi.fairysfName.name,
        "I'm a Shogi FEN! (not)"
      ) must_== false
    }
  }

  "Chess black Checkmate FEN" should {
    "be game end" in {
      Api.gameEnd(
        "chess",
        "rnb1kbnr/pppp1ppp/8/4p3/5PPq/8/PPPPP2P/RNBQKBNR w KQkq - 1 3"
      ) must_== true
      Api.gameResult(
        "chess",
        "rnb1kbnr/pppp1ppp/8/4p3/5PPq/8/PPPPP2P/RNBQKBNR w KQkq - 1 3",
      ) must_== GameResult.Checkmate()
      Api.gameResult(
        "chess",
        "r1bqkbnr/1ppppQ1p/p1n3p1/8/2B1P3/8/PPPP1PPP/RNB1K1NR b KQkq - 0 4",
      ) must_== GameResult.Checkmate()
    }
  }

  "Chess three fold repetition" should {
    "be optional game end" in {
      Api.optionalGameEnd(
        "chess",
        "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1",
        Some(List("b1c3", "g8f6", "c3b1", "f6g8", "b1c3", "g8f6", "c3b1", "f6g8"))
      ) must_== true
    }
    "not be game end" in {
      Api.gameEnd(
        "chess",
        "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1",
        Some(List("b1c3", "g8f6", "c3b1", "f6g8", "b1c3", "g8f6", "c3b1", "f6g8"))
      ) must_== false
    }
  }

  "Shogi four fold repetition" should {
    "be optional game end" in {
      Api.optionalGameEnd(
        variant.Shogi.fairysfName.name,
        variant.Shogi.initialFen.value,
        Some(List("h2i2", "b8a8", "i2h2", "a8b8", "h2i2", "b8a8", "i2h2", "a8b8", "h2i2", "b8a8", "i2h2", "a8b8"))
      ) must_== true
    }
    "not be game end" in {
      Api.gameEnd(
        variant.Shogi.fairysfName.name,
        variant.Shogi.initialFen.value,
        Some(List("h2i2", "b8a8", "i2h2", "a8b8", "h2i2", "b8a8", "i2h2", "a8b8", "h2i2", "b8a8", "i2h2", "a8b8"))
      ) must_== false
    }
  }

  "Shogi Checkmate FEN" should {
    //https://www.pychess.org/cdRztJdY?ply=74
    val checkmateFen = "l2g1g1nl/5sk2/3p1p1p1/p3p1p1p/1n2n4/P4PP1P/1P1sPK1P1/5sR1+r/L4+p1N1[GPSBBglpp] w - - 4 38"
    "be game end" in {
      Api.gameEnd(
        variant.Shogi.fairysfName.name,
        checkmateFen
      ) must_== true
    }
    "be checkmate" in {
      Api.gameResult(
        variant.Shogi.fairysfName.name,
        checkmateFen
      ) must_== GameResult.Checkmate()
    }
    "have no legal moves" in {
      Api.legalMoves(
        variant.Shogi.fairysfName.name,
        checkmateFen
      ).size must_== 0L
    }
    "have pieces in hand" in {
      Api.piecesInHand(
        variant.Shogi.fairysfName.name,
        variant.Shogi.gameFamily,
        checkmateFen
      ).size must_== 9L
    }
  }

  "Shogi fools mate moves" should {
    val foolsFEN = "lnsg1gsnl/5rkb1/ppppppp+Pp/9/9/9/PPPPPPP1P/1B5R1/LNSGKGSNL[P] b - - 0 4"
    "produce fools mate fen" in {
      Api.fenFromMoves(
        variant.Shogi.fairysfName.name,
        variant.Shogi.initialFen.value,
        Some(List("h3h4", "e9f8", "h4h5", "f8g8", "h5h6", "b8f8", "h6h7+"))
      ).value must_== foolsFEN
    }
    "be game end" in {
      Api.gameEnd(
        variant.Shogi.fairysfName.name,
        foolsFEN
      ) must_== true
    }
    "be checkmate" in {
      Api.gameResult(
        variant.Shogi.fairysfName.name,
        foolsFEN,
      ) must_== GameResult.Checkmate()
    }
    "have no legal moves" in {
      Api.legalMoves(
        variant.Shogi.fairysfName.name,
        foolsFEN,
      ).size must_== 0L
    }
  }

  "Chess stalemate is draw" should {
    val stalemateFEN = "5bnr/4p1pq/4Qpkr/7p/7P/4P3/PPPP1PP1/RNB1KBNR b KQ - 2 10"
    "moves produce stalemate fen" in {
      Api.fenFromMoves(
        "chess",
        "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1",
        Some(List("e2e3", "a7a5", "d1h5", "a8a6", "h5a5", "h7h5", "a5c7", "a6h6", "h2h4", "f7f6", "c7d7", "e8f7", "d7b7", "d8d3", "b7b8", "d3h7", "b8c8", "f7g6", "c8e6"))
      ).value must_== stalemateFEN
    }
    "have no legal moves" in {
      Api.legalMoves(
        "chess",
        stalemateFEN,
      ).size must_== 0L
    }
    "be game end" in {
      Api.gameEnd(
        "chess",
        stalemateFEN
      ) must_== true
    }
    "be stalemate" in {
      Api.gameResult(
        "chess",
        stalemateFEN,
      ) must_== GameResult.Draw()
    }
  }

  "Shogi stalemate is win" should {
    val stalemateFEN = "8l/8k/9/8P/9/2P6/PP1PPPP2/1B5R1/LNSGKGSNL[] b - - 0 2"
    "be a valid fen" in {
      Api.validateFEN(
        variant.Shogi.fairysfName.name,
        stalemateFEN
      ) must_== true
    }
    "have no legal moves" in {
      Api.legalMoves(
        variant.Shogi.fairysfName.name,
        stalemateFEN,
      ).size must_== 0L
    }
    "be game end" in {
      Api.gameEnd(
        variant.Shogi.fairysfName.name,
        stalemateFEN
      ) must_== true
    }
    "be checkmate" in {
      Api.gameResult(
        variant.Shogi.fairysfName.name,
        stalemateFEN,
      ) must_== GameResult.Checkmate()
    }
  }

  "Chess king only" should {
    val insufficientMaterialFEN = "4k3/8/8/8/8/8/PPPPPPPP/RNBQKBNR w KQ - 0 1"
    "have insufficient material" in {
      Api.insufficientMaterial(
        "chess",
        insufficientMaterialFEN
      ) must_== ((false, true))
    }
  }

  "Shogi king only" should {
    val insufficientMaterialFEN = "8k/9/9/9/9/9/PPPPPPPPP/1B5R1/LNSGKGSNL[LNSGGSNLBRPPPPPPPPP] b - - 0 2"
    "never have insufficient material" in {
      Api.insufficientMaterial(
        variant.Shogi.fairysfName.name,
        insufficientMaterialFEN
      ) must_== ((false, false))
    }
  }

  //valid moves doesnt filter this out
  //"Shogi pawn drop into checkmate" should {
  //  "setup should not produce valid move" in {
  //    Api.legalMoves(
  //      variant.Shogi.fairysfName.name,
  //      variant.Shogi.initialFen.value,
  //      Some(List("c3c4", "e7e6", "b2g7+", "e9e8", "e3e4", "e6e5", "e4e5", "d9e9", "h2e2", "e8d9", "e5e6", "b8e8", "e6e7", "e8e7", "e2e7+", "a7a6", "e7e5", "d9d8", "e5d5", "d8e7", "g7f7", "e7d8", "f7g7", "d8e7", "a3a4", "c9d8", "a4a5", "e9e8"))
  //    ).contains("P@e6") must_== false
  //  }
  //  "drop should not produce game end" in {
  //    Api.gameEnd(
  //      variant.Shogi.fairysfName.name,
  //      variant.Shogi.initialFen.value,
  //      Some(List("c3c4", "e7e6", "b2g7+", "e9e8", "e3e4", "e6e5", "e4e5", "d9e9", "h2e2", "e8d9", "e5e6", "b8e8", "e6e7", "e8e7", "e2e7+", "a7a6", "e7e5", "d9d8", "e5d5", "d8e7", "g7f7", "e7d8", "f7g7", "d8e7", "a3a4", "c9d8", "a4a5", "e9e8", "P@e6"))
  //    ) must_== false
  //  }
  //  "drop should not produce checkmate" in {
  //    Api.gameResult(
  //      variant.Shogi.fairysfName.name,
  //      variant.Shogi.initialFen.value,
  //      Some(List("c3c4", "e7e6", "b2g7+", "e9e8", "e3e4", "e6e5", "e4e5", "d9e9", "h2e2", "e8d9", "e5e6", "b8e8", "e6e7", "e8e7", "e2e7+", "a7a6", "e7e5", "d9d8", "e5d5", "d8e7", "g7f7", "e7d8", "f7g7", "d8e7", "a3a4", "c9d8", "a4a5", "e9e8", "P@e6"))
  //    ) must_!= GameResult.Checkmate()
  //  }
  //}

  //https://www.pychess.org/ozI3pCRx
  "Shogi perpetual check" should {
    "should produce non optional game end" in {
      Api.gameEnd(
        variant.Shogi.fairysfName.name,
        variant.Shogi.initialFen.value,
        Some(List("c3c4", "e7e6", "b2g7+", "e9d8", "g7f6", "d8e9", "f6g7", "e9d8", "g7f6", "d8e9", "f6g7", "e9d8", "g7f6", "d8e9", "f6g7"))
      ) must_== true
    }
  }

  "Chess white king vs black king only" should {
    val insufficientMaterialFEN = "4k3/8/8/8/8/8/8/3K4 w - - 0 1"
    "have insufficient material" in {
      Api.insufficientMaterial(
        "chess",
        insufficientMaterialFEN
      ) must_== ((true, true))
    }
    "be game end" in {
      Api.gameEnd(
        "chess",
        insufficientMaterialFEN
      ) must_== true
    }
    // TODO: this should be reinstated when we fix this method
    /*"be stalemate" in {
      Api.gameResult(
        "chess",
        insufficientMaterialFEN
      ) must_== GameResult.Draw()
    }*/
  }

  "Chess autodraw" should {
    //https://lichess.org/BdvgPSMd#82
    //from src/test/scala/chess/AutodrawTest.scala
    val moves = Some(List("e2e4", "c7c5", "g1f3", "d7d6", "d2d4", "c5d4", "f3d4", "g8f6", "b1c3", "g7g6", "c1g5", "f8g7", "f2f4", "b8c6", "f1b5", "c8d7", "d4c6", "d7c6", "b5c6", "b7c6", "e1g1", "d8b6", "g1h1", "b6b2", "d1d3", "e8g8", "a1b1", "b2a3", "b1b3", "a3c5", "c3a4", "c5a5", "a4c3", "a8b8", "f4f5", "b8b3", "a2b3", "f6g4", "c3e2", "a5c5", "h2h3", "g4f2", "f1f2", "c5f2", "f5g6", "h7g6", "g5e7", "f8e8", "e7d6", "f2f1", "h1h2", "g7e5", "d6e5", "e8e5", "d3d8", "g8g7", "d8d4", "f7f6", "e2g3", "f1f4", "d4d7", "g7h6", "d7f7", "e5g5", "f7f8", "h6h7", "f8f7", "h7h8", "f7f8", "h8h7", "f8f7", "h7h6", "f7f8", "h6h7", "f8f7", "h7h8", "f7f8", "h8h7", "f8f7", "h7h6", "f7f8", "h6h7"))
    //"be immediate game end" in {
    //  Api.immediateGameEnd(
    //    "chess",
    //    Api.initialFen("chess").value,
    //    moves
    //  ) must_== true
    //}
    "be optional game end" in {
      Api.optionalGameEnd(
        "chess",
        Api.initialFen("chess").value,
        moves
      ) must_== true
    }
    //not be game end because autodraw in lichess isnt a true autodraw
    "not be game end" in {
      Api.gameEnd(
        "chess",
        Api.initialFen("chess").value,
        moves
      ) must_== false
    }
    /* TODO: this should be reinstated when we fix this method
    "be draw" in {
      Api.gameResult(
        "chess",
        Api.initialFen("chess").value,
        moves
      ) must_== GameResult.Draw()
    }*/
  }

  "King of the hill variant win" should {
    val moves = Some(List("e2e4", "a7a6", "e1e2", "a6a5", "e2e3", "a5a4", "e3d4"))
    "be game end" in {
      Api.gameEnd(
        "kingofthehill",
        Api.initialFen("kingofthehill").value,
        moves
      ) must_== true
    }
    //doesnt give variant end for this
    //"be variant end" in {
    //  Api.gameResult(
    //    "kingofthehill",
    //    Api.initialFen("kingofthehill").value,
    //    moves
    //  ) must_== GameResult.VariantEnd()
    //}
  }

  "Racing Kings draw" should {
    val moves = Some(List("h2h3", "a2a3", "h3h4", "a3a4", "h4h5", "a4a5", "h5h6", "a5a6", "h6g7", "a6b7", "g7g8", "b7b8"))
    "be game end" in {
      Api.gameEnd(
        "racingkings",
        Api.initialFen("racingkings").value,
        moves
      ) must_== true
    }
    "be draw" in {
      Api.gameResult(
        "racingkings",
        Api.initialFen("racingkings").value,
        moves
      ) must_== GameResult.Draw()
    }
  }

  "availableVariants" should {
    "should have shogi" in {
      Api.availableVariants().filter(_=="shogi").size must_== 1
    }
    "should have xiangqi" in {
      Api.availableVariants().filter(_=="xiangqi").size must_== 1
    }
    "should not have my little pony" in {
      Api.availableVariants().filter(_=="my little pony").size must_== 0
    }
  }
}
