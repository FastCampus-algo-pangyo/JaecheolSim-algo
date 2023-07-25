import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_1021 {
	
	static int n,m;
	

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		n=Integer.parseInt(st.nextToken()); m=Integer.parseInt(st.nextToken());
		
		//뽑으려는 
	
		st=new StringTokenizer(br.readLine());
		
		ArrayList<Integer> list=new ArrayList<Integer>();
		// 1 2 3 4 5 6 7 8
		
		for(int i=0; i<m; i++) {
			int num=Integer.parseInt(st.nextToken());
			list.add(num);
		}
		int count=0;
		while(!list.isEmpty()) {
			if(list.get(0)==1) {
				list.remove(0);
				n--;
				for(int i=0; i<list.size(); i++) {
					list.set(i, list.get(i)-1);
				}
			}
			else if(list.get(0)<=(n/2)+1) {
				count++;
				for(int i=0; i<list.size(); i++) {
					if(list.get(i)==1) {
						list.set(i,n);
					}
					else {
					list.set(i, list.get(i)-1);
					}
				}
			}
			else {
				count++;
				for(int i=0; i<list.size(); i++) {
					if(list.get(i)==n) {
						list.set(i,1);
					}
					else {
					list.set(i, list.get(i)+1);
					}
				}
			}
			
		}
		
		System.out.println(count);
	}
	
	

}