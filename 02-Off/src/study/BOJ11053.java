package study;

import java.io.*;
import java.util.*;

public class BOJ11053 {
    /*
    arr = {10, 20, 10, 30, 20, 50 }
    dp  = { 1,  2,  2,  3,  3,  4};
     */
    static int[] dp;
    static int[] arr;
    static int N;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        dp = new int[N];

        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int remember = arr[0];
        dp[0] = 1;
        for(int i = 1 ; i < N ; ++i){

        }

        System.out.println(dp[N-1]);
    }
}
