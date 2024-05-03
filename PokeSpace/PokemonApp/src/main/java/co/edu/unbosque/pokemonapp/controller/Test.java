package co.edu.unbosque.pokemonapp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.hc.client5.http.fluent.Content;
import org.apache.hc.client5.http.fluent.Request;
import org.json.JSONArray;
import org.json.JSONObject;

import co.edu.unbosque.pokemonapp.model.Pokemon;
import co.edu.unbosque.pokemonapp.repository.PokemonRepository;

public class Test {

	private static PokemonRepository pokeRep;

	public static void createAllPokemon() {
		createPokemonRecursive(791);
	}

	private static void createPokemonRecursive(int currentId) {
		if (currentId <= 1025) {
			try {
				Content content = Request.get("https://pokeapi.co/api/v2/pokemon/" + currentId).execute()
						.returnContent();
				extractPokeInfo(content.toString());
				// Call the helper method recursively with the next ID
				createPokemonRecursive(currentId + 1);
			} catch (Exception e) {
				System.err.println("Error fetching data for Pokemon with ID: " + currentId);
				e.printStackTrace();
				// If an error occurs, move to the next ID
				createPokemonRecursive(currentId + 1);
			}
		}
	}

	public static void extractPokeInfo(String json) {

		JSONObject pokemonData = new JSONObject(json);

		Pokemon temp = new Pokemon();

		String name = pokemonData.getString("name");
		int height = pokemonData.getInt("height");
		int weight = pokemonData.getInt("weight");

		temp.setPokeId(pokemonData.getInt("id"));
		temp.setName(name);
		temp.setHeight(height);
		temp.setWeight(weight);

		JSONArray typesArray = pokemonData.getJSONArray("types");
		String aux = "";
		String arr[] = null;
		for (int i = 0; i < typesArray.length(); i++) {

			JSONObject typeObj = typesArray.getJSONObject(i).getJSONObject("type");
			String typeName = typeObj.getString("name");
			aux += typeName;
			if (i < typesArray.length() - 1) {
				aux += ",";
			}
			arr = aux.split(",");

		}

		for (int i = 0; i < arr.length; i++) {
			temp.setTypes(arr);
		}

		JSONArray movesArray = pokemonData.getJSONArray("moves");
		String[] randomMoves = getRandomMoves(movesArray, 4);
		temp.setMoves(randomMoves);

		int hp = 0, attack = 0, defense = 0, specialAttack = 0, specialDefense = 0, speed = 0;
		JSONArray statsArray = pokemonData.getJSONArray("stats");
		for (int i = 0; i < statsArray.length(); i++) {
			JSONObject statObj = statsArray.getJSONObject(i);
			String statName = statObj.getJSONObject("stat").getString("name");
			int baseStat = statObj.getInt("base_stat");
			switch (statName) {
			case "hp":
				hp = baseStat;
				temp.setHp(hp);
				break;
			case "attack":
				attack = baseStat;
				temp.setAttack(attack);
				break;
			case "defense":
				defense = baseStat;
				temp.setDefense(defense);
				break;
			case "special-attack":
				specialAttack = baseStat;
				temp.setSpecialAttack(specialAttack);
				break;
			case "special-defense":
				specialDefense = baseStat;
				temp.setSpecialDefense(specialDefense);
				break;
			case "speed":
				speed = baseStat;
				temp.setSpeed(speed);
				break;
			}
		}

//		pokeRep.save(temp);
		System.out.println(temp.toString());

	}

	public static String[] getRandomMoves(JSONArray movesArray, int numMoves) {
		String[] randomMoves = new String[numMoves];
		Random random = new Random();
		int totalMoves = movesArray.length();
		List<Integer> chosenIndices = new ArrayList<>();
		for (int i = 0; i < numMoves; i++) {
			int randomIndex;
			do {
				randomIndex = random.nextInt(totalMoves);
			} while (chosenIndices.contains(randomIndex));

			chosenIndices.add(randomIndex);
			JSONObject moveObj = movesArray.getJSONObject(randomIndex).getJSONObject("move");
			String moveName = moveObj.getString("name");
			randomMoves[i] = moveName;
		}
		return randomMoves;
	}

	public static void main(String[] args) {
		// Here you can provide the JSON data of a Pokemon
		createAllPokemon();

	}
}
