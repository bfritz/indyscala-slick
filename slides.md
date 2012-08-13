# Overview of SLICK for Database Access

<br/>
<br/>
<br/>

"Scala Language-Integrated Connection Kit"

Formerly ScalaQuery.

---

# Approaches Supported

*   "Plain SQL"
    * thin JDBC wrapper
    * stable
*   "Lifted Query"
    * composable queries
    * results behave like Scala collections
    * stable
*   "Direct Query"
    * mostly composable queries
    * uses macros so API is even more collection-like
    * experimental

---

# Databases Currently Supported

* Derby/JavaDB
* H2
* HSQLDB/HyperSQL
* Microsoft Access
* Microsoft SQL Server
* MySQL
* PostgreSQL
* SQLite
* ...or any DB with JDBC driver for at least Plain SQL access

---

# Observations

* Documentation is sparse but enough to get started
* License: BSD
* Requires SBT 0.12.0 to build and Scala 2.10 milestone to run

---

# Resources

* Home: <http://slick.typesafe.com/>
* Code: <https://github.com/slick/slick>
* Docs: <http://slick.typesafe.com/docs/> and <https://github.com/slick/slick/wiki>
* Mailing List: <http://groups.google.com/group/scalaquery>
* Roadmap and Tickets <http://www.assembla.com/spaces/typesafe-slick/>

---

# Me

Brad Fritz

*brad@fewerhassles.com*

*bfritz* on **github**, **irc** (freenode) and **twitter**
