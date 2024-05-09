package co.edu.unbosque.pokemonapp.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.pokemonapp.model.Move;
import co.edu.unbosque.pokemonapp.model.Player;
import co.edu.unbosque.pokemonapp.model.Pokemon;

@Service
public class BattleService {

	@Autowired
	PokemonService pokeServ;
	@Autowired
	PlayerService playerServ;

	public BattleService() {
		// TODO Auto-generated constructor stub
	}

	public int startBattle(Player player1) {

		if (playerServ.playerExist(player1)) {
			return 0;
		} else {
			return 1;
		}

	}

	public boolean allFainted(Pokemon[] team) {
		for (Pokemon pokemon : team) {
			if (pokemon.getHp() > 0) {
				return false;
			}
		}
		return true;
	}

	public String calculateAttackDmg(Pokemon attackerPokemon, Move attackerMove, Pokemon defenderPokemon) {

		Random randomDmg = new Random();
		int specialOrNormal = randomDmg.nextInt(2);

		double dmgDeal = 0;

		if (specialOrNormal == 1) {
			dmgDeal = attackerMove.getAttckDmg() * attackerPokemon.getSpecialAttack() / 100;
			dmgDeal = Math.abs(dmgDeal - defenderPokemon.getSpecialDefense());
		} else if (specialOrNormal == 2) {
			dmgDeal = attackerMove.getAttckDmg() * attackerPokemon.getAttack() / 100;
			dmgDeal = Math.abs(dmgDeal - defenderPokemon.getDefense());
		}
		double hpCalculated = 0;
		if (dmgDeal <= 0) {

			hpCalculated = defenderPokemon.getHp() - 1;
		} else {

			hpCalculated = defenderPokemon.getHp() - dmgDeal;
		}

		return "The pokemon: " + attackerPokemon.getName() + " did: " + dmgDeal + " to the pokemon: "
				+ defenderPokemon.getName() + " leaving it with: " + Math.round(hpCalculated) + " hp";

	}

	public Pokemon firstAttacker(Pokemon pokemonA, Pokemon pokemonB) {
		if (pokemonA.getSpeed() < pokemonB.getSpeed()) {
			return pokemonB;
		} else {
			return pokemonA;
		}
	}

	public Pokemon changePokemon(Pokemon pokemonRetired, Pokemon pokemonReplace) {

		if (pokemonReplace.getHp() != 0) {
			return pokemonReplace;
		} else {
			if (pokemonRetired.getHp() != 0) {
				return pokemonRetired;
			} else {
				return null;
			}

		}

	}

}
