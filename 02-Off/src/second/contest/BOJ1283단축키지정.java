package second.contest;

import java.io.*;
import java.util.*;
import java.util.jar.JarEntry;

public class BOJ1283단축키지정 {
    private static HashSet<Character> set = new HashSet<>();
    private static String[] answer;
    private static int answerIdx = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        //1. "단어의 첫 글자"가 단축키 인가
        //2. 모든 단어의 첫 글자가 지정 -> 왼쪽부터 알파벳
        //3. 아무것도 없으면 그냥 두고 대소문자 구분X
        //입력 순서대로 값을 저장하는 map 객체
        answer = new String[N];

        for (int i = 0; i < N; ++i) {
            String str = br.readLine();
            String[] words = str.split(" ");

            //1. 단어의 첫 글자가 단축키 인지 판단
            if(condition1(words)) continue;


        }

        print();
    }

    private static boolean condition1(String[] words){
        StringBuilder sb = new StringBuilder();
        for(String word : words){
            char c = word.charAt(0);

            if(!set.contains(c)){
                answer[answerIdx++] = sb.append("[" + );
                return true;
            }
        }

        //단어의 첫 글자들이 모두 단축키로 지정되어 있으면
        return false;
    }

    private static void print(){
        StringBuilder sb = new StringBuilder();
        for(String option : answer){
            sb.append(option + "\n");
        }
        System.out.println(sb);
    }
}
