package co.edu.unbosque.pokemonapp.controller;

import java.io.IOException;

import org.apache.hc.client5.http.fluent.Content;
import org.apache.hc.client5.http.fluent.Request;

import co.edu.unbosque.pokemonapp.model.User;
import co.edu.unbosque.pokemonapp.service.SessionService;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		SessionService ses = new SessionService();
//		User temp = new User();
//		ses.create(temp);

		try {

			Content content = Request.get("https://pokeapi.co/api/v2/pokemon/1").execute().returnContent();

			System.out.println(content);
		} catch (IOException error) {
			System.out.println(error);
		}
	}
}
