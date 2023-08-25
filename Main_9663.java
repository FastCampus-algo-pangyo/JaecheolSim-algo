import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_9663 {
    static int caseNum=0;
    static int n;
    static int[] board;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        n=Integer.parseInt(br.readLine());
        board=new int[n+1];

        nQueenPlay(1);
        
        System.out.println(caseNum);
        
    }

    public static void nQueenPlay(int curCol){

        if(curCol==n+1){
        	caseNum++;
        	return;
        }
        for(int row=1; row<=n; row++){
            board[curCol]=row;
           if(!isAttackable(curCol)) {
        	   nQueenPlay(curCol+1);
           }
        }
    }
    public static boolean isAttackable(int curCol){
    	
    	for(int preCol=1; preCol<curCol; preCol++){
            if(board[preCol]==board[curCol] || Math.abs(board[curCol]-board[preCol])==Math.abs(curCol-preCol)){
                	return true;
              }
 	    }
    	return false;
    }
}
