package second.contest;

import java.io.*;
import java.util.StringTokenizer;

public class SWEA5643_Floyd_PF {
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

            for(int i = 0 ; i < M ; ++i){
                st = new StringTokenizer(br.readLine());
                dp[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1]  = 1;
            }
            //문제 풀이를 위한 입력 완료


            //조건이 다른 이유는 inf가 없기 때문이다. 관계가 없으면 0으로 둠
            for(int k = 0 ; k < N ; ++k){//경유 학생(K)
                for (int i = 0; i < N; i++) {//출발 학생(i)
                    if(k == i || dp[i][k] ==0 ) continue;
                    //경유효과 없음(알 수 있는 관계가 없다.)
                    //i와k가 관계가 없어 경유 불가
                    for (int j = 0; j < N; j++) {//도착학생(j)
                        // i < k < j : 학생 i보다 k가 키가 크고, k보다 j가 크면 i < j이다.
                        if(dp[i][j] == 1) continue;
                        dp[i][j] = dp[k][j];
                    }
                }
            }

            //나의 키 순서 : 나보다 작은 것 + 나보다 큰 것 == N-1 : 자신의 순서를 알 수 있다,,?
            //자신을 포함해서 N이 되어야함
            int answer = 0;
            for(int i = 0 ; i < N ;++i){
                int tall = 0;
                int small = 0;
                for(int j = 0 ; j < N ; ++j){
                    tall += dp[i][j]; //자신보다 큰 학생 수 누적
                    small += dp[j][i];//자신보자 작은 학생 수 누적
                }
            }
            bw.write("#"+tc + " " + answer + "\n");
        }
        br.close();
        bw.close();
    }
}
