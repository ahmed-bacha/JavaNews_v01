package fr.tse.lt2c.satin.tp.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class RSSReader {

   /**
    * Parser le fichier XML
    * @param feedurl URL du flux RSS
    */
   public void parse(String feedurl) {
       try {
           DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
           URL url = new URL(feedurl);
           Document doc = builder.parse(url.openStream());
           NodeList nodes = null;
           Node element = null;
           /**
            * Titre  du flux
            */
           nodes = doc.getElementsByTagName("channel");
           Node node = doc.getDocumentElement();
           System.out.println("Flux RSS: " + this.readNode(node, "channel|title"));
           System.out.println();
           /**
            * Elements du flux RSS
            **/
           
           // enregistrer  dans un fichier 
   		 PrintWriter out = new PrintWriter ("doc-copie.txt");
    	
           nodes = doc.getElementsByTagName("item");
           for (int i = 0; i < nodes.getLength(); i++) {
               element = nodes.item(i);
               
               System.out.println("Titre: " + readNode(element, "title"));
               out .println("Titre: " + readNode(element, "title")) ;
               
               System.out.println("Lien: " + readNode(element, "link"));
               out .println("Lien: " + readNode(element, "link")) ;
               
               System.out.println("Description: " + readNode(element, "description"));
               out .println("Description: " + readNode(element, "description")) ;
               
               
               
               // extraction du lien de la source principale
               String s =readNode(element, "link");
               int j =0;
               int compte=0;
               while(compte<3){
               	if(s.charAt(j)=='/')
               		compte=compte+1;
               	j=j+1;	
               }
               	
               System.out.println("Source: " + s.substring(0, j-1));
               out .println("Source: " + s.substring(0, j-1));
               
               out .println("Date de publication: " + readNode(element, "pubDate")) ;
               System.out .println("Date de publication: " + readNode(element, "pubDate")) ;
               
               System.out.println();
           }
           
           
       	out . close ();
       } catch (SAXException ex) {
       	 System.out.println("erreur");
       } catch (IOException ex) {
       	 System.out.println("erreur");
       } catch (ParserConfigurationException ex) {
        System.out.println("erreur");
       }
   }

   /**
    * Méthode permettant de retourner ce que contient d'un noeud
    * @param _node le noeud principal
    * @param _path suite des noms des noeud sans espace séparer par des "|"
    * @return un string contenant le valeur du noeud voulut
    */
   public String readNode(Node _node, String _path) {

       String[] paths = _path.split("\\|");//divise la chaine paths en fonction \\
       Node node = null;

       if (paths != null && paths.length > 0) {
           node = _node;

           for (int i = 0; i < paths.length; i++) {
               node = getChildByName(node, paths[i]);//paths[i].trim() pour travailler sur copie de chaine de caractere
           }
       }

       if (node != null) {
           return node.getTextContent();
       } else {
           return "";
       }
   }

   /**
    * renvoye le nom d'un noeud fils a partir de son nom
    * @param _node noeud principal
    * @param _name nom du noeud fils
    * @return le noeud fils
    */
   public Node getChildByName(Node _node, String _name) {
       if (_node == null) {
           return null;
       }
       NodeList listChild = _node.getChildNodes();

       if (listChild != null) {
           for (int i = 0; i < listChild.getLength(); i++) {
               Node child = listChild.item(i);
               if (child != null) {
                   if ((child.getNodeName() != null && (_name.equals(child.getNodeName()))) || (child.getLocalName() != null && (_name.equals(child.getLocalName())))) {
                       return child;
                   }
               }
           }
       }
       return null;
   }
  
    /**
    * Test
    * 
    * 
    */
   public static void main(String[] args) {
       RSSReader reader = new RSSReader();
       reader.parse("http://rss.leparisien.fr/leparisien/rss/actualites-a-la-une.xml");
    
       		
      
   }
}