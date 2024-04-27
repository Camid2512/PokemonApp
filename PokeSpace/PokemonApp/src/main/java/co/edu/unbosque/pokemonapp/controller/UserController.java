package co.edu.unbosque.pokemonapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.pokemonapp.model.User;
import co.edu.unbosque.pokemonapp.service.UserService;

@CrossOrigin(origins = { "http://localhost:8083", "http://localhost:8082", "*" })
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userServ;

	public UserController() {
		// TODO Auto-generated constructor stub
	}

	@PostMapping("/create")
	public ResponseEntity<String> createUser(@RequestParam String username, @RequestParam String password,
			@RequestParam String email) {

		try {

			User newUser = new User(username, password, email);
			int status = userServ.create(newUser);

			if (status == 0) {
				return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>("Error creating new user, check username already taken",
						HttpStatus.NOT_ACCEPTABLE);
			}

		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>("ERROR Please contact admin", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/getall")
	public ResponseEntity<List<User>> getAll() {

		try {

			List<User> users = userServ.getAll();
			if (users.isEmpty()) {
				return new ResponseEntity<>(users, HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>(users, HttpStatus.ACCEPTED);
			}
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/count")
	public ResponseEntity<Long> countAll() {

		Long count = userServ.count();
		if (count == 0) {

			return new ResponseEntity<>(count, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(count, HttpStatus.ACCEPTED);
		}

	}

	@GetMapping("/getbyid/{id}")
	public ResponseEntity<User> getById(@PathVariable String id) {
		User found = userServ.getById(id);
		if (found != null) {
			return new ResponseEntity<>(found, HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping(path = "/update")
	ResponseEntity<String> updateNew(@RequestParam String id, @RequestParam String newUsername,
			@RequestParam String newPassword, String newEmail) {
		User newUser = new User(newUsername, newPassword, newEmail);

		int status = userServ.updateById(id, newUser);

		if (status == 0) {
			return new ResponseEntity<>("User updated successfully", HttpStatus.ACCEPTED);
		} else if (status == 1) {
			return new ResponseEntity<>("New username already taken", HttpStatus.IM_USED);
		} else if (status == 2) {
			return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>("Error on update", HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(path = "/getrecovercode")
	ResponseEntity<String> getCodeRecovery(@RequestParam String email) {

		return new ResponseEntity<>(userServ.getCodeRecovering(email), HttpStatus.ACCEPTED);

	}

	@PutMapping(path = "/recoverpass")
	ResponseEntity<String> recoverPas(@RequestParam String code, @RequestParam String newPass,
			@RequestParam String email) {

		int status = userServ.recoverPassword(code, newPass, email);

		if (status == 0) {
			return new ResponseEntity<>("password updated successfully", HttpStatus.ACCEPTED);
		} else if (status == 1) {
			return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>("Error on update", HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/deletebyid/{id}")
	ResponseEntity<String> deleteById(@PathVariable String id) {
		int status = userServ.deleteById(id);

		if (status == 0) {
			return new ResponseEntity<>("User deleted successfully", HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>("Error on delete", HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/logincheck")

	ResponseEntity<String> checkLogin(@RequestParam String username, @RequestParam String password) {

		int status = userServ.loginValidation(username, password);
		if (status == 0) {

			return new ResponseEntity<>("Accepted login", HttpStatus.ACCEPTED);

		} else if (status == 1) {
			return new ResponseEntity<>("User or Password incorrect", HttpStatus.UNAUTHORIZED);
		} else {
			return new ResponseEntity<>("Incorrect credentials", HttpStatus.NOT_ACCEPTABLE);
		}

	}

}
