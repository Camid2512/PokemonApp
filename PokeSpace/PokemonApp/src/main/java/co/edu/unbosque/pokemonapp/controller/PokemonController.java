package co.edu.unbosque.pokemonapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.pokemonapp.service.PokemonService;

@CrossOrigin(origins = { "http://localhost:8083", "http://localhost:8082", "*" })
@RestController
@RequestMapping("/pokemon")
public class PokemonController {

	@Autowired
	private PokemonService pokeServ;

	public PokemonController() {
		// TODO Auto-generated constructor stub
	}

	@PostMapping("/create")
	public ResponseEntity<String> createUser() {
		try {

			pokeServ.createAllPokemon();

			return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);

		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>("ERROR Please contact admin", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
