package com.game.factories;

import com.animals.core.Cat;
import com.animals.core.Dog;
import com.animals.core.Elephant;
import com.animals.core.Panthere;
import com.animals.core.Lion;
import com.animals.core.Mouse;
import com.animals.core.Tiger;
import com.animals.core.Wolf;
import com.animals.interfaces.Animal;
import com.game.Board;
import com.game.Player;
import com.game.Point;

public class AnimalFactory {

	public Animal getAnimal(String animalType, Board board, Point position, Player player) {
		if (animalType == null) {
			return null;
		}
		if (animalType.equalsIgnoreCase("Cat")) {
			return new Cat(board, position, player);

		} else if (animalType.equalsIgnoreCase("Dog")) {
			return new Dog(board, position, player);

		} else if (animalType.equalsIgnoreCase("Elephant")) {
			return new Elephant(board, position, player);
		}

		else if (animalType.equalsIgnoreCase("Leopard")) {
			return new Panthere(board, position, player);
		}
		
		else if (animalType.equalsIgnoreCase("Lion")) {
			return new Lion(board, position, player);
		}
		
		else if (animalType.equalsIgnoreCase("Mouse")) {
			return new Mouse(board, position, player);
		}
		
		else if (animalType.equalsIgnoreCase("Tiger")) {
			return new Tiger(board, position, player);
		}
		
		else if (animalType.equalsIgnoreCase("Wolf")) {
			return new Wolf(board, position, player);
		}

		return null;
	}

}

