package co.edu.unbosque.pokemonapp.configuration;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import co.edu.unbosque.pokemonapp.model.User;
import co.edu.unbosque.pokemonapp.repository.UserRepository;
import co.edu.unbosque.pokemonapp.util.AESUtil;

@Configuration
public class LoadDataBase {
	private static final Logger log = LoggerFactory.getLogger(LoadDataBase.class);

	@Bean
	CommandLineRunner initDatabase(UserRepository userRepo) {

		return args -> {
			Optional<User> found = userRepo.findByUsername(AESUtil.encrypt("admin"));
			if (found.isPresent()) {
				log.info("Admin already exists,  skipping admin creating  ...");
			} else {
				userRepo.save(new User(AESUtil.encrypt("admin"), AESUtil.encrypt("programacion2"),
						AESUtil.encrypt("ccdiazr@unbosque.edu.co")));
				log.info("Preloading admin user");
			}
		};
	}

}
