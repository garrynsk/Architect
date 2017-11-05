package scala

import org.junit.Test
import org.junit.Assert._

import architect.algebra.Semigroup
import architect.algebra.Semigroup.Ops._
import architect.instances.option._
import architect.instances.int._
import architect.instances.list._
import architect.instances.coordinates._

class SemigroupTest {

    def f(a: Coordinates): Coordinates = a * 6
    def g(a: Coordinates): Coordinates = a - 4
    def h(a: Coordinates): Coordinates = a + 1
    val d = Coordinates(2,5)

    val a: Option[List[Int]] = Some(List(2,5,8))
    val b: Option[List[Int]] = Some(List(9,1,2,3))
    val c: Option[List[Int]] = None

    val semigroup = Semigroup[Option[List[Int]]]

    @Test def binaryOperation(): Unit = {
       assertEquals(Semigroup[Option[List[Int]]].combine(a, b), Some(List(11, 6, 10)))
    }
    
    @Test def associativity(): Unit = {
        assertEquals(semigroup.associative(semigroup.combine)(a, b, c), true)
    }

    @Test def associativityAlias(): Unit = {
        assertEquals((a <:> b) <:> c, a <:> (b <:> c))
    }

    @Test def associativityFunctions(): Unit = {
        assertEquals(Semigroup[Coordinates].composition(d)(f, g, h), true)
    }
}