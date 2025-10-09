package JobChange.BackTracking;

import java.io.*;
import java.util.*;

public class BOJ14888 {
    static long N, minAns = Long.MAX_VALUE, maxAns = Long.MIN_VALUE;
    static int[] arr;
    static int[] symbol;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        //변수 선언
        N = Long.parseLong(br.readLine());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        symbol = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for(int i = 0 ; i < 4 ; ++i){
            if(symbol[i] > 0){

            }

        }
        getAnswer(1, arr[0]);

        System.out.println(maxAns + "\n" + minAns);
    }

    private static void getAnswer(int depth, int sum){
        if(depth == N){
            minAns = Math.min(minAns, sum);
            maxAns = Math.max(maxAns, sum);
            return;
        }

        for(int i = 0 ; i < 4 ; ++i){
            if(symbol[i] > 0){

                symbol[i]--;

                //연산자에 따른 계산 수행
                switch(i){
                    case 0 : getAnswer(depth+1, sum + arr[depth]); break;
                    case 1 : getAnswer(depth+1, sum - arr[depth]);break;
                    case 2 : getAnswer(depth+1, sum * arr[depth]);break;
                    case 3 : getAnswer(depth+1, sum / arr[depth]);break;
                }

                //재귀호출 종료시 해당 연산자 갯수 복귀
                symbol[i]++;
            }
        }


    }

}

