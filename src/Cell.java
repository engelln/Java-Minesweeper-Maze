// class to model each individual cell on the gameboard
public class Cell {
	
	private String content;
	private String flag;
	private int row;
	private int col;
	
	public Cell(int row, int col) {
		this.content = GameData.CONTENT_EMPTY;
		this.flag = GameData.FLAG_EMPTY;
		this.row = row;
		this.col = col;
		
	}

	@Override
	public String toString() {
		return content;
	}
	
	public String toStringHidden() {
		if(flag.equals("*")) {
			return content;
		}
		else {
			return flag;
		}
	}

	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}

	public String getFlag() {
		return flag;
	}

	public void setAsFinish() {
		this.flag = GameData.FLAG_FINISH;
		this.content = GameData.CONTENT_FINISH;
	}
	
	public void setAsMine() {
		this.flag = GameData.FLAG_MINE;
	}
	
	public void setAsPlayer() {
		this.flag = GameData.FLAG_PLAYER;
		this.content = GameData.CONTENT_PLAYER;
	}
	
	public void setContent(String content) {
		switch(content) {
		
		case GameData.CONTENT_VERTICAL:
			this.content = GameData.CONTENT_VERTICAL;
			break;
		case GameData.CONTENT_HORIZONTAL:
			this.content = GameData.CONTENT_HORIZONTAL;
			break;
		
		}
	}
	
	public void removeFlag() {
		this.flag = GameData.FLAG_EMPTY;
	}
	
	

}
