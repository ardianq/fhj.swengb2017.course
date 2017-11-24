package at.fhj.swengb.apps.battleship

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.{Parent, Scene}
import javafx.stage.Stage

class BattleShipJfxApp extends Application {

  val root = FXMLLoader.load[Parent](getClass.getResource("/at/fhj/swengb/apps/battleship/battleship.fxml"))

  override def start(stage: Stage) = {
    stage.setScene(new Scene(root))
    stage.show()
  }

}