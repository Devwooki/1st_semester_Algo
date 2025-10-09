package JobChange.Graph;

import java.io.*;
import java.util.*;

public class BOJ11725 {
    static int N;
    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    static int[] answer;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N+1];
        visited = new boolean[N+1];
        answer = new int[N+1];
        for(int i = 1 ; i <= N ; ++i){
            graph[i] = new ArrayList<>();
        }

        for(int i = 1 ; i < N ; ++i){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph[u].add(v);
            graph[v].add(u);
        }

        dfs(1);

        for(int i = 2 ; i <= N ; ++i)
            sb.append(answer[i]+ "\n");

        System.out.println(sb);
    }

    private static void dfs(int node){


        visited[node] = true;
        for(Integer cur : graph[node]){
            if(!visited[cur]){
                answer[cur] = node;
                dfs(cur);
            }
        }

    }
}
