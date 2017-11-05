
package architect
package functors

import functors.glue.FunctorComponent
import annotation.implicitNotFound
// https://hseeberger.wordpress.com/2010/11/25/introduction-to-category-theory-in-scala/
// Functors can be thought of as homomorphisms between categories.
// Instances must satisfy the following laws:
// f(idX) = idY
// f(g*h) = f(g) * f(h)


@implicitNotFound("Values of type ${F} cannot be found")
trait Functor[F[_]] extends FunctorComponent[F] { self =>

    /** 
    *   The composition of Functor F and Contravariant G, `[x]F[G[x]]`, is
    *   covariant.
    */

        // def flatMap[A, B](fa: F[A])(f: A => F[B]): F[B]  TODO: move to monad
    //def flatten[A](ffa: F[F[A]]): F[A] = flatMap(ffa)(fa => fa)
    def compose[G[_]: Functor]: Functor[{type λ[α] = F[G[α]]}#λ] = new Functor{
        override def map[A, B](fga: F[G[A]])(f: A => B): F[G[B]] =
            self.map(fga)(Functor[G].map(_)(f))
    } 
    
    def composeContravariant[G[_]: Contravariant]: Contravariant[{type λ[α] = F[G[α]]}#λ] = new Contravariant{
        override def contramap[A, B](fga: F[G[A]])(f: B => A): F[G[B]] =
            self.map(fga)(Contravariant[G].contramap(_)(f))
    } 
}

object Functor {

    def apply[F[_]](implicit instance: Functor[F]): Functor[F] = instance

    object Ops {
 
        implicit class toFunctor[F[_], A](self: F[A])(implicit tc: Functor[F]) extends FunctorComponent.Ops[F,A] { 
            override val typeClassInstance = tc
            override val target = self
        }
    }

    implicit def functorOption: Functor[Option] = new Functor[Option] { self =>
        def map[A,B](fa: Option[A])(f: A => B): Option[B] = fa.map(f)
       // def flatMap[A,B](fa: Option[A])(f: A => Option[B]): Option[B] = fa.flatMap(f)  
    }

    implicit def functorList: Functor[List] = new Functor[List] { self =>
        def map[A,B](fa: List[A])(f: A => B): List[B] = fa.map(f)
       // def flatMap[A,B](fa: List[A])(f: A => List[B]): List[B] = fa.flatMap(f)  
    }
}
