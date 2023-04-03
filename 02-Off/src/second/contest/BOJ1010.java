package second.contest;
import java.util.*;
import java.io.*;

public class BOJ1010 {
    static long[][] dp = new long[30][30];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        /*
            nCr = n!/r!(n-r)!
            nCr = n-1Cr-1 + n-1Cr
            nC0 == nCn == 1
            nC1 == n
        */
        int N = Integer.parseInt(br.readLine());

        for (int n = 1; n < 30; ++n) {
            for(int r = 0 ; r <= n; ++r){
                if(r == 0 || r == n) {
                    dp[n][r] = 1;
                } else if(r == 1){
                    dp[n][r] = n;
                }else{
                    dp[n][r] = dp[n-1][r-1] + dp[n-1][r];
                }

            }
        }

        for(int i = 0 ; i < N ; ++i){
            long result = 0;
            st = new StringTokenizer(br.readLine(), " " );
            int wst = Integer.parseInt(st.nextToken());
            int est = Integer.parseInt(st.nextToken());

            sb.append(dp[est][wst] + "\n");;
        }
        System.out.println(sb);
    }
}
