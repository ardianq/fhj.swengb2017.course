package at.fhj.swengb.apps.maze

/**
  * Companion object for case class Maze
  */
object Maze {

  val SmallMaze = Maze(10, 10, Rect(10, 10))
  val MediumMaze = Maze(40, 40, Rect(10, 10))
  val BigMaze = Maze(100, 100, Rect(5, 5))

  /**
    * Creates a maze with all doors closed
    *
    * @param sizeX
    * @param sizeY
    * @param cellRect
    * @return
    */
  def apply(sizeX: Int, sizeY: Int, cellRect: Rect): Maze = {
    val grid: Array[Cell] =
      Array.tabulate(sizeX, sizeY)((y, x) => {
        val pos = Pos(x, y)
        val coord = Coord(x * cellRect.width, y * cellRect.height)
        Cell(pos, coord, cellRect)
      }).flatten
    Maze(sizeX, sizeY, Pos(0, 0), Pos(sizeX, sizeY), grid, cellRect)
  }

}

case class Maze(sizeX: Int
                , sizeY: Int
                , start: Pos
                , end: Pos
                , grid: Array[Cell]
                , cellRect: Rect) {

  val cellMap: Map[Pos, Cell] = grid.map { c => c.pos -> c }.toMap

  val cellWidth: Double = cellRect.width
  val cellHeight: Double = cellRect.height
  val boundingBox: Rect = Rect(sizeX * cellWidth, sizeY * cellHeight)

  val topBoundary: Set[Cell] = grid.slice(0, sizeX).toSet
  val downBoundary: Set[Cell] = grid.slice(grid.length - sizeX, grid.length).toSet

  val rightBoundary: Set[Cell] = (for (i <- (sizeX - 1) until sizeX * sizeY by sizeX) yield {
    grid(i)
  }).toSet
  val leftBoundary: Set[Cell] = (for (i <- 0 until sizeX * sizeY by sizeX) yield grid(i)).toSet

  val boundaryCells: Set[Cell] = topBoundary ++ rightBoundary ++ downBoundary ++ leftBoundary

  def getCell(pos: Pos): Cell = grid(pos.gridIndex(sizeX))


}





