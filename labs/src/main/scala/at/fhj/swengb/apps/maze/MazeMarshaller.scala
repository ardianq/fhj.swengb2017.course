package at.fhj.swengb.apps.maze

import java.nio.file.{Files, Paths}

/**
  * Showcases how to serialize and deserialize a maze from disc via protobuf
  */
object MazeMarshaller {

  import MazeProtocol._


  def main(args: Array[String]): Unit = {

    val maze: Maze = MazeGenerator.gen(PositiveInt(10), PositiveInt(10), Pos(0, 0), Pos(9, 9), Rect(100, 100))

    val pMaze: MazeProtobuf.Maze = convert(maze)

    val path = Paths.get("target/MazeProtobuf.bin")
    val outstream = Files.newOutputStream(path)

    pMaze.writeTo(outstream)

    println("Wrote to " + path.toAbsolutePath.toString)

    val in = Files.newInputStream(path)

    val protoMaze: MazeProtobuf.Maze = MazeProtobuf.Maze.parseFrom(in)

    val m: Maze = convert(protoMaze)

    println("Read maze from disc: sizeX:" + m.sizeX)
    // print out other data ...
  }

}
