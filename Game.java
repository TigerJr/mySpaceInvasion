package project;

import java.awt.Color;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import sedgewick.StdDraw;

/**
 * Plays game by using all created objects and Movable interface
 * @author Zachary Polsky
 *
 */

public class Game {
	
	private LinkedList<Alien> aliens;
	private LinkedList<Moveable> move;
	private LinkedList<Bullet> bullets;
	private Power power;
	private Player player;
	private double alienSpeed;
	private int score;
	private int level;
	private final Color [] colors = {StdDraw.RED, StdDraw.PINK, StdDraw.ORANGE,StdDraw.BOOK_RED,StdDraw.BOOK_LIGHT_BLUE};
	private Color bgColor;
	/**
	 * constructor of game; once this method is excuted, a new game is started
	 */
	public Game() {
		aliens = new LinkedList<Alien>();
		move = new LinkedList<Moveable>();
		bullets = new LinkedList<Bullet>();
		StdDraw.setScale(-1, 1);
		player = new Player(0, -.9, .04, 3);
		power = new Power();
		move.add(player);
		move.add(power);
		alienSpeed = 0.04;
		addAliens();
		score = 0;
		level = 0;
		bgColor = StdDraw.RED;
	}

	/**
	 * this method draw the Text background which 
	 * shows us what score and level are
	 * 
	 * @param score- the score the player get in the game
	 * @param level- the level the player is now
	 */
	public void drawBoard(int score, int level) {
		StdDraw.clear();
		this.bgColor = colors[(this.level) % 5];
		StdDraw.setPenColor(this.bgColor);
		StdDraw.filledRectangle(0, 0, 1, 1);
		StdDraw.setPenColor(Color.WHITE);
		StdDraw.text(0.75, 0.9, "Score: " + score);
		StdDraw.setPenColor(Color.WHITE);
		StdDraw.text(-0.75, 0.9, "Level: " + level);
	}
	
	

	/**
	 * 
	 * @return false if the player does not have any life remaininig
	 */
	public boolean isOver() {
		return (!(player.getLives() > 0));
	}
	
	/**
	 * add three new aliens
	 */

	public void addAliens(){
		addAlien(0.5, 0.5, alienSpeed, true);
		addAlien(-0.5, 0.5, alienSpeed, true);
		addAlien(-0.9, 0.5, alienSpeed, false);
	}
	

	/**
	 * add one alien to the list alien and the list move;
	 * 
	 * @param x the x position 
	 * @param y the y position
	 * @param speed the initial speed of the alien
	 * @param upDown the boolean value that whether the alien is up or down
	 */
	private void addAlien(double x, double y, double speed, boolean upDown)
	{	
		double w = 0.15;
		for(int i=0;i<=this.level;i++){
			w = w*0.9;
		}
		Alien a = new Alien(x, y, speed, upDown,w);
		aliens.add(a);
		move.add(a);
	}
	
	/**
	 * The method , play, is used to control the logic of the game
	 * It will draw the score and level board on the top of our screen
	 * and make bullets aliens and player move.
	 * if the player touches the cyan power box, it will have unlimited bullets to shot
	 * if the player touches the green power box, it will move faster
	 * if the player touches the gray power box, it will have longer bullets,which means
	 * the length of the bullets will increase.
	 * Once the player collides with the aliens, the number of its lives will decrease
	 * by one and score will be decreased by 50. If the player shots the alien , then the scores will
	 * increase.
	 * Play() method will take use of drawBoard(),addAliens(), levelUp(),drawGameEnd() methods
	 * 
	 */
	
	public void play(){
		// draw the background board which is consisted of 
		// the current level and score the player get
		// in the game
		drawBoard(score,level);
		
		// let the bullets and aliens be moved and drew
		for (Moveable m : move) {
			m.move();
			m.draw();
		}
		
		// regular fire
		if (player.fire() && bullets.size() < 3) {
			Bullet b = new Bullet(player.getPosX(), player.getPosY() + .15, .05);
			move.add(b);
			bullets.add(b);
		}
		
		if (player.collide(power)&&power.getColor()==StdDraw.CYAN&&level!=0){
			power.die(this.bgColor);
			power.setMoreBullets();
		}
		// fire with unlimited bullets
		if (player.fire() && bullets.size() < 300&&power.getMoreBullets()) {
			Bullet b = new Bullet(player.getPosX(), player.getPosY() + .15, .05);
			move.add(b);
			bullets.add(b);
		}
		
		if (player.collide(power)&&power.getColor()==StdDraw.GRAY&&level!=0){
			power.die(this.bgColor);
			power.setBulletSizePower();
		}
		// fire with large size bullet
		if (player.fire() &&power.getBulletSizePower()) {
			Bullet b = new Bullet(player.getPosX(), player.getPosY() + .15, .05);
			b.setSize();
			move.add(b);
			bullets.add(b);
		}
		if (player.collide(power)&&power.getColor()==StdDraw.GREEN&&level!=0){
			power.die(this.bgColor);
			power.setSpeedPowered();
		}
		// move with 2 times speed
		if (power.getSpeedPowered()) {	
			player.speedup();
		}
		//
		/*CODE A*/ // start
	
		
		
		// if player collides with an alien, he will die and score minus 10
		// if he shots it, score plus 50.
		for (Alien a : aliens) {
			if (player.collide(a)) {
				player.die();
				score -= 10;
			}
	
			for (Bullet b : bullets) {
				if (b.collide(a)) {
					a.die();
					b.setOffScreen();
					score += 50;
				}
				else if (b.getPosY() >= 1){
					b.setOffScreen();
				}
			}
		}
		/*CODE A*/ //end
		
		// Used to prevent concurrent modification errors
		Iterator<Alien> alienIter = aliens.iterator();
		while (alienIter.hasNext()) {
		    Alien a = alienIter.next();
		    if (!a.isAlive()) {
		    	alienIter.remove();
		    	move.remove(a);
		    }
		}
		
		Iterator<Bullet> bulletIter = bullets.iterator();
		while (bulletIter.hasNext()) {
		    Bullet b = bulletIter.next();
		    if (b.getIsOffScreen()) {
		    	bulletIter.remove();
		    	move.remove(b);
		    }
		}
		
		// 
		levelUp();
		StdDraw.show(20);
		
		if (isOver()){
			drawGameEnd();
		}
	}
	
	
	/**
	 * if the player enter a new level, the background color will change 
	 * and a new power box will be random selected
	 */
	public void levelUp() {
		if (aliens.isEmpty()) {
			alienSpeed *= 1.05;
			addAliens();
			power.reset();
			level++;
			
			StdDraw.setPenColor(Color.PINK);
			StdDraw.filledRectangle(0, 0, 1, 1);
			StdDraw.setPenColor(Color.WHITE);
			StdDraw.text(0, 0, "LEVEL UP");
			
		}
	}
	/**
	 * when the game is over, it will have a new black 
	 * background and text "Game over"
	 */
	public void drawGameEnd()
	{
		StdDraw.clear();
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.filledRectangle(0, 0, 1, 1);
		StdDraw.setPenColor(Color.WHITE);
		StdDraw.text(0, 0, "GAME OVER");
		StdDraw.show(100);
	}
	/**
	 * this method is the main method of the game.
	 * that will take use of play() method and isOver() method.
	 * @param args
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Game game = new Game();
		while (!game.isOver()){
			game.play();
		}
	}

}
