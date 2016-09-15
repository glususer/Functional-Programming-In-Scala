/**
  * Created by shivangi on 31/05/16.
  */
object FunSets {
  /**
    * We represent a set by its characteristic function, i.e.
    * its `contains` predicate.
    */
  type Set = Int => Boolean

  /**
    * Indicates whether a set contains a given element.
    */
  def contains(s: Set, elem: Int): Boolean = s(elem)
  /**
    * Returns the set of the one given element.
    */
  def singletonSet(elem: Int): Set= {
    y=> y == elem
  }


  /**
    * Returns the union of the two given sets,
    * the sets of all elements that are in either `s` or `t`.
    */
  def union(s: Set, t: Set): Set = (x: Int) => (contains(s, x) || contains(t, x))

  /**
    * Returns the intersection of the two given sets,
    * the set of all elements that are both in `s` and `t`.
    */
  def intersect(s: Set, t: Set): Set = (x: Int) => (contains(s, x) && contains(t, x))

  /**
    * Returns the difference of the two given sets,
    * the set of all elements of `s` that are not in `t`.
    */
  def diff(s: Set, t: Set): Set = (x: Int) => (contains(s, x) && !contains(intersect(s, t), x))

  /**
    * Returns the subset of `s` for which `p` holds.
    */
  def filter(s: Set, p: Int => Boolean): Set = x => contains(s, x) && p(x)


  /**
    * The bounds for `forall` and `exists` are +/- 1000.
    */
  val bound = 1000

  /**
    * Returns whether all bounded integers within `s` satisfy `p`.
    */
  def forall(s: Set, p: Int => Boolean): Boolean = {
    var flag = false
    def iter(a: Int): Boolean = {
      if (contains(s,a) && !p(a))
        flag = false
      else if (contains(s,a)&& p(a)) {
        flag = true
        iter(a+1)
      }
      else
      if(a > -bound && a < bound)iter(a+1)
      if(flag) true
      else false
    }
    iter(-bound)
  }

  /**
    * Returns whether there exists a bounded integer within `s`
    * that satisfies `p`.
    */
  def exists(s: Set, p: Int => Boolean): Boolean = {
    def iter(a:Int):Boolean= {
      if (contains(s, a) && p(a)) true
      else {
        if(a > -bound && a < bound) iter(a+1)
        else false
      }
    }
    iter(-bound+1)
  }

  /**
    * Returns a set transformed by applying `f` to each element of `s`.
    */
  def map(s: Set, f: Int => Int): Set = {
    var empty = ((x:Int)=>false)
    for(i<- -bound to bound)
      if (contains(s, i)) empty = union(empty, singletonSet(f(i)))
    empty
  }

  /**
    * Displays the contents of a set
    */
  def toString(s: Set): String = {
    val xs = for (i <- -bound to bound if contains(s, i)) yield i
    xs.mkString("{", ",", "}")
  }

  /**
    * Prints the contents of a set on the console.
    */
  def printSet(s: Set) {
    println(toString(s))
  }
}