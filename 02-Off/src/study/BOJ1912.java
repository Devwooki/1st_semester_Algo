package study;
import java.io.*;
import java.util.*;


public class BOJ1912 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        int[] dp = new int[n];
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();


        int result = arr[0];
        dp[0] = arr[0];
        for(int i = 1 ; i < n ; ++i){
            dp[i] = Math.max(dp[i-1] + arr[i], arr[i]);

            result= Math.max(dp[i], result);
        }
        System.out.println(result);
    }
}
