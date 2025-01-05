/* Andrea Marquez Tavera
 * Dr. Steinberg
 * COP3503 Spring 2024
 * Programming Assignment 1
 */

import java.util.Random;

public class Game {// (1)

	private int[][] board;// (2a)2D array that symbolizes the board
	private char[] p2Moves;/* (2b)1D char array for p2 moves: 
							'd' is down diag right (index 0);
							'r' is hor right (index 1); 
							'b' is vertical bottom (index 2)*/
	private Random random;// (2c)Used for a random move for p2

    // Constructor (3)
    public Game(Random random) {
        board = new int[8][8];        
        p2Moves = new char[]{'d', 'r', 'b'};
        this.random = random;
    }

    // Method to select a random move from 2b (4)
    public char selectPlayerTwoMove() {
        int randomidx = random.nextInt(p2Moves.length);
        return p2Moves[randomidx];
    }

    // Method to simulate a round of the game (5)
    public int play() {
    	
        int row = 0;
        int col = 0;
        char p2LastMove = 'd';
        char p2Move = 'd';
        int p1Flag = 0;
        int p2Flag = 0;
        while (row != 7 || col != 7) {// Play within bounds
        	p2LastMove = p2Move;// sets p2LastMove as the last move P2 did
        	p2Move = selectPlayerTwoMove();// new p2 move
        	p1Flag = 0;
        	p2Flag = 0;
        
        	if(inBound(row, col, 'r') == true && row == 5 && col  == 6 && p1Flag == 0) {
        		col++;
        		p1Flag = 1;
        		//System.out.println("p1 moved r");
        		board[row][col] = 1;
        	}
        	else if(inBound(row, col, 'b') == true && row == 6 && col == 5 && p1Flag == 0) {
        		row++;
        		//System.out.println("p1 moved b");
        		p1Flag = 1;
        		board[row][col] = 1;
        	}
            // normal moves
        	else if(inBound(row, col, 'd') == true && (p2LastMove == 'd' && p1Flag == 0)){// if possible, p1 moves diagonally
                    row++;
                    col++;
                    //System.out.println("p1 moved d");
                    p1Flag = 1;
                    board[row][col] = 1;// mark p1 move in board, used for testing
            } 
            else if (inBound(row, col, 'r') == true && (p2LastMove == 'b') && p1Flag == 0) {// otherwise, move right
               col++;
               //System.out.println("p1 moved r");
               p1Flag = 1;
               board[row][col] = 1;// mark p1 move in board, used for testing
            }else if (inBound(row, col, 'b') == true && (p2LastMove == 'r') && p1Flag == 0) {// otherwise, can only move down
               row++;
               //System.out.println("p1 moved b");
               p1Flag = 1;
               board[row][col] = 1;// mark p1 move in board, used for testing
            }
        	// if exception happened
            else if (inBound(row, col, 'r') == true && p1Flag == 0) {// otherwise, move right
                col++;
                //System.out.println("p1 moved r");
                p1Flag = 1;
                board[row][col] = 1;// mark p1 move in board, used for testing
            }else if (inBound(row, col, 'b') == true && p1Flag == 0) {// otherwise, can only move down
                row++;
                //System.out.println("p1 moved b");
                p1Flag = 1;
                board[row][col] = 1;// mark p1 move in board, used for testing
            }
            
            
            // p2 moves
            if (inBound(row, col, p2Move) && p2Flag == 0) {// check if p2 move can be made
                switch (p2Move) {
                    case 'd':
                        row++;
                        col++;
                        p2Flag = 1;
                        //System.out.println("p2 moved d");
                        board[row][col] = 2;//board[row][col] = 2;// mark p2 move in board, used for testing
                        break;
                    case 'r':
                        col++;
                        p2Flag = 1;
                        //System.out.println("p2 moved r");
                        board[row][col] = 2;//board[row][col] = 2;// mark p2 move in board, used for testing
                        break;
                    case 'b':
                        row++;
                        p2Flag = 1;
                        //System.out.println("p2 moved b");
                        board[row][col] = 2;//board[row][col] = 2;// mark p2 move in board, used for testing
                        break;
                }
                
            }
            
            else if(inBound(row, col, p2Move) == false && p2Flag == 0) {// if attempted p2 move is invalid, respond accordingly 
            	if(col < 7) {// if p2 can only move right, do so
            		col++;
            		p2Flag = 1;
                    //System.out.println("p2 moved r");
                    board[row][col] = 2;//board[row][col] = 2;// mark p2 move in board, used for testing
            	}
            	else if(row < 7) {// if p2 can only move down, do so
            		row++;
            		p2Flag = 1;
                    //System.out.println("p2 moved b");
                    board[row][col] = 2;//board[row][col] = 2;// mark p2 move in board, used for testing
            	}
            	
            }  
            //System.out.println("out");
        }
        board[0][0] = -1;// marks where knight started, used for testing and aesthetics 
        
        //prints board, used for testing
        /*for(int i = 0; i < 8; i++) {
        	for(int j = 0; j < 8; j++) {
        		System.out.print(board[i][j]+ "|");
        		if(j == 7) {
        			System.out.print("\n");
        		}
        	}
        }*/

        // Check if player 1 won
        if (board[7][7] == 1) {
            return 1;// Player 1 wins
        } else {
            return 2;// Player 2 wins
        }
    }
    
    // Helper method to check if in bounds (6)
    private boolean inBound(int row, int col, char move) {// returns if move is possible (true or false)
        switch (move) {
            case 'd':
                return ((row + 1) <= 7) && ((col + 1) <= 7);
            case 'r':
                return (col + 1) <= 7;
            case 'b':
                return (row + 1) <= 7;
            default:
                return false;
        }
    }
}