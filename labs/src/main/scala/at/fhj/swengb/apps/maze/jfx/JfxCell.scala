package at.fhj.swengb.apps.maze.jfx

import javafx.event.{Event, EventHandler}
import javafx.scene.Group
import javafx.scene.input.MouseEvent
import javafx.scene.paint.Color
import javafx.scene.shape.{Line, Rectangle, Shape}

import at.fhj.swengb.apps.maze.{Cell, Coord}

import scala.collection.JavaConverters._


object Wall {

  val Thickness = 15
}

case class Wall(c1: Coord, c2: Coord) extends Drawable {

  def shape: Shape = {
    val l = new Line(c1.x, c1.y, c2.x, c2.y)
    l.setStrokeWidth(Wall.Thickness)
    l.setFill(Color.BLACK)
    l
  }

}


case class JfxCell(cell: Cell) extends Group {
  private val bground = new Rectangle(cell.x, cell.y, cell.region.width, cell.region.height)
  bground.setFill(Color.WHITE)


  setOnMouseClicked(e => println(s"x: ${cell.pos.x}, y: ${cell.pos.y}"))

  setOnMouseEntered(e => bground.setFill(Color.RED))

  setOnMouseExited(e => bground.setFill(Color.GREY))

  getChildren.add(bground)

  // draw walls
  getChildren.addAll(cell.walls.map(_.shape).asJava)

  // draw 'doors'
  getChildren.addAll(cell.doors.map(_.shape).asJava)


}
