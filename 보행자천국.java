class 보행자천국 {
    int MOD = 20170805;
    static int Y리미트;
    static int X리미트;
    static int[][][] 경우의수;
    public int solution(int m, int n, int[][] cityMap) {
        경우의수=new int[m][n][2];
        Y리미트=m;
        X리미트=n;
        경우의수[0][0][0]=1;
        경우의수[0][0][1]=1; //출발지점은 1로 초기화


        System.out.println("0번 인덱스가 위족에서 온 차랑 \n 1번 인덱스가 왼쪽에서 온 차량");

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n ; j++) {
                System.out.print(cityMap[i][j]+"     ");
            }
            System.out.println();
        }

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(가능한인덱스(i-1,j)){
                    //위에서 오는 차량
                    경우의수[i][j][0]+=도로상태에따른업데이트(cityMap[i-1][j], true, i-1, j);
                    경우의수[i][j][0]%=MOD;
                }
                if(가능한인덱스(i,j-1)){
                    //왼쪽에서 오는 차량
                    경우의수[i][j][1]+=도로상태에따른업데이트(cityMap[i][j-1], false, i, j-1);
                    경우의수[i][j][1]%=MOD;
                }
                System.out.print("["+경우의수[i][j][0]+","+경우의수[i][j][1]+"]  ");
            }
            System.out.println();
        }


        return (경우의수[m-1][n-1][0]+경우의수[m-1][n-1][1])%MOD;
    }
    public boolean 가능한인덱스(int y, int x){
        if( 0<= y && y<Y리미트 && 0<=x && x<X리미트 ){
            return true;
        }
        return false;
    }
    public int 도로상태에따른업데이트(int 도로상태, boolean 위에서온차량인지, int y, int x){

        switch (도로상태){
            case 0:
                if(y==0)
                    return 경우의수[y][x][1];
                else if(x==0)
                    return 경우의수[y][x][0];
                else
                    return 경우의수[y][x][0]+경우의수[y][x][1];
            case 1:
                return 0;
            case 2:
                if(위에서온차량인지){
                    return 경우의수[y][x][0];
                }
                else{
                    return 경우의수[y][x][1];
                }
        }

        return 0;
    }


    //가능한 경로 수를 MOD로 나눈 나머지 값을 출력
    /*
    자동차는 오른쪽 또는 아래 방향으로 한 칸 씩 이동
    0인 경우에는 자동차가 자유롭게 지나갈 수 있다.
    1인 경우에는 자동차 통행이 금지되어 지나갈 수 없다.
    2인 경우는 보행자 안전을 위해 좌회전이나 우회전이 금지된다.
    (왼쪽에서 오던 차는 오른쪽으로만, 위에서 오던 차는 아래쪽으로만 진행 가능하다)
    */
}