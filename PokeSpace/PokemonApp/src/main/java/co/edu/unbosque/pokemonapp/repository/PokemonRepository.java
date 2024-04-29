package co.edu.unbosque.pokemonapp.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.edu.unbosque.pokemonapp.model.Pokemon;

public interface PokemonRepository extends MongoRepository<Pokemon, String> {

	Optional<Pokemon> findByPokeId(int id);

	Optional<Pokemon> findByName(String name);

}
