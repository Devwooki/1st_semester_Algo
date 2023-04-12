package programmers;

import java.util.*;
import java.io.*;


public class 문자열압축 {
    static String[] s = {
            "a",
            "aabbaccc",
            "ababcdcdababcdcd",
            "abcabcdede",
            "abcabcabcabcdededededede",
            "xababcdcdababcdcd"
    };
    static int Solution(String str){
        int answer = Integer.MAX_VALUE;
        //완전 탐색으로 진행
        //
        for(int i = 1 ; i <= str.length()/2 ; ++i){
            //최솟값을 지정한다.
            answer = Math.min(answer, zip(str, i));
        }

        return answer;
    }

    /*
    선정할 문자열 -> 입력문자열.subString(시작위치, 시작위치+len)
    현재 문자열 == 이전 문자열 -{
        cnt 증가
    } 현재 문자열 != 이전 문자열 {
        sb.append(cnt).append(prev), cnt = 0;
    }
    prev = now;


    return sb.length();
     */
    private static int zip(String str, int len) {
        int textLength = str.length();;
        StringBuilder sb = new StringBuilder();
        if(textLength == 1) return 1;
        String prev = "";
        int cnt = 1;
        for(int i = 0 ; i < textLength ; i += len){
            String now = "";
            if(i + len < textLength) now = str.substring(i, len + i);
            else now = str.substring(i, textLength);

            if(now.equals(prev)){
                cnt++;
            }else{
                if(prev.equals("")) {
                    prev = now;
                    continue;
                }

                if(cnt == 1) sb.append(prev);
                else sb.append(cnt).append(prev);

                cnt = 1;
            }

            prev = now;
        }

        //마지막에 남은 것을 붙인가
        if(cnt == 1) sb.append(prev);
        else sb.append(cnt).append(prev);
        return sb.toString().length();
    }


    public static void main(String[] args) throws IOException {
        for (int i = 0; i < s.length; i++) {
            System.out.println(Solution(s[i]));
        }
    }
}
