package JobChange;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class BOJ1182_백트래킹 {
    private static int N, S, answer = 0;
    private static int[] inputs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();


        //getSubSeq();
        //System.out.println(answer);

        //수열의 길이가 0인경우가 존재할 수 있기 때문에 0일경우 1을 뺀다
        getSubSeq2(0,0);
        System.out.println(S == 0 ? --answer : answer);
    }

    private static void getSubSeq(){

        for (int i = 1; i < ( 1 << N); i++) {
            int sum = 0;
            for (int j = 0 ; j < N; j++) {
                if((i & (1 << j)) != 0){
                    //System.out.print(inputs[j] + " ");
                    sum += inputs[j];
                }
            }
            //System.out.println(" : 최종합 = " + sum);
            if(sum == S) answer +=1;
        }
    }

    private static void getSubSeq2(int sum, int cnt){
        if(cnt == N){
            if(sum == S)answer++;
            return;
        }

        getSubSeq2(sum, cnt+1);
        getSubSeq2(sum + inputs[cnt], cnt+1);
    }

}
