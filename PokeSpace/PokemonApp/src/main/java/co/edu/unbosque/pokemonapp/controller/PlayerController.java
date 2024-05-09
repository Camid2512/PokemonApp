package co.edu.unbosque.pokemonapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.pokemonapp.model.User;
import co.edu.unbosque.pokemonapp.service.PlayerService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@CrossOrigin(origins = { "http://localhost:8083", "http://localhost:8082", "*" })
@RestController
@RequestMapping("/player")
public class PlayerController {

	@Autowired
	private PlayerService playerServ;

	public PlayerController() {
		// TODO Auto-generated constructor stub
	}

	@PostMapping("/createplayer")
	ResponseEntity<String> createPlayer(@RequestParam String playerName, @RequestParam String userCreator) {

		int status = playerServ.save(playerName, userCreator);

		if (status == 0) {
			return new ResponseEntity<>("Player created successfully", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("Error creating new player, maybe not user was selected",
					HttpStatus.NOT_ACCEPTABLE);
		}

	}

}
