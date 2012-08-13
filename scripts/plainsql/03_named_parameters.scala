import org.indyscala.slick.City

import scala.slick.session.Database
import Database.threadLocalSession
import scala.slick.jdbc.{GetResult, StaticQuery => Q}

val pg = Database.forURL(
    "jdbc:postgresql://localhost/slick",
    user = "indyscala",
    driver = "org.postgresql.Driver")

val mysql = Database.forURL(
    "jdbc:mysql://localhost/slick",
    user = "indyscala",
    driver = "com.mysql.jdbc.Driver")

val db = pg

import Q.interpolation

/*
  From scaladoc...
  def sql[P](param: P)(implicit pconv: SetParameter[P]): SQLInterpolationResult[P]
  def sqlu[P](param: P)(implicit pconv: SetParameter[P]): StaticQuery0[Int]
*/

def citiesByDistrict(district: String) =
  sql"select * from city where district = $district".as[City]

def addPopulation(count: Int, cityName: String) =
  sqlu"update city set population = population + $count where name = $cityName".first

db withSession {
  println()
  citiesByDistrict("Indiana").foreach{ c =>
    println("%s, %s (%d)".format(c.name, c.district, c.population))
  }

  println("\n--------")
  val rowCount = addPopulation(1000, "Fishers")
  println("updated %d row(s)".format(rowCount))
  println("--------\n")

  citiesByDistrict("Indiana").foreach{ c =>
    println("%s, %s (%d)".format(c.name, c.district, c.population))
  }
}
