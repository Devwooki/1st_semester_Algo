package JobChange;

import java.io.*;
import java.util.*;

public class BOJ1182_부분수열의합_SV2 {
    static int N, S, answer = 0;
    static int[] map;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        //초기화
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        map = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println("방법 1 : " + getSubSeq());

        getSubSeq2(0,0);
        System.out.println("방법 2 : " + answer);

    }

    private static int getSubSeq(){
        int cnt = 0;

        for(int i = 1 ; i < ( 1 << N) ; ++i){
            int sum = 0;
            for(int j = 0 ; j < N ; ++j){
                if((i & ( 1 << j )) != 0) sum += map[j];
            }

            if(sum == S) cnt ++;
        }

        return cnt;
    }

    private static void getSubSeq2(int sum, int cnt){
        if(cnt == N){
            if(sum == S) answer++;
            return;
        }

        getSubSeq2(sum, cnt+1);
        getSubSeq2(sum + map[cnt], cnt+1);
    }
}
