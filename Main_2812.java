import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main_2812 {
	
	static int n,k;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		n=Integer.parseInt(st.nextToken()); k=Integer.parseInt(st.nextToken());
		
		char[] inputNum=br.readLine().toCharArray();
		//입력
		Stack<Character> stack=new Stack<Character>();
		
		for(int i=0; i<n; i++) {
			char cur=inputNum[i];
		
			while(!stack.isEmpty() && k!=0 && stack.peek()<cur) {
				stack.pop();
				k--;
			}
			
			stack.push(cur);
		}
		
		StringBuilder sb=new StringBuilder();
		
		for(int i=0; i<stack.size()-k; i++) {
			sb.append(stack.get(i));
		}
		
		System.out.print(sb.toString());
		
	}
}
