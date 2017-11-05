
package architect
package functors.glue

import AliasComponent._

trait CorepresentableComponent[G[_,_], F[_], A]  { self =>

    def corep[X](fa: G[X, A])(implicit tc: ({type λ[α] = G[α, A]}#λ ~> F)): F[X] =
        tc.apply(fa)

    def uncorep[X](fx: F[X])(implicit tc: F ~> {type λ[α] = G[α, A]}#λ): G[X, A] =
        tc.apply(fx)
}

object CorepresentableComponent {

    trait Ops[G[_,_], F[_], A]  {

        def typeClassInstance: CorepresentableComponent[G, F, A]
        def target: F[A]
    }
}
