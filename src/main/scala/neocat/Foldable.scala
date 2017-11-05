
package architect
package neocat

import algebra.Monoid
import annotation.implicitNotFound
// TODO: In developing
/**
    Data structures that can be folded to a summary value.
    Catamorphism
    Generalization of a n-fold Cartesian product
    
    https://jeremykun.com/2013/09/30/the-universal-properties-of-map-fold-and-filter/
    https://maartenfokkinga.github.io/utwente/mmf91m.pdf
*/
@implicitNotFound("Values of type ${F} cannot be found")
trait Foldable[F[_]] { 

    // Combine the elements of a structure using a monoid.
    def fold[M: Monoid](t: F[M]): M = foldMap[M, M](t)(x => x)

    def foldLeft[A, B](fa: F[A])(acc: B)(f: (B, A) => B): B
    def foldRight[A, B](fa: F[A])(acc: B)(f: (A, B) => B): B

    // Map each element of the structure to a monoid, and combine the results.
    def foldMap[A,B](fa: F[A])(f: A => B)(implicit monoid: Monoid[B]): B

    def count[A](fa: F[A]): Int = length(fa)
    def length[A](fa: F[A]): Int = foldLeft(fa)(0)((b, _) => b + 1)
}
object Foldable {

    def apply[F[_]](implicit instance: Foldable[F]): Foldable[F] = instance

    trait Ops

    implicit def foldableList[A]: Foldable[List] = new Foldable[List] {

        def foldLeft[A, B](fa: List[A])(acc: B)(f: (B, A) => B): B = fa.foldLeft(acc)(f)
        def foldRight[A, B](fa: List[A])(acc: B)(f: (A, B) => B): B = fa.foldRight(acc)(f)

        // Map each element of the structure to a monoid, and combine the results.
        def foldMap[A,B](fa: List[A])(f: A => B)(implicit monoid: Monoid[B]): B = 
                foldLeft(fa)(monoid.id)((x,y) => monoid.combine(x, f(y)))

    }
}