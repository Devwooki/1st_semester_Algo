package study;

import java.io.*;
import java.util.*;

public class BOJ14938_BFS {
    static int N, M, R, result = Integer.MIN_VALUE;
    static int[] itemAmount;
    static List<Node>[] areas;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken()); //노드 수
        M = Integer.parseInt(st.nextToken()); //최대 이동거리
        R = Integer.parseInt(st.nextToken()); //간선 수

        itemAmount = new int[N];

        //아이템수를 입력받는다.
        itemAmount = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        areas = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            areas[i] = new ArrayList<>();
        }

        //지역 초기화
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken())-1;
            int v = Integer.parseInt(st.nextToken())-1;
            int w = Integer.parseInt(st.nextToken());

            areas[u].add(new Node(v, w));
            areas[v].add(new Node(u, w));
        }

        //지역별로 탐색을 실시해 최대로 얻을 수 있는 아이템 가지수를 구하자
        for (int i = 0; i < N; i++) {
            bfs(i);
        }

        System.out.println(result);
    }

    private static void bfs(int cur) {
        Queue<Node> q = new LinkedList<>();
        boolean[] visited = new boolean[N];
        int[] distance = new int[N]; //거리정보를 저장할 배열

        //모두 최대값으로 놓고 현재 위치의 거리는 0으로한다.
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[cur] = 0;

        q.offer(new Node(cur, 0));

        while(!q.isEmpty()){
            Node now = q.poll();

            //현재 지역과 인접한 지역들을 체크한다.
            for(Node near : areas[now.end]){

                //이동한 거리가 M보다 크면 의미가 없다
                if(now.weight + near.weight > M) continue;

                distance[near.end] = Math.min(distance[near.end], near.weight + now.weight);
                //필터링이 완료 되었으면 방문배열 체크, 및 추가로 탐색을 실시한다
                visited[near.end] = true;
                q.offer(new Node(near.end, near.weight + now.weight));
            }
        }

        int getItem = getSum(distance);
        result = Math.max(getItem, result);
    }

    //시작점부터 도착지 까지의 길이가 이동범위보다 작으면 아이템을 파밍할 수 있다.
    private static int getSum(int[] distance) {
        int sum = 0;
        for (int i = 0; i < N; i++) if(distance[i] <= M ) sum += itemAmount[i];
        return sum;
    }

}

class Node{
    int end;
    int weight;

    public Node(int end, int weight) {
        this.end = end;
        this.weight = weight;
    }
}