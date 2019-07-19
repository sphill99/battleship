import java.awt.Graphics;

public class GameBoard {
	// 1 means unoccupied 2 means occupied, 3 means unoccupied and attacked, 4 means occupied and attacked
	private Cell[][] board;

	// 1 means not attacked, 2 means attacked and missed, 3 means attacked and hit

	public GameBoard() {
		board = new Cell[10][10];
	}
	
	
	public Cell[][] getYourBoard() {
		return board;
	}
	
// Blue is nothing, Gray is missed, Red is hit
	public void setYourBoard(int posX, int posY, Status s) {
		if (posX > board.length || posX < 0) {
			throw new IllegalArgumentException("The x position is out of bounds");			
		} else if (posY > board[0].length || posY > 0) {
			throw new IllegalArgumentException("The y position is out of bounds");						
		}
		Cell cell = board[posX][posY];
		cell.setState(s);
	}

	public void draw(Graphics gc) {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				Cell temp = board[i][j];
				temp.drawCell(gc);
			}
		}
	}
}
