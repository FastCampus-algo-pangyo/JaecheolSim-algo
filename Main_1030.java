import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_1030 {

	static int s,N,K,R1,R2,C1,C2;
	
	public static void main(String[] args) throws IOException {
		inputHandle();
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		for(int i=R1; i<=R2; i++) {
			for(int j=C1; j<=C2; j++) {
				if(isBlack(i, j, s)) {
					bw.append("1");
				}
				else {
					bw.append("0");
				}
			}
			bw.append("\n");
		}
		bw.flush();
		bw.close();
		
		//s초 후 I 계산
		
	}
	public static boolean isBlack(int y, int x, int tempS) {
		if(tempS==0) {
			return false;
		}
		//가운데가 검은색인지 검사하고
		//y,x가 어디 방향에 해당하는지에 따라
		//분할해서 검은색인지 판별
		double startIdx=(N-K)*0.5 * Math.pow(N, tempS-1);
		double endIdx=startIdx+K*Math.pow(N, tempS-1);
		if(startIdx<=y&& y<endIdx && startIdx<=x && x<endIdx) {
			return true;
		}
		else {
			y=y%(int)Math.pow(N, tempS-1);
			x=x%(int)Math.pow(N, tempS-1);
			return isBlack(y,x,tempS-1);
		}
	}
	
	
	
	
	
	public static void inputHandle() throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		s=Integer.parseInt(st.nextToken());	N=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken()); R1=Integer.parseInt(st.nextToken());
		R2=Integer.parseInt(st.nextToken()); C1=Integer.parseInt(st.nextToken());
		C2=Integer.parseInt(st.nextToken());
		                                                                                                                                                                                                      
	}
}
