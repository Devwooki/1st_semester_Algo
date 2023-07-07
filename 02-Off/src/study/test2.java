package study;

import java.io.*;
import java.util.*;

public class test2 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        TreeSet<Integer> set = new TreeSet<>();
        st = new StringTokenizer(br.readLine(), " ");

        while(st.hasMoreTokens()) set.add(Integer.parseInt(st.nextToken()));
        System.out.println("수정 데이터 : " + set.size());


        st = new StringTokenizer(br.readLine(), " ");
        System.out.println("누락된 데이터");
        while(st.hasMoreTokens()) {
            int temp = Integer.parseInt(st.nextToken());
            if(set.contains(temp)) continue;
            else System.out.print(temp + " ");
        }
    }
}
