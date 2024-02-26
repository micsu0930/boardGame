package Hunt;

import java.awt.Color;
import java.awt.Point;

public class Board {
	
	private Field[][] board;
	private final int boardSize;
	private int stepCounter;
	
	public Board(int bs) {
		this.boardSize = bs;
		board = new Field[this.boardSize][this.boardSize];
		this.stepCounter = bs*bs;
		for ( int i = 0; i<this.boardSize;i++) {
			for (int j = 0; j<this.boardSize;j++) {
				this.board[i][j] = new Field();
			}
		}
		this.board[0][0].setColor(Color.black);
		this.board[0][bs-1].setColor(Color.black);
		this.board[bs-1][0].setColor(Color.black);
		this.board[bs-1][bs-1].setColor(Color.black);
		this.board[(bs-1)/2][(bs-1)/2].setColor(Color.white);
	}

	public Field get(int x, int y) {
		return board[x][y];
	}
	
	public Field get(Point point) {
		int x = (int)point.getX();
		int y = (int)point.getY();
		return get(x,y);
	}


	public int getStepCounter() {
		return stepCounter;
	}


	public int getBoardSize() {
		return boardSize;
	}

	public Field[][] getBoard() {
		return board;
	}

	public void setBoard(Field[][] board) {
		this.board = board;
	}

	public void setStepCounter(int stepCounter) {
		this.stepCounter = stepCounter;
	}
	
	public boolean isOver() {
		if(stepCounter<=0) {
			return true;
		}
		
		int wX = (boardSize-1)/2;
		int wY = (boardSize-1)/2;
		
		for(int i = 0; i<boardSize;i++) {
			for(int j = 0; j< boardSize;j++) {
				if(this.board[i][j].getColor()==Color.white) {
					
					wX = i;
					wY = j;
					
				}
			}
		}
		if(wX-1 > 0) {
			if(board[wX-1][wY].getColor() == null) {
				return false;
			}
		}
		if(wX+1 < boardSize) {
			if(board[wX+1][wY].getColor() == null) {
				return false;
			}
		}
		if(wY-1 > 0) {
			if(board[wX][wY-1].getColor() == null) {
				return false;
			}
		}
		if(wY+1 <  boardSize) {
			if(board[wX][wY+1].getColor() == null) {
				return false;
			}
		}
		
		
		
		return true;
	
	}
	
 
}
