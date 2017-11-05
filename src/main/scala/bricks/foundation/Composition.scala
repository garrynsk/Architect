
package architect
package bricks

trait Composition[A] {

    def compose[A,B,C](f: B => C)(g: A => B): A => C = f compose g 

    def andThen[A,B,C](f: A => B)(g: B => C): A => C = f andThen g 
    
    // The associativity law for functions
    def composition[B,C,D](a: D)(f: A => B, g: B => C, h: D => A): Boolean = 
        compose(compose(g)(f))(h)(a) == compose(g)(compose(f)(h))(a)

}

object Composition {
    
    trait Ops[A]{
        def typeClassInstance: Composition[A]
        def target: A 
        
    }
}

trait CompositionK[F[_]] {

    def compose[A,B,C](f: F[B] => F[C])(g: F[A] => F[B]): F[A] => F[C] = f compose g 

    def andThen[A,B,C](f: F[A] => F[B])(g: F[B] => F[C]): F[A] => F[C] = f andThen g 
    
    // The associativity law for functions
    def composition[A,B,C,D](a: F[D])(f: F[A] => F[B], g: F[B] => F[C], h: F[D] => F[A]): Boolean = 
        compose(compose(g)(f))(h)(a) == compose(g)(compose(f)(h))(a)

}

object CompositionK {

    trait Ops[F[_], A] {

        def typeClassInstance: CompositionK[F]
        def target: F[A]
    }
    
}