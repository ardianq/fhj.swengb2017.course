package at.fhj.swengb.apps.maze

import at.fhj.swengb.apps.maze.jfx.{Door, Wall}

import scala.util.Random

case class PositiveInt(value: Int) {
  require(value > 0)
}

/**
  * A Mazegenerator creates a datastructure representing a random maze.
  */
object MazeGenerator {

  /**
    * Given a list, this function picks a random element.
    *
    * @param seq
    * @tparam A
    * @return
    */
  def pickRandom[A](seq: Seq[A]): A = {
    require(seq.nonEmpty)
    if (seq.length == 1) seq.head else {
      seq(Random.nextInt(seq.length))
    }
  }

  /**
    * Generates a random maze with a given size and entry and exit cells.
    *
    * The idea is to start at the entry cell, walk in any direction
    *
    * @param sizeX    number of cells in x direction
    * @param sizeY    number of cells in y direction
    * @param start    start / entry cell of the maze
    * @param exit     exit cell of the maze
    * @param cellRect size / region of a cell
    * @return a random maze
    */
  @deprecated("use gen with typesafe signature", "20171117")
  def gen(sizeX: Int, sizeY: Int, start: Pos, exit: Pos, cellRect: Rect): Maze = {
    gen(PositiveInt(sizeX), PositiveInt(sizeY), start, exit, cellRect)
  }

  def gen(sizeX: PositiveInt, sizeY: PositiveInt, start: Pos, exit: Pos, cellRect: Rect): Maze = {
    require(0 <= start.x && start.x <= sizeX.value)
    require(0 <= start.y && start.y <= sizeY.value)
    require(0 <= exit.x && exit.x < sizeX.value)
    require(0 <= exit.y && exit.y < sizeY.value)
    require(start != exit)

    val m = Maze(sizeX.value, sizeY.value, cellRect)
    val startCell = m.getCell(start)
    val exitCell = m.getCell(exit)

    // we require exit and entry to be part of the outer cells
    require(m.boundaryCells.contains(startCell))
    require(m.boundaryCells.contains(exitCell))

    closeOuterDoors(m)
  }

  private def closeOuterDoors(m: Maze): Maze =
    m.copy(grid = for (c <- m.grid) yield clip(m, c))

  private def clip(m: Maze, c: Cell): Cell = {
    val up: Option[Pos] = if (m.topBoundary.contains(c)) None else c.up
    val right: Option[Pos] = if (m.rightBoundary.contains(c)) None else c.right
    val down: Option[Pos] = if (m.downBoundary.contains(c)) None else c.down
    val left: Option[Pos] = if (m.leftBoundary.contains(c)) None else c.left
    c.copy(up = up, down = down, right = right, left = left)
  }

  private def addEntryAndExit(m: Maze, start: Cell, exit: Cell) = {
    for (c <- m.grid) yield {
      if (c == start || c == exit) clip(m, c) else c
    }
  }

  def walls(maze: Maze): Set[Wall] = maze.grid.flatMap(_.walls).toSet

  def doors(maze: Maze): Set[Door] = maze.grid.flatMap(_.doors).toSet

}
