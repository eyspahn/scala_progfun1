object sessions {

  println("Welcome to the Scala worksheet")

  val t1 = new NonEmpty(3, new Empty, new Empty)
  val t2 = t1 incl 4


  abstract class IntSet {
    def incl(x: Int): IntSet
    def contains(x: Int): Boolean
    def union(other: IntSet): IntSet
  }


  //class extensions - consider sets as binary trees
  // 2 types of possible trees: a tree for the empty set
  // and a tree consisting of an integer and 2 sub-trees
  // note -- these are sorted - smaller value on LHS of tree

  class Empty extends IntSet{
    def contains(x: Int): Boolean = false
    def incl(x: Int): IntSet = new NonEmpty(x, new Empty, new Empty)
    def union(other: IntSet): IntSet = other

    override def toString = "."
  }

  class NonEmpty(elem: Int, left: IntSet, right: IntSet) extends IntSet {
    def contains(x: Int): Boolean =
      if (x < elem) left contains x
      else if (x > elem) right contains x
      else true

    def incl(x: Int): IntSet =
      if (x < elem) new NonEmpty(elem, left incl x, right)
      else if (x > elem) new NonEmpty(elem, left, right incl x)
      else this

    def union(other: IntSet): IntSet = {
      ((left union right) union other) incl elem
      }

    override def toString = "{" + left + elem + right + "}"

  }

  // IntSet is the superclass of Empty & NonEmpty
  // Empty & NonEmpty are subclasses of IntSet
  // If no superclass given, standard class Object (from Java) is assumed
  // base classes of NonEmpty are IntSet and Object.


  object Empty2 extends IntSet {
    def contains(x: Int): Boolean = false
    def incl(x: Int): IntSet = new NonEmpty(x, Empty2, Empty2)
    def union(other: IntSet): IntSet = other
  } // defines singleton object named Empty2



}

