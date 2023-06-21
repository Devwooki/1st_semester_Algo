package study;

import java.io.*;
import java.util.*;

public class BOJ18222 {
    //pow : 문자열의 길이
    static long[] pow = new long[65]; //2^26 = 10의 20승의 자릿수

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        Long k = Long.parseLong(br.readLine());
        pow[0] = 1; // pow[i] : 2의 i승 ->
        for(int i = 1 ; i < 65 ; ++i) pow[i] = pow[i-1] * 2;

        System.out.println(rec(k));
    }
    // k번째에 해당하는 문자 -> 이전 문자열을 반전시켜 붙여준 것
    // k번째에 대칭되는 문자를 뒤집은 요소
    // ex) k = 3 -> 0110, 01을 뒤집은 10을 붙여준 것 -> 0을 반전 시킴
    // f(x) = 1 - f(x-y)
    private static int rec(long k) {
        if(k == 1) return 0;

        for(int i = 0 ; i < 65 ; ++i){

        }
        return 0;
    }
}
/*
0   1
01  2
0110    3
0110 1001   4 비대칭
0110 1001 1001 0110 5 대칭
0110 1001 1001 0110 1001 0110 0110 1001     6 비대칭
0110 1001 1001 0110 1001 0110 0110 1001 1001 0110 0110 1001 0110 1001 1001 0110    7 대칭


*/

