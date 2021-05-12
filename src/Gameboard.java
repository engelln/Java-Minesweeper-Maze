import java.util.ArrayList;
import java.util.Random;

// class to model the gameboard
public class Gameboard {
	
	private int rows, cols;
	private Cell[][] gameboard;
	private Coordinate[] mineCoords;
	private Coordinate playerCoords;
	private Coordinate finishCoords;
	private Random rng = new Random();
	
	public Gameboard(int x, int y) {
		rows = y; cols = x;
		gameboard = new Cell[rows][cols];
	}
	
	// fill each spot on the board with a cell
	public void fillBoard() {
		for (int i = 0; i < gameboard.length; i++) {
			for (int j = 0; j < gameboard[i].length; j++) {
				gameboard[i][j] = new Cell(i,j);
			}
		}
	}
	
	public void printBoard() {
		for(Cell[] row : gameboard) {
			for(Cell col : row) {
				System.out.print(col.toString() + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public void printHiddenBoard() {
		for(Cell[] row : gameboard) {
			for(Cell col : row) {
				System.out.print(col.toStringHidden() + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public void setMines(int mines) {
		mineCoords = new Coordinate[mines];
		
		for (int i = 0; i < mines; i++) {
			
			int row = rng.nextInt(rows);
			int col = rng.nextInt(cols);
			
			while(!gameboard[row][col].getFlag().equals(GameData.FLAG_EMPTY)) {
				row = rng.nextInt(rows);
				col = rng.nextInt(cols);
				
			}
			
			gameboard[row][col].setAsMine();
			mineCoords[i] = new Coordinate(row, col);
		}
	}

	public void setPlayer(int row, int col) {
		gameboard[row][col].setAsPlayer();
		if(playerCoords == null) {
			playerCoords = new Coordinate(row,col);
		}
		else {
			playerCoords.setRow(row);
			playerCoords.setCol(col);
		}
		
		
	}

	public void setFinish() {
		int row = rng.nextInt(rows - 1) + 1;
		int col = rng.nextInt(cols - 1) + 1;
		gameboard[row][col].setAsFinish();
		finishCoords = new Coordinate(row, col);
		
	}
	
	// checks in a given direction next to the player and returns true if there is a cell with the given flag
	public boolean scanForFlag(String flag, String direction) {
		
		if(scanForWall(direction)) {return false;}
		
		int dir = ArrayTools.getDirection(direction);
		if(direction.equals("w") || direction.equals("s")) {
			return gameboard[playerCoords.getRow() + dir][playerCoords.getCol()].getFlag().equals(flag);
		}
		else if(direction.equals("a") || direction.equals("d")) {
			return gameboard[playerCoords.getRow()][playerCoords.getCol() + dir].getFlag().equals(flag);
		}
		else {
			return false;
		}
		
	}
	
	// returns true if the player is at the boundaries of the gameboard
	public boolean scanForWall(String direction) {
		
		int dir = ArrayTools.getDirection(direction);
		if(direction.equals("w") || direction.equals("s")) {
			return (playerCoords.getRow() + dir) < 0 || (playerCoords.getRow() + dir) > gameboard.length - 1;
		}
		else if(direction.equals("a") || direction.equals("d")) {
			return (playerCoords.getCol() + dir) < 0 || (playerCoords.getCol() + dir) > gameboard[0].length - 1;
		}
		else {
			return false;
		}
		
	}
	

	// moves the player directly
	// checks for a wall in given direction
	// if no wall the player moves
	public void movePlayer(String direction) {
		// wall do nothing
		if(this.scanForWall(direction)) {return;}
		
		int dir = ArrayTools.getDirection(direction);

		gameboard[playerCoords.getRow()][playerCoords.getCol()].removeFlag();
		
		
		if(direction.equals("w") || direction.equals("s")) {
			gameboard[playerCoords.getRow()][playerCoords.getCol()].setContent(GameData.CONTENT_VERTICAL);
			setPlayer(playerCoords.getRow() + dir, playerCoords.getCol());
			//gameboard[playerCoords.getRow() + dir][playerCoords.getCol()].setAsPlayer();
		}
		else if(direction.equals("a") || direction.equals("d")) {
			gameboard[playerCoords.getRow()][playerCoords.getCol()].setContent(GameData.CONTENT_HORIZONTAL);
			setPlayer(playerCoords.getRow(), playerCoords.getCol() + dir);
			//gameboard[playerCoords.getRow()][playerCoords.getCol() + dir].setAsPlayer();
		}
		else {
			return;
		}
	}

	public String[] getBoardTextArray() {
		ArrayList<String> strings = new ArrayList<String>();
		for(Cell[] row : gameboard) {
			for(Cell col : row) {
				strings.add(col.toString());
			}
		}
		String[] returnArray = new String[strings.size()];
		return strings.toArray(returnArray);
	}
	
	public String[] getBoardTextHiddenArray() {
		ArrayList<String> strings = new ArrayList<String>();
		for(Cell[] row : gameboard) {
			for(Cell col : row) {
				strings.add(col.toStringHidden());
			}
		}
		String[] returnArray = new String[strings.size()];
		return strings.toArray(returnArray);
	}



}
