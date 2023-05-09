package study;

import java.io.*;
import java.util.*;

public class BOJ9663 {
    static int[] map;
    static int N, result =0;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        map =new int[N];

        getNQueen(0);
        System.out.println(result);
    }

    static void getNQueen(int cnt){
        if(cnt == N){ //depth가 N에 도달하면 배열이완성 -> NQueen 배치가 완성된 것이다.
            result++;
            return;
        }

        for (int i = 0; i < N; i++) {
            map[cnt] = i; //cnt, i에 퀸을 놓는다.
            if(isPossible(cnt)) getNQueen(cnt+1);

        }
    }

    static boolean isPossible(int cnt){
        for(int i = 0  ; i  < cnt ; ++i){
            if(map[cnt] == map[i]) return false; //배열의 값이 같음 = 중복되는 열을 가진다 실패
            else if(Math.abs(cnt-i) == Math.abs(map[cnt] -map[i])) return false;//절댓값이 같으면 대각선에 위치한 것
        }
        return true;
    }

}
