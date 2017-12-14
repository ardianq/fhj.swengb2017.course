package at.fhj.swengb.apps.maze

import org.scalacheck.Prop
import org.scalatest.WordSpecLike
import org.scalatest.prop.Checkers


/**
  * MazeProtocolSpec uses a special technique for writing tests, so called 'property based testing'.
  *
  * Check out http://www.scalacheck.org for more documentation about how to use property based testing in conjuction
  * with Scala properly.
  */
class MazeProtocolSpec extends WordSpecLike {

  "Pos" should {
    "be serializable" in {
      Checkers.check(Prop.forAll(MazeGen.posGen) {
        expected: Pos =>
          val actual = MazeProtocol.convert(MazeProtocol.convert(expected))
          actual == expected
      })
    }
  }

  "Maze" should {
    "be deserializable" in {
      Checkers.check(Prop.forAll(MazeGen.mazeGen) {
        // using this syntax, we generate a maze
        // this maze is our expected value which is used later on to compare our result
        expected: Maze => {
          // actual is equal to result for example if both convert methods don't do anything.
          // but as things are, both convert methods represent the inverse function for each other
          // this yields in sum the original value again
          val actual = MazeProtocol.convert(MazeProtocol.convert(expected))

          // if this condition is true for all variations of generated mazes, the test runs through.
          actual == expected
        }
      })
    }
  }
}
