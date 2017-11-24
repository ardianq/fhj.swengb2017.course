package at.fhj.swengb

import org.scalatest.WordSpecLike

class MathUtilSpec extends WordSpecLike {

  "MathUtil.isConnected" should {
    "return true if given a connected sorted Seq" in {
      assert(MathUtil.isConnected(1 to 10))
    }
    "return false if given a nonconnected sorted Seq" in {
      assert(!MathUtil.isConnected(Seq(1,2,3,7)))
    }

    "return true if given a connected Seq" in {
      assert(MathUtil.isConnected(Seq(3,1,5,2,4)))
    }
  }

}
