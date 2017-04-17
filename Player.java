package project;

import java.awt.Color;

import sedgewick.StdDraw;

public class Player implements Moveable {
	
	private double posX;
	private double posY; 
	private final double startPosX;
	private final double startPosY;
	private final double width = 0.1;
	private final double height = 0.1;
	private Color color;
	private int lives;
	private double speed;
	private final Color [] colors = {StdDraw.MAGENTA, StdDraw.YELLOW, StdDraw.WHITE};
	
	/**
	 * Creates a Player object to be implemented in the game
	 * @param x- x-coordinate of player (center)
	 * @param y- y-coordinate of player (center)
	 * @param speed- speed at which the player moves 
	 * @param lives- number of lives the player starts with
	 */
	public Player(double x, double y, double speed, int lives) {
		this.posX = x;
		this.posY = y;
		this.startPosX = x;
		this.startPosY = y;
		this.speed = speed;
		this.lives = lives;
		this.color = colors[(this.lives-1) % 3];
	}
	
	/**
	 * if the player touch the speedup box, the speed will increase
	 */
	public void speedup(){
		this.speed = 0.08;;
	}
	/**
	 * once the power of speedup is used up , it will return its previous 
	 * original speed
	 */
	public void resetSpeed(){
		this.speed = 0.04;
	}
	
	/**
	 * draw the player
	 */
	public void draw() {
		StdDraw.setPenColor(this.color);
		StdDraw.filledRectangle(this.posX, this.posY, this.width/2, this.height/2);
		StdDraw.filledRectangle(this.posX, (this.posY + this.height/1.5), this.width/6, this.height/2);
	}
	/**
	 * move the player
	 * it will be controlled by key A and key D
	 */
	public void move() {
		
		//If movements are possible:
		if ((ArcadeKeys.isKeyPressed(0, 1)) && (this.posX - this.speed > -1)) {
			this.posX -= this.speed;
		}
		else if ((ArcadeKeys.isKeyPressed(0, 3)) && (this.posX + this.speed < 1)) {
			this.posX += this.speed;
		}
	}
	
	/**
	 * 
	 * @return the horizontal position of the player
	 */
	
	public double getPosX() {
		return this.posX;
	}
	/**
	 * 
	 * @return the vertical position of the player
	 */
	
	public double getPosY() {
		return this.posY;
	}
	/**
	 * 
	 * @return how many lives the player still have
	 */
	
	public int getLives() {
		return this.lives;
	}
	/**
	 * the fire key is key W
	 * @return a boolean value whether the bullet is released or not
	 */
	public boolean fire() {
		return (ArcadeKeys.isKeyPressed(0, 0)); //key w pushed
	}
	
	/**
	 * Kills player by reducing life and changing colors
	 */
	public void die() {
		this.lives--;
		if (lives != 0) {
			this.color = colors[lives - 1];
		}
		this.posX = this.startPosX;
		this.posY = this.startPosY; //doesn't do anything but good form
	}
	
	/**
	 * Determine if the player and alien collide based on comparing upper left and bottom right coordinates of each
	 * @param a- alien that player potentially collided with
	 * @return true if collision occurred
	 */
	public boolean collide(Alien a) {	
		double myTopLeftX = posX - width/2;
		double myTopLeftY = posY + height/2;
		double myBottomRightX = posX + width/2;
		double myBottomRightY = posY - height/2;
		
		double otherTopLeftX = a.getPosX() - a.getWidth()/2;
		double otherTopLeftY = a.getPosY() + a.getHeight()/2;
		double otherBottomRightX = a.getPosX() + a.getWidth()/2;
		double otherBottomRightY = a.getPosY() - a.getHeight()/2;
		
		return (myTopLeftY >= otherBottomRightY && myBottomRightY <= otherTopLeftY && myBottomRightX >= otherTopLeftX && myTopLeftX <= otherBottomRightX);
	}
	/**
	 * 
	 * Determine if the player and power box collide based on comparing upper left and bottom right coordinates
	 * @param p power Box that player will collide with
	 * @return true if they collide
	 */
	public boolean collide(Power p){
		double myTopLeftX = posX - width/2;
		double myTopLeftY = posY + height/2;
		double myBottomRightX = posX + width/2;
		double myBottomRightY = posY - height/2;
		
		double otherTopLeftX = p.getPosX() - p.getWidth()/2;
		double otherTopLeftY = p.getPosY() + p.getHeight()/2;
		double otherBottomRightX = p.getPosX() + p.getWidth()/2;
		double otherBottomRightY = p.getPosY() - p.getHeight()/2;
		
		return (myTopLeftY >= otherBottomRightY && myBottomRightY <= otherTopLeftY && myBottomRightX >= otherTopLeftX && myTopLeftX <= otherBottomRightX);
		
	}

}
