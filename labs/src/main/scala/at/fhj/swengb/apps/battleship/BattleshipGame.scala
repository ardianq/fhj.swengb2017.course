package at.fhj.swengb.apps.battleship

import scala.util.Random


/**
  * Denotes a position on the battle arena
  *
  * @param x logical x coordinate of a part of a battleship
  * @param y logical y coordinate of a part of a battleship
  *
  */
case class BattlePos(x: Int, y: Int)

object BattleField {

  def placeRandomly(bf: BattleField): BattleField = {

    def loop(vesselsToPlace: Set[Vessel], workingBattleField: BattleField): BattleField = {
      if (vesselsToPlace.isEmpty) workingBattleField
      else {
        val v = vesselsToPlace.head
        loop(vesselsToPlace.tail, workingBattleField.addAtRandomPosition(v))
      }

    }

    loop(bf.fleet.vessels, bf.copy(fleet = bf.fleet.copy(vessels = Set())))
  }

}


/**
  * Denotes the size of our region of interest
  */
case class BattleField(width: Int, height: Int, fleet: Fleet) {

  /**
    * Adds vessel at a random, free position in the battlefield. if no position could be found,
    * returns the current battlefield without vessel added.
    *
    * @param v vessel to add
    * @return
    */
  def addAtRandomPosition(v: Vessel): BattleField = {

    def loop(pos: Set[BattlePos], currBf: BattleField, found: Boolean): BattleField = {
      if (found) {
        println(s"Placed vessel of type ${v.getClass.getSimpleName} on battlefield ...")
        currBf
      } else if (pos.isEmpty) {
        println(s"Giving up on vessel of type ${v.getClass.getSimpleName}. No place left.")
        currBf
      } else {
        // take random position out of available positions
        val p = pos.toSeq(Random.nextInt(pos.size))
        val vessel = v.copy(startPos = p)
        if (vessel.occupiedPos.subsetOf(availablePos)) {
          loop(pos - p, currBf.copy(fleet = currBf.fleet.copy(vessels = currBf.fleet.vessels + vessel)), true)
        } else {
          loop(pos - p, currBf, false)
        }
      }
    }

    loop(availablePos, this, false)

  }


  /**
    * All positions in this battlefield
    */
  val allPos: Set[BattlePos] = (for {x <- 0 until width
                                     y <- 0 until height} yield BattlePos(x, y)).toSet


  val availablePos: Set[BattlePos] = allPos -- fleet.occupiedPositions

  def randomFleet(): Fleet = {
    Fleet(Set[Vessel]())
  }


}


case class NonEmptyString(value: String) {
  require(value.nonEmpty, "value must not be empty.")
}

/**
  * A vessel is the common denominator of all ships which are to be defined.
  *
  * Implementation shows that by providing constructors which create the datatype correctly
  * one can save much work verifying that our data invariants are correct. If you think about
  * it, you can proof already with the compiler that the types are correct, which leads to less
  * work with runtime tests - you need less unit tests!
  *
  * @param name each vessel has a (nonempty) name.
  *
  */
case class Vessel(name: NonEmptyString, startPos: BattlePos, direction: Direction, size: Int) {

  // parts a vessel consists of parts, they have to be connected either in x or in y direction
  final val occupiedPos: Set[BattlePos] =
    direction match {
      case Horizontal => (startPos.x until (startPos.x + size)).map(x => BattlePos(x, startPos.y)).toSet
      case Vertical => (startPos.y until (startPos.y + size)).map(y => BattlePos(startPos.x, y)).toSet
    }

}

sealed trait Direction

case object Horizontal extends Direction

case object Vertical extends Direction

object FleetConfig {

  /**
    * Standard configuration for a fleet
    */
  val Standard: FleetConfig =
    FleetConfig(Map(classOf[Battleship] -> 1,
      classOf[Cruiser] -> 2,
      classOf[Destroyer] -> 3,
      classOf[Submarine] -> 4)
    )

  val OneShip: FleetConfig = FleetConfig(Map(classOf[Battleship] -> 1))

  val TwoShips: FleetConfig = FleetConfig(Map(classOf[Battleship] -> 2))


}

/**
  * A configuration for a fleet. How many ships are there for which type
  *
  * @param vesselMap a map which tells us how many instances should be allowed for which vessel type
  */
case class FleetConfig(vesselMap: Map[Class[_ <: Vessel], Int]) {

  // for a given configuration, returns the total number of expected occupied battle positions
  lazy val numberOfTotalOccupiedPositions: Int = {
    vesselMap.map {
      case (tpe, count) => (tpe match {
        case t if t == classOf[Battleship] => Battleship.Size
        case t if t == classOf[Cruiser] => Cruiser.Size
        case t if t == classOf[Destroyer] => Destroyer.Size
        case t if t == classOf[Submarine] => Submarine.Size
        case _ => ???
      }) * count
    }.sum
  }

}


/*
case class BattleConfig(battleField: BattleField, fleet: Fleet) {

  /**
    * All available positions in this configuration
    */
  val availablePos: Set[BattlePos] = battleField.allPos -- fleet.occupiedPositions

}
*/


