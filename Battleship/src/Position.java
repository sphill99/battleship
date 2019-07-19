import java.util.*;

public class Position implements Comparable<Position> {
	private int x;
	private int y;
	
	public Position (int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		int val = x;
		return val;
	}
	
	public int getY() {
		int val = y;
		return val;
	}
	
	public static boolean isOneAway(Position p, Position q) {
		int x1 = p.getX();
		int x2 = q.getX();
		int y1 = p.getY();
		int y2 = q.getY();
		
		return ((x1 == x2 && Math.abs(y1 - y2) == 1) || (y1 == y2 && Math.abs(x1 - x2) == 1));
	}
	
	public static Direction getDirection(Position first, Position recent) {
		int x1 = first.getX();
		int x2 = recent.getX();
		int y1 = first.getY();
		int y2 = recent.getY();
		
		if (x1 == x2) {
			if (y2 > y1) {
				return Direction.DOWN;
			} else if (y1 > y2) {
				return Direction.UP;
			}
		} else if (y1 == y2) {
			if (x2 > x1) {
				return Direction.RIGHT;
			} else if (x1 > x2) {
				return Direction.LEFT;
			}
		}
		
		return Direction.UP;
	}
	
	@Override
	public int compareTo(Position o) {
		
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
	
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		try {
			Position p = (Position) o;
			if (x == p.getX() && y == p.getY()) {
				return true;
			}
			return false;
		} catch (ClassCastException e) {
			return false;
		}
	}
	
	public static String print(Position x) {
		String s = "(" + x.getX() + ", " + x.getY() + ")";
		System.out.println(s);
		return s;
	}
	
	public static String printSet(LinkedList<Position> set) {
		String s = "";
		for(Position x : set) {
			s += print(x);
		}
		System.out.println();
		return s;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
}
