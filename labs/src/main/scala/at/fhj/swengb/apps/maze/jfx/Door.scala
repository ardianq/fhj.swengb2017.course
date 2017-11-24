package at.fhj.swengb.apps.maze.jfx

import javafx.scene.paint.{Color, Paint}
import javafx.scene.shape.{Rectangle, Shape}

import at.fhj.swengb.apps.maze.{Coord, Pos, Rect}

object Door {

  def apply(maybeTo: Option[Pos], c: Coord, rect: Rect): Door = {
    if (maybeTo.isDefined) {
      Door(c, rect, Color.WHITE)
    } else {
      Door(c, rect, Color.BLACK)
    }
  }

}

case class Door(coord: Coord, rect: Rect, fill: Paint) extends Drawable {

  lazy val shape: Shape = {
    val ri = new Rectangle(coord.x - rect.width / 2, coord.y - rect.height / 2, rect.width, rect.height)
    ri.setFill(fill)
    ri
  }

}

