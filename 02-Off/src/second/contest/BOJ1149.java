package second.contest;
import java.io.*;
import java.util.*;

public class BOJ1149 {

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        //int[] dp = new int[N+1];
        int[][] map = new int[N][3];
        //int[] state = new int[N+1]; //RGB를 선택

        for(int i = 0 ; i < N ; ++i){
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }


        for(int i = 1 ; i < N ; ++i){
            map[i][0] += Math.min(map[i-1][1], map[i-1][2]);
            map[i][1] += Math.min(map[i-1][0], map[i-1][2]);
            map[i][2] += Math.min(map[i-1][0], map[i-1][1]);
        }
        System.out.println(
                Math.min(
                        Math.min(map[N-1][0], map[N-1][1]),
                        map[N-1][2])
        );

        //2 번째 집에서 부터 N개의 집 수만큼 탐색
//        for(int i = 2 ; i <= N ; ++i){
//
//            //현재 라인과, 다음 라인에서의 최댒값 구함
//            int value = Integer.MAX_VALUE;
//            for(int j = 0 ; j < 3 ; ++j){
//                for(int k = 0 ; k < 3 ; ++k){
//                    if(j == state[i-2]) continue;
//                    if(j == k) continue;
//                    int sum = dp[i-2] + map[i-1][j] + map[i][k];
//                    if(value > sum){
//                        value = sum;
//                        state[i-1] = j;
//                    }
//                }
//            }
//            dp[i] = value;
//        }
//        System.out.println(dp[N]);


    }
}
