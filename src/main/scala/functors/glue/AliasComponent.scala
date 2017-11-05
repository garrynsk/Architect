
package architect
package functors.glue

// TODO: In developing
object AliasComponent{

    type ~>[-F[_], +G[_]] = NaturalTransformationComponent[F, G]
    type <~[+F[_], -G[_]] = NaturalTransformationComponent[G, F]
    
    type ~>>[-F[_,_], +G[_,_]] = BinaturalTransformationComponent[F, G]
    type <<~[+F[_,_], -G[_,_]] = BinaturalTransformationComponent[G, F]
}