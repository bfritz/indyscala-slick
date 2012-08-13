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

db withSession {
  implicit val cityMapper = GetResult(r =>
    City(r.nextInt, r.nextString, r.nextString, r.nextString, r.nextInt))

  val sql = """
    select *
    from city
    order by name
    limit 5
    """

  val q = Q.queryNA[City](sql)
  q foreach { c =>
    println("(%4d) %s".format(c.id, c.name))
  }
}
