package co.edu.unbosque.pokemonapp.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Test {

	public static void main(String[] args) {
		String pokemonName = "bulbasaur"; // Replace with the desired Pokemon name or ID

		try {
			// Make HTTP request to PokeAPI
			URL url = new URL("https://pokeapi.co/api/v2/pokemon/" + pokemonName);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");

			// Read response
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuilder response = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				response.append(line);
			}
			reader.close();

			// Extract and display Pokemon info
			extractPokemonInfo(response.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void extractPokemonInfo(String json) {
		JSONObject pokemonData = new JSONObject(json);

		// Extract basic info
		int id = pokemonData.getInt("id");
		String name = pokemonData.getString("name");
		int height = pokemonData.getInt("height");
		int weight = pokemonData.getInt("weight");
		int baseExperience = pokemonData.getInt("base_experience");

		System.out.println("ID: " + id);
		System.out.println("Name: " + name);
		System.out.println("Height: " + height + " decimetres");
		System.out.println("Weight: " + weight + " hectograms");
		System.out.println("Base Experience: " + baseExperience);

		// Extract abilities
		JSONArray abilitiesArray = pokemonData.getJSONArray("abilities");
		System.out.print("Abilities: ");
		for (int i = 0; i < abilitiesArray.length(); i++) {
			JSONObject abilityObj = abilitiesArray.getJSONObject(i).getJSONObject("ability");
			String abilityName = abilityObj.getString("name");
			System.out.print(abilityName);
			if (i < abilitiesArray.length() - 1) {
				System.out.print(", ");
			}
		}
		System.out.println();

		// Extract types
		JSONArray typesArray = pokemonData.getJSONArray("types");
		System.out.print("Types: ");
		for (int i = 0; i < typesArray.length(); i++) {
			JSONObject typeObj = typesArray.getJSONObject(i).getJSONObject("type");
			String typeName = typeObj.getString("name");
			System.out.print(typeName);
			if (i < typesArray.length() - 1) {
				System.out.print(", ");
			}
		}
		System.out.println();

		// Extract stats
		int hp = 0, attack = 0, defense = 0, specialAttack = 0, specialDefense = 0, speed = 0;
		JSONArray statsArray = pokemonData.getJSONArray("stats");
		for (int i = 0; i < statsArray.length(); i++) {
			JSONObject statObj = statsArray.getJSONObject(i);
			String statName = statObj.getJSONObject("stat").getString("name");
			int baseStat = statObj.getInt("base_stat");
			switch (statName) {
			case "hp":
				hp = baseStat;
				break;
			case "attack":
				attack = baseStat;
				break;
			case "defense":
				defense = baseStat;
				break;
			case "special-attack":
				specialAttack = baseStat;
				break;
			case "special-defense":
				specialDefense = baseStat;
				break;
			case "speed":
				speed = baseStat;
				break;
			}
		}

		System.out.println("HP: " + hp);
		System.out.println("Attack: " + attack);
		System.out.println("Defense: " + defense);
		System.out.println("Special Attack: " + specialAttack);
		System.out.println("Special Defense: " + specialDefense);
		System.out.println("Speed: " + speed);

		// Extract sprite URL
		JSONObject spritesObj = pokemonData.getJSONObject("sprites");
		String backSpriteUrl = spritesObj.getString("back_default");
		System.out.println("Back-Facing Sprite URL: " + backSpriteUrl);
	}
}
