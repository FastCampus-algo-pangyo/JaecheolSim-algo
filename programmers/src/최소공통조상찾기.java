import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class 최소공통조상찾기 {
    public static class Node{
        int to;
        int dist;

        Node(int t, int d){
            to = t;
            dist = d;
        }
    }

    static int[] parent;
    static int[] deepth;
    static int[] parentDist;
    static LinkedList<Node> tree[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int nodeN = Integer.parseInt(br.readLine());
        tree = new LinkedList[nodeN + 1];
        parent = new int[nodeN + 1];
        deepth = new int[nodeN + 1];
        parentDist = new int[nodeN + 1];

        for (int i = 1; i <= nodeN; i++) {
            tree[i] = new LinkedList<>();
        }

        for (int i = 0; i < nodeN - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());
            tree[node1].add(new Node(node2, distance));
            tree[node2].add(new Node(node1, distance));
        }

        int queryN = Integer.parseInt(br.readLine());
        makeInfo(1, 1, 0, 0);

        for (int i = 0; i < queryN; i++) {
            st = new StringTokenizer(br.readLine());
            int queryNode1 = Integer.parseInt(st.nextToken());
            int queryNode2 = Integer.parseInt(st.nextToken());

            sb.append(lcs(queryNode1, queryNode2) + "\n");
        }

        System.out.println(sb.toString());

    }
    public static void makeInfo(int cur, int par, int deep, int dist){
        parent[cur] = par;
        deepth[cur] = deep;
        parentDist[cur] = dist;

        for(Node node : tree[cur]){
            if(parent[node.to] == cur)
                continue;
            makeInfo(node.to, cur, deep+1, node.dist);
        }
    }
    public static int lcs(int a, int b){
        int dist = 0;

        while(deepth[a] < deepth[b]) {
            b = parent[b];
            dist += parentDist[b];
        }

        while(deepth[a] > deepth[b]) {
            a = parent[a];
            dist += parentDist[a];
        }

        while(a != b){
            a = parent[a];
            b = parent[b];
            dist += (parentDist[a] + parentDist[b]);
        }

        return dist;
    }

}