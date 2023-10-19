import java.util.Arrays;

public class 리코쳇로봇 {

    /* dfs
    dfs를 사용할 거다. 상, 하, 좌, 우 4방향으로 움직일 거고,
    목표인 G에 도착한 순간의 이동횟수를 전역 변수인 min에 저장하게 된다.
    min은 dfs가 진행되면서 G에 도착하는 모든 경우에 업데이트가 되고
    min 보다더 작은 이동횟수일 경우만 업데이트가 된다.
    */

    /* dp
    로봇이 어떤 위치에서 멈췄다, 그때의 위치를 [3,4]라고 할 때
    [3,4]에 오기 위해선 12번의 이동이 필요할 수 도, 20번의 이동이 필요할 수 도 있다.
    하지만, 최소 움직임을 min[3][4]에 저장하게 되면, 20번 움직여서 왔을 때, 12번만에 이동한 게 있으니
    20번 움직인 건 굳이 dfs로 더 이동할 필요가 없어진다.
    */
    static int[][] dp;
    static String[] gBoard;

    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) {
        리코쳇로봇 sol = new 리코쳇로봇();
        System.out.println(sol.solution(new String[]{"...D..R", ".D.G...", "....D.D", "D....D.", "..D...."}));
    }

    public int solution(String[] board) {
        int yLen = board.length;
        int xLen = board[0].length();

        int[] startPos = new int[]{0, 0};
        gBoard = board;
        dp = new int[yLen][xLen];

        for (int i = 0; i < yLen; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        for (int i = 0; i < yLen; i++) {
            for (int j = 0; j < xLen; j++) {
                if (board[i].charAt(j) == 'R') startPos = new int[]{i, j};
            }
        }
        dfs(startPos, 0);

        return min == Integer.MAX_VALUE ? -1 : min;
    }

    public void dfs(int[] curPos, int moveCount) {

        int curY = curPos[0];
        int curX = curPos[1];

        if (dp[curY][curX] <= moveCount) {
            return;
        }

        dp[curY][curX] = moveCount;

        if (gBoard[curY].charAt(curX) == 'G') {
            min = Math.min(min, moveCount);
        }

        dfs(상(curPos), moveCount + 1);
        dfs(하(curPos), moveCount + 1);
        dfs(좌(curPos), moveCount + 1);
        dfs(우(curPos), moveCount + 1);

    }

    //벽이거나 장애물 전에 멈춘다.
    public int[] 상(int[] curPos) {
        int[] newPos = new int[]{curPos[0], curPos[1]};

        while (인덱스범위다(newPos) && 벽이아니다(newPos)) {
            newPos[0] += 1;
        }
        newPos[0] -= 1;

        return newPos;
    }

    public int[] 하(int[] curPos) {
        int[] newPos = new int[]{curPos[0], curPos[1]};

        while (인덱스범위다(newPos) && 벽이아니다(newPos)) {
            newPos[0] -= 1;
        }
        newPos[0] += 1;

        return newPos;
    }

    public int[] 우(int[] curPos) {
        int[] newPos = new int[]{curPos[0], curPos[1]};

        while (인덱스범위다(newPos) && 벽이아니다(newPos)) {
            newPos[1] += 1;
        }
        newPos[1] -= 1;

        return newPos;
    }

    public int[] 좌(int[] curPos) {
        int[] newPos = new int[]{curPos[0], curPos[1]};

        while (인덱스범위다(newPos) && 벽이아니다(newPos)) {
            newPos[1] -= 1;
        }
        newPos[1] += 1;

        return newPos;
    }

    public boolean 벽이아니다(int[] pos) {
        return gBoard[pos[0]].charAt(pos[1]) != 'D';
    }

    public boolean 인덱스범위다(int[] pos) {
        return 0 <= pos[0] && pos[0] < gBoard.length && 0 <= pos[1] && pos[1] < gBoard[0].length();
    }


}
