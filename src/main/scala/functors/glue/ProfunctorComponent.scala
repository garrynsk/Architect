
package architect
package functors.glue

trait ProfunctorComponent[F[_,_]] { self =>

    def dimap[A, B, C, D](fab: F[A, B])(f: C => A)(g: B => D): F[C, D]
    
    def lmap[A, B, C](fab: F[A, B])(f: C => A): F[C, B]=
        dimap(fab)(f)(identity)

    def rmap[A, B, C](fab: F[A, B])(f: B => C): F[A, C] =
        dimap[A, B, A, C](fab)(identity)(f)

    def lift[A, B, C, D](f: C => A)(g: B => D): F[A, B] => F[C, D] = dimap(_)(f)(g)

}
object ProfunctorComponent {

    trait Ops[F[_,_], A, B] {

        def typeClassInstance: ProfunctorComponent[F]
        def target: F[A, B] 

    }
}

