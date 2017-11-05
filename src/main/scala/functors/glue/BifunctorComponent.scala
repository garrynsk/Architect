
package architect
package functors.glue

trait BifunctorComponent[F[_,_]] { self =>

    def bimap[A, B, C, D](fab: F[A, B])(f: A => C, g: B => D): F[C,D]

    def fbimap[A, B, C, D](f: A => C, g: B => D): F[A, B] => F[C, D] = 
        bimap(_)(f,g)

}
object BifunctorComponent {

    trait Ops[F[_,_], A, B] {

        def typeClassInstance: BifunctorComponent[F]
        def target: F[A, B] 
    }
}
