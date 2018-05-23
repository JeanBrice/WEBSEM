package main;

public class Film {
	
	private String country, poster, title, genre, runtime, link;
	private int metascore, year;
	private double imdbRating;
	
	/* Récupéré depuis le xml :
	 *  title, link, year
	 * 
	 * Récupéré depuis IMDB :
	 *  country, poster, genre, runtime, metascore, imdbRating
	 */
	
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getRuntime() {
		return runtime;
	}
	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public int getMetascore() {
		return metascore;
	}
	public void setMetascore(int metascore) {
		this.metascore = metascore;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public double getImdbRating() {
		return imdbRating;
	}
	public void setImdbRating(double imdbRating) {
		this.imdbRating = imdbRating;
	}
	
	@Override
	public String toString() {
		return this.title + " (" + this.year + ") " + "@[" + link + "]\nGenre: " + this.genre + "\nIMDB: "
			+ this.imdbRating + ", METASCORE: " + this.metascore + "\nRuntime: "
			+ this.runtime + ", Poster: " + this.poster;
	}
	
	public String toJSON(){
		StringBuilder s = new StringBuilder();
		s.append("\"Film\": {");
		s.append("\"title\": \"" + title + "\",");
		s.append("\"year\": \"" + year + "\",");
		s.append("\"link\": \"" + link + "\",");
		s.append("\"genre\": \"" + genre + "\",");
		s.append("\"imdbrating\": \"" + imdbRating + "\",");
		s.append("\"metascore\": \"" + metascore + "\",");
		s.append("\"runtime\": \"" + runtime + "\",");
		s.append("\"poster\": \"" + poster + "\"");
		s.append("}");
		return s.toString();
	}
}
