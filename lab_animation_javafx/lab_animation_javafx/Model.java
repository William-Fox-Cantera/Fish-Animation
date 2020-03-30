// Name: William Cantera
// I worked alone

import java.util.Random;

public class Model {
    private double xloc = 0;
    private double yloc = 0;
    private double xIncr = 8;
    private double yIncr = 2;
    private int canvasWidth;
    private int canvasHeight;
    private int imgWidth;
    private int imgHeight;
    private Direction direction;
    // My attributes
	Random rand = new Random(); // Picks a random direction for the orc
	boolean hasChanged = false; // Indicator for if the orc has hit a boundary
	boolean isJumping = false; // Indicator for if the orc should start jumping
	
	public Model(int width, int height, int imageWidth, int imageHeight) {
		this.canvasWidth = width;
		this.canvasHeight = height;
		this.imgWidth = imageWidth;
		this.imgHeight = imageHeight;
		this.direction = Direction.SOUTHEAST;
	}
	
	public double getX() {
		return this.xloc;
	}
	
	public double getY() {
		return this.yloc;
	}
	
	public Direction getDirection() {
		return this.direction;
	}
	public double getXincr() {
		return this.xIncr;
	}
	public double getYincr() {
		return this.yIncr;
	}
	public void setXincr(double xIncr) {
		this.xIncr = xIncr;
	}
	public void setYincr(double yIncr) {
		this.yIncr = yIncr;
	}
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	public void updateLocationandDirection() {
		if (xloc < 0) {
        	direction = rand.nextBoolean() ? Direction.NORTHEAST : Direction.SOUTHEAST; // Choose between one of two directions
        	hasChanged = true;
        } else if (xloc > canvasWidth-140) {
        	direction = rand.nextBoolean() ? Direction.NORTHWEST : Direction.SOUTHWEST;
        	hasChanged = true;
        } else if (yloc < 0) {
        	direction = rand.nextBoolean() ? Direction.SOUTHEAST : Direction.SOUTHWEST;
        	hasChanged = true;
        } else if (yloc > canvasHeight-140) {
        	direction = rand.nextBoolean() ? Direction.NORTHEAST : Direction.NORTHWEST;
        	hasChanged = true;
        }
       
                             
        if (direction == Direction.NORTHEAST) { // North East
        	xloc += xIncr;
        	yloc -= yIncr;
        } else if (direction == Direction.NORTHWEST) { // North West
        	xloc -= xIncr;
        	yloc -= yIncr;
        } else if (direction == Direction.SOUTHEAST) { // South East
        	xloc += xIncr;
        	yloc += yIncr;
        } else if (direction == Direction.SOUTHWEST) { // South West
        	xloc -= xIncr;
        	yloc += yIncr;
        } else if (direction == Direction.WEST) {
        	xloc -= xIncr;
        } else if (direction == Direction.EAST) {
        	xloc += xIncr;
        } else if (direction == Direction.NORTH) {
        	yloc -= xIncr;
        } else if (direction == Direction.SOUTH) {
        	yloc += xIncr;
        }
	}
	public static void main(String args[]) { // For printing out locations/direction terminal style
		Model mod = new Model(500, 300, 165, 165);
		while (true) {
			mod.updateLocationandDirection();
			System.out.println("X location: " + mod.xloc +
					"   Y location: " + mod.yloc + "   Direction: " + mod.direction);
			try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }		}
	}
}
