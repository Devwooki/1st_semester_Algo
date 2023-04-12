package programmers;

import java.util.*;
import java.io.*;

public class 멀쩡한사각형 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int w = Integer.parseInt(st.nextToken());
        long W = Long.valueOf(w);
        int h = Integer.parseInt(st.nextToken());

        int gcd = getGcd(w,h);

        int minW = w / gcd;
        int minH = h / gcd;


        long total = w * h;
        long deny = w + h - getGcd(w,h);
        System.out.println(gcd + ", "+ total + ", " + deny);

    }


    static int getGcd(int a, int b){
        if(b == 0) return a;
        else return getGcd(b, a%b);
    }
}
