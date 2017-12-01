package at.fhj.swengb.apps.battleship

/**
  * Place to put mathematical intersting functions.
  */
object MathUtil {

  /**
    * Shows implemenation of isConnected by using iterative
    * approach. This implementation uses mutable variables and
    * a loop, is not a functional approach and is recommended
    * to avoid.
    *
    * @param seq
    * @return
    */
  private def isConnectedWithIterative(seq: Seq[Int]): Boolean = {

    val sortedList = seq.sorted

    var currentElement = sortedList.head
    var connected: Boolean = true
    for (e <- sortedList) {
      if (connected) {
        connected = currentElement == e
        currentElement = currentElement + 1
      }
    }
    connected
  }

  private def isConnectedWithSlidingWindow(seq: Seq[Int]): Boolean = {
    (for (List(a, b) <- seq.sorted.sliding(2)) yield b - a).forall(_ == 1)
  }

  private def isConnectedWithFoldLeft(seq: Seq[Int]): Boolean = {

    type ACC = (Int,Boolean)

    def computeElem(acc: ACC, curr: Int): ACC = {
      if (acc._2) {
        (curr, curr - acc._1 == 1)
      } else acc
    }

    val sorted = seq.sorted

    sorted.foldLeft((sorted.head - 1, true))(computeElem)._2
  }

  /**
    * Checks if given sequence, when sorted, is ascending and contains no gaps.
    */
  val isConnected: Seq[Int] => Boolean = isConnectedWithIterative

  //val isConnected: Seq[Int] => Boolean = isConnectedWithFoldLeft

  //val isConnected: Seq[Int] => Boolean = isConnectedWithSlidingWindow


}
