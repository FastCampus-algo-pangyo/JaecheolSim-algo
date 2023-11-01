import java.util.*;

class Yanolja_3 {

    public class Team implements  Comparable<Team>{
        int teamNum;
        int winCount;

        public Team(int teamNum, int winCount) {
            this.teamNum = teamNum;
            this.winCount = winCount;
        }

        @Override
        public int compareTo(Team o) {
            if(winCount == o.winCount){
                return teamNum - o.teamNum;
            }

            return o.winCount-winCount;
        }
    }
    public void sovle(int n, int[] winCount){
        //승리 횟수가 클수록, 팀 번호가 작을 수록 우선순위가 크다.
        PriorityQueue<Team> pq=new PriorityQueue<>();

        //팀 클래스에는
        // 팀 번호랑 승리 횟수 필드가 있습니다.

        for(int i=0; i<winCount.length; i++){
            pq.add(new Team(i+1,winCount[i]));
        }

        int 최대이길수있는횟수 = 0;
        int tempN=n;

        while(tempN>1){
            tempN/=2;
            최대이길수있는횟수++;
        }

        int[] tree = new int[(int)Math.pow(2, 최대이길수있는횟수+1) -1]; //0번부터 시작, 0이 root인덱스
        tree[0] = pq.poll().teamNum; //루드 노드에 우선순위가 가장 큰 팀 번호를 집어넣었습니다.
        Deque<Integer> dq = new LinkedList<>();
        dq.add(1);

        //루트 노드부터 다음 레벨의 왼쪽 노드부터 숫자가 채워진다. (문제에서 주어진 트리처럼 구성)
        for(int i=1; i<tree.length; i+=2){
            int left=i;
            int right=i+1;
            int parent =  (int)(Math.ceil((double) left/2) - 1);

            int 우선순위최대넘버 = pq.poll().teamNum;

            //부모 팀 번호 와 큐에 있는 가장 우선순위가 큰 원소 중 비교합니다.
            if(tree[parent] < 우선순위최대넘버){
                tree[left] = tree[parent];
                tree[right] = 우선순위최대넘버;
            }
            else{
                tree[left] = 우선순위최대넘버;
                tree[right] = tree[parent];
            }
        }
        //리프 노드만 retrun합니다. 다음 구문이
        Arrays.stream(Arrays.copyOfRange(tree, tree.length - n, tree.length)).forEach(System.out::println);

    }

//    public static void main(String[] args) {
//        Solution sol=new Solution();
//        sol.sovle(8, new int[]{0,0,3,2,1,1,0,0});
//    }





}