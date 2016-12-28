package funsets

import org.scalatest.FunSuite


import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * This class is a test suite for the methods in object FunSets. To run
 * the test suite, you can either:
 *  - run the "test" command in the SBT console
 *  - right-click the file in eclipse and chose "Run As" - "JUnit Test"
 */
@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite {

  /**
   * Link to the scaladoc - very clear and detailed tutorial of FunSuite
   *
   * http://doc.scalatest.org/1.9.1/index.html#org.scalatest.FunSuite
   *
   * Operators
   *  - test
   *  - ignore
   *  - pending
   */

  /**
   * Tests are written using the "test" operator and the "assert" method.
   */
  // test("string take") {
  //   val message = "hello, world"
  //   assert(message.take(5) == "hello")
  // }

  /**
   * For ScalaTest tests, there exists a special equality operator "===" that
   * can be used inside "assert". If the assertion fails, the two values will
   * be printed in the error message. Otherwise, when using "==", the test
   * error message will only say "assertion failed", without showing the values.
   *
   * Try it out! Change the values so that the assertion fails, and look at the
   * error message.
   */
  // test("adding ints") {
  //   assert(1 + 2 === 3)
  // }


  import FunSets._

  test("contains is implemented") {
    assert(contains(x => true, 100))
    info("contains is implemented!")
  }

  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   *
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   *
   *   val s1 = singletonSet(1)
   *
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   *
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   *
   */

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
    val s4 = singletonSet(1)
  }

  /**
   * This test is currently disabled (by using "ignore") because the method
   * "singletonSet" is not yet implemented and the test would fail.
   *
   * Once you finish your implementation of "singletonSet", exchange the
   * function "ignore" by "test".
   */

  test("singletonSet(1) contains 1") {
    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3".
     */
    new TestSets {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "S1 should contain 1")
      assert(!contains(s2, 1), "s2 should not contain 1")
    }
  }

  test("union contains all elements of each set") {
    new TestSets {
      val su = union(s1, s2)
      assert(contains(su, 1), "Union 1")
      assert(contains(su, 2), "Union 2")
      assert(!contains(su, 3), "Union 3")
    }
  }

  test("intersect contains only the same elements of each set") {
    new TestSets {
      val si_1 = intersect(s1, s4)
      val si_2 = intersect(s2, s3)
      assert(contains(si_1, 1), "intersect 1")
      assert(!contains(si_2, 2), "intersect 2")
    }
  }

  test("diff contains elements which are not in both sets") {
//    the set of all elements of `s` that are not in `t`
    new TestSets {
      val setdiff1 = diff(s1, s2)
      val setdiff2 = diff(s1, s4)
      assert(contains(setdiff1, 1), "diff 1")
      assert(!contains(setdiff2, 1), "diff 2")
    }
  }

  test("filter returns the subset of `s` for which `p` holds"){
    new TestSets {
      val setfilter1 = filter(union(s1, s2), (x: Int)=>x%2==0)
      assert(contains(setfilter1, 2), "contains 1")
      assert(!contains(setfilter1, 1), "contains 2")
    }
  }

  test("forall checks if all elements in set satisfies p") {
    new TestSets{
      assert(forall(union(s1, s3),(x:Int)=> x%2!=0), "forall odd")
      assert(forall(s2, (x:Int)=> x%2==0), "forall s2")
    }
  }

  test("exists checks to see if there is an integer in s that satisfies p") {
    new TestSets{
      assert(exists(s1, (n: Int)=> (n==1)), "exists 1")
      assert(exists(union(s1, s3), (n: Int)=> (n==1)), "exists 2")
      assert(!exists(s1, (n: Int)=> (n==2)), "exists 3")

    }
  }

  test("map applies a function to every element in set") {
    new TestSets{
      val mapped = map(union(s1, s2), (x: Int)=> x+5)
      assert(contains(mapped, 6), "mapped 6")
      assert(contains(mapped, 7), "mapped 7")
      assert(!contains(mapped, 8), "mapped 8")
      assert(!contains(mapped, 1), "mapped 1")
    }
  }

}
