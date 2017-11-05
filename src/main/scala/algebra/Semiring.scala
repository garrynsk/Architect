
package architect
package algebra

import algebra.stones.SemiringComponent
import annotation.implicitNotFound

/**
    A semiring is a set R equipped with two binary operations + and ⋅, called addition and multiplication.
    
    - Instances are required to satisfy the category laws:

        (associativity of functions) if f : a → b, g : b → c and h : c → d then h <<< (g <<< f) = (h <<< g) <<< f

        (identity of functions) for every object x, there exists a morphism id : x → x, such that 
            for every morphism f : a → x and every morphism g : x → b, we have id <<< f = f and g <<< id = g.

    - Commutative monoid under addition:

        (associativity) (a |+| b) |+| c = a |+| (b |+| c)

        (identity) zero |+| a = a |+| zero = a

        (commutativity) a |+| b = b |+| a

    - Monoid under multiplication:

        (associativity) (a |*| b) |*| c = a |*| (b |*| c)

        (identity) one |*| a = a |*| one = a

    - Multiplication distributes over addition:

        Left distributivity: a |*| (b |+| c) = (a |*| b) |+| (a |*| c)

        Right distributivity: (a |+| b) |*| c = (a |*| c) |+| (b |*| c)
        
    - Annihilation: zero |*| a = a |*| zero = zero

*/

@implicitNotFound("Values of types Semiring[${A}] cannot be found")
trait Semiring[A] extends SemiringComponent[A] 

object Semiring {

    def apply[A](implicit instance: Semiring[A]): Semiring[A] = instance 

    object Ops {
        
        implicit class toSemiringObject[A](self: A)(implicit tc: Semiring[A]) extends SemiringComponent.Ops[A]  { 

            override def typeClassInstance = tc
            override def target = self
        }
    }
}
