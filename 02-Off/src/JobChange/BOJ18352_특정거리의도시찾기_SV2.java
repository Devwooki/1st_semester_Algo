package JobChange;

import java.io.*;
import java.util.*;

public class BOJ18352_특정거리의도시찾기_SV2 {
    static int N, M, K, X;
    static ArrayList<Integer>[] graph;
    static int[] dist;
    public static void main(String[] args) throws IOException {
        BufferedReader br=  new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken())-1;

        graph = new ArrayList[N];
        for(int i = 0 ; i < N ; ++i){
            graph[i] = new ArrayList<>();
        }

        for(int i = 0 ; i < M ; ++i){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken())-1;
            int v = Integer.parseInt(st.nextToken())-1;

            //단방향 그래프
            graph[u].add(v);
        }

        //bfs(X);
        dijkstra(X);
    }
    private static void bfs(int x){
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        //vertex, dist
        Queue<int[]> q = new ArrayDeque<>();
        boolean[] visited = new boolean[N];

        visited[x] = true;
        q.offer(new int[]{x, 0});


        while(!q.isEmpty()){
            int[] cur = q.poll();

            if(cur[1] == K) {
                pq.offer(cur[0]);
                continue;
            }

            //현재 노드와 인접한 노드를 방문한다.
            for(int next : graph[cur[0]]){
                if(visited[next]) continue;

                visited[next] = true;
                q.offer(new int[]{next, cur[1]+ 1});
            }
        }

        System.out.println("BFS 풀이");
        System.out.println(pq.isEmpty() ? "-1" : getAnswer(pq));
    }

    private static void dijkstra(int x){
        //vertex, dist
        PriorityQueue<Integer> dijkQ = new PriorityQueue<>();
        int[] dist = new int[N];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[x] = 0;
        dijkQ.offer(x);

        while(!dijkQ.isEmpty()){
            int cur = dijkQ.poll();

            for(int next : graph[cur]){
                int nextDist = dist[cur] + 1; //현재거리에서 + 1 한게 next까지 거리,

                if(nextDist < dist[next]){
                    dist[next] = nextDist;
                    dijkQ.offer(next);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < dist.length ; ++i){
            if(dist[i] == K) sb.append((i+1) + "\n");
        }

        System.out.println("DIJKSTRA 풀이");
        System.out.println(sb.toString());
    }

    private static String getAnswer(PriorityQueue<Integer> pq){
        StringBuilder sb = new StringBuilder();

        while (!pq.isEmpty()) {
            sb.append((pq.poll()+1));
            sb.append("\n");
        }
        return sb.toString();
    }

}
