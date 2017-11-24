package at.fhj.swengb.apps.battleship

import java.net.URL
import java.util.ResourceBundle
import javafx.fxml.{FXML, Initializable}
import javafx.scene.control.TextArea
import javafx.scene.layout.GridPane
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle


/**
  * denotes a position on the battle arena
  *
  * @param x
  * @param y
  */
case class BattlePos(x: Int, y: Int)

/**
  * Denotes the size of our region of interest
  */
case class BattleField(width: Int, height: Int)

/**
  * A vessel is the common denominator of all ships which are to be defined.
  */
trait Vessel {

  /**
    * each vessel has a (nonempty) name.
    *
    * @return
    */
  def name: String

  /**
    * position of the parts of a vessel, have to be connected and either
    * all x or all y coordinates have to be the same.
    *
    * @return
    */
  def positions: Set[BattlePos]

}

sealed trait Direction

case object Horizontal extends Direction

case object Vertical extends Direction






// TODO add additional vessels, along with specs for each vessel. start with Cruiser
// TODO add Cruiser class + object (4 cells)
// TODO add Destroyer class + object (3 cells)
// TODO add Submarine class + object (2 cells)

object Fleet {

  // TODO add other vessels as well to the fleet
  // TODO add more randomness to the placement of the fleet.
  // TODO add constraints such that all ships are placed completely _in_ the battlefield
  // TODO maybe a battlefield is too small for a Battleship?
  // TODO what is the smallest battlefield all vessels could be placed on?
  def apply(battleField: BattleField): Fleet = {
    Fleet(Set[Vessel](BattleShip("Archduke John", BattlePos(0, 0), Vertical)))
  }

}

// ships may not overlap, each vessel should have a distinct place on the battleground
case class Fleet(vessels: Set[Vessel]) {
  def findByPos(pos: BattlePos): Option[Vessel] = vessels.find(v => v.positions.contains(pos))

  def findByName(name: String): Option[Vessel] = vessels.find(v => v.name == name)
}


case class BattleCell(pos: BattlePos
                      , width: Double
                      , height: Double
                      , log: String => Unit
                      , someVessel: Option[Vessel] = None
                      , updateGameState: (Vessel, BattlePos) => Unit
                     ) extends Rectangle(width, height) {


  clear()

  def clear(): Unit = {
    setFill(Color.BLUE)
  }

  setOnMouseClicked(e => {
    someVessel match {
      case None =>
        log(s"Missed. Just hit water.")
        setFill(Color.YELLOW)
      case Some(v) =>
        log(s"Hit an enemy vessel!")
        updateGameState(v, pos)
        setFill(Color.RED)
    }
  })

}


case class BattleGround(battleField: BattleField,
                        fleet: Fleet,
                        getCellWidth: Int => Double,
                        getCellHeight: Int => Double,
                        log: String => Unit) {

  var hits: Map[Vessel, Set[BattlePos]] = Map()

  var sunkShips: Set[Vessel] = Set()

  /**
    * We don't ever change cells, they should be initialized only once.
    */
  private val cells = for {x <- 0 until battleField.width
                           y <- 0 until battleField.height
                           pos = BattlePos(x, y)} yield {
    BattleCell(BattlePos(x, y), getCellWidth(x), getCellHeight(y), log, fleet.findByPos(pos), updateGameState)
  }

  // TODO implement a message which complains about the user if he hits a vessel more than one time on a specific position
  // TODO if all parts of a vessel are hit, a log message should indicate that the vessel has sunk. The name should be displayed.
  // TODO make sure that when a vessel was destroyed, it is sunk and cannot be destroyed again. it is destroyed already.
  // TODO if all vessels are destroyed, display this to the user
  // TODO reset game state when a new game is started
  def updateGameState(vessel: Vessel, pos: BattlePos): Unit = ()


  // 'display' cells
  def init(gridPane: GridPane): Unit = {
    gridPane.getChildren.clear()
    for (c <- cells) gridPane.add(c, c.pos.x, c.pos.y)
    cells.foreach(c => c.setFill(Color.BLUE))
  }


  /**
    * Create a new game.
    *
    * This means
    *
    * - resetting all cells to 'empty' state
    * - placing your ships at random on the battleground
    *
    */
  def newGame(gridPane: GridPane) = init(gridPane)


}


class BattleShipController extends Initializable {

  val battleField = BattleField(10, 10)

  lazy val fleet: Fleet = Fleet(battleField)
  lazy val battleGround = BattleGround(battleField, fleet, getCellWidth, getCellHeight, appendLog)

  @FXML private var battleGroundGridPane: GridPane = _

  /**
    * A text area box to place the history of the game
    */
  @FXML private var log: TextArea = _

  @FXML
  def newGame(): Unit = {
    battleGround.newGame(battleGroundGridPane)
    appendLog("New game started.")
  }

  override def initialize(url: URL, rb: ResourceBundle): Unit = {
    assert(battleGroundGridPane != null)
    battleGround.init(battleGroundGridPane)
  }

  private def getCellHeight(y: Int): Double = battleGroundGridPane.getRowConstraints.get(y).getPrefHeight

  private def getCellWidth(x: Int): Double = battleGroundGridPane.getColumnConstraints.get(x).getPrefWidth

  def appendLog(message: String): Unit = log.appendText(message + "\n")

}