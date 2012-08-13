//import scala.slick.driver.MySQLDriver.simple._
import scala.slick.driver.PostgresDriver.simple._
import Database.threadLocalSession

object Members extends Table[(Long, String)]("members") {
  def id = column[Long]("id", O PrimaryKey)
  def name = column[String]("name", O NotNull, O DBType "varchar(200)")
  def * = id ~ name
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
  println("\nInserting members...")
  Members.insert(1, "Ross Baker");

  // in JDBC batch if supported by JDBC driver
  Members.insertAll(
    (2, "Brad Fritz"),
    (3, "Jon Strayer"),
    (4, "Matt Deboard"),
    (5, "Patrick Davenport"),
    (6, "Brian Howard")
  )

  println("Members:\n")
  Query(Members) foreach {
      println(_)
  }
}
