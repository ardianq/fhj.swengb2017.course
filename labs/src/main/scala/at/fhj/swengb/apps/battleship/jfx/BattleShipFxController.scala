package at.fhj.swengb.apps.battleship.jfx

import java.net.URL
import java.util.ResourceBundle
import javafx.fxml.{FXML, Initializable}
import javafx.scene.control.TextArea
import javafx.scene.layout.GridPane

import at.fhj.swengb.apps.battleship.{BattleField, Fleet, FleetConfig}


class BattleShipFxController extends Initializable {


  @FXML private var battleGroundGridPane: GridPane = _

  /**
    * A text area box to place the history of the game
    */
  @FXML private var log: TextArea = _

  @FXML
  def newGame(): Unit = initGame()

  override def initialize(url: URL, rb: ResourceBundle): Unit = {
    initGame()
  }

  private def getCellHeight(y: Int): Double = battleGroundGridPane.getRowConstraints.get(y).getPrefHeight

  private def getCellWidth(x: Int): Double = battleGroundGridPane.getColumnConstraints.get(x).getPrefWidth

  def appendLog(message: String): Unit = log.appendText(message + "\n")


  private def initGame(): Unit = {
    createGame().init(battleGroundGridPane)
    appendLog("New game started.")
  }

  private def createGame(): BattleShipGame = {
    val battleField = BattleField.placeRandomly(BattleField(10, 10, Fleet(FleetConfig.Standard)))
    val game = BattleShipGame(battleField, getCellWidth, getCellHeight, appendLog)
    game
  }

}