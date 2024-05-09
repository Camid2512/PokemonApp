package co.edu.unbosque.pokemonapp.model;

import java.util.Arrays;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "pokemones")
public class Pokemon {
	@Id
	private String id;

	@Indexed(unique = true)
	private int pokeId;

	private String name;
	private int height;
	private int weight;
	private Move[] moves;
	private String sprite;
	private double hp;
	private double attack;
	private double defense;
	private double specialAttack;
	private double specialDefense;
	private int speed;
	private String[] types;

	public Pokemon() {
		// TODO Auto-generated constructor stub
		moves = new Move[4];
		types = new String[4];
	}

	public Pokemon(int pokeId, String name, int height, int weight, Move[] moves, String sprite, double hp,
			double attack, double defense, double specialAttack, double specialDefense, int speed, String[] types) {

		moves = new Move[4];
		types = new String[4];
		this.pokeId = pokeId;
		this.name = name;
		this.height = height;
		this.weight = weight;
		this.moves = moves;
		this.sprite = sprite;
		this.hp = hp;
		this.attack = attack;
		this.defense = defense;
		this.specialAttack = specialAttack;
		this.specialDefense = specialDefense;
		this.speed = speed;
		this.types = types;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getPokeId() {
		return pokeId;
	}

	public void setPokeId(int pokeId) {
		this.pokeId = pokeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public Move[] getMoves() {
		return moves;
	}

	public void setMoves(Move[] moves) {
		this.moves = moves;
	}

	public String getSprite() {
		return sprite;
	}

	public void setSprite(String sprite) {
		this.sprite = sprite;
	}

	public double getHp() {
		return hp;
	}

	public void setHp(double hp) {
		this.hp = hp;
	}

	public double getAttack() {
		return attack;
	}

	public void setAttack(double attack) {
		this.attack = attack;
	}

	public double getDefense() {
		return defense;
	}

	public void setDefense(double defense) {
		this.defense = defense;
	}

	public double getSpecialAttack() {
		return specialAttack;
	}

	public void setSpecialAttack(double specialAttack) {
		this.specialAttack = specialAttack;
	}

	public double getSpecialDefense() {
		return specialDefense;
	}

	public void setSpecialDefense(double specialDefense) {
		this.specialDefense = specialDefense;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public String[] getTypes() {
		return types;
	}

	public void setTypes(String[] types) {
		this.types = types;
	}

	@Override
	public String toString() {
		return "Pokemon [id=" + id + ", pokeId=" + pokeId + ", name=" + name + ", height=" + height + ", weight="
				+ weight + ", moves=" + Arrays.toString(moves) + ", sprite=" + sprite + ", hp=" + hp + ", attack="
				+ attack + ", defense=" + defense + ", specialAttack=" + specialAttack + ", specialDefense="
				+ specialDefense + ", speed=" + speed + ", types=" + Arrays.toString(types) + "]";
	}

}
