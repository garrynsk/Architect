package architect
package algebra

import algebra.stones.MultiplicativeMonoidComponent
import annotation.implicitNotFound

/**
    A multiplicative monoid is like a usual monoid, but its binary operation is multiplication. 
    We need it to construct a ring.  

    - Instances are required to satisfy the category laws:

        (associativity of functions) if f : a → b, g : b → c and h : c → d then h <<< (g <<< f) = (h <<< g) <<< f

        (identity of functions) for every object x, there exists a morphism id : x → x, such that 
            for every morphism f : a → x and every morphism g : x → b, we have id <<< f = f and g <<< id = g.

    - The semigroup law:

        (associativity) (x |*| y) |*| z = x |*| (y |*| z)

    - And the additional law:

        (identity) id |*| x = x |*| id = x
*/

@implicitNotFound("A value of type MultiplicativeMonoid[${A}] cannot be found")
trait MultiplicativeMonoid[A] extends MultiplicativeMonoidComponent[A]

object MultiplicativeMonoid {

    def apply[A](implicit instance: MultiplicativeMonoid[A]): MultiplicativeMonoid[A] = instance

    object Ops {

        implicit class toMultiplicativeMonoidObject[A](value: A)(implicit tc: MultiplicativeMonoid[A]) extends  MultiplicativeMonoidComponent.Ops[A]{ 

            override def typeClassInstance = tc
            override def target = value
        }
    }

}