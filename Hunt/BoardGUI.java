package Hunt;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



public class BoardGUI {
	
	private JButton[][] buttons;
	private Board board;
	private JPanel boardPanel;
	private JLabel stepLabel;

	private boolean blackTurn = true;
	private Field target = null;
	private Field prevTarget = null;
    private int prevX = -1;
    private int prevY = -1;

	
	public BoardGUI(int boardSize) {
        board = new Board(boardSize);
        boardPanel = new JPanel();
       
        boardPanel.setLayout(new GridLayout(board.getBoardSize(), board.getBoardSize()));
        buttons = new JButton[board.getBoardSize()][board.getBoardSize()];
        for (int i = 0; i < board.getBoardSize(); ++i) {
            for (int j = 0; j < board.getBoardSize(); ++j) {
                JButton button = new JButton();
                button.addActionListener(new ButtonListener(i, j));
                button.setPreferredSize(new Dimension(40, 40));
                buttons[i][j] = button;
                boardPanel.add(button);

                
            }
        }
        
        draw(board);
        stepLabel = new JLabel(" ");
        stepLabel.setHorizontalAlignment(JLabel.LEFT);
        stepLabel.setText("Lepesek: " + board.getStepCounter());
    }
	
	

    
    public JPanel getBoardPanel() {
        return boardPanel;
    }
    
    
    public JLabel getStepLabel() {
    	return stepLabel;
    }
    
    public void draw(Board board) {
    	for(int i = 0; i<board.getBoardSize();i++) {
    		for(int j = 0; j<board.getBoardSize(); j++) {
    			if(board.getBoard()[i][j] != null) {
    				if(board.getBoard()[i][j].getColor()==Color.black) {
    					buttons[i][j].setBackground(Color.black);
    				}
    				if(board.getBoard()[i][j].getColor()==Color.white) {
    					buttons[i][j].setBackground(Color.white);
    				}
    			} else {
    				buttons[i][j].setBackground(null);
    			}
    		
    		}
    	}
    	
    }
    
    class ButtonListener implements ActionListener {

        private int x, y;

        public ButtonListener(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
        	
        	target = board.get(x,y);
        	
        	
        	/*if(target.getColor() == null || prevTarget.getColor() != Color.black && blackTurn) {
        		JOptionPane.showMessageDialog(null, "Fekete kore");
        	}*/
        	if(blackTurn) {
        		if(target.getColor() != null && target.getColor() != Color.white) {
        			prevTarget = board.get(x,y);
        			prevX = x;
        			prevY = y;
        		
        			
        		} else if(prevTarget == null){
        			JOptionPane.showMessageDialog(null, "Fekete kore");
        		}
        		if(prevTarget != null && (prevX != x || prevY != y) && target.getColor() != Color.white) {
        			

        			Field[][] b = board.getBoard();

        			if((x == prevX+1 || x == prevX-1) && y == prevY) {
        				b[x][y].setColor(Color.black);
            			b[prevX][prevY].setColor(null);
            			buttons[prevX][prevY].setBackground(null);
        				board.setBoard(b);
        				prevTarget = null;
        				board.setStepCounter(board.getStepCounter()-1);
        				stepLabel.setText("Lepesek: " + board.getStepCounter());
        			} else if((y == prevY+1 || y == prevY-1) && x == prevX) {
        				
        				b[x][y].setColor(Color.black);
            			b[prevX][prevY].setColor(null);
            			buttons[prevX][prevY].setBackground(null);
        				board.setBoard(b);
        				prevTarget = null;
        				board.setStepCounter(board.getStepCounter()-1);
        				stepLabel.setText("Lepesek: " + board.getStepCounter());
        			} else {
        				JOptionPane.showMessageDialog(null, "Ide nem lephetsz!");
        				
        			}
        			blackTurn=false;
        			
        		}


        		
        	} else {
        		
        		int wX = 0;
        		int wY = 0;
        		Field[][] b = board.getBoard();
        		for(int i = 0; i<board.getBoardSize();i++) {
        			for(int j = 0; j< board.getBoardSize();j++) {
        				if(board.getBoard()[i][j].getColor()==Color.white) {
        					
        					wX = i;
        					wY = j;
        					
        				}
        			}
        		}
        		if(target.getColor() != Color.black) {
        		if((x == wX+1 || x == wX-1) && y == wY) {
    				b[x][y].setColor(Color.white);
        			b[wX][wY].setColor(null);
        			buttons[wX][wY].setBackground(null);
    				board.setBoard(b);
    				prevTarget = null;
    				blackTurn = true;

    			} else if((y == wY+1 || y == wY-1) && x == wX) {
    				b[x][y].setColor(Color.white);
        			b[wX][wY].setColor(null);
        			buttons[wX][wY].setBackground(null);
        			board.setBoard(b);
    				prevTarget = null;
    				blackTurn = true;
    			} else {
    				JOptionPane.showMessageDialog(null, "Ide nem lephetsz!");
    			}
        	} else {
        		JOptionPane.showMessageDialog(null, "Ide nem lephetsz!");
        	}
        		
        	}

        	draw(board);
            
        if(board.isOver()) {
        	
        	if(board.getStepCounter()>0) {
        		JOptionPane.showMessageDialog(null, "Fekete Nyert");
        	} else {
        		JOptionPane.showMessageDialog(null, "Feher Nyert");
        	}
        	blackTurn = true;
        	
        	board = new Board(board.getBoardSize());
        	for(int i=0;i<board.getBoardSize();i++) {
        		for(int j=0;j<board.getBoardSize();j++) {
        			buttons[i][j].setBackground(null);
        		}
        	}
        	stepLabel.setText("Lepesek: " + board.getStepCounter());
        	draw(board);
        	
        	
        }
        
        }
    }
    }
    





