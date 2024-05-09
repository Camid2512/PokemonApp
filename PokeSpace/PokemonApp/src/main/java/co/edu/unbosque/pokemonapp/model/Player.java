package co.edu.unbosque.pokemonapp.model;

import java.util.Arrays;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "jugadores")
public class Player {

	@Id
	private String id;

	private String playerName;
	private Pokemon[] pokeTeam;
	private User userCreator;

	public Player() {
		// TODO Auto-generated constructor stub
	}

	public Player(String playerName, Pokemon[] pokeTeam, User userCreator) {
		this.playerName = playerName;
		this.pokeTeam = pokeTeam;
		this.userCreator = userCreator;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public Pokemon[] getPokeTeam() {
		return pokeTeam;
	}

	public void setPokeTeam(Pokemon[] pokeTeam) {
		this.pokeTeam = pokeTeam;
	}

	public User getUserCreator() {
		return userCreator;
	}

	public void setUserCreator(User userCreator) {
		this.userCreator = userCreator;
	}

	@Override
	public String toString() {
		return "Player [id=" + id + ", playerName=" + playerName + ", pokeTeam=" + Arrays.toString(pokeTeam)
				+ ", userCreator=" + userCreator + "]";
	}

}
