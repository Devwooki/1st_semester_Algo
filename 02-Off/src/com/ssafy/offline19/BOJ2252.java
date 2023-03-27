package com.ssafy.offline19;

import java.io.*;
import java.util.*;
public class BOJ2252 {
    static int N, M;
    static ArrayList<Integer>[] graph;
    static int[] inDegree;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        inDegree = new int[N + 1];
        for (int i = 1; i <= N; ++i) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            inDegree[v]++; //진입 차수를 카운트 한다.
        }
        bfs();
    }

    static void bfs(){
        Queue<Integer> q = new LinkedList<>();
        for(int i = 1 ; i <= N ; ++i){
            //진입차수가 0인 정점(번호) 큐에 넣기
            if(inDegree[i] == 0) q.offer(i);
        }

        while(!q.isEmpty()){
            int cur = q.poll();
            sb.append(cur + " ");

            for(int next : graph[cur]){
                inDegree[next] -= 1;
                if(inDegree[next] == 0) q.offer(next);
            }
        }
        System.out.println(sb);
    }


}
