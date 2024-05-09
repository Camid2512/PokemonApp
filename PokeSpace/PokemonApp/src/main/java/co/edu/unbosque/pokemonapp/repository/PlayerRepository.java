package co.edu.unbosque.pokemonapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.edu.unbosque.pokemonapp.model.Player;

public interface PlayerRepository extends MongoRepository<Player, String> {

}
