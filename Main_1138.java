import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1138 {
    static int n;
    static Stack<Integer> stack;
    static ArrayList<Integer> list=new ArrayList<>();


    public static void main(String[] args) throws  Exception {
        inputHandling();
        while(!stack.isEmpty()){
            list.add(stack.pop(),n--);
        }

        list.forEach(x->System.out.print(x+" "));

    }
    public static void inputHandling() throws  Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        n=Integer.parseInt(br.readLine());
        stack=new Stack<>();
        StringTokenizer st=new StringTokenizer(br.readLine());

        //input 받는
        for(int i=0; i<n; i++) {
            stack.push(Integer.parseInt(st.nextToken()));
        }
    }
}

