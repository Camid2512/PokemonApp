package co.edu.unbosque.pokemonapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.pokemonapp.model.Session;
import co.edu.unbosque.pokemonapp.service.SessionService;

@CrossOrigin(origins = { "http://localhost:8083", "http://localhost:8082", "*" })
@RestController
@RequestMapping("/session")
public class SessionController {

	@Autowired
	private SessionService sessionServ;

	public SessionController() {
		// TODO Auto-generated constructor stub
	}

	@PostMapping("/create")
	public ResponseEntity<String> createSession() {

		try {

			int status = sessionServ.create();

			if (status == 0) {
				return new ResponseEntity<>("session saved successfully", HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>("Error saving session", HttpStatus.NOT_ACCEPTABLE);
			}

		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>("ERROR Please contact admin", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/getall")
	ResponseEntity<List<Session>> getAll() {
		try {

			List<Session> sessions = sessionServ.getAll();
			if (sessions.isEmpty()) {
				return new ResponseEntity<>(sessions, HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>(sessions, HttpStatus.ACCEPTED);
			}
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}