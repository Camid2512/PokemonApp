package co.edu.unbosque.pokemonapp.controller;

import java.io.IOException;

import org.apache.hc.client5.http.fluent.Content;
import org.apache.hc.client5.http.fluent.Request;

import com.google.gson.Gson;

import co.edu.unbosque.pokemonapp.model.Pokemon;
import co.edu.unbosque.pokemonapp.model.Sprite;
import co.edu.unbosque.pokemonapp.model.User;
import co.edu.unbosque.pokemonapp.service.SessionService;

public class Test {

	static Gson gson = new Gson();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		SessionService ses = new SessionService();
//		User temp = new User();
//		ses.create(temp);

		try {

			Content content = Request.get("https://pokeapi.co/api/v2/pokemon/1").execute().returnContent();
			Content content2 = Request.get(
					"https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/130.png")
					.execute().returnContent();

			Pokemon newPokemon = gson.fromJson(content.toString(), Pokemon.class);
			Sprite spriteArt = gson.fromJson(content.toString(), Sprite.class);

//			System.out.println(newPokemon.getId());
//			System.out.println(newPokemon.getName());
//			System.out.println(newPokemon.getHeight());
//			System.out.println(newPokemon.getWeight());
////			System.out.println(newPokemon.getMoves());
//			System.out.println(spriteArt.getOfficialartwork());
//			System.out.println(newPokemon.getHp());
//			System.out.println(newPokemon.getAttack());
//			System.out.println(newPokemon.getSpecialattack());
//			System.out.println(newPokemon.getSpeed());
//			System.out.println(newPokemon.getTypes());
			System.out.println(content2);
//			System.out.println(content);
		} catch (IOException error) {
			System.out.println(error);
		}
	}
}
