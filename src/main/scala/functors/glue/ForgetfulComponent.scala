
package architect
package functors.glue

trait ForgetfulComponent[F[_], G[_]] extends FunctorComponent[F] with NaturalTransformationComponent[F, G] { self =>

    def transform[A, B](fa: F[A])(f: A => B): G[B] =
        apply(map(fa)(f))
    
    def ftransform[A, B](f: A => B): F[A] => G[B] =
        transform(_)(f)

}
object ForgetfulComponent {

    trait Ops[F[_], G[_], A, B] extends FunctorComponent.Ops[F, A] with NaturalTransformationComponent.Ops[F, G, B]{

        def typeClassInstance: ForgetfulComponent[F, G]
        def target: F[A] & G[B]

    }
}
