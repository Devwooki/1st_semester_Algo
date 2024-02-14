package second.contest;
import java.io.*;
import java.util.*;


public class BOJ1916 {
    static int N, M, start, end;
    static int[] distance;
    static LinkedList<Vertex>[] citys;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        distance = new int[N];
        Arrays.fill(distance, Integer.MAX_VALUE);

        //비용이
        citys = new LinkedList[N];
        for (int i = 0; i < N; i++) {
            citys[i] = new LinkedList<>();
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken()) - 1;
            int u = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());

            citys[v].add(new Vertex(u, w));
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken()) - 1;
        end = Integer.parseInt(st.nextToken()) - 1;

        getDistance();

        System.out.println(distance[end]);
    }

    public static void getDistance() {
        PriorityQueue<Vertex> pq = new PriorityQueue<>(new Comparator<Vertex>() {
            @Override
            public int compare(Vertex o1, Vertex o2) {
                return Integer.compare(o1.weight, o2.weight);
            }
        });

        boolean[] visited = new boolean[N];

        pq.offer(new Vertex(start, 0));
        distance[start] = 0;

        while (!pq.isEmpty()) {
            Vertex now = pq.poll();

            if(visited[now.cityNum]) continue;

            visited[now.cityNum] = true;

            for (Vertex next : citys[now.cityNum]) {
                //다음 도시까지의 거리 vs 현재까지 거리 + 다음 도시 거리
                if(distance[next.cityNum] > distance[now.cityNum] + next.weight){
                    distance[next.cityNum] = distance[now.cityNum] + next.weight;
                    pq.offer(new Vertex(next.cityNum, distance[next.cityNum]));
                }
            }
        }
    }

    static class Vertex{
        int cityNum;
        int weight;

        public Vertex(int cityNum, int weight) {
            this.cityNum = cityNum;
            this.weight = weight;
        }
    }
}
