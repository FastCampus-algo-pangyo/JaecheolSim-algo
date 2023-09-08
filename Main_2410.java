import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2410 {
	
	static int[] dp=new int[1000001];

	public static void main(String[] args) throws IOException {
		/**
		 * 1 1
		 * 2 11 2
		 * 3 111 12 
		 * 4 1111 112 22 4
		 * 5 11111 1112 122 14 
		 **/ 
		
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		dp[1]=1;
		dp[2]=2;
		
		for(int i=3; i<=n; i++) {
			if (i%2==1) dp[i] = dp[i-1];
			else dp[i] = (dp[i-1]+dp[i/2])%1000000000;
		}
		
		System.out.println(dp[n]);
		
		
		
		// 4일때, 1111 112 22 4 
	}
}
