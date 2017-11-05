
package architect
package universal

// TODO: In developing
object Alias{

    type :*:[A, B <: FAlgebra] = Product[A, B] 

    type :+:[A, B <: FAlgebra] = Coproduct[A] | B
    
}