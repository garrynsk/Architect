package architect
package functors

import functors.glue.HomFunctorComponent
import Alias._
import annotation.implicitNotFound

/** 
    
    Hom(–,–) is a bifunctor from Aop × A to Set which is contravariant in the first argument and covariant in the second. 
    Equivalently, we may say that Hom(–,–) is a profunctor
        Hom(–,–) : Aop × A → Set
    where Aop is the opposite category to A.
    
    For each object A of C let Hom(A,–) be the hom functor that maps object X to the set Hom(A,X).
    As it is a functor it must preserve the structure of the category. 
*/
@implicitNotFound("Values of type ${F} cannot be found")
trait HomFunctor[F[_,_], A] extends HomFunctorComponent[F, A] 

object HomFunctor {

    def apply[F[_,_],A](implicit instance: HomFunctor[F,A]): HomFunctor[F,A] = instance

    object Ops {
 
        implicit class toFunctor[F[_,_], A, B](self: F[A, B])(implicit tc: HomFunctor[F, A]) extends HomFunctorComponent.Ops[F, A, B] { 
            override val typeClassInstance = tc
            override val target = self
        }
    }

}