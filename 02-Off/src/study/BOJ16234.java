package study;

import java.io.*;
import java.util.*;

/*
- 국경을 공유하는 두나라 인구차이가 L이상 R이하면 국경선 하루 연다
- 국경선 열리면 인구이동 가능 -> 그날 하루 연합
- 연합을 이루고 있는 각 칸의 인구수
  (연합의 인구수) / 연합을 이루고 있는 칸의 개수
- 소수 버림
*/
public class BOJ16234 {
    static int N, L, R;
    static int guideLine;
    static int[][] dir = {{1,0},{0,1},{-1,0},{0,-1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        guideLine = L-R;


    }
}

