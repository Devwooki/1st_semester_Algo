package second.contest;

import java.io.*;
import java.util.*;

public class BOJ2156 {
    private static int N;
    private static int[] wines;
    private static int[] dp;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        wines = new int[N+1];

        for (int i = 1; i <= N; i++) {
            wines[i] = Integer.parseInt(br.readLine());
        }

        //dp[K] : K-1번째 와인을 마실 때까지 가장 많이 마신 포도주의 양
        dp = new int[N+1];

        dp[1] = wines[1];
        if(N > 1) dp[2] = dp[1] + wines[2];

        for (int i = 3; i <= N; i++) {
            dp[i] = Math.max(dp[i-1], // i번째를 마시지 않음
                        Math.max(dp[i-2] + wines[i], //i번째 마시고, i-2번째 가지 마신 값
                                 dp[i-3] + wines[i-1] + wines[i])); // i번째, i-1번째, i-3번째 값을 마신 값
        }

        /*
        * 0 1 -> 2 불가
        * 1 -> 2 가능
        * 0 -> 2 가능
        * ------------------
        * i번째와 i-2번째 까지 마신 값
        * i번째와 i-1번째, i-3번째 까지 마신 값
        * i번째를 마시지 않은 값
        * */

        System.out.println(dp[N]);

    }
}
