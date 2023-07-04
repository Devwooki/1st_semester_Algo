package study;

import java.io.*;
import java.util.*;

public class test {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        int[] liquor = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Arrays.sort(liquor);

        int start = 0;
        int end = liquor.length-1;

        int baseValue = Integer.MAX_VALUE;
        int[] result = new int[2];
        while(start < end){
            int sum = liquor[start] + liquor[end];

            if(baseValue > Math.abs(sum)){
                baseValue = Math.abs(sum);
                result[0] = liquor[start];
                result[1] = liquor[end];
            }

            if(sum < 0) start++; //염기가 더 크다는 것 -> start 한칸 올린다.
            else end--; //산이 너무 크다 -> end한칸 내림
        }


        System.out.println(result[0] + " " + result[1]);
    }
}
