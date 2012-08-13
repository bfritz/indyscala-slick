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

// bound parameters
// from scaladoc:  def +?[T](v: T)(implicit p: SetParameter[T]): Self 

def insert(c: City) = (Q.u
  + "insert into city (id, name, countrycode, district, population)"
  + "values (" +? c.id + ", " +? c.name + ", " +? c.countrycode
  + ", " +? c.district + ", " +? c.population + ")").execute

db withSession {
  insert(City(10001, "Carmel", "USA", "Indiana", 79191))

  Seq(
    City(10002, "Fishers", "USA", "Indiana", 76794),
    City(10003, "Greenwood", "USA", "Indiana", 49791)
  ).foreach(insert)
}
