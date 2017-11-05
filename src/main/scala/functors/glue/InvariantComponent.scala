
package architect
package functors.glue

trait InvariantComponent[F[_]] { self =>

    def xmap[A, B](fa: F[A])(f: A => B)(g: B => A): F[B]

    def xlift[A, B](f: A => B)(g: B => A): F[A] => F[B] =
        xmap(_)(f)(g)

}
object InvariantComponent {

    trait Ops[F[_], A] {

        def typeClassInstance: InvariantComponent[F]
        def target: F[A] 

    }
}

