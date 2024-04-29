package co.edu.unbosque.pokemonapp.model;

import java.util.Arrays;

public class Pokemon {

//	private String id;
	private int id;
	private String name;
	private int height;
	private int weight;
//	private Moves[] moves;
	private String sprite;
	private int hp;
	private int attack;
	private int specialattack;
	private int speed;
//	private Type types;

	public Pokemon() {
		// TODO Auto-generated constructor stub
	}

	public Pokemon(int pokeId, String name, int height, int weight, Moves[] moves, String sprite, int hp, int attack,
			int specialattack, int speed, Type types) {

//		this.pokeId = pokeId;
		this.name = name;
		this.height = height;
		this.weight = weight;
//		this.moves = moves;
		this.sprite = sprite;
		this.hp = hp;
		this.attack = attack;
		this.specialattack = specialattack;
		this.speed = speed;
//		this.types = types;
	}

//	public String getId() {
//		return id;
//	}
//
//	public void setId(String id) {
//		this.id = id;
//	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

//	public Moves[] getMoves() {
//		return moves;
//	}
//
//	public void setMoves(Moves[] moves) {
//		this.moves = moves;
//	}

	public String getSprite() {
		return sprite;
	}

	public void setSprite(String sprite) {
		this.sprite = sprite;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getSpecialattack() {
		return specialattack;
	}

	public void setSpecialattack(int specialattack) {
		this.specialattack = specialattack;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

//	public Type getTypes() {
//		return types;
//	}
//
//	public void setTypes(Type types) {
//		this.types = types;
//	}

	@Override
	public String toString() {
		return "Pokemon [id=" + id + ", name=" + name + ", height=" + height + ", weight=" + weight + ", moves="
				+ ", sprite=" + sprite + ", hp=" + hp + ", attack=" + attack + ", specialattack=" + specialattack
				+ ", speed=" + speed + ", types=" + "]";
	}

}
