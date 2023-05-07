package com.portfolio.backend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.sql.DriverManager
import java.sql.SQLException

@SpringBootApplication
class PortfolioBackend

fun main(args: Array<String>) {
	runApplication<PortfolioBackend>(*args)
	try {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver")
		val connection = DriverManager.getConnection("jdbc:sqlserver://;serverName=127.0.0.1;encrypt=false;", "sa", "Umio13OLF4s")
		println("Connected to the database!")
		connection.close()
	} catch(e: SQLException) {
		println("failed to connect to the db")
		e.printStackTrace()
	}


}
/*
use master
GO


CREATE TABLE myWorks (
    href varchar(255),
    techTitle varchar(255),
    title varchar(255),
    description varchar(255),
    linktype1 varchar(255),
    link1 varchar(255),
    linktype2 varchar(255),
    link2 varchar(255),
    linktype3 varchar(255),
    link3 varchar(255),
);

GO
*/