package abdalla;



import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;




public class XMLReaderAbdalla {
	public static  String parseXML(String file,String teacherID)  {
		StringBuilder str = new StringBuilder();
		try {
	        File f = new File(file);
	        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    	Document doc = (Document) dBuilder.parse(f);
	    			
	    	//optional, but recommended
	    	//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
	    	doc.getDocumentElement().normalize();
	    	String ausgabe="";
	    			
	    	NodeList nList = doc.getElementsByTagName("lehrer");
	    	for (int temp = 0; temp< nList.getLength(); temp++) {
	    		Node nNode = nList.item(temp);
	    		Element eElement = (Element) nNode;	
	    		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	    			if( eElement.getAttribute("id").equals(teacherID)) {	
	    				str.append("\nID : " + eElement.getAttribute("id"));
	    				str.append("\nVorname : " + eElement.getElementsByTagName("vorname").item(0).getTextContent());
	    				str.append("\nNachname : " + eElement.getElementsByTagName("nachname").item(0).getTextContent());  			
	    				if(teacherID.equals("marm") || teacherID.equals("lise0")) {
	    					str.append("\nSprechstunde  " + eElement.getAttribute("jahr"));
	    					str.append("\nTag : " + eElement.getElementsByTagName("tag").item(0).getTextContent());
	    					str.append("\nStunde : " + eElement.getElementsByTagName("stunde").item(0).getTextContent());
	    					str.append("\nRaum : " + eElement.getElementsByTagName("raum").item(0).getTextContent());
	    				}
	    				NodeList abteilu = eElement.getElementsByTagName("abteilung");
	    				for (int count1 = 0; count1 < abteilu.getLength(); count1++) {
	    					Node node1 = abteilu.item(count1);
	    					if (node1.getNodeType() == node1.ELEMENT_NODE) {
	    						Element subject = (Element) node1;
	    						str.append("\ngegenstand : ");
	    						str.append(subject.getTextContent());
	    					}
	    				}
	    			}
	    		}	
	    	}
	    }catch (Exception e) {
	    	e.printStackTrace();
	    }
		return str.toString();	
		}
}


