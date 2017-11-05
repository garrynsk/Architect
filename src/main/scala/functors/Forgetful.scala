package architect
package functors

import functors.glue.ForgetfulComponent
import Alias._
import annotation.implicitNotFound

/**
    A forgetful functor is a functor which is defined by ‘forgetting’ something. 
    For example, the forgetful functor from Grp to Set forgets the group structure of a group, remembering only the underlying set.
    TODO: Informally, a free functor is a left adjoint to a forgetful functor – part of a free-forgetful adjunction. 
*/
@implicitNotFound("Values of type ${F} cannot be found")
trait Forgetful[F[_], G[_]] extends ForgetfulComponent[F, G] 

object Forgetful {

    def apply[F[_], G[_]](implicit instance: Forgetful[F, G]): Forgetful[F, G] = instance

    object Ops {
 
        implicit class toForgetful[F[_], G[_], A, B](self: F[A] & G[B])(implicit tc: Forgetful[F, G]) extends ForgetfulComponent.Ops[F, G, A, B] { 
            override val typeClassInstance = tc
            override val target = self
        }
    }

}