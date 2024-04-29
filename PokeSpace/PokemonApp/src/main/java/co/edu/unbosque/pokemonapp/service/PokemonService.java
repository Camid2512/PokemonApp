package co.edu.unbosque.pokemonapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.hc.client5.http.fluent.Content;
import org.apache.hc.client5.http.fluent.Request;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.pokemonapp.model.Pokemon;
import co.edu.unbosque.pokemonapp.repository.PokemonRepository;

@Service
public class PokemonService {

	@Autowired
	PokemonRepository pokeRep;

	public PokemonService() {
		// TODO Auto-generated constructor stub
	}

	public void createAllPokemon() {
		for (int i = 0; i < 1025; i++) {

			try {
				Content content = Request.get("https://pokeapi.co/api/v2/pokemon/" + i + 1).execute().returnContent();
				extractPokeInfo(content.toString());
			} catch (Exception e) {
				// TODO: handle exception
			}

		}
	}

	public void extractPokeInfo(String json) {

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

		pokeRep.save(temp);

	}

	public String[] getRandomMoves(JSONArray movesArray, int numMoves) {
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

}
