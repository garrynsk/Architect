
package architect
package functors.glue

import functors.Coexponential
/**
    https://medium.com/@olxc/yoneda-and-coyoneda-trick-f5a0321aeba4

    In any cartesian closed category C, exponentiation by a fixed object A is a functor,

        (−)^A : C → C.

    Given some function β : B → C, we put β^A : B^A → C^A defined by f → β ◦ f.
    (−)^A is a functor; of course, it is just the representable functor Hom(A, −).
*/
trait CoexponentialComponent[F[_], X]  { self =>
 
    /**
        (−)^A : C → C exponentiation by a fixed object A is a functor;
        of course, it is just the representable functor Hom(A, −).
        A stays fixed, we vary only second part of Hom(A, −).
    */

    def coexp[Y](f: X => Y): F[Y] 

    /** 
        Letting 'a' vary over A then gives a map g: A → β^B defined by a → f (a, y).
        The map g: A → β^B takes the “parameter” a to the function f(a, y): B → C. It is uniquely determined by the equation
        g(a)(b) = f (a, b).

        f: A → β^B is called the (exponential) transpose of f .
    */

    def cotranspose[Y](f: X => Y): Coexponential[F, Y] = new Coexponential{

        override def coexp[Z](β: Y => Z): F[Z]=
            self.coexp(f andThen β)

    }
    /**
        Let the category C have binary products. An exponential of objects B and C consists of an object C^B
        and an arrow f : C^B × B → C, which is called evaluation
    */
    def coeval(value: F[X]): F[X] =
        self.coexp(a => a)

}

object CoexponentialComponent {

    trait Ops[F[_], A] {

        def typeClassInstance: CoexponentialComponent[F, A]
        def target: F[A] 

    }
}

