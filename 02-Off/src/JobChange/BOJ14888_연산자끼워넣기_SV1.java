package JobChange;

import java.io.*;
import java.util.*;


public class BOJ14888_연산자끼워넣기_SV1 {
    static int N;
    static long maxVal = Long.MIN_VALUE, minVal = Long.MAX_VALUE;
    static int[] arr;
    static int[] op;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        op = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        inputOp(arr[0], 1);

        sb.append(maxVal + "\n");
        sb.append(minVal);

        System.out.println(sb);
    }

    private static void inputOp(int sum, int cnt){
        if(cnt == N){
            maxVal = Math.max(maxVal, sum);
            minVal = Math.min(minVal, sum);
            return;
        }

        for(int i = 0 ; i < 4 ; ++i){
            //연산자 개수가 1개 이상
            if(op[i] > 0) {

                //연산자를 1개 제거해서 계산에 포함시킨다.
                op[i]--;

                switch(i){
                    case 0 : inputOp(sum + arr[cnt] , cnt + 1); break;
                    case 1 : inputOp(sum - arr[cnt] , cnt + 1); break;
                    case 2 : inputOp(sum * arr[cnt] , cnt + 1); break;
                    case 3 : inputOp(sum / arr[cnt] , cnt + 1); break;
                }
                //호출 종료 시, 연산자 복구
                op[i]++;
            }
        }
    }
}
