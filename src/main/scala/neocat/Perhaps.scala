
package architect
package neocat

trait Perhaps
object Perhaps {

    def fold[Type, ConvertType](ifPresent: => ConvertType)(ifAbsent: => ConvertType)(implicit value: Type = null): ConvertType = {
        
        lazy val present = ifPresent
        lazy val default = ifAbsent

        if(value != null) { 
            present
        }else{
            default
        }
    }   
}
