import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_17135 {
	
	static LinkedList<int[]> enemyList=new LinkedList<int[]>();
	static int n;
	static int m;
	static int d;
	static int hitCount=0;
	
	public class Pos implements Cloneable{
		int y;
		int x;
	}

	/**
	 * 크기가 N x M 인 격자판
	 * 우선순위
	 * 1) 거리가 D이하인 적 중 가장 가까운 적
	 * 2) 가장 왼쪽에 있는 적부터
	 * 
	 * 주의사항
	 * 1) 궁수가 동시에 같은 적을 공격할 수 있다.
	 * 2) 공격받은 적은 사라진다. 
	 * 3) 궁수는 N+1행에 위치한다.
	 * 4) 공격이후, 적이 아래로 한칸 이동! 성이 있는 칸에 이동한 경우, 게임에서 제외된다.
	 */
	
	public static void main(String[] args) {
		
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));){
			StringTokenizer st=new StringTokenizer(br.readLine());
			n=Integer.parseInt(st.nextToken()); 
			m=Integer.parseInt(st.nextToken()); 
			d=Integer.parseInt(st.nextToken()); //공격거리제한
			
			for(int i=0; i<n; i++) {
				st=new StringTokenizer(br.readLine());
				for(int j=0; j<m; j++) {
					if(st.nextToken().equals("1")) {
						enemyList.add(new int[] {i,j});
					}
				}
			}
			
			//적이 다 사라지면 끝!!
			//모든 경우에 대해서 아쳐를 배치해야 된다.
			for(int i=0; i<m; i++) {
				for(int j=i+1; j<m; j++) {
					for(int k=j+1; k<m; k++) {
						hitCount=Math.max(attack(new int[] {i,j,k},copyList()),hitCount);		
					}
				}
			}
			
			System.out.println(hitCount);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	public static int attack(int[] archersPos, LinkedList<int[]> enemyList) {
		
		PriorityQueue<Integer> remoableIdx=new PriorityQueue<Integer>(Collections.reverseOrder());
		int tempHitCount=0;
		
		while(!enemyList.isEmpty()) {
			for(int i=0; i<3; i++) {
				int closeEnemyIdx=findCloseEnemy(new int[] {n,archersPos[i]},enemyList);
				if(!remoableIdx.contains(closeEnemyIdx)) {
				remoableIdx.add(closeEnemyIdx);//궁수의 위치
				}
			}
			
			while(!remoableIdx.isEmpty()) {
				enemyList.remove(remoableIdx.poll());
				tempHitCount++;
			}		
			moveEnemy(enemyList);
		}
		
		return tempHitCount;
		
	}
	public static void moveEnemy(LinkedList<int[]> enemyList) {
		
		for(int i=0; i<enemyList.size(); i++) {
			int[] enemyPos=enemyList.get(i);
			if(enemyPos[0]<n)
			enemyList.set(i,new int[] {enemyPos[0]+1,enemyPos[1]});
			else
			enemyList.remove(i);	
		}
	}
	
	public static int findCloseEnemy(int[] archerPos, LinkedList<int[]> enemyList) {
		
		int idx=0;
		int dist=Integer.MAX_VALUE;
		
		for(int i=0; i<enemyList.size(); i++) {
			int curDist=calDistance(archerPos,enemyList.get(i));
			
			if(dist>curDist && d>=curDist) {
				dist=curDist;
				idx=i;
			}
		}
		return idx;
	}
	public static int calDistance(int[] pos1, int[] pos2) {
		
		return Math.abs(pos1[0]-pos2[0])+Math.abs(pos1[1]-pos2[1]);
	}
	public static LinkedList<int[]> copyList() {
		LinkedList<int[]> copy=new LinkedList<int[]>();
		
		for(int[] enemy:enemyList) {
			copy.add(enemy);
		}
		
		return copy;
	}
	
}
