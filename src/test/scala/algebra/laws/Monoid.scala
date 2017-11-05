package scala

import org.junit.Test
import org.junit.Assert._

import architect.algebra.Monoid
import architect.algebra.Monoid.Ops._
import architect.instances.option._
import architect.instances.int._
import architect.instances.list._
import architect.instances.coordinates._

class MonoidTest {

    def f(a: Coordinates): Coordinates = a * 6
    def g(a: Coordinates): Coordinates = a - 4
    def h(a: Coordinates): Coordinates = a + 1
    val d = Coordinates(2,5)

    val a: Option[List[Int]] = Some(List(2,5,8))
    val b: Option[List[Int]] = Some(List(9,1,2,3))
    val c: Option[List[Int]] = None

    val monoidO = Monoid[Option[List[Int]]]
    val monoidC = Monoid[Coordinates]

    @Test def associativity(): Unit = {
        assertEquals(monoidO.associative(monoidO.combine)(a, b, c), true)
    }
    
    @Test def associativityAlias(): Unit = {
        assertEquals((a <:> b) <:> c, a <:> (b <:> c))
    }

    @Test def identity(): Unit = {
        assertEquals(monoidO.id <:> a, a )
    }

    @Test def binaryOperation(): Unit = {
       assertEquals(monoidO.combine(a,b), Some(List(11, 3, 4, 5, 14, 6, 7, 8, 17, 9, 10, 11)))
    }

    @Test def associativityFunctions(): Unit = {
        assertEquals(monoidC.composition(d)(f, g, h), true)
    }

    @Test def unitLeft(): Unit = {
       assertEquals(monoidC.unitLeft(d)(f), true)
    }

    @Test def unitRight(): Unit = {
       assertEquals(monoidC.unitRight(d)(f), true)
    }  
}