package com.ssafy.offline06;
import java.io.*;
import java.util.*;
public class SWEA1952DP {
    static int[] prices = new int[4], months = new int[12];
    static int[] dp = new int[13];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int TC =Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= TC ; tc++) {

            prices = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            months = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            //첫 달 : 일일권 vs 1달권 vs 3달권
            dp[1] = Math.min(months[0] * prices[0],
                                Math.min(prices[1], prices[2]));
            //두 번째 달 : 일일권 2달치 vs 1달권 2개 vs 3달권
            dp[2] = Math.min(dp[1] + prices[0] * months[1],
                                Math.min(dp[1] + prices[1], prices[2]));
            //세 번째 달 : 일일권 3달치 vs 1달권 3개 vs 3달권
            dp[3] = Math.min(dp[2] + prices[0] * months[2],
                                Math.min(dp[2] + prices[1], prices[2]));

            for(int i = 4 ; i <= 12 ; ++i){
                dp[i] = Math.min(dp[i-1] + prices[0] * months[i-1],
                                Math.min(dp[i-1] + prices[1], dp[i-3] + prices[2]));
            }

            dp[12] = Math.min(prices[3], dp[12]);//마지막으로 1년권과 비교
            sb.append("#" + tc + " " + dp[12] +"\n");
        }
        System.out.println(sb);
    }
}
