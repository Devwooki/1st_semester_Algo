package com.ssafy.offline19;
import java.io.*;
import java.util.*;
public class BOJ2623 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ArrayList<Integer>[] graph = new ArrayList[N+1];
        int[] inDegree = new int[N+1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i = 0 ; i < M ; ++i) {
            st = new StringTokenizer(br.readLine());
            int amount = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());
            for (int j = 0; j < amount - 1; ++j) {
                int end = Integer.parseInt(st.nextToken());
                graph[from].add(end);
                inDegree[end]++;
                from = end;
            }
        }

        Queue<Integer> q = new LinkedList<>();
        for(int i = 1 ; i<= N ; ++i){
            if(inDegree[i] == 0) q.offer(i);//진입 차수가 0인 것들 넣음
        }

        while(!q.isEmpty()){
            int cur = q.poll();
            sb.append(cur + "\n");//0인 것들은 출력하게 stringBuilder에 추가하고

            for(int next : graph[cur]){
                inDegree[next] -= 1;
                if(inDegree[next] == 0) q.offer(next);
            }
        }
        int cnt = 0;
        for(int i = 1 ; i <= N ; ++i){
            if(inDegree[i] != 0) cnt++;
        }
        if(cnt == 0) System.out.println(sb);
        else System.out.println(0);

    }

}
