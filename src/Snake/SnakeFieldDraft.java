package Snake;

import java.awt.event.KeyEvent;
import java.util.Random;

public class SnakeFieldDraft {
	private int nbOfLines;
	private int nbOfColumns;
	public Snake snake;
	public int fruitPosition;
	public int[][] cells;

	public SnakeFieldDraft(int nbOfLines, int nbOfColumns) {
		this.nbOfLines = nbOfLines;
		this.nbOfColumns = nbOfColumns;
		snake = new Snake();
		initializeField();
		initializeFruitPosition();
		initializeSnakePosition();
	}

	public int getNbOfLines() {
		return nbOfLines;
	}

	public int getNbOfColumns() {
		return nbOfColumns;
	}
	
	public Snake getSnake() {
		return snake;
	}

	private void initializeField() {
		this.cells = new int[nbOfLines][nbOfColumns];
		int nb = 0;
		for (int line = 0; line < nbOfLines; line++)
			for (int column = 0; column < nbOfColumns; column++) {
				cells[line][column] = nb;
				nb++;
			}
	}

	private void initializeSnakePosition() {
		int line = nbOfLines / 2;
		int column = nbOfColumns / 2;
		snake.add(cells[line][column]);
		snake.add(cells[line][column - 1]);
	}

	private void initializeFruitPosition() {
		Random random = new Random();
		int line= 0, column = 0; 
		boolean doesFruitInSnakeBody = true;
		while (doesFruitInSnakeBody) {
			line = random.nextInt(nbOfLines);
			column = random.nextInt(nbOfColumns);
			doesFruitInSnakeBody = snake.contains(cells[line][column]);
		}
		fruitPosition = cells[line][column];
	}

	public void displayField() {
		for (int line = 0; line < nbOfLines; line++) {
			for (int column = 0; column < nbOfColumns; column++)
				displayCell(line, column);
			System.out.println();
		}
	}

	private void displayCell(int line, int column) {
		int currentPosition = cells[line][column];
		
		if (currentPosition == fruitPosition) {
			System.out.printf("%4s", "$");
		} else if (snake.contains(currentPosition)) {			
			if (currentPosition == snake.get(0))
				System.out.printf("%4s", "x");
			else
				System.out.printf("%4s", "o") ;
				
		} else {
			System.out.printf("%4s", "-");
		}
	}
	
	
	public void isSnakeMoveValid(int keyCode) {		
		int currentDirection = snake.getCurrentDirection();
		
		if (areDirectionsOpposed(keyCode, currentDirection))
			return;
		
		updateSnakeDirection(keyCode);
						
		endGameIfWrongMove(keyCode);
	}

	public void endGameIfWrongMove(int keyCode) {
		
		try {
			switch (keyCode) {
			case KeyEvent.VK_UP:				
				up();
				break;
			case KeyEvent.VK_DOWN:
				down();
				break;
			case KeyEvent.VK_LEFT:
				left();
				break;
			case KeyEvent.VK_RIGHT:
				right();
				break;
			}
		} catch(Exception ArrayIndexOutOfBoundsExcepion) {
			System.out.println("Game over");
			System.exit(0);
		}
		
		if (isSnakeHeadDuplicated(snake.get(0))) {
			System.out.println("Game over");
			System.exit(0);
		}
	}
	
	private boolean isSnakeHeadDuplicated(int value) {
		int snakeHeadCount = 0;
		for (int bodyPart = 0; bodyPart < snake.snakeBody.size(); bodyPart++)
			snakeHeadCount += snake.get(bodyPart) == value ? 1 : 0;
		return snakeHeadCount > 1;
	}
	
	private static boolean areDirectionsOpposed(int keyCode, int currentDirection) {
		boolean condition1 = keyCode == KeyEvent.VK_UP && currentDirection == Snake.DOWN;
		boolean condition2 = keyCode == KeyEvent.VK_DOWN && currentDirection == Snake.UP;
		boolean condition3 = keyCode == KeyEvent.VK_LEFT && currentDirection == Snake.RIGHT;
		boolean condition4 = keyCode == KeyEvent.VK_RIGHT && currentDirection == Snake.LEFT;
		
		return condition1 || condition2 || condition3 || condition4;
	}
	
	private void updateSnakeDirection(int keyCode) {
		switch (keyCode) {
        case KeyEvent.VK_UP:
            snake.setCurrentDirection(Snake.UP);
            break;
        case KeyEvent.VK_DOWN:
            snake.setCurrentDirection(Snake.DOWN);
            break;
        case KeyEvent.VK_LEFT:
            snake.setCurrentDirection(Snake.LEFT);
            break;
        case KeyEvent.VK_RIGHT:
            snake.setCurrentDirection(Snake.RIGHT);
            break;
		}
	}

	private int[] getSnakeHeadIndexes(int snakePosition) {
		int[] snakePositionIndexes = new int[2];
		for (int line = 0; line < nbOfLines; line++)
			for (int column = 0; column < nbOfColumns; column++)
				if (cells[line][column] == snakePosition) {
					snakePositionIndexes[0] = line;
					snakePositionIndexes[1] = column;
					break;
				}
		return snakePositionIndexes;
	}	

	public void updateSnakePosition(int snakeHeadNewValue) {
		int tmp1 = snake.get(0);
		int tmp2 = snake.get(1);				
		snake.set(0, snakeHeadNewValue);
		
		for (int bodyPart = 1; bodyPart < snake.size(); bodyPart++) {
			snake.set(bodyPart, tmp1);
			tmp1 = tmp2;
			if (bodyPart + 1 <  snake.size())
				tmp2 = snake.get(bodyPart + 1);
		}
	}

	private void up() {
		int[] snakeHeadIndexes = getSnakeHeadIndexes(snake.get(0));
		int snakeHeadNewValue = cells[snakeHeadIndexes[0] - 1][snakeHeadIndexes[1]];
		
		if (snakeHeadNewValue == fruitPosition) {
			initializeFruitPosition();
			snake.increaseSnakeBody();
		}
		updateSnakePosition(snakeHeadNewValue);			
			
	}

	private void down() {
		int[] snakeHeadIndexes = getSnakeHeadIndexes(snake.get(0));
		int snakeHeadNewValue = cells[snakeHeadIndexes[0] + 1][snakeHeadIndexes[1]];
		
		if (snakeHeadNewValue == fruitPosition) {
			initializeFruitPosition();
			snake.increaseSnakeBody();
		}
		updateSnakePosition(snakeHeadNewValue);
	}

	private void left() {
		int[] snakeHeadIndexes = getSnakeHeadIndexes(snake.get(0));
		int snakeHeadNewValue = cells[snakeHeadIndexes[0]][snakeHeadIndexes[1] - 1];
		
		if (snakeHeadNewValue == fruitPosition) {
			initializeFruitPosition();
			snake.increaseSnakeBody();
		}
		updateSnakePosition(snakeHeadNewValue);
	}

	private void right() {
		int[] snakeHeadIndexes = getSnakeHeadIndexes(snake.get(0));
		int snakeHeadNewValue = cells[snakeHeadIndexes[0]][snakeHeadIndexes[1] + 1];
		
		if (snakeHeadNewValue == fruitPosition) {
			initializeFruitPosition();
			snake.increaseSnakeBody();
		}
		updateSnakePosition(snakeHeadNewValue);
	}

}


