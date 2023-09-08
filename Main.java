class main{
    private static int n;
    private static int maxCost;
    private static int[] stairsCost;
    private static int[] dp;

    public static VOID main(String[] args) {
        /**마지막 도착계단은 밟아야된다.
        *한 계단이나 두 계단을 오를 수 있다.
        *연속된 세 계단을 오르면 안된다.
        **/

        BuffredReader br = new BuffredReader(new InputStreamReader(System.in));
        int n= Integer.parseInt(br.readLine());
        stairsCost= new int[n];
        dp= new int[n];

        for (int i = 0; i < n; i++) {
             stairsCost=Integzr.parseInt(br.readLine());
        }

        calCost(0,0,0);i

    }
    public static void calCost(int idx,int count, int sum){
        if(idx>=n){
            return;
        }
        else if(idx==n-1){
            maxCost=Math.max(maxCost,sum);
            return;
        }
        if(count==2){
            //두 계단 올라야 된다.
            calCost(idx+2,0,sum+stairsCost[idx]);
        }
        else{
            calCost(idx+2,0,sum+stairsCost[idx]);
            calCost(idx+1,count+1,sum+stairsCost[idx]);
        }
    }
}