import java.util.Scanner;

// class to throw all the elements of the game together
public class ArrayGame {
	
	private Scanner userInput = new Scanner(System.in);
	private Gameboard gameboard;
	private int rows, cols;
	private int mines;
	private boolean isAlive;
	private boolean isFinished;
	
	

	
	public ArrayGame(int x, int y, int mines) {
		gameboard = new Gameboard(x,y);
		rows = y;
		cols = x;
		this.mines = mines;
		isAlive = true;
		isFinished = false;
	}


	public void start() {
		
		gameboard.fillBoard();
		gameboard.setPlayer(0,0);
		gameboard.setFinish();
		gameboard.setMines(mines);
		//gameplayLoop();
		//endGame();
		//gameboard.printHiddenBoard();
		
	}
	
	private void endGame() {
		if(!isAlive) {
			System.out.println("You died!");
		}
		if(isFinished) {
			System.out.println("You win!");
		}
		
	}


	private void gameplayLoop() {
		// check if alive and if finished
		// while alive and not finished
		// ask for direction
		// move the player in that direction
		
		while(isAlive && !isFinished) {
			
			gameboard.printBoard();
			
			System.out.print("Up, down, left or right: ");
			String direction = userInput.nextLine();
			movePlayer(direction);
		}
		
	}
	
	public void gameplayLoop2(char dir) {
		
		if(isAlive && !isFinished) {
			String direction = String.valueOf(dir);
			movePlayer(direction);
		}
		
	}


	// moves player in the given direction indirectly
	// scans for mines and the finish and if neither are there tells the gameboard to move the player
	private void movePlayer(String direction) {
		

		// if its a mine, no longer alive
		if(gameboard.scanForFlag(GameData.FLAG_MINE, direction)) {
			isAlive = false;
			return;
		}
		// if its the finish they win
		if(gameboard.scanForFlag(GameData.FLAG_FINISH, direction)) {
			isFinished = true;
			return;
		}
		
		gameboard.movePlayer(direction);
		
		
	}
	
	public boolean getIsAlive() {
		return isAlive;
	}

	public boolean getIsFinished() {
		return isFinished;
	}

	public int getRows() {
		return rows;
	}


	public int getCols() {
		return cols;
	}
	
	public String[] getCells() {
		return gameboard.getBoardTextArray();
	}
	
	public String[] getHiddenCells() {
		return gameboard.getBoardTextHiddenArray();
	}
	
	

}
