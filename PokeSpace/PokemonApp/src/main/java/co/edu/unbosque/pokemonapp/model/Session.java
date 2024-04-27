package co.edu.unbosque.pokemonapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sesiones")
public class Session {

	@Id
	private String id;

	private String ip_address;
	private Float longitude;
	private Float latitude;

	public Session() {
		// TODO Auto-generated constructor stub
	}

	public Session(String ip_address, Float longitude, Float latitude) {

		this.ip_address = ip_address;
		this.longitude = longitude;
		this.latitude = latitude;

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "Session [id=" + id + ", ip_address=" + ip_address + ", longitude=" + longitude + ", latitude="
				+ latitude + "]";
	}

}
