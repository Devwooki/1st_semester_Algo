package study;

import java.io.*;
import java.util.*;

//상근이가 게임을 먼저한다
public class BOJ9655 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int TC = Integer.parseInt(br.readLine());

        if(TC%2 == 0 ) System.out.println("CY");
        else System.out.println("SK");

    }
}

