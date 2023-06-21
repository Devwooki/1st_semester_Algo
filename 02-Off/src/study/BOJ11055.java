package study;
import java.io.*;
import java.util.*;

/*
     1, 100, 2, 50,  60, 3,  5,  6,  7,  8
dp : 1, 101, 3, 53, 113, 6, 11, 17, 24, 32
*
*/
public class BOJ11055 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int A = Integer.parseInt(br.readLine());

        int[] map = new int[A];
        map = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] dp = new int[A];//i idx의 부분 수열 최대합

        //0번째 인자는 계산된 것이 없으므로 자기 자신을 대입한다.
        dp[0] = map[0];

        for(int i = 1 ; i < A ; ++i){
            dp[i] = map[i]; //기준이 되는 값을 저장한다.

            for (int j = 0; j < i; j++) {//i위치 까지 재 탐 수행한다.
                if(map[j] < map[i]){ //기준보다 큰 값이면 DP를 갱신한다
                    //이전 최대합에서 현재를 더하기 VS 현재 cost 가격
                    dp[i] = Math.max(dp[j] + map[i], dp[i]);
                }
            }
        }

        int result = Integer.MIN_VALUE;
        for(int value : dp){
            result = Math.max(result, value);
        }
        System.out.println(result);
    }
}
