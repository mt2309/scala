/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2013, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

package scala.collection
package mutable

import convert.Wrappers.JSetWrapper
import java.util
import util.Collections
import generic.{CanBuildFrom, MutableSetFactory}

/** A hash map with references to entries which are weakly reachable. Entries are
  *  removed from this map when the key is no longer (strongly) referenced. This class wraps
  *  `java.util.WeakHashMap`, using newSetFromMap
  *
  *  @tparam A      type of keys contained in this set
  *
  *  @since 2.10
  *
  *  @define Coll `WeakHashSet`
  *  @define coll weak hash set
  *  @define thatinfo the class of the returned collection. In the standard library configuration,
  *    `That` is always `WeakHashSet[A]`. This is because an implicit of type
  *    `CanBuildFrom[WeakHashMap, A, WeakHashSet[A]]` is defined in object `WeakHashSet`.
  *  @define bfinfo an implicit value of class `CanBuildFrom` which determines the
  *    result class `That` from the current representation type `Repr`
  *    and the new element type `B`. This is usually the `canBuildFrom` value
  *    defined in object `WeakHashSet`.
  *  @define mayNotTerminateInf
  *  @define willNotTerminateInf
  */

class WeakHashSet[A] extends JSetWrapper[A](Collections.newSetFromMap(new util.WeakHashMap))
                     with SetLike[A, WeakHashSet[A]]
{
  override def empty = new WeakHashSet[A]
}

/** $factoryInfo
  *  @define Coll `WeakHashSet`
  *  @define coll weak hash set
  */
object WeakHashSet extends MutableSetFactory[WeakHashSet] {
  implicit def canBuildFrom[A]: CanBuildFrom[Coll, A, WeakHashSet[A]] = setCanBuildFrom[A]
  def empty[A, B]: WeakHashSet[A] = new WeakHashSet[A]
}