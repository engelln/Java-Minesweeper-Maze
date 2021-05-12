import java.util.Arrays;

// useful tools for arrays
public class ArrayTools {
	
	
	public static void printBoard(Object[][] board) {
		for(Object[] row : board) {
			for(Object col : row) {
				System.out.print(col.toString() + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static int getDirection(String direction) {
		switch (direction.toLowerCase()) {
		case "w":
			return -1;
		case "a":
			return -1;
		case "s":
			return 1;
		case "d":
			return 1;
	
		default:
			return 0;
		}
	}

}
