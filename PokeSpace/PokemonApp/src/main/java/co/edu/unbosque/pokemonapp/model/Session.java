package co.edu.unbosque.pokemonapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sesiones")
public class Session {

	@Id
	private String id;

	private User userSession;
	private String ip_address;
	private Float longitude;
	private Float latitude;
	private String datetime;

	public Session() {
		// TODO Auto-generated constructor stub
	}

	public Session(User userSession, String ip_address, Float longitude, Float latitude, String datetime) {

		this.userSession = userSession;
		this.ip_address = ip_address;
		this.longitude = longitude;
		this.latitude = latitude;
		this.datetime = datetime;

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getUserSession() {
		return userSession;
	}

	public void setUserSession(User userSession) {
		this.userSession = userSession;
	}

	public String getIp_address() {
		return ip_address;
	}

	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
	}

	public Float getLongitude() {
		return longitude;
	}

	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}

	public Float getLatitude() {
		return latitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	@Override
	public String toString() {
		return "Session [id=" + id + ", userSession=" + userSession + ", ip_address=" + ip_address + ", longitude="
				+ longitude + ", latitude=" + latitude + ", datetime=" + datetime + "]";
	}

}
