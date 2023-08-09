import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_17140 {
	
	static int r,c,k;
	static int[][] A=new int[101][101];
	
	public static class IntOrder implements Comparable<IntOrder>{
		int i;
		int number;
		
		IntOrder(int inputI, int inputNumber) {
			i=inputI;
			number=inputNumber;
		}
		
		public int compareTo(IntOrder o) {
			if(o.number<number) {
				return 1;
			}
			else if(o.number==number) {
				if(o.i<i) {
					return 1;
				}
				else {
					return -1;
				}
			}
			else {
				return -1;
			}
		}
		
	}

	public static void main(String[] args)throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		r=Integer.parseInt(st.nextToken()); c=Integer.parseInt(st.nextToken()); k=Integer.parseInt(st.nextToken());

		for(int i=1; i<=3; i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=1; j<=3; j++) {
				A[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		int[] schema=new int[] {3,3};
		int sec=0;
		
		while(A[r][c]!=k) {
			if(sec>=100) {
				System.out.println(-1);
				return;
			}
			int row=schema[0];
			int col=schema[1];
				if(row>=col) {
					schema=opR(row, col);
				}
				else {
					schema=opC(row, col);
				}
				sec++;
		}
		
		System.out.println(sec);
		

	}
	public static int[] opR(int r, int c) {
		//모든 행 정렬
		int tempC=0;
		for(int i=1; i<=r; i++) {
			tempC=Math.max(tempC, count(true,i,c));
		}
		
		return new int[] {r,tempC};
	}
	public static int[] opC(int r, int c) {
		//모든 열 정렬
		int tempR=0;
		for(int i=1; i<=c; i++) {
			tempR=Math.max(tempR, count(false,i,r));
		}
		
		return new int[] {tempR,c};
		
	}
	public static int count(boolean isOpR, int varN, int rOrC) {
		int[] countMap=new int[101];
		int ans=0;
		PriorityQueue<IntOrder> pq=new PriorityQueue<>();
		
		for(int i=1; i<=rOrC; i++) {
			if(isOpR) {
				countMap[A[varN][i]]++;
			}
			else {
				countMap[A[i][varN]]++;
			}
		}
		
		for(int i=1; i<=100; i++) {
			if(countMap[i]>=1) {
				pq.add(new IntOrder(i,countMap[i]));
			}
		}
		ans=pq.size()*2;
		
		if(ans>100) {
			ans=100;
		}
		
		int idx=1;
		
		for(int i=1; i<=100; i+=2) {
			IntOrder temp=new IntOrder(0,0);
			if(!pq.isEmpty()) {
				temp=pq.poll();
			}
				if(isOpR) {
					A[varN][i]=temp.i;
					A[varN][i+1]=temp.number;
				}
				else {
					A[i][varN]=temp.i;
					A[i+1][varN]=temp.number;
				}
			
		}
		
		return ans;
	}
}

/*
 * 배열 인덱스 1부터 시작
 * 3X3인 배열 A
 * R 연산: 모든 행 정렬 (행의 개수>= 열의 개수)
 * => 가장 큰 행을 기준으로 행 크기가 변한다.
 * C 연산: 모든 열 정렬 (행의 개수<열의 개수)
 * => 가장 큰 열을 기준으로 열 크기가 변한다.
 * 
 * 정렬: 수의 등장 횟수가 커지는 순!! 그리고 등장 횟수가 같으면 수가 커지는 순! 수 + 등장횟수
 * 0은 무시!
 * 행 또는 열의 크기가 100을 넘어가는 경우에는 처음 100개를 제외한 나머지는 버린다.
 * */

// A[r][c]==k 이기 위한 최소 시간!