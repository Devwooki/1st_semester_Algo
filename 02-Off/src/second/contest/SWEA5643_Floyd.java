package second.contest;
import java.util.*;
import java.io.*;
public class SWEA5643_Floyd {
    static final int INF = 1<<25;
    static int N, M;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int TC = Integer.parseInt(br.readLine());

        for(int tc = 1 ; tc <= TC ; ++tc){
            N = Integer.parseInt(br.readLine());
            M = Integer.parseInt(br.readLine());
            dp = new int[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(i == j) continue;
                    else dp[i][j] = INF;
                }
            }

            for(int i = 0 ; i < M ; ++i){
                st = new StringTokenizer(br.readLine());
                dp[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1]  = 1;
            }
            //문제 풀이를 위한 입력 완료

            for(int k = 0 ; k < N ; ++k){
                for (int i = 0; i < N; i++) {
                    if(k == i ) continue;
                    for (int j = 0; j < N; j++) {
                        if(i == j || k == j) continue;
                        dp[i][j] = Math.min(dp[i][j], dp[k][j] + dp[i][k]);
                    }
                }
            }

            //나의 키 순서 : 나보다 작은 것 + 나보다 큰 것 == N-1 : 자신의 순서를 알 수 있다,,?
            //자신을 포함해서 N이 되어야함
            int res = 0;
            for(int i = 0 ; i < N ; ++i){
                int taller = 0;
                int smaller = 0;
                for(int j = 0; j < N ;++j){
                    if(i == j) continue;
                    if(dp[i][j] != INF) taller++;
                    if(dp[j][i] != INF) smaller++;
                }
                if(taller+smaller == N-1) res++;
            }
            bw.write("#"+tc + " " + res + "\n");
        }
        br.close();
        bw.close();
    }
}

