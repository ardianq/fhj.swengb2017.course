package at.fhj.swengb.apps.battleship

import at.fhj.swengb.apps.battleship._
import org.scalatest.WordSpecLike

/**
  * Specifications for the Battleship class.
  */
class FleetSpec extends WordSpecLike {

  val bp = BattlePos(0, 0)
  val battleship = new Battleship("a name", bp, Vertical)
  val cruiser = new Cruiser("a cruiser", bp, Vertical)
  val destroyer = new Destroyer("a destroyer", bp, Vertical)
  val submarine = new Submarine("a submarine", bp, Vertical)

  "Ships" should {
    "be instantiable for battleship" in assert(battleship != null)
    "be instantiable for cruiser" in assert(cruiser != null)
    "be instantiable for destroyer" in assert(destroyer != null)
    "be instantiable for submarine" in assert(submarine != null)
    "a battleship without a name can clear the port" in intercept[IllegalArgumentException](new Battleship("", bp, Vertical))
  }


  "Battlefield.placerandom" should {
    "one ship" in {
      val bf = mkRandomBattleField(10, 10, FleetConfig.OneShip)
      assert(bf.fleet.vessels.size == 1)
      assert(bf.fleet.occupiedPositions.size == 5)
    }
    "creates fleet positions which don't overlap for simple config" in {
      assert(FleetConfig.OneShip.numberOfTotalOccupiedPositions == mkRandomBattleField(10, 10, FleetConfig.OneShip).fleet.occupiedPositions.size)
    }
    "creates fleet positions which don't overlap for standard config" in {
      assert(FleetConfig.Standard.numberOfTotalOccupiedPositions == mkRandomBattleField(10, 10, FleetConfig.Standard).fleet.occupiedPositions.size)
    }

  }

  private def mkRandomBattleField(width: Int, height: Int, config: FleetConfig): BattleField = {
    val bf = BattleField(width, height, Fleet(config))
    BattleField.placeRandomly(bf)
  }
}
