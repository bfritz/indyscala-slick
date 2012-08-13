# sample code for Typesafe SLICK overview

## Running a demo script:

The following steps assume you have PostgreSQL 9.1 installed, a
`indyscala` user with no password, a `slick` database where
`indyscala` has full privileges, and the world sample data from
<http://pgfoundry.org/projects/dbsamples/> inserted into the `slick`
database.

You may also change the JDBC URLs, drivers and users and passwords
in the script files if your setup differs.

0. Install sbt 0.12
1. Run `sbt update` once to download dependencies.
2. Run `sbt console` to start sbt with a REPL and the classpath
   configured.
3. Type `:load scripts/plainsql/01_load_cities.scala` in the REPL
   to run the first Plain SQL demo script.
