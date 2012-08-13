//import scala.slick.driver.MySQLDriver.simple._
import scala.slick.driver.PostgresDriver.simple._
import Database.threadLocalSession

object Countries extends Table[(String, String, String)]("country") {
  def code = column[String]("code", O.PrimaryKey)
  def name = column[String]("name")
  def continent = column[String]("continent")
  def * = code ~ name ~ continent
}

object Cities extends Table[(Int, String, String, String, Int)]("city") {
  def id = column[Int]("id", O.PrimaryKey)
  def name = column[String]("name")
  def countrycode = column[String]("countrycode")
  def district = column[String]("district")
  def population = column[Int]("population")
  def * = id ~ name ~ countrycode ~ district ~ population
  def country = foreignKey("country_fk", countrycode, Countries)(_.code)
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
  println("\nCities in Indiana and their continents (implicit join):")
  val q1 = for {
      ci <- Cities if ci.district === "Indiana"
      co <- ci.country  // equiv to:  co <- Countries if co.code === ci.countrycode
  } yield (ci.name, co.continent)

  q1.list foreach {
      println(_)
  }

  println("\nCities in Indiana and their continents (explicit join):")
  import scala.slick.lifted.Join
  val q2 = for {
      Join(ci, co) <- Cities innerJoin Countries on (_.countrycode === _.code)
      if ci.district === "Indiana"
  } yield (ci.name, co.continent)

  q2.list foreach {
      println(_)
  }
}
