package at.fhj.swengb.apps.fxml

import java.net.URL
import java.util.ResourceBundle
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Label


class HelloFxmlController extends Initializable {

  @FXML private var label: Label = null

  @FXML private def handleButtonAction(event: ActionEvent): Unit = {
    System.out.println("You clicked me!")
    label.setText("Hello World!")
  }

  override def initialize(url: URL, rb: ResourceBundle): Unit = {
    // TODO
  }

}