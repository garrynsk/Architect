
package architect
package algebra

import algebra.stones.MultiplicativeMonoidKComponent
import annotation.implicitNotFound
// TODO: Add tests
/**
    Higher-kinded alternative to a MultiplicativeMonoid trait, useful for a higher-kinded types such as List or Option. 

    - Instances are required to satisfy the category laws:

        (associativity of functions) if f : a → b, g : b → c and h : c → d then h <<< (g <<< f) = (h <<< g) <<< f
        (identity of functions) for every object x, there exists a morphism id : x → x, such that 
            for every morphism f : a → x and every morphism g : x → b, we have id <<< f = f and g <<< id = g.

    - The semigroup law:

        (associativity) (x |*| y) |*| z = x |*| (y |*| z)

    - And the additional law:

        (identity) id |*| x = x |*| id = x
*/

@implicitNotFound("A value of type MultiplicativeMonoidK[${F}] cannot be found")
trait MultiplicativeMonoidK[F[_]] extends MultiplicativeMonoidKComponent[F] { self =>

    def construct[A](implicit tc: MultiplicativeMonoidK[F]): MultiplicativeMonoid[F[A]] =
        new MultiplicativeMonoid[F[A]]{   

            override def mult(a: F[A], b: F[A]): F[A] = self.mult(a, b)

            override def one: F[A] = self.one
            
        }
    
}
object MultiplicativeMonoidK {

    def apply[F[_]](implicit instance: MultiplicativeMonoidK[F]): MultiplicativeMonoidK[F] = instance

    object Ops {

        implicit class toMultiplicativeMonoidKObject[F[_], A](self: F[A])(implicit tc: MultiplicativeMonoidK[F]) extends MultiplicativeMonoidKComponent.Ops[F,A] { 
            
            override val typeClassInstance = tc
            override val target = self
        }
    }
}