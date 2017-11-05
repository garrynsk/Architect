
package architect
package functors.glue

import AliasComponent._

trait NaturalTransformationComponent[-F[_], +G[_]] { self =>
    
    def apply[A](fa: F[A]): G[A]

    def lift[A]: F[A] => G[A] = apply(_)

    def compose[N[_]](f: N ~> F): N ~> G = new (N ~> G){

        def apply[A](fa: N[A]): G[A] = self(f(fa))

    } 

    def andThen[H[_]](f: G ~> H): F ~> H =
        f compose self

}
object NaturalTransformationComponent {

    trait Ops[-F[_], +G[_], A] {

        def typeClassInstance: NaturalTransformationComponent[F, G]
        def target: G[A] 

    }
}
