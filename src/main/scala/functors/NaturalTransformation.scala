
package architect
package functors

import functors.glue.NaturalTransformationComponent
import annotation.implicitNotFound

@implicitNotFound("Values of type ${F} cannot be found")
trait NaturalTransformation[-F[_], +G[_]] extends NaturalTransformationComponent[F, G]

object NaturalTransformation {

    def apply[F[_], G[_]](implicit instance: NaturalTransformation[F,G]): NaturalTransformation[F,G] = instance

    object Ops {
 
        implicit class toNaturalTransformation[-F[_], +G[_], A](self: G[A])(implicit tc: NaturalTransformation[F, G]) extends NaturalTransformationComponent.Ops[F, G, A] { 
            override val typeClassInstance = tc
            override val target = self
        }
    }

}