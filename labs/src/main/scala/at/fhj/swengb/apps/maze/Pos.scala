package at.fhj.swengb.apps.maze

/**
  * A position in the maze.
  *
  * @param x the x index of the cell
  * @param y the y index of the cell
  */
case class Pos(x: Int, y: Int) {

  def gridIndex(sizeX: Int): Int = sizeX * y + x


  // example how to use immutable data structures in combination with an action on this data structure
  // instead of returning a index, we return a new Pos object.
  def up: Pos = Pos(x, y - 1)

  def right: Pos = Pos(x + 1, y)

  def down: Pos = Pos(x, y + 1)

  def left: Pos = Pos(x - 1, y)
}
