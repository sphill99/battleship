import java.util.Set;
import java.util.TreeSet;

public class ShipBoard extends GameBoard {
	private Cell[][] board;
	private Set<Cell> ships;
	
	public ShipBoard() {
		super();
		board = getYourBoard();
		ships = new TreeSet<Cell>();
		make();
	}
	
	
	private void make() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				int x = 200 + 35 * i;
				int y = 387 + 35 * j;
				Cell temp = new Cell(x, y);
				board[i][j]= temp;
			}
		}
        addShips();

	}
	
	public void reset() {
		ships = new TreeSet<Cell>();
		make();
	}
	
	public void removeShip(Cell c) {
		ships.remove(c);
	}
	
	public int shipsLeft() {
		return ships.size();
	}
	
	public void addShips() {
		generateLocation(5);
		generateLocation(4);
		generateLocation(3);
		generateLocation(3);
		generateLocation(2);
	}
	
	private void generateLocation(int size) {
		boolean orientation = Math.random() < .5;
		int x = (int) (Math.random() * 10);
		int y = (int) (Math.random() * 10);
		boolean otherShips = false;
		if (orientation) {
			for (int i = x; i < x + size; i++) {
				if (i > 9 || board[i][y].isShip()) {
					otherShips = true;
					generateLocation(size);
					break;
				}
			}
			if (!otherShips) {
				for (int i = x; i < x + size; i++) {
					 board[i][y].setShip(true);
					 ships.add(board[i][y]);
				}
			}
		} else {
			for (int i = y; i < y + size; i++) {
				if (i > 9 || board[x][i].isShip()) {
					otherShips = true;
					generateLocation(size);
					break;
				}
			}
			if (!otherShips) {
				for (int i = y; i < y + size; i++) {
					 board[x][i].setShip(true);
					 ships.add(board[x][i]);
				}
			}
		}
	}

}
