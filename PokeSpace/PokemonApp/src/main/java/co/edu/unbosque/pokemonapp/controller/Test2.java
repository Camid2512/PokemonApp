package co.edu.unbosque.pokemonapp.controller;

import java.util.Random;
import java.util.Scanner;

import co.edu.unbosque.pokemonapp.model.Move;
import co.edu.unbosque.pokemonapp.model.Pokemon;
import co.edu.unbosque.pokemonapp.service.BattleService;
import co.edu.unbosque.pokemonapp.service.PokemonService;

// Define a class for a Pokemon move
//class Move {
//	String name;
//	int power;
//
//	// Constructor
//	public Move(String name, int power) {
//		this.name = name;
//		this.power = power;
//	}
//
//}
//
//// Define a class for a Pokemon
////class Pokemon {
////	String name;
////	int hp;
////	int attack;
////	int defense;
////	Move[] moves;
////
////	// Constructor
////	public Pokemon(String name, int hp, int attack, int defense, Move[] moves) {
////		this.name = name;
////		this.hp = hp;
////		this.attack = attack;
////		this.defense = defense;
////		this.moves = moves;
////	}
////
////	// Method to attack another Pokemon
////	public void attack(Pokemon opponent, int moveIndex) {
////		Move move = moves[moveIndex];
////		int damage = move.power - opponent.defense;
////		if (damage < 0) {
////			damage = 0;
////		}
////		opponent.hp -= damage;
////		System.out.println(
////				this.name + " used " + move.name + " and attacked " + opponent.name + " for " + damage + " damage.");
////	}
//
//	// Method to select a move
//	public int selectMove() {
//		System.out.println("Select a move for " + name + ":");
//		for (int i = 0; i < moves.length; i++) {
//			System.out.println((i + 1) + ". " + moves[i].name);
//		}
//		Scanner scanner = new Scanner(System.in);
//		int moveIndex = scanner.nextInt();
//		return moveIndex - 1; // Convert to 0-based index
//	}
//}
//
//// Define a class for a battle
//class Battle {
//	Random rand = new Random();
//
//	// Method to simulate a battle between two teams of Pokemon
//	public void simulateBattle(Pokemon[] team1, Pokemon[] team2) {
//		while (!allFainted(team1) && !allFainted(team2)) {
//			// Select random Pokemon from each team to attack
//			Pokemon attacker1 = team1[rand.nextInt(team1.length)];
//			Pokemon attacker2 = team2[rand.nextInt(team2.length)];
//
//			// Simulate an attack from each Pokemon
//			int moveIndex1 = attacker1.selectMove();
//			int moveIndex2 = attacker2.selectMove();
//			attacker1.attack(team2[rand.nextInt(team2.length)], moveIndex1);
//			attacker2.attack(team1[rand.nextInt(team1.length)], moveIndex2);
//		}
//
//		// Determine the winner
//		if (allFainted(team1)) {
//			System.out.println("Team 2 wins!");
//		} else {
//			System.out.println("Team 1 wins!");
//		}
//	}
//
//	// Method to check if all Pokemon in a team have fainted
//	private boolean allFainted(Pokemon[] team) {
//		for (Pokemon pokemon : team) {
//			if (pokemon.hp > 0) {
//				return false;
//			}
//		}
//		return true;
//	}
//}

public class Test2 {
	public static void main(String[] args) {
//        // Create moves for Pokemon
//        Move[] pikachuMoves = {
//            new Move("Thunderbolt", 25),
//            new Move("Quick Attack", 20),
//            // Add more moves as needed
//        };
//
//        Move[] charmanderMoves = {
//            new Move("Flamethrower", 30),
//            new Move("Ember", 20),
//            // Add more moves as needed
//        };
//
//        // Create Pokemon instances for both teams
//        Pokemon pikachu = new Pokemon("Pikachu", 100, 20, 10, pikachuMoves);
//        Pokemon charmander = new Pokemon("Charmander", 120, 18, 12, charmanderMoves);
//        // Add more Pokemon as needed
//
//        // Create a Battle instance and simulate the battle
//        Battle battle = new Battle();
//        battle.simulateBattle(new Pokemon[] { pikachu }, new Pokemon[] { charmander });

		BattleService batServ = new BattleService();

		Move move1 = new Move("role-play", 0, "physyci", 10);
		Move move2 = new Move("lash-out", 75, "physyci", 5);
		Move move3 = new Move("role-play", 0, "physyci", 10);
		Move move4 = new Move("role-play", 0, "physyci", 10);

		Move[] attacks = new Move[4];

		attacks[0] = move1;
		attacks[1] = move2;
		attacks[2] = move3;
		attacks[3] = move4;

		String typeplant = "plant";
		String typevenom = "venom";
		String typefire = "fire";

		String[] types = new String[2];
		types[0] = typeplant;
		types[1] = typevenom;
		String[] types2 = new String[1];
		types2[0] = typefire;
		Pokemon attacker = new Pokemon(69, "bulbasour", 150, 150, attacks, null, 45, 49, 49, 65, 65, 45, types);
		Pokemon defender = new Pokemon(70, "charmander", 150, 150, attacks, null, 80, 82, 83, 100, 100, 80, types2);

		System.out.println(attacks[1].toString());
		System.out.println();

		System.out.println(batServ.calculateAttackDmg(attacker, attacks[1], defender));

	}
}
