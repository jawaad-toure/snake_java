package Snake;

import java.util.Scanner;

public class TestSnake {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int nbOfLines = 11;
		int nbOfColumns = 17;		
		
		Snake mySnake = new Snake();
		
		SnakeField snakeField = new SnakeField(nbOfLines, nbOfColumns);
		snakeField.displayField();
		
		boolean stop = false;
		
		do {
			System.out.println("('z' haut, 'q' gauche, 's' bas, 'd' droite)");
			System.out.println("Sens de déplacement ?");
			String direction = scan.next();
			
			String snakeDirection = mySnake.move(direction);
			
//			snakeField.isSnakeMoveValid(snakeDirection);
			
			snakeField.displayField();

		} while (!stop);

		scan.close();
	}

}
