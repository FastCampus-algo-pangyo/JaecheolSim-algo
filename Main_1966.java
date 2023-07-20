import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1966{

    public static void main(String[] args) throws IOException {

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
        int t=Integer.parseInt(br.readLine());




        for(int i=0; i<t; i++){
            StringTokenizer st=new StringTokenizer(br.readLine());
            int n=Integer.parseInt(st.nextToken()); //문서 개수
            int m=Integer.parseInt(st.nextToken()); //찾고자 하는 문서 인덱스

            int count=0;

            Deque<Integer> dq=new LinkedList<>();
            PriorityQueue<Integer> pq=new PriorityQueue<>(Collections.reverseOrder());

            st=new StringTokenizer(br.readLine());

            for(int j=0; j<n; j++){
                int value=Integer.parseInt(st.nextToken());
                dq.add(value);
                pq.add(value);
            }

            /*
            1) 맨 앞이 찾고자 하는 문서 일때 //가장 크면, 끝이고, 아니면 다시 뺏다가 뒤로 넣는다.
            맨 앞이 가장 큰 거면 count+1 이 답!  
            맨 앞이 큰 게 아니면 m=n-count-1;  //맨뒤로 보낸다.
            2) 아닐때 //문서를 빼야된다. 그 문서가 가장 크면 아예 빼버리고, 아니면 빼고 뒤로 넣는다.
            맨 앞이 가장 큰 거면 count++ , m-- //문서를 빼고, 찾고자 하는 문서 인덱스를 앞당긴다. 
            맨 앞이 큰 게 아니면 m-- 
            */

            while(true){

                if(m==0){
                    if(dq.getFirst()==pq.peek()){
                        bw.append((count+1)+"\n");
                        break;
                    }
                    else{
                        int temp=dq.poll();
                        dq.add(temp);
                        m=n-count-1;
                    }
                }
                else{
                    if(dq.getFirst()==pq.peek()){
                        dq.poll();
                        pq.poll();
                        count++;
                        m--;
                    }
                    else{
                        int temp=dq.poll();
                        dq.add(temp);
                        m--;
                    }

                }
            }

            bw.flush();



        }
    }
}