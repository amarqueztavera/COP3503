/* Andrea Marquez Tavera
 * Dr. Steinberg
 * COP3503 Spring 2024
 * Programming Assignment 2
 */

 import java.io.*;
 import java.util.*;
 
 public class MagicMaze// 1. You are going to create a Class called MagicMaze.
 { 
	 public char[][] mazeMap;// 2a. 2D primitive char array that will store the entire input maze from the provided text file.
	 public String mazeCount;// 2b. A String object that stores the maze number (maze1, maze2, etc…)
	 public int rowsIdx;// 2c. 2 primitive integer that hold the number of rows...
	 public int columnsIdx;// ...and columns in the 2D maze
	 public int portal1Flag = 0;
	 public int portal2Flag = 0;
	 public int portal3Flag = 0;
	 public int portal4Flag = 0;
	 public int portal5Flag = 0;
	 public int portal6Flag = 0;
	 public int portal7Flag = 0;
	 public int portal8Flag = 0;
	 public int portal9Flag = 0;
 
	 // 2d. You are welcome to add additional attributes that feel may be necessary.
	 public Map<Character, Portal> Hmap = new HashMap<>();// to keep track of each portal's coordinates
	 
	 public MagicMaze(String FileName, int rowsIdx, int columnsIdx)// 3. The MagicMaze Class has one constructor with three parameters.
	 { 
		 this.mazeCount = FileName;// 3a. A String Object that represents the name of the maze text file
		 this.rowsIdx = rowsIdx;// 3b. A primitive int that represents the number of rows.
		 this.columnsIdx = columnsIdx;// 3c. A primitive int that represents the number of columns.
	   
		 this.mazeMap = new char[rowsIdx][columnsIdx]; 
		 readMazeFile(FileName);
	 }
 
	 public void readMazeFile(String mazeFile)// 4. The MagicMaze Class must have some method that will perform scanning the maze from the provided text file into the respective class attribute
	 { 
		 try {// in case file is not found
			 Scanner scanner = new Scanner(new File(mazeFile));// open scanner to read file 
			 for (int idx = 0; idx < rowsIdx; idx++) {// for every row
				 String readLine = scanner.nextLine();// save that row
				 for (int jIdx = 0; jIdx < columnsIdx; jIdx++) {// for every column
					 mazeMap[idx][jIdx] = readLine.charAt(jIdx);// save that character into the temp mazeMap
				 }
			 }
			 scanner.close();// close scanner
	   } catch (Exception e) {// otherwise throw exception
		  System.out.println("File name not found!");
	   }
	 }
 
	 public boolean solveMagicMaze()// 5. The MagicMaze Class has a non-static method called solveMagicMaze, which will be the actual problem solving that you will need to implement.
	 { 
 
		 return solveMagicMazeR(rowsIdx - 1, 0, mazeMap);//... the method returns a Boolean value. If the value is true, then the maze was solved successfully, otherwise the maze was not solved successfully.
	 }
 
	 public boolean solveMagicMazeR(int row, int column, char[][] mazeMap)// Based off NQueen.java!! (..."I would recommend creating a recursive method like the N-Queens problem we did in class
	 { 
		 for (row = rowsIdx - 1; row >= 0; row--)// check each character starting from bottom left
		 {
			 for (column = 0; column < columnsIdx; column++) 
			 {
				 
				 if (mazeMap[row][column] == 'X') { // check if position is win block (base case)
					 return true;
				 }
				 
				 if (positionOk(row, column) && mazeMap[row][column] != '@')// check position is within bounds (from NQueen.java) and that block is walkable
				 {
					 if((mazeMap[row][column] == '1' || mazeMap[row][column] == '2' || mazeMap[row][column] == '3' || mazeMap[row][column] == '4' || mazeMap[row][column] == '5' || mazeMap[row][column] == '6' || mazeMap[row][column] == '7' || mazeMap[row][column] == '8' || mazeMap[row][column] == '9')) // check if position is a portal
					 { 
						 char portalNum = mazeMap[row][column];// store where the portal is
						 int saveFlag = 0;
						 switch(portalNum) {// check if portal has already been saved
							 case '1':
								 if(portal1Flag == 1)
									 saveFlag = 1;
								 break;
							 case '2':
								 if(portal2Flag == 1)
									 saveFlag = 1;
								 break;
							 case '3':
								 if(portal3Flag == 1)
									 saveFlag = 1;
								 break;
							 case '4':
								 if(portal4Flag == 1)
									 saveFlag = 1;
								 break;
							 case '5':
								 if(portal5Flag == 1)
									 saveFlag = 1;
								 break;
							 case '6':
								 if(portal6Flag == 1)
									 saveFlag = 1;
								 break;
							 case '7':
								 if(portal7Flag == 1)
									 saveFlag = 1;
								 break;
							 case '8':
								 if(portal8Flag == 1)
									 saveFlag = 1;
								 break;
							 case '9':
								 if(portal9Flag == 1)
									 saveFlag = 1;
								 break;
							 default:
								 break;
							 
						 }
						 if(saveFlag == 0) {// if it has not been saved, save it to hashmap
							 Portal portalCoo = new Portal(row, column);// saves what coordinates the portal is for the hashmap
							 Hmap.put(portalNum, portalCoo);// saves what portal is with what coordinates into the hashmap
							 for (int tmpRow = row; tmpRow >= 0; tmpRow--)// check each after to find the coresponding portal coordinate
							 {
								 for (int tmpcolumn = 0; tmpcolumn < columnsIdx; tmpcolumn++) 
								 {
									 if(mazeMap[tmpRow][tmpcolumn] == portalNum) {
										 Portal portal2Coo = new Portal(tmpRow, tmpcolumn);
										 Hmap.put(portalNum, portal2Coo);
										 // change to teleport to this location
										 row = tmpRow;
										 column = tmpcolumn;
									 }
								 }
							 }
							 
						 }
						 switch(portalNum) {// switch flag for specific portal
						 case '1':
							 portal1Flag = 1;
							 break;
						 case '2':
							 portal2Flag = 1;
							 break;
						 case '3':
							 portal3Flag = 1;
							 break;
						 case '4':
							 portal4Flag = 1;
							 break;
						 case '5':
							 portal5Flag = 1;
							 break;
						 case '6':
							 portal6Flag = 1;
							 break;
						 case '7':
							 portal7Flag = 1;
							 break;
						 case '8':
							 portal8Flag = 1;
							 break;
						 case '9':
							 portal9Flag = 1;
							 break;
						 default:
							 break;
						 }
						 
					 } else {// if not portal, then kenny walks normally
						 mazeMap[row][column] = 'd';// save "d" for done to know where Kenny has walked through
						 
					 }
				 } else {// if hit out of bounds or a wall
					 if (mazeMap[row][column] != '@') {// if found a wall
						 // backtrack!!
						 mazeMap[row][column] = mazeMap[rowsIdx- 1][0];
						 mazeMap[row][column] = '*';
						 return false;// not correct, restart
					 }
				 }
			 }
		 }
 
		 return false;// if did not exit the maze
	 }
 
	 // 9. You are welcome to create helper methods in your solution class, but please do not modify the driver class in any way.
	 // will keep track of what coordinate that portal is in the hashmap
	 public class Portal {
		 int row;
		 int column;
		 
		 public int getRow() {
			 return row;
		 }
		 public void setRow(int row) {
			 this.row = row;
		 }
		 public int getColumn() {
			 return column;
		 }
		 public void setColumn(int column) {
			 this.column = column;
		 }
		 public Portal(int row, int column) {
			 this.row = row;
			 this.column = column;
		 }
	 }
	 
	 // From NQueen.java!!!!
	 public boolean positionOk(int row, int column) {
		 if(row < rowsIdx && column < columnsIdx)
			 return row >= 0 && column >= 0;
		 else
			 return false;
	 }
 
	   // From NQueen.java!!
	   public String toString()
	   {
		   StringBuilder str = new StringBuilder("Board Size: ");
		   //str.append(n + " X " + n + "\n");
		   str.append("Board Visualization\n");
		   for(int row = 0; row < rowsIdx; ++row)
		  {
			   for(int column = 0; column < columnsIdx; ++column) 
				   str.append(mazeMap[row][column] + " ");
			   str.append("\n");
		  }	
		   return str.toString();
	  }
 }