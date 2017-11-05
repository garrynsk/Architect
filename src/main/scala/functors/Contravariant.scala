
package architect
package functors

import functors.glue.ContravariantComponent
import annotation.implicitNotFound

// Instances must satisfy the following laws:
// f(idX) = idY
// f(g*h) = f(g) * f(h)
@implicitNotFound("Values of type ${F} cannot be found")
trait Contravariant[F[_]] extends ContravariantComponent[F] { self =>

    /** 
    *   The composition of Contravariant F and G, `[x]F[G[x]]`, is
    *   covariant.
    */
    def compose[G[_]: Contravariant]: Functor[{type λ[α] = F[G[α]]}#λ] = new Functor{

        override def map[A, B](fga: F[G[A]])(f: A => B): F[G[B]] =
            self.contramap(fga)(Contravariant[G].contramap(_)(f))

    } 

    def composeFunctor[G[_]: Functor]: Contravariant[{type λ[α] = F[G[α]]}#λ] = new Contravariant{
        override def contramap[A, B](fga: F[G[A]])(f: B => A): F[G[B]] =
            self.contramap(fga)(Functor[G].map(_)(f))
    }
}

object Contravariant {

    def apply[F[_]](implicit instance: Contravariant[F]): Contravariant[F] = instance

    object Ops {
 
        implicit class toContravariant[F[_], A](self: F[A])(implicit tc: Contravariant[F]) extends ContravariantComponent.Ops[F,A] { 
            override val typeClassInstance = tc
            override val target = self
        }
    }
}
