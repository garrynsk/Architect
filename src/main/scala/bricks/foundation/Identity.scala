
package architect
package bricks

trait Identity[A] extends Composition[A] {

    // Identity. For every object x, there exists a morphism 1x : x → x called the identity morphism for x, 
    // such that for every morphism f : a → b, we have 1b ∘ f = f = f ∘ 1a.
    def id(a: A): A = a

    def unitLeft(a: A)(f: A => A): Boolean = 
        compose(f)(id)(a) == f(a)

    def unitRight(a: A)(f: A => A): Boolean = 
        andThen(f)(id)(a) == f(a)
        
    def identity(a: A)(f: A => A): Boolean =
        unitLeft(a)(f) == unitRight(a)(f)
        
}

object Identity {
     trait Ops[A] {

        def typeClassInstance: Identity[A]
        def target: A 
    }
}


trait IdentityK[F[_]] {

    // Identity. For every object x, there exists a morphism 1x : x → x called the identity morphism for x, 
    // such that for every morphism f : a → b, we have 1b ∘ f = f = f ∘ 1a.
    def id[A](a: F[A]): F[A] = a

    def unitLeft[A](a: F[A])(f: F[A] => F[A])(implicit tc: CompositionK[F]): Boolean = 
        tc.compose[A,A,A](f)(id)(a) == f(a)

    def unitRight[A](a: F[A])(f: F[A] => F[A])(implicit tc: CompositionK[F]): Boolean = 
        tc.andThen(f)(id)(a) == f(a)
        
    def identity[A](a: F[A])(f: F[A] => F[A])(implicit tc: CompositionK[F]): Boolean =
        unitLeft(a)(f) == unitRight(a)(f)
        
}

object IdentityK {

    trait Ops[F[_],A] {
        def typeClassInstance: IdentityK[F]
        def target: F[A] 
    }
    
}