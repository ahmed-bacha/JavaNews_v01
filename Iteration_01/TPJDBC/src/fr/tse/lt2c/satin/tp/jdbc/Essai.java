package fr.tse.lt2c.satin.tp.jdbc;


import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Set;

import com.mysql.jdbc.Statement;

//import fr.tse.lt2c.satin.tp.jdbc.beans.Etudiant;


	

public class Essai {
	private static Connection conn;
	
	public static void main(String[] args) throws ClassNotFoundException,
	SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		  // Setup the connection with the DB
		  conn = DriverManager.getConnection("jdbc:mysql://localhost/JavaNews?user=root");

		 
		  PreparedStatement req =conn.prepareStatement("INSERT INTO Articles VALUES (NULL,?,?,?,?,?,NULL)");
		          req.setString(1,"Le parisien");
		          req.setString(2,"article du sciecle, el courra dans une equipe");
		          req.setString(3,"www.com");
		          req.setDate(4,new Date(System.currentTimeMillis()));
		          req.setString(5,"El watan");
		          req.execute();
		     req.close();
	
	}

}
