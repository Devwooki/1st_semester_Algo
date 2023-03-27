package com.ssafy.offline19;
import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class BOJ2407 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        BigInteger[][] dp = new BigInteger[101][101];

        for (int i = 1; i <= N; ++i) {
            for (int j = 0; j <= i; ++j) {
                if (j == 0 || j == i)
                    dp[i][j] = new BigInteger("1");
                dp[i][j] = dp[i - 1][j - 1].add(dp[i - 1][j]);
            }
        }
        System.out.println(dp[N][M]);
    }
}
