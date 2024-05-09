package co.edu.unbosque.pokemonapp.model;

public class Move {

	private String name;
	private double attckDmg;
	private String type;
	private int pp;

	public Move() {
		// TODO Auto-generated constructor stub
	}

	public Move(String name, double attckDmg, String type, int pp) {
		super();
		this.name = name;
		this.attckDmg = attckDmg;
		this.type = type;
		this.pp = pp;
	}

	public double getAttckDmg() {
		return attckDmg;
	}

	public void setAttckDmg(double attckDmg) {
		this.attckDmg = attckDmg;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getPp() {
		return pp;
	}

	public void setPp(int pp) {
		this.pp = pp;
	}

	@Override
	public String toString() {
		return "Move [name=" + name + ", attckDmg=" + attckDmg + ", type=" + type + ", pp=" + pp + "]";
	}

}
