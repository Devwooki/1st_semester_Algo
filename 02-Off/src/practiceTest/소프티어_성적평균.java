package practiceTest;

import com.sun.security.jgss.GSSUtil;

import java.io.*;
import java.util.*;

public class 소프티어_성적평균 {
    static  int N,K;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        //0 10 50 20 70 100
        int[] scores = new int[N+1];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 1; i <= N ; ++i){
            scores[i] = Integer.parseInt(st.nextToken());
        }

        //0 10 60 80 150 250
        int[] sums = new int[N+1];
        sums[1] = scores[1];
        for(int i = 2 ; i <= N; ++i){
            sums[i] = sums[i-1] + scores[i];
        }

        StringBuilder sb = new StringBuilder();

        for(int i = 0 ; i < K ; ++i){
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            int cnt = end - start + 1;
            double sum = sums[end] - sums[start-1];
            sb.append(String.format("%.2f", sum/cnt) + "\n");
        }
        System.out.println(sb);
    }
}
