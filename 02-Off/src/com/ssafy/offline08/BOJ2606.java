package com.ssafy.offline08;
import java.io.*;
import java.util.*;
public class BOJ2606 {
    static ArrayList[] list;
    static boolean[] visited;
    static int cnt = 0;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        list = new ArrayList[N+1];
        visited = new boolean[N+1];
        for(int i = 1 ; i <= N ; ++i){
            list[i] = new ArrayList<>();
        }
        int M = Integer.parseInt(br.readLine());
        for(int i = 0 ; i < M ; ++i){
            st  = new StringTokenizer(br.readLine()," ");
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            list[u].add(v);
            list[v].add(u);
        }
        //cntVirus(1);
        cntVirusBFS(1);
        System.out.println(cnt);
    }

    static void cntVirus(int now){
        if(visited[now]) return;

        visited[now] = true;
        for(int i = 0 ; i < list[now].size() ; ++i){
            int next = (int) list[now].get(i);
            if(visited[next]) continue;
            else{
                cntVirus(next);
                cnt++;
            }
        }
    }

    static void cntVirusBFS(int now){
        Queue<Integer> q = new LinkedList<>();

        q.add(now);

        while(!q.isEmpty()){
            int find = q.poll();
            visited[find] = true;
            for(int i = 0 ; i < list[now].size() ; ++i){
                int next = (int) list[now].get(i);

                if(visited[next]) continue;
                else{
                    q.add(next);
                    cnt++;
                }
            }
        }


    }
}
