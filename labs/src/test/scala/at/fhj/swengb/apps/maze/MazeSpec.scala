package at.fhj.swengb.apps.maze

import org.scalatest.WordSpecLike

class MazeSpec extends WordSpecLike {

  "A Maze" when {
    ".generate" should {
      "throw Exception (1)" in intercept[IllegalArgumentException](MazeGenerator.gen(0, 1, Pos(1, 1), Pos(1, 1), Rect(1, 1)))
      "throw Exception (2)" in intercept[IllegalArgumentException](MazeGenerator.gen(1, 0, Pos(1, 1), Pos(1, 1), Rect(1, 1)))
      "throw Exception (3)" in intercept[IllegalArgumentException](MazeGenerator.gen(1, 1, Pos(-1, 1), Pos(1, 1), Rect(1, 1)))
      "throw Exception (4)" in intercept[IllegalArgumentException](MazeGenerator.gen(1, 1, Pos(1, 10), Pos(1, 1), Rect(1, 1)))
      "throw Exception (5)" in intercept[IllegalArgumentException](MazeGenerator.gen(1, 1, Pos(1, 1), Pos(-1, 1), Rect(1, 1)))
      "throw Exception (6)" in intercept[IllegalArgumentException](MazeGenerator.gen(1, 1, Pos(1, 1), Pos(1, 10), Rect(1, 1)))
      "throw Exception (7)" in intercept[IllegalArgumentException](MazeGenerator.gen(1, 1, Pos(1, 1), Pos(1, 1), Rect(0, 1)))
      "throw Exception (8)" in intercept[IllegalArgumentException](MazeGenerator.gen(1, 1, Pos(1, 1), Pos(1, 1), Rect(1, 0)))
      ".generate some maze" in {
        assert(MazeGenerator.gen(10, 10, Pos(0, 0), Pos(9, 9), Rect(10, 10)) != null)
      }
    }

    /**
      * Test door implementation
      */
    /*
    "test open doors" in {

    }
    */
  }
}