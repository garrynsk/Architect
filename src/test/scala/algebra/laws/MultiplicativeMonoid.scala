package scala

import org.junit.Test
import org.junit.Assert._

import architect.algebra.MultiplicativeMonoid
import architect.algebra.MultiplicativeMonoid.Ops._
import architect.instances.option._
import architect.instances.int._
import architect.instances.list._
import architect.instances.coordinates._

class MultiplicativeMonoidTest {

    def f(a: Coordinates): Coordinates = a * 6
    def g(a: Coordinates): Coordinates = a - 4
    def h(a: Coordinates): Coordinates = a + 1
    val d = Coordinates(2,5)

    val a: Option[List[Int]] = Some(List(2,5,8))
    val b: Option[List[Int]] = Some(List(9,1,2,3))
    val c: Option[List[Int]] = None

    val multMonoid = MultiplicativeMonoid[Option[List[Int]]]
    val multMonoidC = MultiplicativeMonoid[Coordinates]

    @Test def associativity(): Unit = {
        assertEquals(MultiplicativeMonoid[Option[List[Int]]].associative(multMonoid.mult)(a, b, c), true)
    }

    @Test def associativityAlias(): Unit = {
        assertEquals((a |*| b) |*| c, a |*| (b |*| c))
    }

    @Test def identity(): Unit = {
        assertEquals(multMonoidC.identity(d)(f), true)
    }
    
    @Test def binaryOperation(): Unit = {
       assertEquals(multMonoid.mult(a,b), Some(List(18, 2, 4, 6, 45, 5, 10, 15, 72, 8, 16, 24)))
    }

    @Test def associativityFunctions(): Unit = {
        assertEquals(multMonoidC.composition(d)(f, g, h), true)
    }

    @Test def leftIdentity(): Unit = {
       assertEquals(multMonoidC.unitLeft(d)(f), true)
    }

    @Test def rightIdentity(): Unit = {
       assertEquals(multMonoidC.unitRight(d)(f), true)
    }
}
