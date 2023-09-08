import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2579 {

    private static int[] stairsCost;
    private static int[][] dp;
    private static int n;
    private static int maxCost;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        stairsCost = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            stairsCost[i] = Integer.parseInt(br.readLine());
        }

        dp = new int[n + 1][3];
        dp[0][0]=-1;
        calCost(0, 0, 0);
        //처음에 두번 뛸수도 있다.
        System.out.println(maxCost);
    }

    public static void calCost(int idx, int count, int sum) {

        if (idx == n) {
            maxCost = Math.max(sum, maxCost);
            return;
        }
        if (dp[idx][count] >= sum)
            return;

        dp[idx][count] = sum;

        if (count == 0 || count==1) {
            if (idx + 1 <= n)
                calCost(idx + 1, count + 1, sum + stairsCost[idx + 1]); //바로 옆 계단을 밟으면 count
        }
        if (idx + 2 <= n)
            calCost(idx + 2, 1, sum + stairsCost[idx + 2]); //옆옆 계단을 밟으면 count를 초기화
    }
}