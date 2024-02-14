package Snake;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Snake {
	public static final int UP = KeyEvent.VK_UP;
	public static final int DOWN = KeyEvent.VK_DOWN;
	public static final int LEFT = KeyEvent.VK_LEFT;
	public static final int RIGHT = KeyEvent.VK_RIGHT;
	
	public List<Integer> snakeBody;
	private int snakeInitialLenght;
	private int currentDirection;
	
	public Snake() {
		snakeInitialLenght = 2;
		snakeBody = new ArrayList<>(snakeInitialLenght);
		currentDirection = 0;
	}
	
	public int getCurrentDirection() {
		return currentDirection;
	}
	
	public void setCurrentDirection(int currentDirection) {
		this.currentDirection = currentDirection;
	}
	
	public void increaseSnakeBody() {
		snakeBody.add(0);
	}
	
	public void updateSnakeBody() {
		snakeBody = new ArrayList<>(snakeInitialLenght);
	}
	
	public int size() {
		return snakeBody.size();
	}
	
	public int get(int value) {
		return snakeBody.get(value);
	}
	
	public void add(int value) {
		snakeBody.add(value);
	}
	
	public boolean contains(int value) {
		return snakeBody.contains(value);
	}
	
	public void set(int index, int value) {
		snakeBody.set(index, value);
	}
	
	public String move(String direction) {
		return direction;
	}
}

