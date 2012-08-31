package org.apidesign.effectivelist

/** A member of a {@link List} that makes keeping references
 * to previous and next items in the list effective.
 */
trait Listable[T <: Listable[T]] {
  private[effectivelist] var prev : T = _
  private[effectivelist] var next : T = _
}
