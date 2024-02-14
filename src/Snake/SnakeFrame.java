package Snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

public class SnakeFrame extends JFrame {
	private static final int DELAY = 400;
	
	SnakePanel snakePanel;
	SnakeField snakeField;
	private Timer timer;
	private JDialog gameOverDialog;
	
	public SnakeFrame() {
		setTitle("Snake");
		setSize(new Dimension(800, 800));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		snakeField = new SnakeField(15, 15);
		snakePanel = new SnakePanel(snakeField);
		add(snakePanel);
		
        addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                int direction = e.getKeyCode();
                
                boolean condition1 = direction == KeyEvent.VK_UP;
                boolean condition2 = direction == KeyEvent.VK_DOWN;
                boolean condition3 = direction == KeyEvent.VK_LEFT;
                boolean condition4 = direction == KeyEvent.VK_RIGHT;
                
                if (condition1 || condition2 || condition3 || condition4) {
                	snakeField.isSnakeMoveValid(direction);
                	snakePanel.repaint();
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
        });
        
        timer = new Timer(DELAY, new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {
                snakeField.isSnakeMoveValid(snakeField.snake.getCurrentDirection());
                snakePanel.repaint();
            }
        });
        timer.start();
        
        JLabel gameOverLabel = new JLabel();
        gameOverLabel.setText("GAME OVER");
        gameOverLabel.setVerticalAlignment(JLabel.TOP);
        gameOverLabel.setForeground(Color.RED);
        
        JButton replayButton = new JButton("Rejouer");
        replayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restartGame();
            }
        });
        replayButton.setFocusable(false);
        
        JButton endButton = new JButton("Quitter");
        endButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                endGame();
            }
        });
        endButton.setFocusable(false);
        
        gameOverDialog = new JDialog();
        gameOverDialog.setSize(250, 120);
        gameOverDialog.setLayout(null);
        gameOverDialog.setLocationRelativeTo(null);
        
        gameOverDialog.addWindowListener(new WindowAdapter() {
        	@Override
        	public void windowClosing(WindowEvent e) {
        		System.exit(0);
			}
        });
        
        gameOverDialog.add(gameOverLabel);
        gameOverLabel.setBounds(80, 10, 100, 30);
        
        gameOverDialog.add(replayButton);
        replayButton.setBounds(15, 40, 90, 30);
        
        gameOverDialog.add(endButton);
        endButton.setBounds(130, 40, 90, 30);
                
        
        snakeField.setGameOverListener(new GameOverListener() {
            @Override
            public void onGameOver() {
                timer.stop();
                gameOverDialog.setVisible(true);
            }
        });
	}
	
	private void restartGame() {
		gameOverDialog.dispose();
		snakeField.snake.updateSnakeBody();
		snakeField.snake.setCurrentDirection(0);
		snakeField.initializeField();
		snakeField.initializeFruitPosition();
		snakeField.initializeSnakePosition();
		timer.start();
    }
	
	private void endGame() {
		System.exit(0);
    }

}
