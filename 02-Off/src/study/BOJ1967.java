package study;

import com.ssafy.offline03.이현욱_SWEA1954_달팽이숫자;

import java.io.*;
import java.util.*;

public class BOJ1967 {
    static class Node{
        int end;
        int weight;

        public Node(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }
    }
    static int N, result = Integer.MIN_VALUE;
    static List<Node>[] tree;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        tree = new ArrayList[N+1];
        //트리 생성
        for (int i = 0; i < N; i++) {
            tree[i] = new ArrayList<>();
        }

        //트리 초기화
        for (int i = 0; i < N-1 ; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            tree[u].add(new Node(v, w));
        }

        dfs();
        System.out.println(result);
    }

    private static void dfs() {
    }
}
