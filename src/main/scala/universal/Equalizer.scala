
package architect
package universal

import functors.Functor
import algebra.Monoid

// http://blog.functorial.com/posts/2012-02-19-What-If-Haskell-Had-Equalizers.html

import annotation.implicitNotFound
trait Equalizer[F[_]: Functor] {
    // filter from monad
    def equalizer[A, B](fa: F[A])(f: A => B)(g: A => B): F[A] 
           
    def fequalizer[A,B](f: A => B)(g: A => B): F[A] => F[A] 

    def coequalizer[A, B](fa: F[A])(f: A => B)(g: A => B): F[B]

    def fcoequalizer[A, B](f: A => B)(g: A => B): F[A] => F[B]
}
object Equalizer {

    def apply[F[_]](implicit instance: Equalizer[F]): Equalizer[F] = instance

}