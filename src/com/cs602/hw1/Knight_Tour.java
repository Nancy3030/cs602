package com.cs602.hw1;

import java.util.Scanner;

public class Knight_Tour {
	//set eight possible moves(direction) of the knight
	static int X_Direction[] = {-2, -1, 1, 2, 2, 1, -1, -2};
	static int Y_Direction[] = {1, 2, 2, 1, -1, -2, -2, -1};  // set the size for the chase board
	static int n = 8;
	
	//To determine to which step did not be occupied yet  
    static boolean Valid(int X, int Y, int board[][]) { 
        return (X >= 0 && X < n && Y >= 0 && Y < n && board[X][Y] == 0);
    }
    //calculate how many possible for "the input" (X, Y)  
    static int GetDirections(int X, int Y, int board[][]) {
		int Directions = 0;
		for (int i = 0; i < 8; i++) {
			if (Valid(X + X_Direction[i], Y + Y_Direction[i], board)) {
				Directions++;
			}
		}
		return Directions;
	}
    //Using the recursive from the second step
    static boolean Recursion (int X, int Y, int board[][]) {
    	int NextstepX = X; 
		int NextstepY = Y;
		// calculate how many possible for every step 
    	for (int StepNum = 2; StepNum <= n * n; StepNum ++) {
    		int choices = GetDirections(X, Y, board);	//calculate how many possibility for every (X, Y)
    		if (choices == 0) {
    			return false;
    		}  else {
    			int MinRoute = 8;	 // There are only eight possibilities for every step. So, we initialize the value for 8 and whenever a step is occupied, we minus 1  
        		int Routes[] = new int [choices];	// calculate how many possible for eight step and put into the array
        		// calculate which direction has the fewest pass possibility
        		for (int i = 0; i < choices; i++) {
        			for (int j = 0; j < 8; j++) {
        				int NextX = X + X_Direction[j];
            			int NextY = Y + Y_Direction[j];
            			Routes[i] = GetDirections(NextX, NextY, board);		//calculate how many possible for the (NextX,NextY)
            			if (Valid(NextX, NextY, board) && Routes[i] < MinRoute) {
            				MinRoute = Routes[i];
            				NextstepX = NextX;
            				NextstepY = NextY;	// calculate which direction has fewest possibility and put into the (NextX,NextY)
            			}
        			}
        		}
            
            X = NextstepX;
            Y = NextstepY;	// let (X,Y) equal to the (NextstepX, NextstepY), because we will use the X and y for the next recursive  
            board[X][Y] = StepNum;	// let the (X, Y) to the Chase board's coordinate and set the current number of movement   
        	}
    	}
    	
    	return true;
    }
    
    public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int X, Y;
		//Set the First step 
		System.out.print("Enter the number of column of the first step: ");
		X = input.nextInt();
		System.out.print("Enter the number of row of the first step: ");
		Y = input.nextInt();

        int board[][] = new int[n][n];
        // initialize the Chase board to the zero
        for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				board[i][j] = 0;
			}
		}
		board[X][Y] = 1;	//set the board[X][Y] as the first step   
		//Output the result whether success or Failure
		if(Recursion(X, Y, board)) {
			System.out.println("Success: ");
			Picture(board);
		} else {
			System.out.println("Failure!");
		}		
	}
	
	//Output the board 
  	static void Picture(int board[][]) { 
          for (int i = 0; i < n; i++) { 
              for (int j = 0; j < n; j++) 
                  System.out.print(board[i][j] + "	"); 
              System.out.println(); 
          } 
      }
    
}


