package programmers;

import java.util.*;
import java.io.*;


public class 문자열압축 {
    static String[] s = {
            "aabbaccc",
            "ababcdcdababcdcd",
            "abcabcdede",
            "abcabcabcabcdededededede",
            "xababcdcdababcdcd"
    };
    static int Solution(String str){
        int answer = Integer.MAX_VALUE;
        for(int i = 0 ; i <= str.length()/2 ; ++i){
            answer = Math.min(answer, zip(i,str));
        }
        return answer;
    }

    private static int zip(int len, String str) {


    }


    public static void main(String[] args) throws IOException {
        for (int i = 0; i < s.length; i++) {
            System.out.println(Solution(s[i]));
        }
    }
}
