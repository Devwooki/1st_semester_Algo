package com.ssafy.offline10;

import java.io.*;
import java.util.*;
public class BOJ1697 {
    static int N, K;
    static boolean[] visited;
    static int cnt = 0;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        if( N == K){
            System.out.println(0);
            return;
        }

        visited = new boolean[100001];
        bfs();
    }

    static void bfs(){
        Queue<int[]> q = new LinkedList<>();

        q.offer(new int[]{N, 0});
        while(!q.isEmpty()){
            int temp[] = q.poll();
            if(temp[0] < 0 || temp[0] > 100000 || visited[temp[0]]) continue;

            visited[temp[0]] = true;

            //방문 안하고 범위안에 있어야함.
            if(temp[0] == K) {
                System.out.println(temp[1]);
                return;
            }
            //각 노드 방문 안했으면 큐에 넣음
            if(temp[0] - 1 >= 0 && !visited[temp[0] - 1]) q.offer(new int[]{temp[0] - 1, temp[1]+1});
            if(temp[0] + 1 < 100001 && !visited[temp[0] + 1]) q.offer(new int[]{temp[0] + 1, temp[1]+1});
            if(temp[0] * 2 < 100001 && !visited[temp[0] * 2]) q.offer(new int[]{temp[0] * 2, temp[1]+1});
        }
    }
}
