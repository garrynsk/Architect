
package architect
package functors.glue

import AliasComponent._

trait RepresentableComponent[G[_,_], F[_], A]  { self =>

    /**
        A representation of F is a pair (A, Φ) where Φ : Hom(A,–) → F is a natural isomorphism.
        For F to be representable we require that: There be an object a in C; one natural transformation α from C(a, -) to F; 
        another natural transformation, β, in the opposite direction; and that their composition be the identity natural transformation.
    */
    def rep[X](gax: G[A, X])(implicit tc: ({type λ[α] = G[A, α]}#λ ~> F)): F[X] =
        tc.apply(gax)
    
    def unrep[X](fy: F[X])(implicit tc: F ~> {type λ[α] = G[A, α]}#λ): G[A, X] =
        tc.apply(fy)

}

object RepresentableComponent {

    trait Ops[G[_,_], F[_], A]  {

        def typeClassInstance: RepresentableComponent[G, F, A]
        def target: F[A]

    }
}
