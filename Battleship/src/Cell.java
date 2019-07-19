import java.awt.Color;
import java.awt.Graphics;

public class Cell implements Comparable<Cell> {
	private int x; 
	private int y;
	private boolean isSelected;
	private Status state;
	private final int size = 35;
	private boolean ship;
	
	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
		isSelected = false;
		setState(Status.EMPTY);
		setShip(false);
	}
	
	public void changeSelection() {
		isSelected = !isSelected;
	}
	
	public boolean alreadyAttacked() {
		if (state == Status.EMPTY) {
			return false;
		}
		return true;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
		
	
	public Status getState() {
		return state;
	}

	public void setState(Status state) {
		this.state = state;
	}
	
	public void drawCell(Graphics gc) {
		Color c = state.getColor();
		gc.setColor(c);
		gc.fillRect(x, y, size, size);
		if (isSelected) {
			Color col = new Color(255, 255, 0, 100);
			gc.setColor(col);
			gc.fillRect(x, y, size, size);
		}
		gc.setColor(Color.BLACK);
		gc.drawRect(x, y, size, size);
		if (isShip()) {
			gc.fillOval(x, y, size, size);
		}
	}

	

	public boolean isShip() {
		return ship;
	}

	public void setShip(boolean ship) {
		this.ship = ship;
	}

	@Override
	public int compareTo(Cell o) {
		if (x > o.getX()) {
			return 1;
		} else if (x < o.getX()) {
			return -1;
		} else if (y > o.getY()) {
			return 1;
		} else if (y < o.getY()) {
			return -1;
		}
		return 0;
	}
	
	public boolean equalsTo(Cell o) {
		return (x == o.getX() && y == o.getY());
	}
}
