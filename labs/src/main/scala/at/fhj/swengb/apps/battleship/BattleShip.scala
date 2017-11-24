package at.fhj.swengb.apps.battleship

object BattleShip {

  /**
    * This is a convenience constructor for a Battleship. You can define a starting position and
    * a direction and don't need to define all positions explicitly.
    *
    * @param name the name of the battleship
    * @param p    starting position
    * @param d    direction
    * @return
    */
  def apply(name: String, p: BattlePos, d: Direction): BattleShip = {
    d match {
      case Horizontal => BattleShip(name, (p.x until (p.x + 5)).map(x => BattlePos(x, p.y)).toSet)
      case Vertical => BattleShip(name, (p.y until (p.y + 5)).map(y => BattlePos(p.x, y)).toSet)
    }
  }

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
  * @param name      the name of the ship (must be set and not empty)
  * @param positions the positions
  */
case class BattleShip(name: String, positions: Set[BattlePos]) extends Vessel {

  // every battleship has to have a name
  require(name.nonEmpty, "Name has to be set.")

  // require proofs that positions is of size 5
  require(positions.size == 5, s"For mighty battleship '$name' required 5 positions, but got ${positions.size}.")

  // a requirement is missing!

  // mission: we have to proof that all x positions or all y positions are the same and that all cells are connected.

  private val allXCoordinatesAreTheSame = positions.map(_.x).size == 1

  private val allYCoordinatesAreTheSame = positions.map(_.y).size == 1

  val allCoordinatesAreTheSameForXOrY: Boolean = allXCoordinatesAreTheSame || allYCoordinatesAreTheSame

  // either all x coordinates are the same or all y coordinates are the same
  require(allCoordinatesAreTheSameForXOrY)

  // additional requirement is needed to check for connectedness
  require(true)
}