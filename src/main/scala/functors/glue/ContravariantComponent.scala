
package architect
package functors.glue

trait ContravariantComponent[F[_]] extends InvariantComponent[F] { self =>

    def contramap[A, B](fa: F[A])(f: B => A): F[B]

    def lift[A, B](f: B => A): F[A] => F[B] = 
        contramap(_)(f)

    override def xmap[A, B](fa: F[A])(f: A => B)(g: B => A): F[B] =
        contramap(fa)(g)

}
object ContravariantComponent {

    trait Ops[F[_], A] extends InvariantComponent.Ops[F, A]{

        def typeClassInstance: ContravariantComponent[F]
        def target: F[A] 

    }
}

