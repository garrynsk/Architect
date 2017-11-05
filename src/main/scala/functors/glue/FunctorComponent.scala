
package architect
package functors.glue

trait FunctorComponent[F[_]] extends InvariantComponent[F] { self =>

    def map[A, B](fa: F[A])(f: A => B): F[B]

    def lift[A, B](f: A => B): F[A] => F[B] = 
        map(_)(f)
    
    override def xmap[A, B](fa: F[A])(f: A => B)(g: B => A): F[B] =
        map(fa)(f)

}
object FunctorComponent {

    trait Ops[F[_], A] extends InvariantComponent.Ops[F, A] {

        def typeClassInstance: FunctorComponent[F]
        def target: F[A] 

    }
}

