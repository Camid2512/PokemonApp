package co.edu.unbosque.pokemonapp.service;

import java.io.IOException;
import java.util.Random;

import org.apache.hc.client5.http.fluent.Request;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.edu.unbosque.pokemonapp.model.Move;

@Service
public class MoveService {

	private static final String API_BASE_URL = "https://pokeapi.co/api/v2/move/";
	private Move move;

	public MoveService() {
		// TODO Auto-generated constructor stub
	}

	public static Move getRandomMove() {
		Random random = new Random();
		int randomMoveId = random.nextInt(826) + 1; // There are 826 moves in total in the API

		String apiUrl = API_BASE_URL + randomMoveId;

		try {
			String jsonResponse = Request.get(apiUrl).execute().returnContent().asString();
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode rootNode = objectMapper.readTree(jsonResponse);

			String name = rootNode.get("name").asText();
			int attackDamage = rootNode.get("power").asInt();
			String type = rootNode.get("type").get("name").asText();
			int pp = rootNode.get("pp").asInt();

			return new Move(name, attackDamage, type, pp);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Move[] getRandomMovesArray() {
		Move[] moves = new Move[4];
		for (int i = 0; i < 4; i++) {
			moves[i] = getRandomMove();
		}
		return moves;
	}

}
