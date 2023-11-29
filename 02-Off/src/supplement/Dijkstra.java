package supplement;
import java.io.*;
import java.util.*;


public class Dijkstra {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());

        ArrayList<Node>[] connections = new ArrayList[n + 1];
        for(int i = 1 ; i <= n ; i ++) {
            connections[i] = new ArrayList<>();
        }

        for(int i = 0 ; i < e ; i ++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            connections[x].add(new Node(y, z));
        }

        //거리를 저장할 배열 시작점의 거리는0이다
        int[] dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        boolean[] visited = new boolean[n+1];
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>(){
            @Override
            public int compare(Node o1, Node o2) {
                return o1.cost - o2.cost;
            }
        });
    }

    static class Node {
        int next, cost;
        public Node(int next, int cost) {
            this.next = next;
            this.cost = cost;
        }
    }
}
