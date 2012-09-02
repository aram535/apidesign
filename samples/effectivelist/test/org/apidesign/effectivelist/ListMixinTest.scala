package org.apidesign.effectivelist

import org.junit._
import Assert._

// BEGIN: effectivelist.point
class Point(val x: Int, val y : Int);
// END: effectivelist.point

class ListMixinTest {
  // BEGIN: effectivelist.convert
  class ListablePoint(x: Int, y: Int)
  extends Point(x,y) with Listable[ListablePoint] {
    def this(r : Point) = this(r.x, r.y)
  }
  implicit def toList(p : Point) : ListablePoint = new ListablePoint(p)

  @Test def useThePlainOldPointInList : Unit = {
    val list = new List[ListablePoint]
    list.add(new Point(10, 20))
    assertEquals("One element", 1, list.size)
    assertEquals("X is 10", 10, list.get(0).x)
    assertEquals("y is 20", 20, list.get(0).y)
  }
  // END: effectivelist.convert

}
