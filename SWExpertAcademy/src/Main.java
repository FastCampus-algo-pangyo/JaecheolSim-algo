import java.io.*;
import java.util.StringTokenizer;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    int viewN;
    public void calView (int[] buildingHeight) {
        viewN = 0;
        int tempViewN = 0;
        for (int i = 2; i < buildingHeight.length-2; i++) {
            tempViewN = Math.min (calSideView(buildingHeight[i-2], buildingHeight[i-1], buildingHeight[i])
                    , calSideView(buildingHeight[i+2], buildingHeight[i+1], buildingHeight[i]));
            viewN += tempViewN ;
        }
    }
    public int calSideView (int twoSpacesAwayBuildingHeight, int oneSpacesAwayBuildingHeight, int curBuildingHeight) {
         int maxBuildingHeight = Math.max(twoSpacesAwayBuildingHeight, oneSpacesAwayBuildingHeight);
         return curBuildingHeight - maxBuildingHeight > 0 ? curBuildingHeight - maxBuildingHeight : 0 ;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int testCaseNum = 10;

        for (int i = 0; i < testCaseNum; i++) {
            int buildingNum = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            int[] buildingHeight = new int [buildingNum];
            for (int j = 0; j < buildingNum; j++) {
                buildingHeight[j] = Integer.parseInt(st.nextToken());
            }
            Main main = new Main();
            main.calView(buildingHeight);
            bw.append("#"+i+" "+main.viewN+"\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}