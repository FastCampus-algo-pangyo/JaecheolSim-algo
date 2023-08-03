import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_17298 {
	
	static int n;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		n=Integer.parseInt(br.readLine());
		
		StringTokenizer st=new	StringTokenizer(br.readLine());
		
		int[] input=new int[n];
		
		Stack<Integer> stack=new Stack<Integer>();
		
		for(int i=0; i<n; i++) {
			input[i]=Integer.parseInt(st.nextToken());
		}
		
		int[] nge=new int[n];
		
		nge[n-1]=-1;
		
		
		for(int i=n-2; i>=0; i--) {
			nge[i]=-1;
			if(input[i]<input[i+1]) {
				nge[i]=input[i+1];
				stack.add(input[i+1]);
			}
			else {
				while(!stack.isEmpty()) {
					int ngeNum=stack.pop();
					if(input[i]<ngeNum) {
						nge[i]=ngeNum;
						stack.add(ngeNum);
						break;
					}
				}
			}
		}
		
		
		Arrays.stream(nge).forEach(x->{
			try {
				bw.append(x+" ");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		bw.flush();
		bw.close();
		
		/** 
		 * 4
		 * 3 5 2 7
		 * 
		 * 뒤에서부터 7은 -1
		 * 2는 오른쪽 7이 자신보다 크니까 7
		 * 5는 오른쪽 2의 NGE가 자신보다 크니까 7
		 * 3은 오른쪽 5가 자신보다 크니까 5
		**/
		
		
		/**
		 * 
		 * n이 1,000,000 이니까 n^2을 하면 시간을 넘긴다.
		 * 최소 n * log(n) 시간 복잡도가 되어야 된다. 
		 */
	}

}
