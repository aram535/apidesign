package org.apidesign.effectivelist

import org.junit._
import Assert._

class ListTest {
  // BEGIN: effectivelist.person
  case class Person(name : String, age : Int) extends Listable[Person]
  
  var list : List[Person] = _
  var p1, p2, p3, p4 : Person = _
  
  @Before def initializeListAndValues: Unit = {
    list = new List[Person]
    p1 = new Person("Jarda", 39)
    p2 = new Person("Sona", 37)
    p3 = new Person("Anna", 7)
    p4 = new Person("Ondra", 6)
  }

  @Test def tryFewAdditions = {
    assertTrue(list.add(p1))
    assertTrue(list.add(p2))
    assertTrue(list.add(p3))
    assertTrue(list.add(p4))
    
    assertEquals("Jarda was inserted first", "Jarda", list.get(0).name)
    assertEquals("Sona was inserted 2nd", 37, list.get(1).age)
    assertEquals("Anna was inserted 3rd", "Anna", list.get(2).name)
    assertEquals("Anna was inserted 4th", 6, list.get(3).age)
  }
  // END: effectivelist.person

  @Test def addFewAndThenTryToRemoveHead() = {
    assertTrue(list.add(p1))
    assertTrue(list.add(p2))
    assertTrue(list.add(p3))
    
    assertEquals("Size it three", 3, list.size())
    assertEquals("Jarda was inserted first", "Jarda", list.get(0).name)

    assertFalse("Can't remove not added element", list.remove(p4))
    assertTrue("OK to remove head", list.remove(p1))
    
    assertEquals("Sona is now first", "Sona", list.get(0).name)
    assertEquals("Anna is now 2nd", "Anna", list.get(1).name)
    
    try {
      list.get(2)
      fail("Previous call should throw exception")
    } catch {
      case e : IndexOutOfBoundsException => ; // OK
    }
  }
  
  @Test def addFewAndThenTryToRemoveSecond() = {
    assertEquals(0, list.size())
    assertTrue(list.add(p1))
    assertEquals(1, list.size())
    assertTrue(list.add(p2))
    assertEquals(2, list.size())
    assertTrue(list.add(p3))
    assertEquals(3, list.size())
    
    assertEquals("Size it three", 3, list.size())
    assertEquals("Jarda was inserted first", "Jarda", list.get(0).name)

    assertFalse("Can't remove not added element", list.remove(p4))
    assertTrue("OK to remove head", list.remove(p2))
    
    assertEquals("Jarda is still first", "Jarda", list.get(0).name)
    assertEquals("Anna is now 2nd", "Anna", list.get(1).name)
    
    try {
      list.get(2);
      fail("Previous call should throw exception")
    } catch {
      case e : IndexOutOfBoundsException => ; // OK
    }
    assertEquals(2, list.size());
    
    list.remove(p1)
    list.remove(p3)
    
    assertEquals("Not it is empty", 0, list.size())
  }
}
