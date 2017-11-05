package scala

import org.junit.Test
import org.junit.Assert._

import architect.algebra.Group
import architect.algebra.Group.Ops._
import architect.instances.option._
import architect.instances.int._
import architect.instances.list._
import architect.instances.coordinates._

class GroupTest {
    
    def f(a: Coordinates): Coordinates = a * 6
    def g(a: Coordinates): Coordinates = a - 4
    def h(a: Coordinates): Coordinates = a + 1
    val d = Coordinates(2,5)

    val a: Option[List[Int]] = Some(List(2,5,8))
    val b: Option[List[Int]] = Some(List(9,1,2,3))
    val c: Option[List[Int]] = None

    val groupO = Group[Option[List[Int]]]
    val groupC = Group[Coordinates]

    @Test def leftInverse(): Unit = {
        assertEquals(groupO.leftInverse(groupO.combine)(a), true)
    }

    @Test def rightInverse(): Unit = {
        assertEquals(groupO.rightInverse(groupO.combine)(a), true)
    }

    @Test def associativity(): Unit = {
        assertEquals(groupO.associative(groupO.combine)(a, b, c), true)
    }
    
    @Test def associativityAlias(): Unit = {
        assertEquals((a <:> b) <:> c, a <:> (b <:> c))
    }

    @Test def identity(): Unit = {
        assertEquals(groupO.id <:> a, a )
    }

    @Test def binaryOperation(): Unit = {
       assertEquals(groupO.combine(a,b), Some(List(5, 10, 14, 6, 9, 17, 7, 3, 11, 8, 4)))
    }

    @Test def associativityFunctions(): Unit = {
        assertEquals(groupC.composition(d)(f, g, h), true)
    }

    @Test def unitLeft(): Unit = {
       assertEquals(groupC.unitLeft(d)(f), true)
    }

    @Test def unitRight(): Unit = {
       assertEquals(groupC.unitRight(d)(f), true)
    }  

}