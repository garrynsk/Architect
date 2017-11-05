
package architect
package algebra

import algebra.stones.MultiplicativeGroupComponent
import annotation.implicitNotFound
// TODO: Add tests
/**

    - Instances are required to satisfy the category laws:

        (associativity of functions) if f : a → b, g : b → c and h : c → d then h <<< (g <<< f) = (h <<< g) <<< f

        (identity of functions) for every object x, there exists a morphism id : x → x, such that 
            for every morphism f : a → x and every morphism g : x → b, we have id <<< f = f and g <<< id = g.

    - The semigroup law:

        (associativity) (x |*| y) |*| z = x |*| (y |*| z)

    - The monoid law:

        (identity) id |*| x = x |*| id = x

    - The groupoid law:

         (inverse) inverse(a) |*| a = one and a |*| inverse(a) = one are always defined.

*/

@implicitNotFound("Values of types ${A} cannot be found")
trait MultiplicativeGroup[A] extends MultiplicativeGroupComponent[A] 

object MultiplicativeGroup {

    def apply[A](implicit instance: MultiplicativeGroup[A]): MultiplicativeGroup[A] = instance

    object Ops {
 
        implicit class toMultiplicativeGroupObject[A](value: A)(implicit tc: MultiplicativeGroup[A]) extends MultiplicativeGroupComponent.Ops[A] { 
            
            override val typeClassInstance = tc
            override val target = value
        }
    }   

}
