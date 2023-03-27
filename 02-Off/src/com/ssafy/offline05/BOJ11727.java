package com.ssafy.offline05;

import java.io.*;
import java.util.*;
public class BOJ11727 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        long[] dp = new long[1002];

        dp[1] = 1;
        dp[2] = 3;
        //노가다로 n = 4 까지 구해보니
        // 다음과 같은 식을 구할 수 있었슴
        for (int i = 3; i <= N; i++) {
            dp[i] = (dp[i-1] + 2*dp[i-2])%10007;
        }
        System.out.println(dp[N]);

    }
}
