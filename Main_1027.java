import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1027 {
	
	static int[] buildings;

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	
		int n=Integer.parseInt(br.readLine());
		
		//다른 고층 빌딩을 지나거나 접하지 않아야 된다.
		//좌표 상에 지나거나 접하지 않아야 된다.
		//제한 사항: 높이 1.000.000.000 보다 작거나 같은 자연수
		// n은 50보다 작거나 같은 자연수
		
		buildings=new int[n];
		int[] seeCount=new int[n];
		
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		for(int i=0; i<n; i++) {
			buildings[i]=Integer.parseInt(st.nextToken());
		}
		
		//0번 인덱스부터 시작
		
	
		//A에서 B건물을 보기 위해선, 두 지붕을 잇는 선분이 다른 고층 빌딩을 지나거나 접하지 않아야 한다.
		//이를 확인하기 위해선, (B-A)*{(사이 건물 인덱스- A인덱스)/(B인덱스-A인덱스)}>사이건물 높이 되어야 된다.
		//이중 포문을 돌면서 첫번째 인덱스부터 인덱스 끝까지!! 
		for(int i=0; i<n; i++) {
			for(int j=i+1; j<n; j++) {
				if(see(i,j)) {
					//사이에 건물이 있는지
					seeCount[i]++;
					seeCount[j]++;
				}
			}
		}
		
		int max=0; 
		
		for(int i=0; i<n; i++) {
			if(max<seeCount[i])
				max=seeCount[i];
		}
		System.out.print(max);
		
		
	}
	public static boolean see(int startIdx,int endIdx) {
		
		double slope=getSlope(startIdx,endIdx);
		
		for(int i=startIdx+1; i<endIdx; i++) {
			double nextSlope=getSlope(startIdx,i);
			if(nextSlope>=slope) {
				return false;
			}
		}
		
		return true;
	}
	public static double getSlope(int startIdx, int endIdx) {
		return (double)(buildings[endIdx]-buildings[startIdx])/(endIdx-startIdx);
	}
	

}
