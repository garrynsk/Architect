
package architect
package functors

import functors.glue.BifunctorComponent
import annotation.implicitNotFound

/**
    A bifunctor or functor of two variables is simply a functor whose domain is the product of two categories.

    For for C1, C2 and D categories, a functor
    F:C1×C2→D
    is also called a bifunctor from C1 and C2 to D.

*/
@implicitNotFound("Values of type ${:=>} cannot be found for Bifunctor")
trait Bifunctor[F[_, _]] extends BifunctorComponent[F] { self =>
    
  //  def right[G[_], A, B, C](fba: F[B, A])(f: A => C)(implicit tc: Representable[F, G, B], fun: {type λ[α] = F[B, α]}#λ ~> G): G[C] = 
  //      tc.rep(fba)(f)(fun)

   // def left[G[_], A, B, C](fba: F[A, B])(f: C => A)(implicit tc: Corepresentable[F, G, B], fun: {type λ[α] = F[α, B]}#λ ~> G): G[C] =
    //    tc.corep(fba)(f)(fun)

    def compose[G[_,_]: Bifunctor]: Bifunctor[{type λ[α, β]= F[G[α, β], G[α, β]]}#λ] = new Bifunctor{
        
        override def bimap[A, B, C, D](fab: F[G[A, B], G[A, B]])(f: A => C, g: B => D): F[G[C, D], G[C, D]] =
            self.bimap(fab)(Bifunctor[G].bimap[A,B,C,D](_)(f, g), Bifunctor[G].bimap(_)(f, g))
    }
}

object Bifunctor {

    def apply[F[_,_]](implicit instance: Bifunctor[F]): Bifunctor[F] = instance

    object Ops {
 
        implicit class toBifunctor[F[_,_], A, B](value: F[A, B])(implicit tc: Bifunctor[F]) extends BifunctorComponent.Ops[F, A, B] { 
            override val typeClassInstance = tc
            override val target = value
        }
    }
}

