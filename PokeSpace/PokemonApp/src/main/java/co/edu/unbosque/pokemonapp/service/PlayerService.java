package co.edu.unbosque.pokemonapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.pokemonapp.model.Player;
import co.edu.unbosque.pokemonapp.model.Pokemon;
import co.edu.unbosque.pokemonapp.model.User;
import co.edu.unbosque.pokemonapp.repository.PlayerRepository;
import co.edu.unbosque.pokemonapp.repository.UserRepository;
import co.edu.unbosque.pokemonapp.util.AESUtil;

@Service
public class PlayerService {

	@Autowired
	PlayerRepository playerRep;

	@Autowired
	PokemonService pokeServ;

	@Autowired
	UserRepository userRep;

	public PlayerService() {
		// TODO Auto-generated constructor stub
	}

	public int save(String playerName, String userCreator) {

		Optional<User> found = userRep.findByUsername(AESUtil.encrypt(userCreator));

		if (found.isPresent()) {

			Player newPlayer = new Player();
			newPlayer.setPlayerName(playerName);
			newPlayer.setPokeTeam(pokeServ.getRandomTeam());
			newPlayer.setUserCreator(found.get());
			playerRep.save(newPlayer);
			return 0;
		} else {
			return 1;
		}

	}

	public boolean playerExist(Player found) {

		Optional<Player> find = playerRep.findById(found.getId());

		if (find.isPresent()) {
			return true;
		} else {
			return false;
		}

	}

}
