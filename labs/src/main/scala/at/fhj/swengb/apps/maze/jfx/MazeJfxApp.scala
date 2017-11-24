package at.fhj.swengb.apps.maze.jfx

import javafx.application.Application
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import javafx.scene.{Group, Scene}
import javafx.stage.Stage

import at.fhj.swengb.apps.maze._
import at.fhj.swengb.common.CanLog

import scala.collection.JavaConverters._

object JfxMaze {
  def apply(m: Maze, x: Double, y: Double): JfxMaze = {
    val jfxMaze = new JfxMaze(m)
    jfxMaze.setTranslateX(x)
    jfxMaze.setTranslateY(y)
    jfxMaze
  }
}


case class JfxMaze(maze: Maze) extends Group {
  val boundingBox: Rect = maze.boundingBox
  val cellWidth: Double = maze.cellWidth
  val cellHeight: Double = maze.cellHeight

  // background
  getChildren.add(new Rectangle(maze.boundingBox.width, maze.boundingBox.height, Color.WHITE))

  val cells: Seq[JfxCell] = maze.grid.map(JfxCell).toSeq

  getChildren.addAll(cells.asJava)
}

class MazeJfxApp extends Application with CanLog {


  override def start(primaryStage: Stage): Unit = {
    primaryStage.setTitle("A mazing")

    val maze: Maze = MazeGenerator.gen(PositiveInt(10), PositiveInt(10), Pos(0, 0), Pos(9, 9), Rect(50, 50))
    val jfxMaze = JfxMaze(maze, 10, 10)
    val width = jfxMaze.cellWidth
    val height = jfxMaze.cellHeight
    primaryStage.setScene(new Scene(jfxMaze, jfxMaze.boundingBox.height + 20, jfxMaze.boundingBox.width + 20))
    primaryStage.show()

  }

}
