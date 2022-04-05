
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Racquet {
	private Game game;
	private int width = 60;
	private int height = 10;
	private int xSpeed = 0;
	private int x;
	private int y;
	int left;
	int right;

	// constructor - x, y, left and right can vary with different racquets
	public Racquet(Game game, int x, int y, int left, int right){
		this.game = game;
		this.x = x;
		this.y = y;
		this.left = left;
		this.right = right;
	}
	
	public void move() {
		if (x + xSpeed > 0 && x + xSpeed < game.getWidth() - width)
			x = x + xSpeed;
	}

	public void paint(Graphics2D g) {
		g.fillRect(x, y, width, height);
	}

	public void keyReleased(KeyEvent e) {
		xSpeed = 0;
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == left)
			xSpeed = -3; // move to left
		if (e.getKeyCode() == right)
			xSpeed = 3; // move to right
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
	
	// position in y
	public int getY() {
		return y;
	}
}
