package Snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class SnakePanel extends JPanel {
	SnakeField snakeField;
	SnakeImages snakeImages;
	
	public SnakePanel(SnakeField snakeField) {
		this.snakeField = snakeField;
		this.snakeImages = new SnakeImages();
	}
	
	@Override
	public void paint(Graphics g) {
		paintSnakeField(g);
	}
	
	private void paintSnakeField(Graphics g) {
		for (int line = 0; line < snakeField.getNbOfLines(); line++)
			for (int column = 0; column < snakeField.getNbOfColumns(); column++)
				paintCell(g, line, column);
	}
	
	public void paintCell(Graphics g, int line, int column) {
		int cellHeight = getHeight() / snakeField.getNbOfLines();
		int cellWidth = getWidth() / snakeField.getNbOfColumns();
		
		int x = column * cellWidth;
		int y = line * cellHeight;
		
		int cellValue = snakeField.cells[line][column];

	    if ((line + column) % 2 == 0)
	        g.setColor(Color.LIGHT_GRAY);
	    else
	        g.setColor(Color.WHITE);
	    
	    g.fillRect(x, y, cellWidth, cellHeight);

	    if (cellValue == snakeField.fruitPosition)
	        g.drawImage(snakeImages.appleImage, x, y, cellWidth, cellHeight, null);
	    else if (snakeField.snake.contains(cellValue)) {
	        if (cellValue == snakeField.snake.get(0))
	            g.drawImage(snakeImages.headImage, x, y, cellWidth, cellHeight, null);
	        else
	            g.drawImage(snakeImages.bodyImage, x, y, cellWidth, cellHeight, null);
	        
	    }
	}
	
}
