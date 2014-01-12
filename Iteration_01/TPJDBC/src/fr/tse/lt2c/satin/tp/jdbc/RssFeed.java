package fr.tse.lt2c.satin.tp.jdbc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;


public class RssFeed extends Articles{
	
	private static Connection conn;
	private int id;
	private String nom;
	private String url;
	
	
	public RssFeed(String n,String u){
		this.nom = n;
		this.url = u;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void InsertDbRss()throws ClassNotFoundException,SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/JavaNews?user=root");
		PreparedStatement req =(PreparedStatement) conn.prepareStatement("INSERT INTO RssUrl VALUES (NULL,?,?)",Statement.RETURN_GENERATED_KEYS); 
		req.setString(1,this.getNom()); 
		req.setString(2,this.getUrl());  
		req.executeUpdate(); 
		ResultSet rs = req.getGeneratedKeys();
		rs.next();
		int i = rs.getInt(1);
		req.close();
		conn.close();
		this.setId(i);

	}
	
	public String ParseRss() throws ClassNotFoundException, SQLException{
		
		
		//Set<Articles> li =  new HashSet<Articles>();
		try{
				URL resUrl = new URL(this.url);
				BufferedReader in = new BufferedReader(new InputStreamReader(resUrl.openStream()));
				String sourceCode = "";
				String line;
				while((line = in.readLine()) != null){
					
					Articles at = new Articles();
					
					if(line.contains("<title>")){
						int firstPos = line.indexOf("<title>");
						String temp = line.substring(firstPos);
						temp = temp.replace("<title><![CDATA[", "TITRE : ");
						int lastPos = temp.indexOf("</title>");
						temp = temp.substring(0,lastPos-3);
						temp = temp.replace("Â","");
						temp = temp.replace("Ã©","é");
						temp = temp.replace("Ã¨","è");
						temp = temp.replace("Ã","à");
						temp = temp.replace("à´","ô");
						temp = temp.replace("à®","î");
						temp = temp.replace("â€™","'");
						temp = temp.replace("àª","ê");
						temp = temp.replace("à¢","â");
						temp = temp.replace("à§","ç");
						sourceCode += temp+"\n";
						
						
						at.setTitre(temp);
						
						temp="";
					}
					if(line.contains("<description>")){
						int firstPo = line.indexOf("<description>");
						String temp = line.substring(firstPo);
						temp = temp.replace("<description><![CDATA[", "DESCRIPTION : ");
						int lastPo = temp.indexOf("</description>");
						temp = temp.substring(0,lastPo-3);
						temp = temp.replace("Â","");
						temp = temp.replace("Ã©","é");
						temp = temp.replace("Ã¨","è");
						temp = temp.replace("Ã","à");
						temp = temp.replace("à´","ô");
						temp = temp.replace("à®","î");
						temp = temp.replace("â€™","'");
						temp = temp.replace("àª","ê");
						temp = temp.replace("à¢","â");
						temp = temp.replace("à§","ç");
						sourceCode += temp+"\n";
						
						
						at.setDescription(temp);
						
						temp="";
					}
			
					if(line.contains("<link>")){
						int firstPo = line.indexOf("<link>");
						String temp = line.substring(firstPo);
						temp = temp.replace("<link><![CDATA[", "LIEN : ");
						int lastPo = temp.indexOf("</link>");
						temp = temp.substring(0,lastPo-3);
						temp = temp.replace("Â","");
						temp = temp.replace("Ã©","é");
						temp = temp.replace("Ã¨","è");
						temp = temp.replace("Ã","à");
						temp = temp.replace("à´","ô");
						temp = temp.replace("à®","î");
						temp = temp.replace("â€™","'");
						temp = temp.replace("àª","ê");
						temp = temp.replace("à¢","â");
						temp = temp.replace("à§","ç");
						sourceCode += temp+"\n\n";
						
						
						at.setLink(temp);
						
						temp="";
					}
					at.setSource(this.getNom());
					at.InsertDbArticle(this.getNom(),this.getId());
				}
				
				in.close();
				
				return sourceCode;
				
			} catch(MalformedURLException ue){
				System.out.println("Malformed URL");
			} catch(IOException ioe){
				System.out.println("Something went wrong reading the contexts");
			}
			
			return null;
		}
	
	


	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		RssFeed r = new RssFeed("parisien","http://rss.leparisien.fr/leparisien/rss/actualites-a-la-une.xml");
		System.out.println(r.ParseRss());
		//System.out.println(r.InsertDbRss());
		
	}
	
}