package com.ssafy.offline10;
import java.io.*;
import java.util.*;
public class BOJ11403 {
    static ArrayList<Integer>[] map2;
    static int[][] map, result;
    static int[] visited;
    static int N, cnt=0;

    public static void main(
            String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        map2 = new ArrayList[N];
        for(int i = 0 ; i < N ; ++i){
            map2[i] = new ArrayList<>();
        }

        for(int i = 0 ; i < N ; ++i){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < N ; ++j){
                if(Integer.parseInt(st.nextToken()) == 1)
                    map2[i].add(j);
            }
        }
        result = new int[N][N];

//        for(int i = 0 ; i < N ; ++i){
//            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
//        }

        for(int i = 0 ; i < N ; ++i){
            visited = new int[N];
            dfs(i, false);
            for(int j = 0 ; j < N ; ++j){
                result[i][j] = visited[j];
            }
        }

        for(int i = 0 ; i < N ; ++i){
            for(int j = 0 ; j < N ; ++j){
                sb.append(result[i][j] + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
    static void dfs(int node, boolean isFirst) {
        if(isFirst){
            visited[node] = 1;
        }

        for(int i = 0 ; i < map2[node].size(); ++i){
            int nextLoc = map2[node].get(i);

            if(visited[nextLoc] == 1) continue;
            dfs(nextLoc, true);
        }
    }

}
