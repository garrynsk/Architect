package scala

import org.junit.Test
import org.junit.Assert._

import architect.algebra.Semiring
import architect.algebra.Semiring.Ops._
import architect.instances.option._
import architect.instances.int._
import architect.instances.list._
import architect.instances.coordinates._

class SemiringTest {

    def f(a: Coordinates): Coordinates = a * 6
    def g(a: Coordinates): Coordinates = a - 4
    def h(a: Coordinates): Coordinates = a + 1
    val d = Coordinates(2,5)

    val a: Option[List[Int]] = Some(List(2,5,8))
    val b: Option[List[Int]] = Some(List(9,1,2,3))
    val c: Option[List[Int]] = None


    @Test def commutativity(): Unit = {
        assertEquals(Semiring[Option[List[Int]]].commutative(Semiring[Option[List[Int]]].add)(a, b), true)
    }

    @Test def annihilation(): Unit = {
            assertEquals(Semiring[Coordinates].annihilation(d), true)
        }

    @Test def associativityAddition(): Unit = {
        assertEquals(Semiring[Option[List[Int]]].associative(Semiring[Option[List[Int]]].add)(a, b, c), true)
    }

    @Test def identityAddition(): Unit = {
       assertEquals(Semiring[Coordinates].identity(d)(f), true)
    }

    @Test def associativityMultiplication(): Unit = {
        assertEquals(Semiring[Option[List[Int]]].associative(Semiring[Option[List[Int]]].mult)(a, b, c), true)
    }

    @Test def identityMultiplication(): Unit = {
        assertEquals(Semiring[Coordinates].identity(d)(f), true)
    }

    @Test def associativityFunctions(): Unit = {
        assertEquals(Semiring[Coordinates].composition(d)(f, g, h), true)
    }

    @Test def leftIdentity(): Unit = {
       assertEquals(Semiring[Coordinates].unitLeft(d)(f), true)
    }

    @Test def rightIdentity(): Unit = {
       assertEquals(Semiring[Coordinates].unitRight(d)(f), true)
    }
}
