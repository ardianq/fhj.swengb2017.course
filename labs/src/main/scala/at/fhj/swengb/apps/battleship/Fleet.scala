package at.fhj.swengb.apps.battleship

import scala.util.Random

object Fleet {

  val Empty = Fleet(Set[Vessel]())


  // TODO add other vessels as well to the fleet
  // TODO add more randomness to the placement of the fleet.
  // TODO add constraints such that all ships are placed completely _in_ the battlefield
  // TODO maybe a battlefield is too small for a Battleship?
  // TODO what is the smallest battlefield all vessels could be placed on?
  def apply(battleField: BattleField): Fleet = {
    Default
  }

  def apply(fleetConfig: FleetConfig): Fleet = {
    val vessels: Set[Vessel] =
      (for {((k, v), i) <- fleetConfig.vesselMap.zipWithIndex
            a <- 0 until v} yield {
        val direction = if (Random.nextBoolean()) Horizontal else Vertical
        k match {
          case x if x == classOf[Battleship] => new Battleship(s"Battleship $i $a", BattlePos(0, 0), direction)
          case x if x == classOf[Cruiser] => new Cruiser(s"Cruiser $i $a", BattlePos(0, 0), direction)
          case x if x == classOf[Destroyer] => new Destroyer(s"Destroyer $i $a", BattlePos(0, 0), direction)
          case x if x == classOf[Submarine] => new Submarine(s"Submarine $i $a", BattlePos(0, 0), direction)
        }
      }).toSet


    Fleet(vessels)
  }

  val Default: Fleet = {
    val battleships: Set[Vessel] = Set(new Battleship("Archduke John", BattlePos(0, 0), Vertical))
    val cruisers: Set[Vessel] = Set(new Cruiser("Cruz", BattlePos(1, 0), Vertical), new Cruiser("Santa", BattlePos(2, 0), Vertical))
    val destroyers: Set[Vessel] = Set(
      new Destroyer("Graz", BattlePos(5, 5), Horizontal),
      new Destroyer("Wien", BattlePos(0, 6), Horizontal),
      new Destroyer("Linz", BattlePos(0, 7), Horizontal),
    )
    val submarines: Set[Vessel] = Set(
      new Submarine("A", BattlePos(6, 6), Horizontal),
      new Submarine("A", BattlePos(1, 6), Horizontal),
      new Submarine("A", BattlePos(3, 2), Horizontal),
      new Submarine("A", BattlePos(4, 4), Horizontal),
    )

    val fleet: Set[Vessel] = battleships ++ cruisers ++ destroyers ++ submarines
    Fleet(fleet)
  }

}

// ships may not overlap, each vessel should have a distinct place on the battleground
case class Fleet(vessels: Set[Vessel]) {

  def occupiedPositions: Set[BattlePos] = vessels.flatMap(v => v.occupiedPos)

  def findByPos(pos: BattlePos): Option[Vessel] = vessels.find(v => v.occupiedPos.contains(pos))

  def findByName(name: String): Option[Vessel] = vessels.find(v => v.name == VesselName(name))

}