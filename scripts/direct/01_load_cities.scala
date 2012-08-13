// import scala.slick.driver.MySQLDriver
// import scala.slick.driver.MySQLDriver.simple.Database
import scala.slick.driver.PostgresDriver
import scala.slick.driver.PostgresDriver.simple.Database
import Database.{threadLocalSession => session}
import scala.slick.direct._

@table(name="city")
case class City(
  @column(name="id")
  id : Int,
  @column(name="name")
  name : String,
  @column(name="countrycode")
  countrycode : String,
  @column(name="district")
  district : String,
  @column(name="population")
  population : Int
)

val pg = Database.forURL(
    "jdbc:postgresql://localhost/slick",
    user = "indyscala",
    driver = "org.postgresql.Driver")

val mysql = Database.forURL(
    "jdbc:mysql://localhost/slick",
    user = "indyscala",
    driver = "com.mysql.jdbc.Driver")

val backend = new SlickBackend(PostgresDriver)
val db = pg

db withSession {
  println("\nCities with more than 5 million people:")
  val cities = Queryable[City]
  val q1 = cities.filter( _.population > 5000000 ).map( _ .name )

  val bigCities = backend.result(q1, session)

  println("\nresult is of type: %s".format(bigCities.getClass.getName))
  println("\nCities:")
  bigCities foreach {
      println(_)
  }
}
