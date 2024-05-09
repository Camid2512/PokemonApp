package co.edu.unbosque.pokemonapp.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.apache.hc.client5.http.fluent.Content;
import org.apache.hc.client5.http.fluent.Request;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.pokemonapp.model.Move;
import co.edu.unbosque.pokemonapp.model.Pokemon;
import co.edu.unbosque.pokemonapp.repository.PokemonRepository;

@Service
public class PokemonService {

	@Autowired
	PokemonRepository pokeRep;

	@Autowired
	MoveService moveServ;

	public PokemonService() {
		// TODO Auto-generated constructor stub
	}

	public void createAllPokemon() {
		createPokemonRecursive(1);
	}

	private void createPokemonRecursive(int currentId) {
		if (currentId <= 1025) {
			if ((currentId >= 1 && currentId <= 131) || (currentId >= 133 && currentId <= 200)
					|| (currentId >= 202 && currentId <= 234) || (currentId >= 236 && currentId <= 787)
					|| (currentId >= 791 && currentId <= 1025)) {
				try {
					Content content = Request.get("https://pokeapi.co/api/v2/pokemon/" + currentId).execute()
							.returnContent();
					extractPokeInfo(content.toString());
					createPokemonRecursive(currentId + 1);
				} catch (Exception e) {
					System.err.println("Error fetching data for Pokemon with ID: " + currentId);
					e.printStackTrace();
					createPokemonRecursive(currentId + 1);
				}
			} else {
				createPokemonRecursive(currentId + 1);
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

		Move[] randomMoves = moveServ.getRandomMovesArray();
		temp.setMoves(randomMoves);
		JSONObject spritesObj = pokemonData.getJSONObject("sprites");
		String backSpriteUrl = spritesObj.getString("back_default");
		temp.setSprite(backSpriteUrl);
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

	public Pokemon[] getRandomTeam() {
		Pokemon[] battleTeam = new Pokemon[6];
		Random randomNum = new Random();

		List<Integer> availableIndices = new ArrayList<>();
		List<Integer> chosenIndices = new ArrayList<>();

		// Populate availableIndices with IDs of existing Pokémon, excluding the
		// specified IDs
		List<Integer> existingIds = pokeRep.findAll().stream().map(pokemon -> pokemon.getPokeId())
				.collect(Collectors.toList());
		for (int id = 1; id <= 1024; id++) {
			if (existingIds.contains(id) && !Arrays.asList(132, 201, 235, 789, 790).contains(id)) {
				availableIndices.add(id);
			}
		}

		// Generate random team
		for (int i = 0; i < 6; i++) {
			int randomIndex;
			if (availableIndices.isEmpty()) {
				break; // No more available Pokémon IDs
			}
			do {
				randomIndex = availableIndices.get(randomNum.nextInt(availableIndices.size()));
			} while (chosenIndices.contains(randomIndex));

			chosenIndices.add(randomIndex); // Add the selected index to the list
			battleTeam[i] = pokeRep.findByPokeId(randomIndex).orElse(null); // Using optional orElse to handle empty
																			// result
		}

		return battleTeam;
	}

	public Pokemon getPokeInfo(String name) {
		Optional<Pokemon> found = pokeRep.findByName(name);

		if (found.isPresent()) {
			return found.get();
		} else {
			return null;
		}
	}

}
