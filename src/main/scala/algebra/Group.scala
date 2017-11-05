
package architect
package algebra

import algebra.stones.GroupComponent
import annotation.implicitNotFound

/**

    - Instances are required to satisfy the category laws:

        (associativity of functions) if f : a → b, g : b → c and h : c → d then h <<< (g <<< f) = (h <<< g) <<< f
        (identity of functions) for every object x, there exists a morphism id : x → x, such that 
            for every morphism f : a → x and every morphism g : x → b, we have id <<< f = f and g <<< id = g.

    - The semigroup law:

        (associativity) (x <:> y) <:> z = x <:> (y <:> z)

    - The monoid law:

        (identity) id <:> x = x <:> id = x

    - The groupoid law:

         (inverse) inverse(a) <:> a = id and a <:> inverse(a) = id are always defined.

*/

@implicitNotFound("A value of type Group[${A}] cannot be found")
trait Group[A] extends GroupComponent[A]

object Group {

    def apply[A](implicit instance: Group[A]): Group[A] = instance
    
    object Ops {
 
        implicit class toGroupObject[A](self: A)(implicit tc: Group[A]) extends GroupComponent.Ops[A] { 
            override val typeClassInstance = tc
            override val target = self
        }
    }

}
