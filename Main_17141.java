import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_17141 {

	static int n,m;
	static int minSec=Integer.MAX_VALUE;
	static int map[][];
	static ArrayList<int[]> infetiousRoom=new ArrayList<int[]>();	
	static int[] dir= {1,-1,0,0};
	public static void main(String[] args) throws IOException{
		//바이러스 M
		/**
		 * BFS 50 * 50 (방문했던 곳은 안 갈거니까) 
		 * 조합 10 C M => 252 
		 *  O | X 
		 *  X | X 
		 *  X X X
		 * 
		 *  3x3이지만 최대 7초가 걸림.
		 *  50 * 25 + 25 => 최대 1000초
		 *  
		 * 1000* 2500 * 252
		 */
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken()); m=Integer.parseInt(st.nextToken());
		
		map=new int[n][n];
		
		for(int i=0; i<n; i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if(map[i][j]==2) {
					infetiousRoom.add(new int[]{i,j});
				}
			}
		}
		
		combination(0,new ArrayList<int[]>(),0);
		
		minSec=(minSec==Integer.MAX_VALUE)?-1:minSec;
		
		System.out.println(minSec);
		
	}
	public static void combination(int count, ArrayList<int[]> combinedRooms, int startIdx) {
		//  10 C M 
		if(count==m) {
			bfs(combinedRooms);
		}
		
		for(int i=startIdx; i<infetiousRoom.size(); i++) {
			combinedRooms.add(infetiousRoom.get(i));
			combination(count+1, combinedRooms, i+1);
			combinedRooms.remove(infetiousRoom.get(i));
		}
		
	}
	public static void bfs(ArrayList<int[]> combinedRooms) {
		// 50 * 50
		int curTime=0;
		boolean[][] checkRoom=new boolean[n][n];
		int[][] checkTime=new int[n][n];
		//갔던 방은 안 가기 위해 check 설정
		
		Queue<int[]> dq=new LinkedList<int[]>();
		for(int[] combinedOneRoom: combinedRooms) {
			int y=combinedOneRoom[0];
			int x=combinedOneRoom[1];
			dq.add(new int[] {y,x,0});
			checkRoom[y][x]=true;
			//virus를 놓을 수 있는 방 중 조합된 방의 Y, X 좌표 그리고 시간이 써있다.
		}
		
		while(!dq.isEmpty()) {
			int[] virusRoom=dq.poll();
			int curY=virusRoom[0];
			int curX=virusRoom[1];
			curTime=virusRoom[2];
			
			if(curTime>=minSec) {
				return;
			}
			
			for(int i=0; i<4; i++) {
				int newY=curY+dir[i];
				int newX=curX+dir[3-i];
				
				if(newY<0 || newX<0 || newY>=n || newX>=n)
					continue;
				
				
				
				if(!checkRoom[newY][newX] && map[newY][newX]!=1) {
					dq.add(new int[] {newY,newX,curTime+1});
					checkRoom[newY][newX]=true;
				}
			}
		}
		
		if(isSearchAllRoom(checkRoom)) {
			minSec=curTime;
			//print(checkTime);
			
		}
		//바이러스가 들어갈 수 있는 공간이 다 체크되있는지 확인
		
		
	}
	public static boolean isSearchAllRoom(boolean[][] check) {
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(map[i][j]!=1) {
					//바이러스로 감염되있어야 된다.
					if(!check[i][j]) {
						return false;
					}
				}
			}
		}
		return true;
	}
	public static void print(int[][] check) {
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				System.out.print(check[i][j]+" ");
			}
			System.out.println();
		}
	}
}
