package scala

import org.junit.Test
import org.junit.Assert._

import architect.neocat.Category
import architect.instances.coordinates._

class CategoryTest {

    def f(a: Coordinates): Coordinates = a * 6
    def g(a: Coordinates): Coordinates = a - 4
    def h(a: Coordinates): Coordinates = a + 1
    val a = Coordinates(2,5)

    @Test def associativity(): Unit = {
            assertEquals(Category[Coordinates].composition(a)(f, g, h), true)
    }

    @Test def unitLeft(): Unit = {
       assertEquals(Category[Coordinates].unitLeft(a)(f), true)
    }

    @Test def unitRight(): Unit = {
       assertEquals(Category[Coordinates].unitRight(a)(f), true)
    }

}