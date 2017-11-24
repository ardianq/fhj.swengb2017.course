package at.fhj.swengb.apps.fxml

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.{Parent, Scene}
import javafx.stage.Stage

object HelloworldFxml {

  def main(args: Array[String]): Unit = {
    Application.launch(classOf[HelloworldFxml], args: _*)
  }
}

class HelloworldFxml extends Application {
  val root = FXMLLoader.load[Parent](getClass.getResource("helloworld.fxml"))

  override def start(stage: Stage) = {
    stage.setScene(new Scene(root))
    stage.show()
  }

}