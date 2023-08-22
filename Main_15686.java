import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_15686 {
	
	static int n,m;
	static int min=Integer.MAX_VALUE;
	static ArrayList<int[]> chickenList;
	static	ArrayList<int[]> houseList;
	//r,c 1부터 시작
	//  치킨집 m개만 고르고 다 폐업시킬 때, 치킨 거리가 최소가 되는 해 구한다.
	// 0 빈칸 1 집 2 치킨집

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		
		chickenList=new ArrayList();
		houseList=new ArrayList();
		
		
		//집 위치와 치킨 집 위치를 다 저장한다.
		
		for(int i=1; i<=n; i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=1; j<=n; j++) {
				String curInform=st.nextToken();
				if(curInform.equals("2")) {
					chickenList.add(new int[] {i,j});
				}
				else if(curInform.equals("1")) {
					houseList.add(new int[] {i,j});
				}
			}
		}
		
		makeCombi(new ArrayList<Integer>(), 0);
		
		System.out.println(min);
	}
	public static void makeCombi(ArrayList<Integer> combination, int startIdx) {
		
		if(combination.size()>=m) {
			int sumChickenDist=0;
			int oneChickenDist;
			for(int j=0; j<houseList.size(); j++) {
				oneChickenDist=Integer.MAX_VALUE;
				for(int i=0; i<m; i++) {
					oneChickenDist=Math.min(oneChickenDist,calDistance(houseList.get(j), chickenList.get(combination.get(i))));
				}
				sumChickenDist+=oneChickenDist;
			}
			
			if(sumChickenDist<min) {
				min=sumChickenDist;
			}
			
			return;
		}
		
		for(int i=startIdx; i<chickenList.size(); i++) {
			combination.add(Integer.valueOf(i));
			makeCombi(combination,i+1);
			combination.remove(Integer.valueOf(i));
		}
	}
	public static int calDistance(int[] source, int[] dest) {
		return Math.abs(source[0]-dest[0])+Math.abs(source[1]-dest[1]);
	}

}
