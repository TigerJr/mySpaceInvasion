package project;

import java.awt.Color;
import java.util.Random;

import sedgewick.StdDraw;

public class Power implements Moveable {
	
	private double posX;
	private double posY = -0.9; 
	private final double width = 0.1;
	private final double height = 0.1;
	private Color color;
	private boolean moreBullets = false;
	private boolean bulletSizePowered = false;
	private boolean speedPowered = false;
	
	/**
	 * Creates a power object to be implemented in the game
	 * initially the power box is hidden 
	 * 
	 */
	public Power(){
		double r = new Random().nextDouble();
		double randomx = -1+(r * 2); 
		this.posX = randomx;
		this.posY = 0;
		this.color = StdDraw.RED;
	}
	/**
	 * if the player touches the moreBullets power box,
	 * the boolean value, MoreBullets, will turn to true
	 */
	public void setMoreBullets(){
		this.moreBullets = true;
	}
	
	/**
	 * 
	 * @return the boolean value whether the player has more bullets or not
	 */
	public boolean getMoreBullets(){
		return this.moreBullets;
	}
	/**
	 * if the player touches the LongerBullet power box,
	 * the boolean value, bulletSizePowered, will turn to true
	 */
	public void setBulletSizePower(){
		this.bulletSizePowered = true;
	}
	
	/**
	 * 
	 * @return the boolean value whether the player has longer bullets or not
	 */
	public boolean getBulletSizePower(){
		return this.bulletSizePowered;
	}
	
	/**
	 * if the player touches the speed power box,
	 * the boolean value,speedPowered, will turn to true
	 */
	
	public void setSpeedPowered(){
		this.speedPowered = true;
	}
	
	/**
	 * 
	 * @return the boolean value whether the player is speedy or not
	 */
	public boolean getSpeedPowered(){
		return this.speedPowered;
	}
	
	
	
	/**
	 * draw the power box
	 */
	public void draw() {
		StdDraw.setPenColor(this.color);
		StdDraw.filledRectangle(this.posX, this.posY, this.width/2, this.height/2);
		
	}
	
	/**
	 * 
	 * @return the horizontal position of the power box
	 */
	public double getPosX() {
		return this.posX;
	}
	
	/**
	 * 
	 * @return the vertical position of the power box
	 */
	
	public double getPosY() {
		return this.posY;
	}
	
	/**
	 * 
	 * @return the width of the powerbox
	 */
	public double getWidth() {
		return this.width;
	}
	
	/**
	 * 
	 * @return the height of the powerBox
	 */
	public double getHeight() {
		return this.height;
	}
	
	/**
	 * different Colors mean different powers
	 * @return the color of the current box
	 */
	
	public Color getColor(){
		return this.color;
	}

	@Override
	
	/**
	 * move the power box once the player touch it
	 */
	public void move() {
		// TODO Auto-generated method stub
		
	}

	
	/**
	 * hide the power box once the player touch it
	 * @param BgColor is the same color as the background
	 * 
	 */
	public void die( Color BgColor) {
		this.posY= 0;
		this.color = BgColor;
	}
	/**
	 * the power box will be randomly selected
	 * it has random color and random horizontal position
	 */
	public void reset(){
		this.posY = -0.9;
		double r = new Random().nextDouble();
		double randomc = r * 3;
		double randomx = -1 +(r * 2); 
		this.posX = randomx;
		if(randomc >0&&randomc<1){
			this.color = StdDraw.GREEN;
		}
		if(randomc >1&&randomc<2){
			this.color = StdDraw.CYAN;
		}
		if(randomc >2){
			this.color = StdDraw.GRAY;
		}
		this.moreBullets = false;
		this.bulletSizePowered = false;
		this.speedPowered = false;
		
	}
	

	
}
