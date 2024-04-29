package co.edu.unbosque.pokemonapp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.hc.client5.http.fluent.Content;
import org.apache.hc.client5.http.fluent.Request;
import org.json.JSONArray;
import org.json.JSONObject;

public class Test {

	public static void extractPokemonInfo(String json) {
		JSONObject pokemonData = new JSONObject(json);

		// Extract basic info
		String name = pokemonData.getString("name");
		int height = pokemonData.getInt("height");
		int weight = pokemonData.getInt("weight");

		System.out.println("Name: " + name);
		System.out.println("Height: " + height);
		System.out.println("Weight: " + weight);

		// Extract types
		JSONArray typesArray = pokemonData.getJSONArray("types");
		System.out.print("Types: ");

		String aux = "";
		String arr[] = null;
		for (int i = 0; i < typesArray.length(); i++) {
			JSONObject typeObj = typesArray.getJSONObject(i).getJSONObject("type");
			String typeName = typeObj.getString("name");
			aux += typeName;
			System.out.print(typeName);
			if (i < typesArray.length() - 1) {
				aux += ",";
				System.out.print(", ");
			}
			arr = aux.split(",");
		}
		System.out.println();

		for (int i = 0; i < arr.length; i++) {
			System.out.println("Posicion " + i + ": " + arr[i]);
		}

		// Extract moves
		JSONArray movesArray = pokemonData.getJSONArray("moves");
		System.out.print("Moves: ");
		for (int i = 0; i < movesArray.length(); i++) {
			JSONObject moveObj = movesArray.getJSONObject(i).getJSONObject("move");
			String moveName = moveObj.getString("name");
			System.out.print(moveName);
			if (i < movesArray.length() - 1) {
				System.out.print(", ");
			}
		}
		System.out.println();

		JSONArray statsArray = pokemonData.getJSONArray("stats");
		for (int i = 0; i < statsArray.length(); i++) {
			JSONObject statObj = statsArray.getJSONObject(i);
			String statName = statObj.getJSONObject("stat").getString("name");
			int baseStat = statObj.getInt("base_stat");
			System.out.println(statName + ": " + baseStat);
		}
		// Extract all sprite URLs if available
		JSONObject spritesObj = pokemonData.getJSONObject("sprites");
		if (!spritesObj.isNull("other")) {
			JSONObject otherSpritesObj = spritesObj.getJSONObject("other");
			if (otherSpritesObj.has("official-artwork")) {
				JSONObject officialArtworkObj = otherSpritesObj.getJSONObject("official-artwork");
				String officialArtworkUrl = officialArtworkObj.getString("front_default");
				System.out.println("Official Artwork URL: " + officialArtworkUrl);
			} else {
				System.out.println("Official Artwork URL not available");
			}
		} else {
			System.out.println("Sprites not available");
		}
		// Extract random moves and store in an array of size 4
		System.out.print("Random Moves: ");
		String[] randomMoves = getRandomMoves(movesArray, 4);
		for (int i = 0; i < randomMoves.length; i++) {
			System.out.print(randomMoves[i]);
			if (i < randomMoves.length - 1) {
				System.out.print(", ");
			}
		}
		System.out.println();
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
			} while (chosenIndices.contains(randomIndex)); // Ensure not choosing same move twice
			chosenIndices.add(randomIndex);
			JSONObject moveObj = movesArray.getJSONObject(randomIndex).getJSONObject("move");
			String moveName = moveObj.getString("name");
			randomMoves[i] = moveName;
		}
		return randomMoves;
	}

	public static void main(String[] args) {
		// Here you can provide the JSON data of a Pokemon

		try {
			Content content = Request.get("https://pokeapi.co/api/v2/pokemon/1").execute().returnContent();
			extractPokemonInfo(content.toString());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
