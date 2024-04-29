package co.edu.unbosque.pokemonapp.model;

public class Sprite {

	private String officialartwork;

	public Sprite() {
		// TODO Auto-generated constructor stub
	}

	public Sprite(String officialartwork) {
		super();
		this.officialartwork = officialartwork;
	}

	public String getOfficialartwork() {
		return officialartwork;
	}

	public void setOfficialartwork(String officialartwork) {
		this.officialartwork = officialartwork;
	}

	@Override
	public String toString() {
		return "Sprite [officialartwork=" + officialartwork + "]";
	}

}
