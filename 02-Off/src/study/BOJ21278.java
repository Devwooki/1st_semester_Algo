package study;

import java.io.*;
import java.util.*;

//풀기전에 플로이드 워셜 다시 정리

/*
* 음수 사이클이 없는 그래프 내에서 모든 정점에서 모든 노드로 가는 간선비용을 최소화한 알고리즘
*
* */
public class BOJ21278 {
    static int N, M;
    static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken()); //건물 수
        M = Integer.parseInt(st.nextToken()); //간선 수

        graph = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken())-1;
            int e = Integer.parseInt(st.nextToken());
            graph[s].add(e);
            graph[e].add(s);
        }
    }
}
