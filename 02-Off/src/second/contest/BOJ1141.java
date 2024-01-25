package second.contest;

import java.io.*;
import java.util.*;

public class BOJ1141 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        List<String> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {

            list.add(br.readLine());
        }
        Collections.sort(list, (str1, str2) -> str1.length() - str2.length() );

    }
}


