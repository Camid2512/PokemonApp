package co.edu.unbosque.pokemonapp.model;

public class DateTime {

	private String datetime;

	public DateTime() {
		// TODO Auto-generated constructor stub
	}

	public DateTime(String datetime) {
		this.datetime = datetime;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	@Override
	public String toString() {
		return "DateTime [datetime=" + datetime + "]";
	}

}
