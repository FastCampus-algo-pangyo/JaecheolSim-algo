import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_2666{
	/*
	 * 문제 이해: 벽장 문이 있으면 (벽장이 닫힘)
	 * 이웃 벽장이 열려있으면 이동 가능. 
	 * 
	 */
	static int dp[][][];
	static ArrayList<Integer> useList;
	static int len;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		int openDoor1=Integer.parseInt(st.nextToken());
		int openDoor2=Integer.parseInt(st.nextToken());
		
		if(openDoor2<openDoor1) {
			int tmp=openDoor1;
			openDoor1=openDoor2;
			openDoor2=tmp;
		}
		
		len=Integer.parseInt(br.readLine());
		
		//결과물: 이동횟수
		
		useList=new ArrayList<>();
		
		for(int i=0; i<len; i++) {
			useList.add(Integer.parseInt(br.readLine()));
		}
		
		dp=new int[n+1][n+1][len+1];  //문의 상태를 나타내고 그 다음은 순서 인덱스를 나타낸다.
		
		
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=n; j++) {
				for(int c=1; c<=len; c++) {
					dp[i][j][c]=Integer.MAX_VALUE;
				}
			}
		}
		
	
		//문을 옮기는 작업
		//문의 상태를 메모제이션, 
		dfs(openDoor1,openDoor2,0,0);
		
		int min=Integer.MAX_VALUE;
		
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=n; j++) {
				min=Math.min(min, dp[i][j][len]);
			}
		}
		
		System.out.println(min);
	
	}
	
	public static void dfs(int openDoor1, int openDoor2, int order, int count) {
		
		if(dp[openDoor1][openDoor2][order]<count) {
			return;
		}
		else {
			dp[openDoor1][openDoor2][order]=count;
		}
		
		if(order==len) {
			return;
		}
		
		int useDoor=useList.get(order);
		
		if(useDoor==openDoor1 || useDoor==openDoor2) {
			dfs(openDoor1,openDoor2,order+1,count);
		}
		else {
			if(openDoor1<useDoor && useDoor<openDoor2) {
				//열린 문이 좌우에 있을 경우
				dfs(useDoor,openDoor2,order+1,count+useDoor-openDoor1);
				dfs(openDoor1,useDoor,order+1,count+openDoor2-useDoor);
			}
			else if(useDoor<openDoor1 && useDoor<openDoor2) {
				//열린 문이 오른쪽에 있을 경우
				dfs(useDoor,openDoor2,order+1,count+openDoor1-useDoor);
			}
			else {
				//열린 문의 왼쪽에 있을 경우
				dfs(openDoor1,useDoor,order+1,count+useDoor-openDoor2);
			}
			
		}
	}
}