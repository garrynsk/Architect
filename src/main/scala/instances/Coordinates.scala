
package architect
package instances

import algebra.{ Ring, Semiring, Monoid, MultiplicativeMonoid, AdditiveMonoid, Semigroup, Group, MultiplicativeGroup, AdditiveGroup }
import neocat.{ Category, Semicategory, Groupoid }

trait ReachCoordinates {

    type A = Coordinates

    case class Coordinates(x: Int, y: Int) { self =>

        def *(b: Int) = Coordinates(self.x * b, self.y * b) 
    
        def -(b: Int) = Coordinates(self.x - b, self.y - b)

        def +(b: Int) = Coordinates(self.x + b, self.y + b)

    }
    object Coordinates {

        implicit object coordinatesRing extends Ring[A]{

            override def zero: A = Coordinates(0,0)

            override def add(a: A, b: A): A = Coordinates(a.x + b.x, a.y + b.y) 

            override def one: A = Coordinates(1,1)

            override def mult(a: A, b: A): A = Coordinates(a.x * b.x, a.y * b.y)    

            override def negation(a: A): A  = Coordinates(-a.x, -a.y)  

        }
        
        implicit object coordinatesMultiplicativeGroup extends MultiplicativeGroup[A]{

            override def one: A = Coordinates(1,1)

            override def mult(a: A, b: A): A = Coordinates(a.x * b.x, a.y * b.y)    

            override def reciprocal(a: A): A  = Coordinates(1/a.x, 1/a.y)  

        }

        implicit object coordinatesAdditiveGroup extends AdditiveGroup[A]{

            override def zero: A = Coordinates(0,0)

            override def add(a: A, b: A): A = Coordinates(a.x + b.x, a.y + b.y) 

            override def negation(a: A): A  = Coordinates(-a.x, -a.y)  

        }

        implicit object coordinatesGroup extends Group[A]{

            override def id: A = Coordinates(0,0)

            override def combine(a: A, b: A): A = Coordinates(a.x + b.x, a.y + b.y) 

            override def inverse(a: A): A  = Coordinates(-a.x, -a.y)

        }

        implicit object coordinatesSemiring extends Semiring[A]{

            override def zero: A = Coordinates(0,0)

            override def add(a: A, b: A): A = Coordinates(a.x + b.x, a.y + b.y) 

            override def one: A = Coordinates(1,1)

            override def mult(a: A, b: A): A = Coordinates(a.x * b.x, a.y * b.y)    

        }

        implicit object coordinatesMultiplicativeMonoid extends MultiplicativeMonoid[A]{ 

            override def one: A = Coordinates(1,1)

            override def mult(a: A, b: A): A = Coordinates(a.x * b.x, a.y * b.y)    

        }
       
        implicit object coordinatesAdditiveMonoid extends AdditiveMonoid[A]{ 

            override def zero: A = Coordinates(0,0)

            override def add(a: A, b: A): A = Coordinates(a.x + b.x, a.y + b.y) 

        }

        implicit object coordinatesMonoid extends Monoid[A]{

            override def id: A = Coordinates(0,0)

            override def combine(a: A, b: A): A = Coordinates(a.x + b.x, a.y + b.y) 

        }
        
        implicit object coordinatesGroupoid extends Groupoid[A]{

            override def inverse(a: A): A  = Coordinates(-a.x, -a.y)  

        }

        implicit object coordinatesSemigroup extends Semigroup[A]{

            override def combine(a: A, b: A): A = Coordinates(a.x + b.x, a.y + b.y) 

        }

        implicit object coordinatesCategory extends Category[A]

        implicit object coordinatesSemicategory extends Semicategory[A]
    }
}
