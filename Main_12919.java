import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_12919 {
	
	static String s,t;

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		s=br.readLine();
		t=br.readLine();
		//글자수는 정해져 있다
		dfs(new StringBuilder(t));
		
		System.out.println(0);
	}
	public static void dfs(StringBuilder t) {
	
		if(t.length()<=s.length()) {			
			if(s.compareTo(t.toString())==0) {
				System.out.println(1);
				System.exit(0);
			}
			return;
		}
		
		if(t.charAt(0)=='B') {
			StringBuilder tempT=new StringBuilder(t.toString());
			tempT.reverse();
			tempT.deleteCharAt(tempT.length()-1);
			dfs(tempT);
		}
		if(t.charAt(t.length()-1)=='A') {
			StringBuilder tempT=new StringBuilder(t.toString());
			tempT.deleteCharAt(tempT.length()-1);
			dfs(tempT);
			
		}
			
		
	}
}
