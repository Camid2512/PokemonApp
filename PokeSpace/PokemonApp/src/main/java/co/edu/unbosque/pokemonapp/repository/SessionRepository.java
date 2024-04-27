package co.edu.unbosque.pokemonapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.edu.unbosque.pokemonapp.model.Session;

public interface SessionRepository extends MongoRepository<Session, String> {

}
