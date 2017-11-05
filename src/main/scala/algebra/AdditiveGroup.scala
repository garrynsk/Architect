package architect
package algebra

import algebra.stones.AdditiveGroupComponent
import annotation.implicitNotFound
// TODO: Add tests
/**

    - Instances are required to satisfy the category laws:

        (associativity of functions) if f : a → b, g : b → c and h : c → d then h <<< (g <<< f) = (h <<< g) <<< f

        (identity of functions) for every object x, there exists a morphism id : x → x, such that 
            for every morphism f : a → x and every morphism g : x → b, we have id <<< f = f and g <<< id = g.

    - The semigroup law:

        (associativity) (x |+| y) |+| z = x |+| (y |+| z)

    - The monoid law:

        (identity) id |+| x = x |+| id = x

    - The groupoid law:

         (inverse) inverse(a) |+| a = zero and a |+| inverse(a) = zero are always defined.

*/

@implicitNotFound("Values of types ${A} cannot be found")
trait AdditiveGroup[A] extends AdditiveGroupComponent[A]

object AdditiveGroup {

    def apply[A](implicit instance: AdditiveGroup[A]): AdditiveGroup[A] = instance

    object Ops {

        implicit class toAdditiveGroupObject[A](value: A)(implicit tc: AdditiveGroup[A]) extends AdditiveGroupComponent.Ops[A]  { 

            override val typeClassInstance = tc
            override val target = value
        }
    }

}