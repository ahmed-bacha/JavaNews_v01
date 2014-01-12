package fr.tse.lt2c.satin.tp.jdbc;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.*;
import java.net.*;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;



public class Articles {
	

	private static Connection conn;
	private String titre;
	private String description;
	private String link;
	private Date pubdate;
	private String source;
	
	public void InsertDbArticle(String s,int i)throws ClassNotFoundException,SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/JavaNews?user=root");
		PreparedStatement req =(PreparedStatement) conn.prepareStatement("INSERT INTO Articles VALUES (NULL,?,?,?,?,?,?)"); 
		req.setString(1,this.getTitre()); 
		req.setString(2,this.getDescription());  
		req.setString(3,this.getLink());  
		req.setDate(4,this.getPubdate());
		req.setString(5,s);
		req.setInt(6,i);
		req.execute(); 
		req.close();
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Date getPubdate() {
		return pubdate;
	}

	public void setPubdate(Date pubdate) {
		this.pubdate = pubdate;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Articles a = new Articles();
		a.setTitre("toto");
		a.setDescription("lolo");
		a.InsertDbArticle("lien", 3);
	}
	
}
