
public class Minesweeper {

	public static char[][] makeBoard(int h,int w,int numMines){
		char[][] board = new char[h][w];
		for(int i=0;i<numMines;i++){
			boolean mineSet=false;
			while(!mineSet){
			int x=(int)(Math.random()*h);
			int y=(int)(Math.random()*w);
			if(board[x][y]!='X'){
				board[x][y]='X';
				mineSet=true;
			}
			
			}
			
		}
		for(int x=0;x<h;x++){
			for(int y=0;y<w;y++){
				int count=0;
					if(board[x][y]!='X'){
						if(x+1<h&&y+1<w&&board[x+1][y+1]=='X')
							count++;
						if(x+1<h&&y-1>-1&&board[x+1][y-1]=='X')
							count++;
						if(x-1>-1&&y-1>-1&&board[x-1][y-1]=='X')
							count++;
						if(x-1>-1&&y+1<w&&board[x-1][y+1]=='X')
							count++;
						if(x-1>-1&&board[x-1][y]=='X')
							count++;
						if(y-1>-1&&board[x][y-1]=='X')
							count++;
						if(x+1<h&&board[x+1][y]=='X')
							count++;
						if(y+1<w&&board[x][y+1]=='X')
							count++;
				}
					if(board[x][y]!='X')
					board[x][y]=Integer.toString(count).charAt(0);
			}
		}
		return board;
	}
	public static void main(String[] args) {
		
		
		char[][] board =makeBoard(10,10,10);
		for(char[] c:board){
			for(char cc:c){
				System.out.print(cc+" ");
			}
			System.out.println();
		}

	}

}
