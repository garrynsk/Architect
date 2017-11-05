
package architect
package functors.glue

import AliasComponent._
/**
    In interface for natural transformations between two parameter functors
*/
trait BinaturalTransformationComponent[-F[_,_], +G[_,_]] { self =>
    
    def apply[A, B, C, D](fa: F[A, B]): G[C, D]

    def compose[N[_,_]](f: N ~>> F): N ~>> G = new (N ~>> G){
        def apply[A, B, C, D](fa: N[A, B]): G[C, D] = self(f(fa))
    } 

    def andThen[H[_,_]](f: G ~>> H): F ~>> H =
        f compose self

}
object BinaturalTransformationComponent {

    trait Ops[-F[_,_], +G[_,_], A, B] {

        def typeClassInstance: BinaturalTransformationComponent[F, G]
        def target: G[A, B] 

    }
}
