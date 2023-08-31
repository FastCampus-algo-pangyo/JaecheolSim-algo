import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_14503 {
	
	/**
	 * 출력: 청소하는 영역의 개수
	 * 2500 
	 */
	enum RoomStatus{
		
		DIRTY(0),
		WALL(1),
		CLEANED(2);
		
		final private int roomStatus;
		
		RoomStatus(int status) {
			roomStatus=status;
		}
		
		public int getValue() {
			return roomStatus;
		}

	}
	
	static int dir[]= {1,0,-1,0};
	static int[][] map;
	static int startY, startX, startD;
	static int n,m;


	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		inputHandling();
		System.out.println(work());
	
	}
	public static int work() {
		
		int cleandRoomCount=0;
		int curY=startY, curX=startX, curD=startD;
		while(true) {
			if(map[curY][curX]==RoomStatus.DIRTY.getValue()) {
				map[curY][curX]=RoomStatus.CLEANED.getValue();
				cleandRoomCount++;
			}
			if(!checkAroundDirtyRoom(curY, curX)) {
				if(reversible(curY, curX, curD)) {
					curY=curY+dir[curD];
					curX=curX+dir[3-curD];
				}
				else {
					break;
				}
			}
			else {
				/*반시계 방향으로 90도
				 * 동 -> 북 -> 서 -> 남 
				 * 1     0     3     2
				 */
				while(true) {
					curD=(curD==0)?3:curD-1;
					if(isDirtyRoomInTheFront(curY, curX, curD)) {
						curY=curY-dir[curD];
						curX=curX-dir[3-curD];
						break;
					}
				}
			}
		}
		return cleandRoomCount;
	}
	public static  boolean reversible(int curY, int curX, int curD) {
		/* D의 값에 따른 방향 그리고 후진했을 시 Y, X의 변화
		 * 0: 북 , Y: +1 
		 * 1: 동   X: -1
		 * 2: 남   Y: -1
		 * 3: 서  X: +1
		 */
		int newY=curY+dir[curD];
		int newX=curX+dir[3-curD];
		
		if(isVaildIndex(newY, newX) && map[newY][newX]!=RoomStatus.WALL.getValue()) {
			return true;
		}
	
		return false;
		
	}
	public static boolean isDirtyRoomInTheFront(int curY, int curX, int curD) {
		
		int newY=curY-dir[curD];
		int newX=curX-dir[3-curD];
		
		if(isVaildIndex(newY, newX) && map[newY][newX]==RoomStatus.DIRTY.getValue()) {
			return true;
		}
	
		return false;
	}
	public static boolean checkAroundDirtyRoom(int curY, int curX) {
		for(int i=0; i<4; i++) {
			int newY=curY+dir[i];
			int newX=curX+dir[3-i];
			
			if(isVaildIndex(newY, newX)) {
				if(map[newY][newX]==RoomStatus.DIRTY.getValue()) {
					return true;
				}
			}
		}
		
		return false;
	}
	public static boolean isVaildIndex(int newY, int newX) {
		if(0<=newY && newY<n && 0<=newX && newX<m) {
			return true;
		}
		return false;
	}
	public static void inputHandling() throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		
		st=new StringTokenizer(br.readLine());
		startY=Integer.parseInt(st.nextToken());
		startX=Integer.parseInt(st.nextToken());
		startD=Integer.parseInt(st.nextToken());
		
		map=new int[n][m];
		
		for(int i=0; i<n; i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
	}
	
}
