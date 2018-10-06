package Knight_Tour;

import java.util.Scanner;

public class Knight_Tour {
	//输入行走路线和棋盘大小
	static int X_Direction[] = {-2, -1, 1, 2, 2, 1, -1, -2};
	static int Y_Direction[] = {1, 2, 2, 1, -1, -2, -2, -1};
	static int n = 8;
	//输入点（x,y）是否在棋盘内并且未被走过
    static boolean Valid(int X, int Y, int board[][]) { 
        return (X >= 0 && X < n && Y >= 0 && Y < n && board[X][Y] == 0);
    }
    //输入点（x,y）的下一步能走的位置数
    static int GetDirections(int X, int Y, int board[][]) {
		int Directions = 0;
		for (int i = 0; i < 8; i++) {
			if (Valid(X + X_Direction[i], Y + Y_Direction[i], board)) {
				Directions++;
			}
		}
		return Directions;
	}
    //从第二步开始进行递归
    static boolean Recursion (int X, int Y, int board[][]) {
    	int NextstepX = X; 
		int NextstepY = Y;
		//计算从第二步开始，每一步的落点
    	for (int StepNum = 2; StepNum <= n * n; StepNum ++) {
    		int choices = GetDirections(X, Y, board);	//计算点（x,y）的下一步能走的位置数
    		if (choices == 0) {
    			return false;
    		}  else {
    			int MinRoute = 8;	 //由于对每一个点来说最多只可能有8个方向能走，在计算最少的路径时将初始值赋值为8较为合理
        		int Routes[] = new int [choices];	//由上面的计算可知下一步能走的位置数，所以需要对每一个位置计算其下一步能走的位置数
        		//计算点（x，y）的所有下一步能走的位置中出路最少的一个点
        		for (int i = 0; i < choices; i++) {
        			for (int j = 0; j < 8; j++) {
        				int NextX = X + X_Direction[j];
            			int NextY = Y + Y_Direction[j];
            			Routes[i] = GetDirections(NextX, NextY, board);		//计算每一个点（NextX,NextY）的下一步能走的位置数
            			if (Valid(NextX, NextY, board) && Routes[i] < MinRoute) {
            				MinRoute = Routes[i];
            				NextstepX = NextX;
            				NextstepY = NextY;	//将下一步能走的位置数最少的其中一个点（NextX,NextY）的坐标赋值给NextstepX，NextstepY
            			}
        			}
        		}
            
            X = NextstepX;
            Y = NextstepY;	//将NextstepX，NextstepY赋值给x,y，使得下一次循环时使用新的x,y
            board[X][Y] = StepNum;	//将这一点（x，y）记为此步要走的点
        	}
    	}
    	
    	return true;
    }
    
    public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int X, Y;
		//输入初始点的位置
		System.out.print("Enter the number of column of the first step: ");
		X = input.nextInt();
		System.out.print("Enter the number of row of the first step: ");
		Y = input.nextInt();

        int board[][] = new int[n][n];
        //将棋盘上的所有点初始化为0
        for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				board[i][j] = 0;
			}
		}
		board[X][Y] = 1;	//初始点设为第一步
		//运行程序，输出结果
		if(Recursion(X, Y, board)) {
			System.out.println("Success: ");
			Picture(board);
		} else {
			System.out.println("Failure!");
		}		
	}
	
	//画出图像
  	static void Picture(int board[][]) { 
          for (int i = 0; i < n; i++) { 
              for (int j = 0; j < n; j++) 
                  System.out.print(board[i][j] + "	"); 
              System.out.println(); 
          } 
      }
    
}


