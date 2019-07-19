import java.util.*;

public class PegBoard extends GameBoard {
	private Cell[][] board;
	private int selectedX;
	private int selectedY;
	private Direction lastD;
	private LinkedList<Position> lastMoves; 
	private List<Position> oddSquares;
	private List<Position> evenSquares;
	
	public PegBoard() {
		super();
		board = getYourBoard();
		make();
	}
	
	private void make() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				int x = 200 + 35 * i;
				int y = 12 + 35 * j;
				Cell temp = new Cell(x, y);
				board[i][j]= temp;
			}
		}
		selectedX = 0;
		selectedY = 0;
		lastD = Direction.UP;
		changeSelector();
		lastMoves = new LinkedList<Position>();
		oddSquares = new ArrayList<Position>();
		evenSquares = new ArrayList<Position>();
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				Position temp = new Position (i, j);
				if ((i + j) % 2 == 0) {
					evenSquares.add(temp);
				} else {
					oddSquares.add(temp);
				}
			}
 		}
		
	}
	
	public void changeSelector() {
		Cell temp = board[selectedX][selectedY];
		temp.changeSelection();
	}
	
	public void reset() {
		make();
	}
	
	public void resetSelector() {
		changeSelector();
		selectedX = 0;
		selectedY = 0;
		changeSelector();
	}
	
	public void selector(KeyStroke k) {
		changeSelector();
		switch(k) {
		case DOWNKEY:
			selectedY = (selectedY + 1) % 10;
			break;
		case LEFTKEY:
			selectedX = (9 + selectedX) % 10;
			break;
		case RIGHTKEY:
			selectedX = (selectedX + 1) % 10;
			break;
		case UPKEY:
			selectedY = (9 + selectedY) % 10;			
			break;
		default:
			break;
		}
		changeSelector();
	}
	
	public Cell attack(Cell[][] shipBoard) {
		Cell temp = board[selectedX][selectedY];
		Cell attacking = shipBoard[selectedX][selectedY];
		Status s = temp.getState();
		boolean isShip = attacking.isShip();
		if (s != Status.EMPTY) {
			return new Cell(100, 100);
		} else {
			if(isShip) {
				temp.setState(Status.HIT);
				attacking.setState(Status.HIT);
				lastMoves.addFirst(new Position (selectedX, selectedY));
//				lastX = selectedX; 
//				lastY = selectedY;
				
			} else {
				temp.setState(Status.MISS);
				attacking.setState(Status.MISS);
			}
			evenSquares.remove(new Position(selectedX, selectedY));
			oddSquares.remove(new Position(selectedX, selectedY));
			return attacking;
		}
		//resetSelector();
	}
	
	private boolean isNearOne(Position p) {
		boolean yes = false;
		
		for (int i = 0; i < lastMoves.size(); i++) {
			if (Position.isOneAway(p, lastMoves.get(i))) {
				yes = true;
			}
		}
		
		return yes;
	}
	
	public Position getRandomSpot() {
		Position p;
		if (!evenSquares.isEmpty()) {
			p = evenSquares.get((int) (Math.random() * evenSquares.size()));
		} else {
			p = oddSquares.get((int) (Math.random() * oddSquares.size()));
		}
		int x = p.getX();
		int y = p.getY();
		Cell c = board[x][y];
		Status s = c.getState();
		if (s == Status.EMPTY) {
			return new Position(x, y);
		}
		return getRandomSpot();
	}
	
	public Position moveWithDirection(int x, int y, int z, int a, Direction d, int times, int index) {
		switch(d) {
		case DOWN:
			y = (y + 1) % 10;
			Cell c = board[x][y];
			if (c.getState() == Status.EMPTY) {
				return new Position(x, y);
			} else if (times == 0) {
				return moveWithDirection(z, a, x, y, rotate2(d), 1, index);
			} else {
				return getBestSpot(index + 1);
			}
		case LEFT:
			x = (10 + x - 1) % 10;
			Cell t = board[x][y];
			if (t.getState() == Status.EMPTY) {
				return new Position(x, y);
			} else if (times == 0) {
				return moveWithDirection(z, a, x, y, rotate2(d), 1, index);
			} else {
				return getBestSpot(index + 1);
			}
		case RIGHT:
			x = (x + 1) % 10;
			Cell v = board[x][y];
			if (v.getState() == Status.EMPTY) {
				return new Position(x, y);
			} else if (times == 0) {
				return moveWithDirection(z, a, x, y, rotate2(d), 1, index);
			} else {
				return getBestSpot(index + 1);
			}
		case UP:
			y = (9+ y) % 10;
			Cell u = board[x][y];
			if (u.getState() == Status.EMPTY) {
				return new Position(x, y);
			} else if (times == 0) {
				return moveWithDirection(z, a, x, y, rotate2(d), 1, index);
			} else {
				return getBestSpot(index + 1);
			}
		default:
			return getRandomSpot();
		}
	}
	
	public static Direction rotate2(Direction d) {
		switch(d) {
		case DOWN:
			return Direction.UP;
		case LEFT:
			return Direction.RIGHT;
		case RIGHT:
			return Direction.LEFT;
		case UP:
			return Direction.DOWN;
		default:
			return Direction.UP;
		}
	}
	
	public static Direction rotate(Direction d) {
		switch(d) {
		case DOWN:
			return Direction.LEFT;
		case LEFT:
			return Direction.UP;
		case RIGHT:
			return Direction.DOWN;
		case UP:
			return Direction.RIGHT;
		default:
			return Direction.UP;
		}
	}
	
	public Position move(int x, int y, int times, Direction d, int index) {
		switch(d) {
		case DOWN:
			int a = (y + 1) % 10;
			Cell c = board[x][a];
			if (c.getState() == Status.EMPTY) {
				return new Position(x, a);
			} else if (times < 4) {
				return move(x, y, times + 1, rotate(d), index + 1);
			} else {
				return getBestSpot(index + 1);
			}
		case LEFT:
			int o = (10 + x - 1) % 10;
			Cell oo = board[o][y];
			if (oo.getState() == Status.EMPTY) {
				return new Position(o, y);
			} else if (times < 4) {
				return move(x, y, times + 1, rotate(d), index + 1);
			} else {
				return getBestSpot(index + 1);
			}
		case RIGHT:
			int f = (y + 1) % 10;
			Cell uu = board[f][y];
			if (uu.getState() == Status.EMPTY) {
				return new Position(f, y);
			} else if (times < 4) {
				return move(x, y, times + 1, rotate(d), index + 1);
			} else {
				return getBestSpot(index + 1);
			}
		case UP:
			int g = (9+ y) % 10;
			Cell u = board[x][g];
			if (u.getState() == Status.EMPTY) {
				return new Position(x, g);
			} else if (times < 4) {
				return move(x, y, times + 1, rotate(d), index + 1);
			} else {
				return getBestSpot(index + 1);
			}
		default:
			return getRandomSpot();
		}
	}
	
	public Position getBestSpot(int index) {
		if (index < lastMoves.size() - 1) {
			Position p = lastMoves.get(index);
			Position q = lastMoves.get(index + 1);
			if (Position.isOneAway(p, q)) {
				Direction d = Position.getDirection(q, p);
				int x = p.getX();
				int y = p.getY();
				int x1 = q.getX();
				int y1 = q.getY();
				lastD = d;
				return moveWithDirection(x, y, x1, y1, d, 0, index);
			} else if (isNearOne(p)) {

				int x = p.getX();
				int y = p.getY();
				return move (x, y, 0, lastD, index);
			} else {	
				return getBestSpot(index + 1);
			}
		} else if (index == lastMoves.size() - 1) {
			Position p = lastMoves.get(0);
			int x = p.getX();
			int y = p.getY();
			return move(x, y, 0, lastD, index);
		}
			return getRandomSpot();
	}
	
	public Cell attackAI(Cell[][] shipBoard, int counter) {
		Position p = getBestSpot(0);
		selectedX = p.getX();
		selectedY = p.getY();
		return attack(shipBoard);
		//}
	}
}



