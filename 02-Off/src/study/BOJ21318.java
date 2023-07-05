package study;
import java.io.*;
import java.util.*;
/*
1 2 3 3  4  1 10  8  1      - 단계별 난이도
1 3 6 9 13 14 24 32 33      - 난이도 누적 합

24 - 9 15

33 - 13 20
* */

public class BOJ21318 {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        int[] isHard = new int[N];//다음 index값에서 값이 작아지면 true

        st = new StringTokenizer(br.readLine());
        int prev = Integer.parseInt(st.nextToken());
        for(int i = 1 ; i < N ; ++i){
            int now = Integer.parseInt(st.nextToken());
            if(prev > now) isHard[i] = 1;

            prev = now;
        }

        for (int i = 1; i < N; i++) {
            isHard[i] += isHard[i-1];
        }

        int Q = Integer.parseInt(br.readLine());
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken())-1;
            int end = Integer.parseInt(st.nextToken())-1;
            sb.append( (isHard[end] - isHard[start]) + "\n");
        }
        System.out.println(sb);
    }
}
//완탐은 시간 초과