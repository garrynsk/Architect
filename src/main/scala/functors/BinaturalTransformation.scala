
package architect
package functors

import functors.glue.BinaturalTransformationComponent
import annotation.implicitNotFound

@implicitNotFound("Values of type ${F} cannot be found")
trait BinaturalTransformation[-F[_,_], +G[_,_]] extends BinaturalTransformationComponent[F, G]

object BinaturalTransformation {

    def apply[F[_,_], G[_,_]](implicit instance: BinaturalTransformation[F,G]): BinaturalTransformation[F,G] = instance

    object Ops {
 
        implicit class toNaturalTransformation[-F[_,_], +G[_,_], A, B](self: G[A, B])(implicit tc: BinaturalTransformation[F, G]) extends BinaturalTransformationComponent.Ops[F, G, A, B] { 
            override val typeClassInstance = tc
            override val target = self
        }
    }

}