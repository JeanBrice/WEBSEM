package main;

public class RequestBuilder {
	
	private Film f;
	private Scene s;
	
	public RequestBuilder(Film f, Scene s){
		this.f = f;
		this.s = s;
	}
	
	public String build(){
		StringBuilder sb = new StringBuilder();
		
		String filmName = clean(f.getTitle());
		String sceneName = clean(s.getLocationName() + "-" + s.hashCode());
		
		sb.append("PREFIX :<http://www.semanticweb.org/localuser/ontologies/2018/4/untitled-ontology-5/>\n");
		sb.append("INSERT DATA {\n");
		sb.append("	:" + filmName + " :has_for_genre \"" + f.getGenre() + "\";\n");
		sb.append("		:has_for_country \"" + f.getCountry() + "\";\n");
		sb.append("		:has_for_imdb_link \"" + f.getLink() + "\";\n");
		sb.append("		:has_for_imdb_rating \"" + f.getImdbRating() + "\";\n");
		sb.append("		:has_for_metascore \"" + f.getMetascore() + "\";\n");
		sb.append("		:has_for_poster \"" + f.getPoster() + "\";\n");
		sb.append("		:has_for_runtime \"" + f.getRuntime() + "\";\n");
		sb.append("		:has_for_title \"" + f.getTitle().replaceAll("\"", "'").replaceAll("\n", "") + "\";\n");
		sb.append("		:has_for_year \"" + f.getYear() + "\" .\n");
		sb.append("	:" + sceneName + " :has_for_location_name \"" + s.getLocationName().replaceAll("\"", "'").replaceAll("\n", "") + "\";\n");
		sb.append("		:has_latitude " + s.getLatitude() + ";\n");
		sb.append("		:has_longitude " + s.getLongitude() + " .\n");
		sb.append("	:" + filmName + " :contains :" + sceneName + " .\n");
		sb.append("	:" + sceneName + " :takesPlaceIn :New_York .\n");
		sb.append("	:" + sceneName + " :isContainedIn :" + filmName + " .\n");
		sb.append("	:New_York :hosts :" + sceneName + " .\n");
		sb.append("}");
		return sb.toString();
	}

	public String clean(String s){
		s = s.replaceAll(" ", "_");
		s = s.replaceAll("<br>", "");
		s = s.replaceAll(",", "_");
		s = s.replaceAll("…", "");
		s = s.replaceAll("\\*", "");
		s = s.replaceAll("\\.", "");
		s = s.replaceAll("\"", "");
		s = s.replaceAll("'", "-");
		s = s.replaceAll("\\&", "and");
		s = s.replaceAll("\n", "");
		s = s.replaceAll("\\(", "-");
		s = s.replaceAll("\\)", "-");
		
		return s;
	}
	
}
