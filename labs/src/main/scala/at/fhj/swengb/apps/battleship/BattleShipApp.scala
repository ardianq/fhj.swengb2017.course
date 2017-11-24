package at.fhj.swengb.apps.battleship

import javafx.application.Application

object BattleShipApp {

  def main(args: Array[String]): Unit = {
    Application.launch(classOf[BattleShipJfxApp], args: _*)
  }
}
