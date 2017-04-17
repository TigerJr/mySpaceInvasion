package project;

import java.awt.Color;

import sedgewick.StdDraw;

public class Alien implements Moveable {
	
	private double posX;
	private double posY;
	private final double startX;
	private final double startY;
	private double width = 0.15;
	private final double height = 0.15;
	private double speed;	
	private boolean upDown;
	private boolean isAlive = true;
	private final Color [] colors = {StdDraw.RED, StdDraw.YELLOW, StdDraw.WHITE,StdDraw.BLUE,StdDraw.DARK_GRAY};
	
	/**
	 * Creates an Alien object to be implemented in the game
	 * @param x- x-coordinate of alien (center)
	 * @param y- y-coordinate of alien (center)
	 * @param speed- speed at which the alien moves 
	 * @param upDown- true if alien moves up/down pattern; false if alien moves side-to-side pattern
	 */
	/*
	 * this method is the constructor
	 */
	public Alien(double x, double y, double speed, boolean upDown,double width) {
		this.posX = x;
		this.posY = y;
		this.startX = x;
		this.startY = y;
		this.speed = -speed;
		this.upDown = upDown;
		this.width = width;
	}
	/**
	 * 
	 * @return the horizontal position of the Alien
	 */
	public double getPosX() {
		return this.posX;
	}
	
	/**
	 * 
	 * @return the vertical position of the Alien
	 */
	public double getPosY() {
		return this.posY;
	}
	
	/**
	 * 
	 * @param width; reset the value of width of the alien;input the width which 
	 * you want
	 * 
	 */
	
	public void setWidth(double width){
		this.width = width;
	}
	
	/**
	 * 
	 * @return the width of the alien
	 */
	public double getWidth() {
		return this.width;
	}
	
	/**
	 * 
	 * @return the height of the alien
	 */
	
	public double getHeight() {
		return this.height;
	}
	
	/**
	 * draw the alien
	 */
	
	public void draw() {
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.filledRectangle(this.posX, this.posY, this.width/2, this.height/2);
	}
	
	
	/**
	 * make the alien move in pattern
	 */
	public void move() {
		if (isOffScreen()) {
			speed *= -1;
		}
		if (upDown) {
			this.posY += speed;
		} else {
			this.posX += speed;
		}

	}
	
	
	/**
	 * 
	 * @return the boolean value whether the alien is not on the screen or not
	 */
	public boolean isOffScreen() {
		return (this.posX > 1 || this.posX < -1 || this.posY > 1 || this.posY < -1);
	}
	
	/**
	 *  reset the position (both horizontal and vertical) of the alien
	 */
		
	public void moveToStart() {
		this.posX = startX;
		this.posY = startY;
	}
	
	/**
	 * 
	 * @return the boolean value whether the alien is dead or not
	 */
	
	public boolean isAlive() {
		return this.isAlive;
	}
	
	/**
	 * this method will let the boolean data type, isAlive be false
	 */
	
	public void die() {
		this.isAlive = false;
	}
	

}
