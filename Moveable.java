package project;

public interface Moveable {
	/**
	 * this abstract method means that Alien and Bullet will move
	 */
	public void move();
	
	/**
	 * this abstract method means the both alien and bullet must be drew
	 */
	public void draw();
}
