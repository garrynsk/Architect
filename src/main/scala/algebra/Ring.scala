package architect
package algebra

import algebra.stones.RingComponent
import annotation.implicitNotFound

/**

The Ring trait is for types that support addition, multiplication, and subtraction operations.

Instances must satisfy the following laws:

- The category laws:

    (associativity of functions) if f : a → b, g : b → c and h : c → d then h <<< (g <<< f) = (h <<< g) <<< f

    (identity of functions) for every object x, there exists a morphism id : x → x, such that 
        for every morphism f : a → x and every morphism g : x → b, we have id <<< f = f and g <<< id = g.

- Ring is an abelian group under addition

    (associativity) (a |+| b) |+| c = a |+| (b |+| c)

    (identity) zero |+| a = a |+| zero = a

    (commutativity) a |+| b = b |+| a

    (inverse) g |-| g = id = g |-| g 

- Ring is a monoid under multiplication

    (associativity) (a |*| b) |*| c = a |*| (b |*| c)

    (identity) one |*| a = a |*| one = a

- Multiplication distributes over addition:

    (left distributivity) a |*| (b |+| c) = (a |*| b) |+| (a |*| c)

    (right distributivity) (a |+| b) |*| c = (a |*| c) |+| (b |*| c)

*/
trait newRing
@implicitNotFound("Values of types ${A} cannot be found")
trait Ring[A] extends RingComponent[A] 
object Ring {

    def apply[A](implicit instance: Ring[A]): Ring[A] = instance

    object Ops {
        
        implicit class toRingObject[A](value: A)(implicit tc: Ring[A]) extends RingComponent.Ops[A]  { 

            override def typeClassInstance = tc
            override def target = value
        }
    }

}
