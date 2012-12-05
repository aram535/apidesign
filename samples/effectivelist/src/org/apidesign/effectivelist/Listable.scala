package org.apidesign.effectivelist

/** A member of a {@link List} that makes keeping references
 * to previous and next items in the list effective.
 */
// BEGIN: effectivelist.item
trait Listable[T] {
  private[effectivelist] var prev : T = _
  private[effectivelist] var next : T = _
}
// END: effectivelist.item
