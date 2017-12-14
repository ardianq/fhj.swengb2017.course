package at.fhj.swengb.apps.maze

import java.lang

import scala.collection.JavaConverters._

/**
  * Encodes conversions between business models and protocol encodings
  */
object MazeProtocol {

  def convert(pos: MazeProtobuf.Pos): Pos = ???

  def convert(end: Pos): MazeProtobuf.Pos = MazeProtobuf.Pos.newBuilder().build()

  def convert(cellRect: Rect): MazeProtobuf.Rect = MazeProtobuf.Rect.newBuilder().build()

  def convert(cell: Cell): MazeProtobuf.Cell = MazeProtobuf.Cell
    .newBuilder().build

  /**
    * Provided a protobuf encoded maze, create a business model class 'maze' again
    */
  // TODO implement missing functionality
  def convert(protoMaze: MazeProtobuf.Maze): Maze = {
    println(protoMaze.getSizeX)
    println(protoMaze.getSizeY)
    Maze(
      protoMaze.getSizeX,
      protoMaze.getSizeY,
      Pos(0, 0), // TODO read from proto
      Pos(1, 0), // TODO read from proto
      Array.fill(protoMaze.getSizeX * protoMaze.getSizeY)(Cell(Pos(0, 0), Coord(0, 0), Rect(0, 0))), // TODO read from proto
      Rect(100, 100) // TODO read from proto
    )
  }

  /**
    * Convert a business model maze to a protocol encoded maze
    */
  def convert(maze: Maze): MazeProtobuf.Maze = {
    val pCoord = MazeProtobuf.Coord.newBuilder().build

    val cells: lang.Iterable[MazeProtobuf.Cell] = maze.grid.map(convert).toIterable.asJava

    val pMaze = MazeProtobuf.Maze.newBuilder()
      .setSizeX(maze.sizeX)
      .setSizeY(maze.sizeY)
      .setStart(convert(maze.start))
      .setEnd(convert(maze.end))
      .addAllGrid(cells)
      .setCellRect(convert(maze.cellRect))
      .build()
    pMaze
  }


}
