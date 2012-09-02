package org.apidesign.effectivelist

/** 
 * Implementation of add and remove efficient list. It has just one
 * restriction - its items need to carry additional information to help
 * the list to be effective. All items must extend trait {@link Listable}:
 * <pre>
 * <b>case class</b> Person(name : String) <b>extends</b> Listable[Person]
 * </pre>
 */
// BEGIN: effectivelist.list
final class List[T <: Listable[T]] {
  private var item : T = _
  private final var EMPTY : T = _
  
  final def get(index : Int) : T = {
    var pos : T = item;
    for (i <- 1 to index) {
      pos = pos.next;
      if (pos == item) {
        throw new IndexOutOfBoundsException()
      }
    }
    return pos
  }
  
  final def size() : Int = {
    if (item == EMPTY) {
      return 0
    }
    var size = 0
    var pos : T = item
    do {
      size = size + 1
      pos = pos.next
    } while (pos != item)
    return size
  }
  
  final def add(toAdd : T) : Boolean = {
    if (toAdd.prev != EMPTY) {
      return false
    }
    
    if (item == null) {
      item = toAdd
      toAdd.next = toAdd
      toAdd.prev = toAdd
      return true
    } else {
      toAdd.prev = item.prev
      item.prev.next = toAdd
      item.prev = toAdd
      toAdd.next = item
      return true
    }
  }
  final def remove(toRemove : T) : Boolean = {
    if (toRemove.prev == EMPTY) {
      return false
    } else {
      toRemove.prev.next = toRemove.next
      toRemove.next.prev = toRemove.prev;
      if (item.next == item) {
        item = EMPTY
      }
      if (item == toRemove) {
        item = toRemove.next
      }
      toRemove.next = EMPTY
      toRemove.prev = EMPTY
      return true
    }
  }
}
// END: effectivelist.list
