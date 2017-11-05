
package architect
package functors

import functors.glue.InvariantComponent
import annotation.implicitNotFound

//http://comonad.com/reader/2008/rotten-bananas/
// TODO: ExpFunctor?
// Instances must satisfy the following laws:
// f(idX) = idY
// f(g*h) = f(g) * f(h)
/**
    The name came from the article http://comonad.com/reader/2008/rotten-bananas/, where it is also called an exponential functor 
    in contrast to a polynomial.
    It is the base functor for an expression type that has both positive and negative occurences of a variable.
*/
@implicitNotFound("Values of type ${F} cannot be found")
trait Invariant[F[_]] extends InvariantComponent[F] { self =>

    // def flatMap[A, B](fa: F[A])(f: A => F[B]): F[B]  TODO: move to monad
    //def flatten[A](ffa: F[F[A]]): F[A] = flatMap(ffa)(fa => fa)
    def compose[G[_]: Invariant]: Invariant[{type λ[α] = F[G[α]]}#λ] = new Invariant{

        override def xmap[A, B](fga: F[G[A]])(f: A => B)(g: B => A): F[G[B]] =
            self.xmap(fga)(Invariant[G].xmap(_)(f)(g))(Invariant[G].xmap(_)(g)(f))

    } 

    def composeFunctor[G[_]: Functor]: Invariant[{type λ[α] = F[G[α]]}#λ] = new Invariant{

        override def xmap[A, B](fga: F[G[A]])(f: A => B)(g: B => A): F[G[B]] =
            self.xmap(fga)(Functor[G].xmap(_)(f)(g))(Functor[G].xmap(_)(g)(f))

    }

    def composeContravariant[G[_]: Contravariant]: Invariant[{type λ[α] = F[G[α]]}#λ] = new Invariant{
        
        override def xmap[A, B](fga: F[G[A]])(f: A => B)(g: B => A): F[G[B]] =
            self.xmap(fga)(Contravariant[G].xmap(_)(f)(g))(Contravariant[G].xmap(_)(g)(f))

    }
}

object Invariant {

    def apply[F[_]](implicit instance: Invariant[F]): Invariant[F] = instance

    object Ops {
 
        implicit class toInvariant[F[_], A](self: F[A])(implicit tc: Invariant[F]) extends InvariantComponent.Ops[F,A] { 
            override val typeClassInstance = tc
            override val target = self
        }
    }

}
