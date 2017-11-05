package architect
package functors

import functors.glue.YonedaComponent
import annotation.implicitNotFound

/**
    The Yoneda embedding is the functor y : C → Sets^Cop taking C to the contravariant representable functor,
        yC = Hom(−, C) : Cop → Sets
    and taking f : C → D to the natural transformation,
    yf = Hom C (−, f ) : Hom C (−, C) → Hom C (−, D).
*/

@implicitNotFound("Values of type ${F} cannot be found")
trait Yoneda[F[_], A] extends YonedaComponent[F, A] 

object Yoneda {

   def apply[F[_], A](implicit instance: Yoneda[F, A]): Yoneda[F, A] = instance

    object Ops {
 
        implicit class toYoneda[F[_], A](self: F[A])(implicit tc: Yoneda[F, A]) extends YonedaComponent.Ops[F, A] { 
            override val typeClassInstance = tc
            override val target = self
        }
    }

}