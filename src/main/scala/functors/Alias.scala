
package architect
package functors

// TODO: In developing
object Alias{

    type ~>[-F[_], +G[_]] = NaturalTransformation[F, G]
    type <~[+F[_], -G[_]] = NaturalTransformation[G, F]
    
    /** 
        A function type encoded as a natural transformation by adding a
        phantom parameter.
    */
    type ->[A, B] = {type λ[α] = A}#λ ~> {type λ[α] = B}#λ
}