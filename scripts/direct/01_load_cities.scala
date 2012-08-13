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
  val q1 = Queryable[City]
  val q2 = q1.filter( _.population > 5000000 ).map( _ .name )

  val result = backend.result(q2, session)

  println("\nresult is of type: %s".format(result.getClass.getName))
  println("\nCities:")
  result foreach {
      println(_)
  }
}
