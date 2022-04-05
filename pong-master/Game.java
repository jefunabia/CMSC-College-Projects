import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Game extends JPanel {
	
	/* NOTES:
	 * player 1 = down
	 * player 2 = up
	 */
	
	// constructor - x, y, left and right can vary with different racquets
	Ball ball = new Ball(this);
	Racquet racquet = new Racquet(this, 3, 400, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);
	Racquet racquet2 = new Racquet(this, 0, 3, KeyEvent.VK_A, KeyEvent.VK_D);
	int score1 = 0;
	int score2 = 0;

	// score getters
	private int getScore1() {
		return score1;
	}
	private int getScore2(){
		return score2;
	}
	
	public Game() {
		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				racquet.keyReleased(e);
				racquet2.keyReleased(e);
			}

			@Override
			public void keyPressed(KeyEvent e) {
				racquet.keyPressed(e);
				racquet2.keyPressed(e);
			}
		});
		setFocusable(true);
		Sound.BACK.loop();
	}
	
	private void move() {
		ball.move();
		racquet.move();
		racquet2.move();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		// paint ball
		Graphics2D ballPaint = (Graphics2D) g;
		ballPaint.setColor(Color.BLACK);
		ball.paint(ballPaint);
		
		// paint racquet 1
		Graphics2D racquetPaint = (Graphics2D) g;
		racquetPaint.setColor(Color.BLUE);
		racquet.paint(ballPaint);
		
		// paint racquet 2
		Graphics2D racquet2Paint = (Graphics2D) g;
		racquet2Paint.setColor(Color.RED);
		racquet2.paint(ballPaint);
			
		// print player 1 score
		Graphics2D player1 = (Graphics2D) g;
		player1.setColor(Color.BLUE);
		player1.setFont(new Font("Verdana", Font.BOLD, 18));
		player1.drawString("Player 1 Score:", 0, 430);
		player1.drawString(String.valueOf(getScore1()), 180, 430);
		
		// print player 2 score
		Graphics2D player2 = (Graphics2D) g;
		player2.setColor(Color.RED);
		player2.setFont(new Font("Verdana", Font.BOLD, 18));
		player2.drawString("Player 2 Score:", 0, 450);
		player2.drawString(String.valueOf(getScore2()), 180, 450);
	}
	
	// game over if score == 3, have an option to restart or exit
	public void gameOver() {
	    Sound.BACK.stop();
	    Sound.GAMEOVER.play();
		//it won't print the winner score which is 3. only up to 2 then game over.
		JOptionPane.showMessageDialog(this, "WINNER IS: " + ball.getStr(), "Game Over", JOptionPane.YES_NO_OPTION);
		
		int reply = JOptionPane.showConfirmDialog(this, "Restart? " , "Game Over", JOptionPane.YES_NO_OPTION);
		if(reply == JOptionPane.YES_OPTION){
			restart();
		}
		else{
			JOptionPane.showMessageDialog(null, "Goodbye!");
			System.exit(ABORT);
		}
	}
	
	// resets the game
	public void restart(){
		Sound.BACK.loop();
		score1 = 0;
		score2 = 0;
		ball = new Ball(this);
		ball.setSpeed(1);
	}
	
	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame("Pong");
		Game game = new Game();
		frame.add(game);
		frame.setSize(350, 500);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		while (true) {
			game.move();
			game.repaint();
			Thread.sleep(10);
		}
	}
}
