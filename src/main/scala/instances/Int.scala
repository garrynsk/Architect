
package architect
package instances

import algebra.{ Ring, Semiring, Monoid, MultiplicativeMonoid, AdditiveMonoid, Semigroup, Group, MultiplicativeGroup, AdditiveGroup }
import neocat.{ Category, Semicategory, Groupoid }
import neocat.Perhaps

trait IntInstances extends  IntSemicategory with IntCategory with IntGroupoid with IntSemigroup with 
                            IntGroup with IntRing with IntMonoid with IntMultMonoid with IntAddMonoid
                            with IntAdditiveGroup with IntMultiplicativeGroup with IntSemiring


trait IntRing {

    implicit def intRing: Ring[Int] = new Ring[Int] {

        override def zero: Int = 0

        override def add(a: Int, b: Int): Int = a + b 

        override def one: Int = 1

        override def mult(a: Int, b: Int): Int = a * b    

        override def negation(a: Int): Int = -a 
    }

}


trait IntSemiring {

    implicit def intSemiring: Semiring[Int] = new Semiring[Int] {

        override def zero: Int = 0

        override def add(a: Int, b: Int): Int = a + b 

        override def one: Int = 1

        override def mult(a: Int, b: Int): Int = a * b    

    }
}

trait IntAddMonoid  {

    implicit def intAddMonoid: AdditiveMonoid[Int] = new AdditiveMonoid[Int] {

        override def zero: Int = 0

        override def add(a: Int, b: Int): Int = a + b 

    }

}


trait IntMultMonoid  {

    implicit def intMultMonoid: MultiplicativeMonoid[Int] = new MultiplicativeMonoid[Int] {

        override def one: Int = 1

        override def mult(a: Int, b: Int): Int = a * b   

    }

}

trait IntMultiplicativeGroup  {

    implicit def intMultGroup: MultiplicativeGroup[Int] = new MultiplicativeGroup[Int] {

        override def one: Int = 1

        override def mult(a: Int, b: Int): Int = a * b   

        override def reciprocal(a: Int): Int = 1/a 

    }
}

trait IntAdditiveGroup  {

    implicit def intAddGroup: AdditiveGroup[Int] = new AdditiveGroup[Int] {

        override def zero: Int = 0

        override def add(a: Int, b: Int): Int = a + b 

        override def negation(a: Int): Int = -a 

    }
}

trait IntGroup  {

    implicit def intGroup: Group[Int] = new Group[Int] {

        override def id: Int = 0

        override def combine(a: Int, b: Int): Int = a + b 

        override def inverse(a: Int): Int = -a 

    }
}

trait IntMonoid  {

    implicit def intMonoid: Monoid[Int] = new Monoid[Int] {

        override def id: Int = 0

        override def combine(a: Int, b: Int): Int = a + b 

    }
}

trait IntSemigroup  {

    implicit def intSemigroup: Semigroup[Int] = new Semigroup[Int] {

        override def combine(a: Int, b: Int): Int = a + b 

    }
}

trait IntGroupoid  {

    implicit def intGroupoid: Groupoid[Int] = new Groupoid[Int] {

        override def inverse(a: Int): Int = -a 
    }
    
}

trait IntCategory  {

    implicit def intCategory: Category[Int] = new Category[Int] {}
    
}

trait IntSemicategory  {

    implicit def intSemicategory: Semicategory[Int] = new Semicategory[Int] {}
    
}