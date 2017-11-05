
package architect
package functors.glue

// Allow currying of a profunctor

trait HomFunctorComponent[F[_,_], A] extends ProfunctorComponent[F] { self =>

    def covariant[X, Y](fa: F[A, X])(g: X => Y): F[A, Y] =
        dimap[A,X,A,Y](fa)(a => a)(g)
        
    // Hom(A,–) maps each morphism f : X → Y to the function
    // Hom(A, f) : Hom(A, X) → Hom(A, Y) 
    def fcovariant[X, Y](g: X => Y): F[A, X] => F[A, Y] =
        covariant(_)(g)

    // Hom(–,A) maps each object X in C to the set of morphisms, Hom(X, A)
    def contravariant[X, Y](fa: F[Y, A])(g: X => Y): F[X, A] =
        dimap(fa)(g)(a => a)

    // Hom(–,A) maps each morphism h : X → Y to the function
    // Hom(h, A) : Hom(Y, A) → Hom(X, A) (natural transformation)
    def fcontravariant[X, Y](g: X => Y): F[Y, A] => F[X, A] =
        contravariant(_)(g)

}
object HomFunctorComponent {

    trait Ops[F[_,_], A, B] {

        def typeClassInstance: HomFunctorComponent[F, A]
        def target: F[A, B] 

    }
}

