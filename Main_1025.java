import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main_1025 {
	
	static String[] maps;
	static HashSet<Integer> set=new HashSet<>();
	static int n,m;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		//입력
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		n=Integer.parseInt(st.nextToken()); m=Integer.parseInt(st.nextToken());
		
		maps=new String[n];
		
		for(int i=0; i<n; i++) {
			maps[i]=br.readLine();
		}
		
		
		//알고리즘 고민
		//가장 큰 완전 제곱수
		//만들 수 없는 경우 -1 출력
		//각각 이차원배열에서 다른 칸을 선택하되 선택한순서대로 행의 번호, 열의 번호가 순서대로 등차수열을 가지고 있어야 된다.
		//완전 제곱수, 어떤 정수를 제곱한 수
		//dfs활용해야겠다.
		
		//dfs(rowDeffer, colDeffer, check , stringbuffer ){
		/**
		 * rowDeffer는 1부터 n-1까지
		 * colDeffer 1부터 m-1까지 
		 */
		

		
		//dfs 내에서 행, 열이 등차수열을 가지게 이중 for문을 돌며 sb에 넣어준다.
		
		//그리고 sb가 len이 1보다 크면 완전제곱인지 확인 후 HashSet<>에 넣어준다.
		//HashSet에서 가장 큰 값을 출력한다. o
		//HashSet에 값이 없을 때, -1출력  o
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				for(int y=0; y<n; y++) {
					for(int x=0; x<m; x++) {
						for(int s=0; s<4; s++) {
							dfs(i,j,new StringBuilder(),new boolean[n][m],y,x,s);
						}
					}
				}
		
			}
		}
		
		if(set.isEmpty()) {
			System.out.println(-1);
		}
		else {
			//값이 있을 때
			int max=0;
			for(int n:set) {
				if(max<n)
					max=n;
			}
			System.out.println(max);
			
			
			
		}
		 
		
		
	}
	public static void dfs(int yDif, int xDif, StringBuilder sb,boolean[][] check, int preY, int preX, int status) {
		if(preY>=n || preX>=m || preY<0 || preX<0 || check[preY][preX]) {
			return;
		}
		check[preY][preX]=true;
		sb.append(maps[preY].charAt(preX));
				
		if(sb.length()>=1) {
			int tempN=Integer.parseInt(sb.toString());
			if(isPerfectSqure(tempN)) {
				set.add(tempN);
			}
			
			if(sb.length()>n*m) {
				return;
			}
		}
		
		if(status==0) {
			dfs(yDif,xDif,sb,check,preY+yDif,preX+xDif,0);
		}
		else if(status==1) {
			dfs(yDif,xDif,sb,check,preY-yDif,preX-xDif,1);
		}
		else if(status==2) {
			dfs(yDif,xDif,sb,check,preY+yDif,preX-xDif,2);
		}
		else {
			dfs(yDif,xDif,sb,check,preY-yDif,preX+xDif,3);
		}
	
	}

	public static boolean isPerfectSqure(int num) {
		if(Math.sqrt(num)%1>0) {
			return false;
		}
		return true;
	}

}
