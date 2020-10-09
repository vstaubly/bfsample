# bfsample
## Air Traffic Control Simulation

To build:
> mvn package

To run:
> mvn spring-boot:run

(Right now, it always listens on default port of 8080)

Load "http://127.0.0.1:8080/index.html" in browser to try out sample web UI.
Or look in scripts directory for examples of using curl to test REST API directly.

## A note about the database

The latest version stores the state of the queue in a simple relational database.
I chose SQLite v3 as the data store, and JDBC as the access method, for a few main reasons
* Quick implementation: allowed me to focus on the actual DB operations and not on infrastructure
* Simple: Not much in the way of code to access the DB
* SQL focused: allows me to focus on SQL queries and statements without much in the way

For the current schema, see schema.sql ... I currently have checked in
an empty SQLite db file with the table already created. If you decide on a
different DB, it's up to you to create the table, as well as add any JDBC
driver library to pom.xml. And, you would need to edit src/main/resources/db.properties
to match the needed settings as well.
