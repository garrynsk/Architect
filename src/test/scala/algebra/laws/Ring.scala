package scala

import org.junit.Test
import org.junit.Assert._

import architect.algebra.Ring
import architect.algebra.Ring.Ops._
import architect.instances.option._
import architect.instances.int._
import architect.instances.list._
import architect.instances.coordinates._

class RingTest {

    def f(a: Coordinates): Coordinates = a * 6
    def g(a: Coordinates): Coordinates = a - 4
    def h(a: Coordinates): Coordinates = a + 1
    val d = Coordinates(2,5)

    val a: Option[List[Int]] = Some(List(2,5,8))
    val b: Option[List[Int]] = Some(List(9,1,2,3))
    val c: Option[List[Int]] = None
    
    @Test def leftNegation(): Unit = {
        assertEquals(Ring[Option[List[Int]]].leftNegation(Ring[Option[List[Int]]].add)(a), true)
    }

    @Test def rightNegation(): Unit = {
        assertEquals(Ring[Option[List[Int]]].rightNegation(Ring[Option[List[Int]]].add)(a), true)
    }

    @Test def commutativity(): Unit = {
        assertEquals(Ring[Option[List[Int]]].commutative(Ring[Option[List[Int]]].add)(a, b), true)
    }

    @Test def annihilation(): Unit = {
            assertEquals(Ring[Coordinates].annihilation(d), true)
        }

    @Test def associativityAddition(): Unit = {
        assertEquals(Ring[Option[List[Int]]].associative(Ring[Option[List[Int]]].add)(a, b, c), true)
    }

    @Test def identityAddition(): Unit = {
       assertEquals(Ring[Coordinates].identity(d)(f), true)
    }

    @Test def associativityMultiplication(): Unit = {
        assertEquals(Ring[Option[List[Int]]].associative(Ring[Option[List[Int]]].mult)(a, b, c), true)
    }

    @Test def identityMultiplication(): Unit = {
        assertEquals(Ring[Coordinates].identity(d)(f), true)
    }

    @Test def associativityFunctions(): Unit = {
        assertEquals(Ring[Coordinates].composition(d)(f, g, h), true)
    }

    @Test def leftIdentity(): Unit = {
       assertEquals(Ring[Coordinates].unitLeft(d)(f), true)
    }

    @Test def rightIdentity(): Unit = {
       assertEquals(Ring[Coordinates].unitRight(d)(f), true)
    }

}
