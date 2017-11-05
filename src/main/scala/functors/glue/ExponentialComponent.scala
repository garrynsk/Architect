
package architect
package functors.glue

import functors.Exponential
/**
    https://medium.com/@olxc/yoneda-and-coyoneda-trick-f5a0321aeba4

    In any cartesian closed category C, exponentiation by a fixed object A is a functor,

        (−)^A : C → C.

    Given some function β : B → C, we put β^A : B^A → C^A defined by f → β ◦ f.
    (−)^A is a functor; of course, it is just the representable functor Hom(A, −).
*/
trait ExponentialComponent[A] { self =>

    def compose[B, C](other: Exponential[B])(g: B => A): Exponential[A] = new Exponential[A] {

        override def exp[C](f: A => C): () => C ={

             other.exp[C](g andThen f) 

        }
    }
    /**
        (−)^A : C → C exponentiation by a fixed object A is a functor;
        of course, it is just the representable functor Hom(A, −).
        A stays fixed, we vary only second part of Hom(A, −).
    */
    def exp[B](f: A => B): () => B 


    //def coexp[D](f: D => A): D => ()
    /** 
        Letting 'a' vary over A then gives a map g: A → β^B defined by a → f (a, y).
        The map g: A → β^B takes the “parameter” a to the function f(a, y): B → C. It is uniquely determined by the equation
        g(a)(b) = f (a, b).

        f: A → β^B is called the (exponential) transpose of f .
    */
    def transpose[B, C](g: A => B): Exponential[B] = new Exponential {

        override def exp[C](f: B => C): () => C ={

             self.exp[C](g andThen f) 

        }
           

    //    override def coexp[C, D, E](f: D => C)(β: B => A): D => B =
     //       self.exp()()
    }

    /**
        Let the category C have binary products. An exponential of objects B and C consists of an object C^B
        and an arrow f : C^B × B → C, which is called evaluation
    */
    def eval: A ={

        self.exp(a => a)()

    }
       
 
}

object ExponentialComponent {

    trait Ops[A] {

        def typeClassInstance: ExponentialComponent[A]
        def target: A 
        
    }


}

