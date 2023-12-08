package study;
import java.io.*;
import java.util.*;


public class BOJ1629 {
    static long A, B,C;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        A = Long.parseLong(st.nextToken());
        B = Long.parseLong(st.nextToken());
        C = Long.parseLong(st.nextToken());
        // (A^B) % C의 값을

        long temp = (long) (Math.pow(A, B) % C);

        System.out.println(temp);
    }
}
