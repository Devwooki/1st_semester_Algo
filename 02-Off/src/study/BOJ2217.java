package study;


import java.io.*;
import java.util.*;
public class BOJ2217 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        int[] lopes = new int[N];
        for(int i = 0 ; i < N ; ++i){
            lopes[i] = Integer.parseInt(br.readLine());
        }


        Arrays.sort(lopes);

        int min = lopes[0] * N;
        //int
        //모든 로프를 쓸 필요는 없으며 임의의 로프만 골라도 된다.

    }
}
