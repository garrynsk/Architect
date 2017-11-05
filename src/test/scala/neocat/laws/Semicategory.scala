package scala

import org.junit.Test
import org.junit.Assert._

import architect.neocat.Semicategory
import architect.instances.coordinates._

class SemicategoryTest {

  def f(a: Coordinates): Coordinates = a * 4
  def g(a: Coordinates): Coordinates = a - 4
  def h(a: Coordinates): Coordinates = a + 1
  val a = Coordinates(2,5)

  @Test def associativity(): Unit = {
        assertEquals(Semicategory[Coordinates].composition(a)(f, g, h), true)
  }

}