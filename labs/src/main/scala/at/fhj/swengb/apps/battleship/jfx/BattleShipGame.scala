package at.fhj.swengb.apps.battleship.jfx

import javafx.scene.layout.GridPane

import at.fhj.swengb.apps.battleship.{BattleField, BattlePos, Vessel}

import scala.collection.immutable

/**
  * Contains all information about a battleship game.
  *
  * @param battleField
  * @param getCellWidth
  * @param getCellHeight
  * @param log
  */
case class BattleShipGame(battleField: BattleField,
                          getCellWidth: Int => Double,
                          getCellHeight: Int => Double,
                          log: String => Unit) {

  /**
    * remembers which vessel was hit at which position
    * starts with the empty map, meaning that no vessel was hit yet.
    *
    **/
  var hits: Map[Vessel, Set[BattlePos]] = Map()

  /**
    * contains all vessels which are destroyed
    */
  var sunkShips: Set[Vessel] = Set()

  /**
    * We don't ever change cells, they should be initialized only once.
    */
  private val cells: Seq[BattleCell] = for {x <- 0 until battleField.width
                                            y <- 0 until battleField.height
                                            pos = BattlePos(x, y)} yield {
    BattleCell(BattlePos(x, y),
      getCellWidth(x),
      getCellHeight(y),
      log,
      battleField.fleet.findByPos(pos),
      updateGameState)
  }

  // TODO implement a message which complains about the user if he hits a vessel more than one time on a specific position
  // TODO if all parts of a vessel are hit, a log message should indicate that the vessel has sunk. The name should be displayed.
  // TODO make sure that when a vessel was destroyed, it is sunk and cannot be destroyed again. it is destroyed already.
  // TODO if all vessels are destroyed, display this to the user
  // TODO reset game state when a new game is started
  def updateGameState(vessel: Vessel, pos: BattlePos): Unit = {
    log("Vessel " + vessel.name.value + " was hit at position " + pos)

    if (hits.contains(vessel)) {
      // this code is executed if vessel was already hit at least once

      // pos
      // vessel
      // map (hits)

      // we want to update the hits map
      // the map should be updated if
      // we hit a vessel which is already contained
      // in the 'hits' map, and it's values (
      // the set of BattlePos) should be added
      // the current pos
      val oldPos: Set[BattlePos] = hits(vessel)

      hits = hits.updated(vessel, oldPos + pos)

      hits(vessel).foreach(p => log(p.toString))

      if (oldPos.contains(pos)) {
        log("Position was triggered two times.")
      }

      if (vessel.occupiedPos == hits(vessel)) {
        log(s"Ship ${vessel.name.value} was destroyed.")
        sunkShips = sunkShips + vessel

        if (battleField.fleet.vessels == sunkShips) {
          log("G A M E   totally  O V E R")
        }
      }



    } else {
      // vessel is not part of the map
      // but vessel was hit!
      // it was hit the first time ever!
      hits = hits.updated(vessel, Set(pos))
    }

  }


  // 'display' cells
  def init(gridPane: GridPane): Unit = {
    gridPane.getChildren.clear()
    for (c <- cells) gridPane.add(c, c.pos.x, c.pos.y)
    cells.foreach(c => c.init)
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
