import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main_1082 {
	static int n;
	static int m;
	
	
	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		n=Integer.parseInt(br.readLine());
		int[] numberCost=new int[n]; 
		StringTokenizer st=new StringTokenizer(br.readLine());
		PriorityQueue<Integer>[] maxCost=new PriorityQueue[51];
		
		for(int i=0; i<=50; i++) {
			maxCost[i]=new PriorityQueue(Collections.reverseOrder());
		}
		
		for(int i=0; i<n; i++) {
			numberCost[i]=Integer.parseInt(st.nextToken());
		}
		m=Integer.parseInt(br.readLine());
		
		// --- 입출력 처리 끝 ---- //
		
		/**
		 * 가진 돈 만큼 for문
		 * i가 가진 돈
		 * numberCost가 i보다 작거나 같으면 
		 * number를 추가
		 * 
		 */
		for(int i=1; i<=m; i++) {
			if(i>=2)
			maxCost[i]=findBig(maxCost[i], maxCost[i-1]);
			for(int j=0; j<n; j++) {
				if(i>=numberCost[j]) {
				PriorityQueue<Integer> newPq=copyPq(maxCost[i-numberCost[j]]);
				newPq.add(j);
				maxCost[i]=findBig(newPq, maxCost[i]);
				}
			}
			
		}
		maxCost[m].stream().forEach(System.out::print);
		
		
	}
	public static PriorityQueue<Integer> findBig(PriorityQueue<Integer> one, PriorityQueue<Integer> two){
		
		//시작인덱스가 0인걸 없애야 된다.
		
		
		if(one.size()> two.size()) {
			return copyPq(one);
		}
		else if(two.size()>one.size()) {
			return copyPq(two);
		}
		else {
		StringBuilder strOne=new StringBuilder();
		StringBuilder strTwo=new StringBuilder();
		one.stream().map(x->String.valueOf(x)).forEach(x->strOne.append(x));
		two.stream().map(x->String.valueOf(x)).forEach(x->strTwo.append(x));
		
		
		if(strOne.toString().compareTo(strTwo.toString())>0) {
			return copyPq(one);
		}
		else {
			return copyPq(two);
		}
		
		
		}
		
		
		
	
		
		
	}
	public static PriorityQueue<Integer> copyPq(PriorityQueue<Integer> source) {
		PriorityQueue<Integer> newPq=new PriorityQueue<Integer>(Collections.reverseOrder());
		if(!source.isEmpty() &&  source.peek()==0) {
			newPq.add(0);
			return newPq;
		}
		source.stream().forEach(x->newPq.add(x));
		return newPq;
	}
}