package study;

import java.io.*;
import java.util.*;

public class BOJ15666 {
    static int N, M;
    static int[] input;
    static int[] perm;
    static HashSet<String> set = new HashSet<>();
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        input = new int[N];
        perm = new int[M];


        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; ++i){
            input[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(input);

        //순열 구하기
        //getDuplPerm(0);
        //조합 구하기
        getDuplComb(0, 0);

        //결과 출력
        System.out.println(sb);
    }

    private static void getDuplPerm(int cnt) {
        if(cnt == M){
            for(int i : perm) sb.append(i + " ");

            set.add(sb.toString());
            sb.setLength(0); //string builder를 비운다.
            return;
        }

    }

    //중복 조합인가
    private static void getDuplComb(int cnt, int start) {
        if(cnt == M){
            StringBuilder temp = new StringBuilder();
            for(int i : perm) temp.append(i + " ");

            //객체에 들어있지 않으면 추가하고 출력한다.
            if(!set.contains(temp.toString())){
                set.add(temp.toString());
                sb.append(temp.toString() + "\n");
            }
            return;
        }

        for(int i = start ; i < N ; ++i){
            perm[cnt] = input[i];
            getDuplComb(cnt+1, i);
        }
    }
}
