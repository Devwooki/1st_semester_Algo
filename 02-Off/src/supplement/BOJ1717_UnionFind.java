package supplement;

import java.io.*;
import java.util.*;

public class BOJ1717_UnionFind {
    static int N, M;
    static int[] p;
    // N : 서로소 집합의 수
    // M : 입력으로 주어지는 연산의 수
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        p = new int[N+1];
        for(int i = 0 ; i <= N ; ++i){
            p[i] = i;
        }
        for(int i = 0 ; i < M ; ++i){
            st= new StringTokenizer(br.readLine(), " ");
            int cmd = Integer.parseInt(st.nextToken());
            int a =Integer.parseInt(st.nextToken());
            int b =Integer.parseInt(st.nextToken());

            if(cmd == 0){
                union2(a,b);
            }else{
                sb.append( union2(a,b) ? "yes"  : "no").append("\n");
            }
        }
        System.out.println(sb);
    }

    static int findSet(int num){
        if(p[num]== num) return num;
        return p[num] = findSet(p[num]);
    }

    static void union(int a, int b){
        int aRoot = findSet(a);
        int bRoot = findSet(b);

        if(a != b){
            if(a > b) p[aRoot] = bRoot;
            else p[bRoot] = aRoot;
        }
    }

    static boolean checkUnion(int a, int b){
        return findSet(a) == findSet(b);
    }

    static boolean union2(int a, int b){
        int aRoot = findSet(a);
        int bRoot = findSet(b);
        if(aRoot == bRoot) return false;

        if(a != b){
            if(a > b) p[aRoot] = bRoot;
            else p[bRoot] = aRoot;
        }
        return true;
    }
}
