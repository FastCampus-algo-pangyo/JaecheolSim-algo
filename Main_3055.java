import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main_3055{

    static char map[][];
    static int r,c;
    static int[] dir={0,0,-1,+1};
    static boolean possible=false;
    static int time=-1;

    public static void main(String[] args) throws IOException {

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st=new StringTokenizer(br.readLine());
        r=Integer.parseInt(st.nextToken());
        c=Integer.parseInt(st.nextToken());

        int startY=0,startX=0;
        
        map=new char[r+1][c+1];
        for(int i=1; i<=r; i++){
            String temp=br.readLine();
            for(int j=1; j<=c; j++){
                map[i][j]=temp.charAt(j-1);
                if(map[i][j]=='S'){
                    startY=i;
                    startX=j;
                }
            }
        }
        
        moveHedgehog(startY, startX);

        if(possible)
        System.out.println(time);
        else
        System.out.println("KAKTUS");

        


        
        //D 비버, S 고슴도치 돌 X, 물 *, 비어있는 곳.

        //고슴도치가 비버의 굴로 들어가기 위한 최소 시간
        // 이동 X 면 "KAKTUS"
    }
    public static void moveHedgehog(int startY, int startX) {
        boolean[][] check=new boolean[r+1][c+1];

        Deque<int[]> dq=new ArrayDeque<>();
        dq.add(new int[]{startY,startX,0});
        check[startY][startX]=true;

        while(!dq.isEmpty()){
            int temp[]=dq.poll();
            int minute=temp[2];
            if(possible){
                break;
            }
            if(time<minute){
                fillwater();
                time=minute;
            }

            for(int i=0; i<4; i++){
                int newY=temp[0]+dir[i];
                int newX=temp[1]+dir[3-i];
                if(isPossibleIdx(newY, newX) && !check[newY][newX]){
                    if(map[newY][newX]=='.'){
                       map[newY][newX]='S';
                       check[newY][newX]=true;
                       dq.add(new int[]{newY,newX,minute+1});
                    }
                    else if(map[newY][newX]=='D'){
                        possible=true;
                        time=minute+1;
                        break;
                    }
                }
            }
        }
    }
    public static void fillwater(){
    	boolean check[][]=new boolean[r+1][c+1];
    	
    	for(int i=1; i<=r; i++) {
    		for(int j=1; j<=c; j++) {
    			if(map[i][j]=='*' && !check[i][j]) {
    				for(int k=0; k<4; k++) {
    					int newY=i+dir[k];
    	              int newX=j+dir[3-k];
    	              if(isPossibleIdx(newY, newX)){
	    	            	if(map[newY][newX]!='X' && map[newY][newX]!='D' && map[newY][newX]!='*') {
	    	            		map[newY][newX]='*';
	    	            		check[newY][newX]=true;
	    	            	}
    	            	  
    	              	}
    				}
    			}
    		}
    	}
    }
    public static boolean isPossibleIdx(int newY,int newX) {
    	if(1<=newY && newY<=r && 1<=newX && newX<=c) {
    		return true;
      	}
    	return false;
    }
   
}