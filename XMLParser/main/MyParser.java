package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.update.UpdateRequest;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class MyParser {

	public static void main(String[] args) {
		
		try {
			File fXmlFile = new File("src/resources/Interactive_Map_Data.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("Row");
			
			int j = 0;
			for (int i = 0; i < nList.getLength() ; i++) {
				Node nNode = nList.item(i);
				
				Element eElement = (Element) nNode;
				
				NodeList subList = eElement.getElementsByTagName("Cell");
				
				Node title = subList.item(0);
				Node year = subList.item(1);
				Node link = subList.item(15);
				Node type = subList.item(14);

				Node locationName = subList.item(8);
				Node latitude = subList.item(9);
				Node longitude = subList.item(10);
				
				// On va remplir l'objet film
				/* On ignore : 
				 * 	les objets null
				 * 	les TV Shows
				 * 	les objets dont le titre est vide
				 */
				if(title != null && type != null && !title.getTextContent().equals("") && type.getTextContent().equals("Film")) {
					
					System.out.println("---" + j + "---");
					System.out.println("(i="+i+")");
					
					Film f = new Film();
					f.setTitle(title.getTextContent());
					f.setYear(!year.getTextContent().equals("") ? new Integer(year.getTextContent()) : 0);
					f.setLink(link.getTextContent());
					
					Scene s = new Scene();
					s.setLatitude(new Double(latitude.getTextContent()));
					s.setLongitude(new Double(longitude.getTextContent()));
					s.setLocationName(locationName.getTextContent());
					
					// Requete IMDB
					String key = "86ecf20f";
					String baseURL = "http://www.omdbapi.com/?apikey=" + key + "&t=";
					String urlString = baseURL + f.getTitle().replaceAll(" ", "%20");
					
					URL url = new URL(urlString);
					URLConnection conn = url.openConnection();
					InputStream is = conn.getInputStream();
					
					BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			        StringBuilder imdbResponse = new StringBuilder();
			        String line;
			        while ((line = reader.readLine()) != null) {
			        	imdbResponse.append(line);
			        }			
			        JSONObject obj = new JSONObject(imdbResponse.toString());
			        
			        if(obj.has("Country") && obj.has("Poster") && obj.has("Year") && obj.has("Genre") && obj.has("Runtime")){
				        String country = obj.has("Country") ? obj.getString("Country") : "N/A";
				        String poster = obj.getString("Poster");
				        int year1 = new Integer(!obj.getString("Year").equals("N/A") ? obj.getString("Year") : "0");
				        String genre = obj.getString("Genre");
				        String runtime = obj.getString("Runtime");
				        
				        int metascore = new Integer(!obj.getString("Metascore").equals("N/A") ? obj.getString("Metascore") : "0");
				        Double imdbRating = new Double(obj.getString("imdbRating"));
				        
				        f.setCountry(country);
				        f.setGenre(genre);
				        f.setPoster(poster);
				        f.setMetascore(metascore);
				        f.setImdbRating(imdbRating);
				        f.setRuntime(runtime);
				        
				        if(f.getYear() == 0){
				        	f.setYear(year1);
				        }
				        
				        //System.out.println(f.toJSON());
				        //System.out.println(s.toJSON());
				        RequestBuilder query = new RequestBuilder(f, s);
				        System.out.println(query.build());
				        //System.out.println("---");
				        
				        // Request to populate db
				        String service = "http://localhost:3030/MoviesDataset/update";
				        UpdateProcessor qe = UpdateExecutionFactory.createRemote(new UpdateRequest().add(query.build()), service);
	
				        qe.execute();
						
				        // counting how many movies we have handled
						j++;
					}				
				}
			}
			System.out.println("\n" + j + " éléments traités");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
