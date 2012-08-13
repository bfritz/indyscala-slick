// import scala.slick.driver.MySQLDriver.simple._
import scala.slick.driver.PostgresDriver.simple._
import Database.threadLocalSession

object Cities extends Table[(Int, String, String, String, Int)]("city") {
  def id = column[Int]("id", O.PrimaryKey)
  def name = column[String]("name")
  def country = column[String]("countrycode")
  def district = column[String]("district")
  def population = column[Int]("population")
  def * = id ~ name ~ country ~ district ~ population
}


val pg = Database.forURL(
    "jdbc:postgresql://localhost/slick",
    user = "indyscala",
    driver = "org.postgresql.Driver")

val mysql = Database.forURL(
    "jdbc:mysql://localhost/slick",
    user = "indyscala",
    driver = "com.mysql.jdbc.Driver")

val db = pg

db withSession {
  println("\nFirst 5 cities returned:")
  val q1 = Query(Cities)

  // becomes LIMIT 5 in SQL
  q1 take(5) foreach {
      println(_)
  }

  println("\nCities in Indiana:")
  val q2 = for ( c <- Cities; if c.district === "Indiana") yield (c.id, c.name)
  q2.list foreach {
      println(_)
  }
}
