
package architect
package algebra

import algebra.stones.MonoidKComponent
import annotation.implicitNotFound

/**
    Higher-kinded alternative to a Monoid trait, useful for a higher-kinded types such as List or Option.
    
    Instances are required to satisfy the category laws:

        (associativity) if f : a → b, g : b → c and h : c → d then h <<< (g <<< f) = (h <<< g) <<< f

        (identity) for every object x, there exists a morphism id : x → x, such that 
            for every morphism f : a → x and every morphism g : x → b, we have id <<< f = f and g <<< id = g.

    The semigroup law:

        (associativity) (x <:> y) <:> z = x <:> (y <:> z)

    And the additional law:

        (identity) id <> x = x <> id = x
*/


// Can't extend from Category, as the kinds are not equal for the method
@implicitNotFound("A value of type MonoidK[${F}] cannot be found")
trait MonoidK[F[_]] extends MonoidKComponent[F] { self =>

    def construct[A]: Monoid[F[A]] =
        new Monoid[F[A]]{

            override def combine(a: F[A], b: F[A]): F[A] = self.combine(a, b)

            override def id: F[A] = self.id[A]

        }
}

object MonoidK {

    def apply[F[_]](implicit instance: MonoidK[F]): MonoidK[F] = instance


    object Ops {

        implicit class toMonoidKObject[F[_],A](self: F[A])(implicit tc: MonoidK[F]) extends MonoidKComponent.Ops[F,A] { 
            override val typeClassInstance = tc
            override val target = self
        }
    }

}