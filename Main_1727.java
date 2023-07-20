import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1727 {
	
	static int[] menPersonality;
	static int[] womenPersonality;
	
			
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		/******** 입 출력 처리 ********/
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int n=Integer.parseInt(st.nextToken()); int m=Integer.parseInt(st.nextToken());
		menPersonality=new int[n+1];
		womenPersonality=new int[m+1];
		
		st=new StringTokenizer(br.readLine());
		
		for(int i=1; i<=n; i++) {
			menPersonality[i]=Integer.parseInt(st.nextToken());
		}
		
		st=new StringTokenizer(br.readLine());
		
		for(int i=1; i<=m; i++) {
			womenPersonality[i]=Integer.parseInt(st.nextToken());
		}
		/***** 입출력 처리 끝 *********/

		System.out.println(makeCouples(m, n));
		

		
		
	}
	public static long makeCouples(int womenCount, int menCount) {
		int maxCouples=womenCount;
		int maxSearchIndex=menCount;
		
		/******알고리즘************/
		//둘다 오름차순으로 정렬
		Arrays.sort(menPersonality);
		Arrays.sort(womenPersonality);
		
		//생각해보면, 둘다 정렬된 상태에서는 (여자:3, 남자:5) (여자:4, 남자:2) 처럼 교차해서 커플이 되면 최솟값을 가질 수 가 없다. 이를 위해 정렬을 한다.  
		
		//dp[i][j]= 현재탐색중인 j 인덱스에서 커플이 된수가 i일때의 차이 합의 최솟값을 의미한다.
		boolean moreMan=true;
		
		if(maxCouples>maxSearchIndex) {
			int temp=maxCouples;
			maxCouples=maxSearchIndex;
			maxSearchIndex=temp;
			
			moreMan=false;
		}
		//항상 m이 작은 상황을 만들기 위해 사용했다. 그러면 결국 m은 최대 커플수가 된다.
		
		
		//커플 수가 m까지이므로 m이 작은 값을 가져야 된다.
		long dp[][]=new long[maxCouples+1][maxSearchIndex+1];
		for(int i=1; i<=maxCouples; i++) { //커플 1~m까지 (m은 최대 커플수)
			for(int j=i; j<=maxSearchIndex; j++) { //탐색 인덱스는 i부터 n까지 (n : 여자와 남자 중 많은 인원의 최대 인덱스 수)  
				long temp;
				if(moreMan){
					temp=Math.abs(womenPersonality[i]-menPersonality[j]);
				}  
				else {
					temp=Math.abs(womenPersonality[j]-menPersonality[i]);
				} 
				if(i==j) {
					dp[i][j]=dp[i-1][j-1]+temp;
				}
				else {
					dp[i][j]=Math.min(dp[i-1][j-1]+temp, dp[i][j-1]);
				}
				
			}
		}
		/******알고리즘************/
		return dp[maxCouples][maxSearchIndex];
	}
	

}
