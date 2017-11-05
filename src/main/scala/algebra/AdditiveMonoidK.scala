package architect
package algebra

import algebra.stones.AdditiveMonoidKComponent
import annotation.implicitNotFound
// TODO: Add tests
/**
    Higher-kinded alternative to a AdditiveMonoid trait, useful for a higher-kinded types such as List or Option. 

    - Instances are required to satisfy the category laws:

        (associativity of functions) if f : a → b, g : b → c and h : c → d then h <<< (g <<< f) = (h <<< g) <<< f
        (identity of functions) for every object x, there exists a morphism id : x → x, such that 
            for every morphism f : a → x and every morphism g : x → b, we have id <<< f = f and g <<< id = g.

    - The semigroup law:

        (associativity) (x |+| y) |+| z = x |+| (y |+| z)

    - And the additional law:

        (identity) id |+| x = x |+| id = x
*/

@implicitNotFound("A value of type AdditiveMonoidK[${F}] cannot be found")
trait AdditiveMonoidK[F[_]] extends AdditiveMonoidKComponent[F] { self =>

    def construct[A]: AdditiveMonoid[F[A]] =
        new AdditiveMonoid[F[A]]{

            override def add(a: F[A], b: F[A]): F[A] = self.add(a, b)

            override def zero: F[A] = self.zero
            
        }

}
object AdditiveMonoidK {

    def apply[F[_]](implicit instance: AdditiveMonoidK[F]): AdditiveMonoidK[F] = instance

    object Ops {
 
        implicit class toAdditiveMonoidKObject[F[_], A](self: F[A])(implicit tc: AdditiveMonoidK[F]) extends AdditiveMonoidKComponent.Ops[F,A] { 
            override val typeClassInstance = tc
            override val target = self
        }
    }
}