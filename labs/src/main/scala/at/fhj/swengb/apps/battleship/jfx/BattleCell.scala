package at.fhj.swengb.apps.battleship.jfx

import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle

import at.fhj.swengb.apps.battleship.{BattlePos, Vessel}

/**
  * Represents one part of a vessel or one part of the ocean.
  *
  * @param pos
  * @param width
  * @param height
  * @param log
  * @param someVessel
  * @param f
  */
case class BattleCell(pos: BattlePos
                      , width: Double
                      , height: Double
                      , log: String => Unit
                      , someVessel: Option[Vessel] = None
                      , fn: (Vessel, BattlePos) => Unit
                     ) extends Rectangle(width, height) {

  def init(): Unit = {
    if (someVessel.isDefined) {
      setFill(Color.YELLOWGREEN)
    } else {
      setFill(Color.BLUE)
    }
  }

  setOnMouseClicked(e => {
    someVessel match {
      case None =>
        log(s"Missed. Just hit water.")
        setFill(Color.AQUAMARINE)
      case Some(v) =>
       // log(s"Hit an enemy vessel!")
        fn(v, pos)
        setFill(Color.RED)
    }
  })

}
