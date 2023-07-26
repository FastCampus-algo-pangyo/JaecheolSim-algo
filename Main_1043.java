import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_1043 {

	static int n,m;
	static StringTokenizer st;
	static BufferedReader br;
	static ArrayList<Integer>[] partyList;
	static ArrayList<Integer> knowTruePeople;
	static boolean[] mustTellTruth;
	
	public static void main(String[] args)throws IOException{
		br=new BufferedReader(new InputStreamReader(System.in));
		
		
		st=new StringTokenizer(br.readLine());//n,m 받기
		n=Integer.parseInt(st.nextToken()); m=Integer.parseInt(st.nextToken());
		partyList=new ArrayList[m];
		knowTruePeople=new ArrayList();
		mustTellTruth=new boolean[n+1];
		
		st=new StringTokenizer(br.readLine()); //진실 아는 사람 수 와 그들이 각각 몇 번인지 번호 받기
		int numOfPeopleKnow=Integer.parseInt(st.nextToken()); 
		
		for(int i=0; i<numOfPeopleKnow; i++) {
			int peopleKnowNumber=Integer.parseInt(st.nextToken());
			knowTruePeople.add(peopleKnowNumber);
		}
		//해쉬맵을 통해서 파티에 온 사람 번호가 진실을 아는 사람인지 O(1)로 찾게 하기 위해 설정 과정
		
		for(int i=0; i<m; i++) {
			st=new StringTokenizer(br.readLine());
			partyList[i]=new ArrayList<Integer>();
			int numOfPeople=Integer.parseInt(st.nextToken());
			for(int j=0; j<numOfPeople; j++) {
				int peopleNumber=Integer.parseInt(st.nextToken());
				partyList[i].add(peopleNumber);
			}
		}
		
		//입력 출력 처리 -----------------------------
		
		int[][] partGraph=makeGraph(partyList);
		makeArrayToTellLie(partGraph);
		
		int numOfPartyToTell=0;
		for(int i=0; i<m; i++) {
			numOfPartyToTell++;
			for(int number:partyList[i]) {
				if(mustTellTruth[number]) {
					numOfPartyToTell--;
					break;
				}
			}
		}
		
		System.out.println(numOfPartyToTell);
		
		
	}
	public static int[][] makeGraph(ArrayList<Integer>[] list) {	
		int[][] graph=new int[n+1][n+1];
		
		
		for(ArrayList<Integer> eachList: list) {
			for(int i=0; i<eachList.size(); i++) {
				int startIdx=eachList.get(i);
				for(int j=i+1; j<eachList.size(); j++) {
					int endIdx=eachList.get(j);
					
					graph[startIdx][endIdx]=1;
					graph[endIdx][startIdx]=1;
				}
			}
		}
		
		return graph;
	}
	
	public static void makeArrayToTellLie(int[][] partGraph) {
		for(int knowTruePeopleNumber:knowTruePeople) {
			dfs(knowTruePeopleNumber,partGraph);
		}
	}
	public static void dfs(int preNumber, int[][] partGraph) {
		mustTellTruth[preNumber]=true;
		for(int i=1; i<=n; i++) {
			if(!mustTellTruth[i] && partGraph[preNumber][i]==1) {
				dfs(i,partGraph);
			}
		}
	}
}
