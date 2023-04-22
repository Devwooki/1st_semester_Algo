package study;
import java.io.*;
import java.util.*;

public class BOJ2668 {
    static int[] graph;
    static boolean[] visited;
    static int N, max = Integer.MIN_VALUE;
    static TreeSet<Integer> pq = new TreeSet<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());

        //인접행렬 초기화
        graph = new int[N+1];

        for(int i = 1 ; i <= N ; ++i){
            graph[i] =Integer.parseInt(br.readLine());
        }

        //bfs를 돌면서 가장 큰 사이클을 발견
        for(int i = 1 ; i <= N ; ++i){
            visited = new boolean[N+1];
            dfs2(i,i);
        }

        sb.append(pq.size() + "\n");

        for(int value : pq){
            sb.append(value + "\n");
        }
        System.out.println(sb);
    }
    //가설1
    //가장 큰 사이클을 찾기

    //가설 2.
    //사이클의 갯수 파악 - 사이클이 발생하는 번호를 pq에 넣기
    private static void dfs2(int now, int start) {
        //다음 방문할 노드가 안 간 곳이면
        if(!visited[graph[now]]){
            //방문 처리 후 다음 노드dfs 실시
            visited[graph[now]] = true;
            dfs2(graph[now], start);
        }

        //다음 방문할 노드가 원점이면 1개의 사이클
        if(graph[now] == start){
            //pq.offer(start);
            pq.add(start);
        }
    }

}