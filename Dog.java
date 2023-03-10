package thesis;

public class Dog extends Game {

	private int currentPositionX;
	private int currentPositionY;
	private int steps;
	private int hp;
	
	public Dog() {
		
	}
	
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getSteps() {
		return steps;
	}
	public void setSteps(int steps) {
		this.steps = steps;
	}
	@Override
	public String toString() {
		return "Dog [currentPositionX=" + currentPositionX + ", currentPositionY=" + currentPositionY + ", steps="
				+ steps + "]";
	}
	
	public Dog(int currentPositionX, int currentPositionY) {
		this.currentPositionX = currentPositionX;
		this.currentPositionY = currentPositionY;
	}
	public int getCurrentPositionX() {
		return currentPositionX;
	}
	public void setCurrentPositionX(int currentPositionX) {
		this.currentPositionX = currentPositionX;
	}
	public int getCurrentPositionY() {
		return currentPositionY;
	}
	public void setCurrentPositionY(int currentPositionY) {
		this.currentPositionY = currentPositionY;
	}
	
}
