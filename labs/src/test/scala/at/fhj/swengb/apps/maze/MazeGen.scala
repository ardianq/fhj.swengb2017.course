package at.fhj.swengb.apps.maze

import org.scalacheck.Gen

/**
  * Contains generators for test data for maze.
  *
  * By using this generators, arbitrary (random) Maze instances are generated which can be used for testing (but not only for testing!)
  */
object MazeGen {

  val maxX: Int = 100
  val maxY: Int = 100

  val posGen: Gen[Pos] = for {
    x <- Gen.chooseNum(1, maxX - 1)
    y <- Gen.chooseNum(1, maxY - 1)
  } yield Pos(x, y)

  val cellRectGen: Gen[Rect] = for {width <- Gen.chooseNum(1, maxX)
                                    height <- Gen.chooseNum(1, maxY)} yield Rect(width, height)

  val coordGen: Gen[Coord] = for {
    x <- Gen.chooseNum[Double](0.0, maxX.toDouble)
    y <- Gen.chooseNum[Double](0.0, maxY.toDouble)
  } yield Coord(x, y)

  val cellGen: Gen[Cell] =
    for {
      pos <- posGen
      topLeft <- coordGen
      region <- cellRectGen
      upPos <- posGen
      rightPos <- posGen
      downPos <- posGen
      leftPos <- posGen
      someUp <- Gen.oneOf[Option[Pos]](None, Option(upPos))
      someRight <- Gen.oneOf[Option[Pos]](None, Option(rightPos))
      someDown <- Gen.oneOf[Option[Pos]](None, Option(downPos))
      someLeft <- Gen.oneOf[Option[Pos]](None, Option(leftPos))
    } yield Cell(pos, topLeft, region, someUp, someRight, someDown, someLeft)

  val mazeGen: Gen[Maze] =
    for {
      x <- Gen.chooseNum(1, maxX)
      y <- Gen.chooseNum(1, maxY)
      start <- posGen
      end <- posGen
      grid <- Gen.listOfN(x * y, cellGen)
      cellRect <- cellRectGen
    } yield Maze(x, y, start, end, grid.toArray, cellRect)

}
