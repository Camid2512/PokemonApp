package co.edu.unbosque.pokemonapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.pokemonapp.model.User;
import co.edu.unbosque.pokemonapp.repository.UserRepository;

@Service
public class UserService {
	private String codeTemp = "";
	@Autowired
	private UserRepository userRep;

	@Autowired
	private EmailService emailService;

	public UserService() {
		// TODO Auto-generated constructor stub
	}

	public int create(User newUser) {

		String username = newUser.getUsername();
		String password = newUser.getPassword();
		String email = newUser.getEmail();
		if (findUsernameAlreadyTaken(newUser) || findEmailAlreadyTaken(newUser)) {
			return 1;
		} else {
			newUser.setUsername(username);
			newUser.setPassword(password);
			newUser.setEmail(email);
			System.out.println(email);
			emailService.sendWelcomeEmail(email);
			userRep.save(newUser);
			return 0;
		}
	}

	public int loginValidation(String username, String password) {

		String userValidiation = username;
		String passwordValdiation = password;
		for (User u : getAll()) {

			if (u.getUsername().equals(userValidiation)) {
				if (u.getPassword().equals(passwordValdiation)) {

					return 0;
				}
			}
		}
		return 1;

	}

	public long count() {
		return userRep.count();
	}

	public List<User> getAll() {
		return (List<User>) userRep.findAll();
	}

	public int deleteById(String id) {
		Optional<User> found = userRep.findById(id);
		if (found.isPresent()) {
			userRep.delete(found.get());
			return 0;
		} else {
			return 1;
		}
	}

	public int updateById(String id, User newData) {
		Optional<User> found = userRep.findById(id);
		Optional<User> newFound = userRep.findByUsername(newData.getUsername());

		if (found.isPresent() && !newFound.isPresent()) {
			User temp = found.get();
			temp.setUsername(newData.getUsername());
			temp.setPassword(newData.getPassword());
			temp.setEmail(newData.getEmail());
			userRep.save(temp);
			emailService.sendUpdateEmail(newData.getEmail());
			return 0;
		}
		if (found.isPresent() && newFound.isPresent()) {
			return 1;
		}
		if (!found.isPresent()) {
			return 2;
		} else {
			return 3;
		}
	}

	public User getById(String id) {
		Optional<User> found = userRep.findById(id);
		if (found.isPresent()) {
			return found.get();
		} else {
			return null;
		}
	}

	public User getByUsername(String username) {
		Optional<User> found = userRep.findByUsername(username);
		if (found.isPresent()) {
			return found.get();
		} else {
			return null;
		}
	}

	public String getCodeRecovering(String email) {

		Optional<User> found = userRep.findByEmail(email);
		if (found.isPresent()) {
			codeTemp = emailService.recoverPasswordEmail(email);
			System.out.println(codeTemp);
			return codeTemp;
		} else {
			System.out.println(codeTemp);
			return codeTemp;
		}
	}

	public int recoverPassword(String code, String newPass, String email) {

		Optional<User> found = userRep.findByEmail(email);
		String codeGet = codeTemp;
		System.out.println(codeGet);
		if (found.isPresent()) {
			if (code.equals(codeGet)) {

				User temp = found.get();
				temp.setUsername(found.get().getUsername());
				temp.setPassword(found.get().getPassword());
				temp.setEmail(found.get().getEmail());
				userRep.save(temp);
				emailService.changedPassword(email, newPass);
				return 0;
			}
		} else {
			return 1;
		}
		return 2;
	}

	public boolean findUsernameAlreadyTaken(User userSearch) {
		Optional<User> found = userRep.findByUsername(userSearch.getUsername());
		if (found.isPresent()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean findEmailAlreadyTaken(User userSearch) {
		Optional<User> found = userRep.findByEmail(userSearch.getEmail());
		if (found.isPresent()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean findPassword(User userSearch) {

		Optional<User> found = userRep.findByPassword(userSearch.getPassword());

		if (found.isPresent()) {
			return true;
		} else {
			return false;
		}

	}

}
