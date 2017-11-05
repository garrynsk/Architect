
package architect
package functors

import functors.glue.ProfunctorComponent
import annotation.implicitNotFound


//To say something is a Profunctor in Haskell is just to say it is contravariant in the first argument and covariant in the second.
@implicitNotFound("Values of type ${F} cannot be found for Bifunctor")
trait Profunctor[F[_, _]] extends ProfunctorComponent[F] 

object Profunctor {

    def apply[F[_,_]](implicit instance: Profunctor[F]): Profunctor[F] = instance

    object Ops {
 
        implicit class toProfunctor[F[_,_], A, B](self: F[A, B])(implicit tc: Profunctor[F]) extends ProfunctorComponent.Ops[F, A, B] { 
            override val typeClassInstance = tc
            override val target = self
        }
    }
}
