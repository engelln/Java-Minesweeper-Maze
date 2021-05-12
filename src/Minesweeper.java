import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

public class Minesweeper extends JFrame {

	private static final long serialVersionUID = -8333843038245579432L;

	// Configure these to your liking
	private int x = 10;
	private int y = 10;
	private int mines = 3;

	private ArrayGame theGame = new ArrayGame(x, y, mines);
	private GridLayout layout = new GridLayout(theGame.getRows(), theGame.getCols());
	private ArrayList<JLabel> cells = new ArrayList<>();
	
	public static void main(String[] args) {
		new Minesweeper();
	}
	
	public Minesweeper() {
		this.setSize(500,500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Minesweeper");
		this.setResizable(false);
		this.setVisible(true);
		this.setLayout(layout);
		
		theGame.start();
		addCells();
		//Timer t = new Timer(33, new UpdateCells());
		//t.start();
		
		
		this.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// Not needed
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				theGame.gameplayLoop2(e.getKeyChar());
				updateCells();
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// not needed
				
			}
		});
	}
	
	private void addCells() {
		for(String s : theGame.getCells()) {
			JLabel newLabel = new JLabel(s);
			newLabel.setAlignmentX(CENTER_ALIGNMENT);
			this.add(newLabel);
			cells.add(newLabel);
		}
	}
	
	
	private void updateCells() {
		for(int i = 0; i < cells.size(); i++) {
			if(!theGame.getIsAlive() || theGame.getIsFinished()) {
				cells.get(i).setText(theGame.getHiddenCells()[i]);
			}
			else {
				cells.get(i).setText(theGame.getCells()[i]);
			}
			
		}
	}
	
	private class UpdateCells implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			for(int i = 0; i < cells.size(); i++) {
				if(!theGame.getIsAlive() || theGame.getIsFinished()) {
					cells.get(i).setText(theGame.getHiddenCells()[i]);
				}
				else {
					cells.get(i).setText(theGame.getCells()[i]);
				}
				
			}
			
		}
		
	}

}
