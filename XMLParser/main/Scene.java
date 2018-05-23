package main;

public class Scene {

	private double latitude, longitude;
	private String locationName;
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}	
	
	@Override
	public String toString() {
		return "    Location : " + this.locationName + " (" + this.latitude + ", " + this.longitude + ")";
	}
	
	public String toJSON(){
		StringBuilder s = new StringBuilder();
		s.append("\"Scene\": {");
		s.append("\"location\": \"" + locationName + "\",");
		s.append("\"lat\": \"" + latitude + "\",");
		s.append("\"long\": \"" + longitude + "\"");
		s.append("}");
		return s.toString();
	}

	@Override
	public int hashCode(){
		int a = new Double(this.latitude).hashCode();
		int b = new Double(this.longitude).hashCode();
		
		return a+b;
	}
}
