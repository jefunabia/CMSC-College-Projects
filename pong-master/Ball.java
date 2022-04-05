import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Ball {
	private static final int DIAMETER = 30;
	private Game game;
	private int x = 50;
	private int y = 50;
	private int xSpeed = 1;
	private int ySpeed = 1;
	private int speed = 1;
	private String str;
	
	public Ball(Game game) {
		this.game = game;
	}
	
	void move() {
		boolean changeDirection = true;
		if (x + xSpeed < 0) { // ball at left
			xSpeed = getSpeed();
		}
		else if (x + xSpeed > game.getWidth() - DIAMETER){ // ball at right
			xSpeed = -getSpeed();
		} 
		else if (y + ySpeed < 0){ // ball at up
			Sound.SCORE.play();
			game.score1++;
			ySpeed = getSpeed();
		} 
		else if (y + ySpeed > game.getHeight() - DIAMETER){ // ball at down
			Sound.SCORE.play();
			game.score2++;
			ySpeed = -getSpeed();
		} 
		
		// ball collide racquet
		if (collision()){
			Sound.HIT.play();
			ySpeed = -getSpeed();
			y = game.racquet.getY() - DIAMETER;
			setSpeed(getSpeed() + 1);
		} 
		else if (collision2()){
			Sound.HIT.play();
			ySpeed = getSpeed();
			y = game.racquet2.getY() + DIAMETER;
			setSpeed(getSpeed() + 1);
		} 
		
	    else
	        changeDirection = false;
	      if (changeDirection){
	    	  Sound.HIT.play();
	      }
	  
		x = x + xSpeed;
		y = y + ySpeed;
		
		// game over if one player scores 3 points
		if(game.score1 == 3){
			setStr(new String("PLAYER 1"));
			game.score1++;
			game.gameOver();
		}
		else if(game.score2 == 3){
			setStr(new String("PLAYER 2"));
			game.score2++;
			game.gameOver();
		}
	}

	// if ball collides with racquet
	private boolean collision() {
		return game.racquet.getBounds().intersects(getBounds());
	}
	
	private boolean collision2() {
		return game.racquet2.getBounds().intersects(getBounds());
	}

	public void paint(Graphics2D g) {
		g.fillOval(x, y, DIAMETER, DIAMETER);
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, DIAMETER, DIAMETER);
	}
	
	
	// getters and setters for string and speed -> to be used in game class
	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
}
