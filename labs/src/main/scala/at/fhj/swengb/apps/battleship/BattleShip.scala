package at.fhj.swengb.apps.battleship

object Battleship {
  // defines number of occupied battle positions for corresponding vessel
  val Size = 5
}

object Cruiser {
  // defines number of occupied battle positions for corresponding vessel
  val Size = 4
}

object Destroyer {
  // defines number of occupied battle positions for corresponding vessel
  val Size = 3
}

object Submarine {
  // defines number of occupied battle positions for corresponding vessel
  val Size = 2
}

/**
  * A battleship has a name and a set of positions.
  *
  * Those positions have to be connected. Also they have to be in a straight line, that means
  * that either all x coordinates are equal or all y coordinates are equal.
  *
  * Often it is far easier to use the convenience constructor defined in the companion object to construct
  * a battleship.
  *
  * @param shipName the name of the ship (must be set and not empty)
  */
class Battleship(shipName: String, pos: BattlePos, direction: Direction) extends Vessel(NonEmptyString(shipName), pos, direction, Battleship.Size)

class Cruiser(shipName: String, pos: BattlePos, direction: Direction) extends Vessel(NonEmptyString(shipName), pos, direction, Cruiser.Size)

class Destroyer(shipName: String, pos: BattlePos, direction: Direction) extends Vessel(NonEmptyString(shipName), pos, direction, Destroyer.Size)

class Submarine(shipName: String, pos: BattlePos, direction: Direction) extends Vessel(NonEmptyString(shipName), pos, direction, Submarine.Size)