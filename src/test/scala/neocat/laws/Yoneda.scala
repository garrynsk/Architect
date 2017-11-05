package scala

import org.junit.Test
import org.junit.Assert._

/**

class YonedaTest {

    case class Street(number: Int, name: String)
    case class Address(city: String, street: Street)    

    case class Company(name: String, address: Address)
    object Company {

        implicit def addressRep(value: Company): Exponential[Company] = new Exponential[Company]{ self =>

            override def exp[B](f: Company => B): () => B ={

                () => f(value)

            }
        }
    }
    case class Employee(name: String, company: Company)
    object Employee {

        implicit def employeeRep(value: Employee): Exponential[Employee] = new Exponential[Employee]{ self =>

            override def exp[B](f: Employee => B): () => B ={

                () => f(value)

            }
        }
    }
   


  @Test def associativity(): Unit = {
        val employee = Employee("john", Company("awesome inc", Address("london", Street(23, "high street"))))
        val comp = Company("awesome inc", Address("london", Street(23, "high street")))
        val street = Street(23, "new street")
        val trans = employee.compose()((a: Company) => employee.copy(company = a))
        val newemp = employee.copy(company = employee.company.copy(name = "new name"))
        assertEquals(trans, true)

  }

}
*/
