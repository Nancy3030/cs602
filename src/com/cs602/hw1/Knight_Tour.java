package Knight_Tour;

import java.util.Scanner;

public class Knight_Tour {
	//��������·�ߺ����̴�С
	static int X_Direction[] = {-2, -1, 1, 2, 2, 1, -1, -2};
	static int Y_Direction[] = {1, 2, 2, 1, -1, -2, -2, -1};
	static int n = 8;
	//����㣨x,y���Ƿ��������ڲ���δ���߹�
    static boolean Valid(int X, int Y, int board[][]) { 
        return (X >= 0 && X < n && Y >= 0 && Y < n && board[X][Y] == 0);
    }
    //����㣨x,y������һ�����ߵ�λ����
    static int GetDirections(int X, int Y, int board[][]) {
		int Directions = 0;
		for (int i = 0; i < 8; i++) {
			if (Valid(X + X_Direction[i], Y + Y_Direction[i], board)) {
				Directions++;
			}
		}
		return Directions;
	}
    //�ӵڶ�����ʼ���еݹ�
    static boolean Recursion (int X, int Y, int board[][]) {
    	int NextstepX = X; 
		int NextstepY = Y;
		//����ӵڶ�����ʼ��ÿһ�������
    	for (int StepNum = 2; StepNum <= n * n; StepNum ++) {
    		int choices = GetDirections(X, Y, board);	//����㣨x,y������һ�����ߵ�λ����
    		if (choices == 0) {
    			return false;
    		}  else {
    			int MinRoute = 8;	 //���ڶ�ÿһ������˵���ֻ������8���������ߣ��ڼ������ٵ�·��ʱ����ʼֵ��ֵΪ8��Ϊ����
        		int Routes[] = new int [choices];	//������ļ����֪��һ�����ߵ�λ������������Ҫ��ÿһ��λ�ü�������һ�����ߵ�λ����
        		//����㣨x��y����������һ�����ߵ�λ���г�·���ٵ�һ����
        		for (int i = 0; i < choices; i++) {
        			for (int j = 0; j < 8; j++) {
        				int NextX = X + X_Direction[j];
            			int NextY = Y + Y_Direction[j];
            			Routes[i] = GetDirections(NextX, NextY, board);		//����ÿһ���㣨NextX,NextY������һ�����ߵ�λ����
            			if (Valid(NextX, NextY, board) && Routes[i] < MinRoute) {
            				MinRoute = Routes[i];
            				NextstepX = NextX;
            				NextstepY = NextY;	//����һ�����ߵ�λ�������ٵ�����һ���㣨NextX,NextY�������긳ֵ��NextstepX��NextstepY
            			}
        			}
        		}
            
            X = NextstepX;
            Y = NextstepY;	//��NextstepX��NextstepY��ֵ��x,y��ʹ����һ��ѭ��ʱʹ���µ�x,y
            board[X][Y] = StepNum;	//����һ�㣨x��y����Ϊ�˲�Ҫ�ߵĵ�
        	}
    	}
    	
    	return true;
    }
    
    public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int X, Y;
		//�����ʼ���λ��
		System.out.print("Enter the number of column of the first step: ");
		X = input.nextInt();
		System.out.print("Enter the number of row of the first step: ");
		Y = input.nextInt();

        int board[][] = new int[n][n];
        //�������ϵ����е��ʼ��Ϊ0
        for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				board[i][j] = 0;
			}
		}
		board[X][Y] = 1;	//��ʼ����Ϊ��һ��
		//���г���������
		if(Recursion(X, Y, board)) {
			System.out.println("Success: ");
			Picture(board);
		} else {
			System.out.println("Failure!");
		}		
	}
	
	//����ͼ��
  	static void Picture(int board[][]) { 
          for (int i = 0; i < n; i++) { 
              for (int j = 0; j < n; j++) 
                  System.out.print(board[i][j] + "	"); 
              System.out.println(); 
          } 
      }
    
}


