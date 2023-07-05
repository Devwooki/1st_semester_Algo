package study;

import java.io.*;
import java.util.*;

public class BOJ2470 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Arrays.sort(arr);

        int start = 0;
        int end = N - 1;
        int prevCalc = Integer.MAX_VALUE;

        while(true){
            //종료조건 선언
            if(arr[start] > 0 || arr[end] < 0) break;

            int base = arr[start];
            int acid = arr[end];

            if()
        }

        System.out.println(arr[start] + " " + arr[end]);
    }
}