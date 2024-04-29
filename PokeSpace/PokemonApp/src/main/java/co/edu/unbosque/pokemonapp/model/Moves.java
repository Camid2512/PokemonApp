package co.edu.unbosque.pokemonapp.model;

public class Moves {

	private String name;
	private int accurraacy;
	private int pp;
	private int power;

	public Moves() {
		// TODO Auto-generated constructor stub
	}

	public Moves(String name, int accurraacy, int pp, int power) {
		super();
		this.name = name;
		this.accurraacy = accurraacy;
		this.pp = pp;
		this.power = power;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAccurraacy() {
		return accurraacy;
	}

	public void setAccurraacy(int accurraacy) {
		this.accurraacy = accurraacy;
	}

	public int getPp() {
		return pp;
	}

	public void setPp(int pp) {
		this.pp = pp;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

}
