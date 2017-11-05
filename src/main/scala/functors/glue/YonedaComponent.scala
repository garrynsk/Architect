
package architect
package functors.glue

import AliasComponent._
import functors.{ Representable, Corepresentable }

/**
    http://www.math3ma.com/mathema/2017/9/6/the-yoneda-embedding
    http://www.math3ma.com/mathema/2017/9/14/the-yoneda-lemma
    https://bartoszmilewski.com/2015/07/13/from-lenses-to-yoneda-embedding/

    Among the objects of Sets^C are certain very special ones, namely the (covariant) representable functors,

        Hom(C, −) : C → Sets.
    
    Observe that for each h : C → D in C, we have a natural transformation

        Hom(h, −) : Hom(D, −) → Hom(C, −)

    The Yoneda embedding is the functor 
        
        y : C → Sets^Cop (of course, this functor k is just the exponential transpose of the profunctor Hom(-,-): Cop × C → Sets) 
        
    taking C to the contravariant representable functor,
    
        yC = Hom(−, C) : Cop → Sets
    
    and taking f : C → D to the natural transformation,

        yf = Hom(−, f ) : Hom(−, C) → Hom(−, D).

    The Yoneda lemma. Let C be locally small. For any object C and functor F ∈ Sets^Cop there is an isomorphism
        Hom(yC, F ) ∼> FC which, moreover, is natural in both F and C.
    
    Another words, for each relationship (morphism) between X and Y, there should exist exactly one relationship (natural transformation) between
    Hom(-,X) and Hom(-,Y):

        Hom(X,Y) = Nat(Hom(-,X), Hom(-,Y)), where Nat means natural isomorphism

*/

trait YonedaComponent[F[_], A]  { self =>
 /**
    def fromYoneda[G[_], X, Y](fax: F[A, X])(f: X => Y)(implicit tc: Representable[F, G, A], fun: ({type λ[α] = F[A, α]}#λ ~> G)): G[Y] =
        tc.rep(fax)(f)(fun)

    def toYoneda[G[_], X, Y](gy: G[Y])(f: Y => X)(implicit tc: Representable[F, G, A], fun:  G ~> {type λ[α] = F[A, α]}#λ): F[A, X] =
        tc.unrep(gy)(f)(fun)

    def fromYonedaLeft[G[_], X, Y](fxa: F[X, A])(f: Y => X)(implicit tc: Corepresentable[F, G, A], fun: ({type λ[α] = F[α, A]}#λ ~> G)): G[Y] =
        tc.corep(fxa)(f)(fun)

    def toYonedaLeft[G[_], X, Y](gy: G[Y])(f: X => Y)(implicit tc: Corepresentable[F, G, A], fun:  G ~> {type λ[α] = F[α, A]}#λ): F[X, A] =
        tc.uncorep(gy)(f)(fun)
*/
}
object YonedaComponent {

    trait Ops[F[_], A]  {

        def typeClassInstance: YonedaComponent[F, A]
        def target: F[A] 

    }
}

