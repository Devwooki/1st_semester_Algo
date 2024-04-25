package second.contest;

import java.io.*;
import java.util.*;

public class BOJ1283단축키지정 {

    private static int N, answerIdx=0;
    private static String[] answer;
    private static boolean[] isFinish;
    private static HashSet<Character> set = new HashSet<>();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        answer = new String[N];
        isFinish = new boolean[N];

        for(int i = 0 ; i < N ; ++i){
            String str = br.readLine();
            String[] words = str.split(" ");

            condition1(words);
            if(!isFinish[i]) condition2(str, i);
            if(!isFinish[i]) answer[i] = str;

        }

        print();
    }

    private static void condition1(String[] words) {
        boolean flag = false;
        StringBuilder sb = new StringBuilder();

        for(String word : words){
            char c = word.charAt(0);
            char template = makeTemplate(c);

            int wordLen = word.length();
            if(!set.contains(template) && !flag){
                flag = true;
                set.add(template);
                sb.append("[" + c + "]" + word.substring(1, wordLen) + " ");
            }else{
                sb.append(word + " ");
            }
        }

        if(flag) isFinish[answerIdx] = true;
        answer[answerIdx++] = sb.toString().trim();
    }

    private static void condition2(String str, int idx){
        StringBuilder sb = new StringBuilder();
        int strLen = str.length();

        for (int i = 0; i < strLen; i++) {
            char c = str.charAt(i);
            if(c == ' ') continue;

            char template = makeTemplate(c);
            if(!set.contains(template)){
                set.add(template);
                sb.append(str.substring(0, i) + "[" + c + "]"+ str.substring(i+1, strLen));
                isFinish[idx] = true;
                break;
            }
        }
        answer[idx] = sb.toString().trim();
    }

    private static char makeTemplate(char c){
        return 'A' <= c && c <= 'Z' ? c : (char)(c - 32);
    }

    private static void print(){
        StringBuilder sb = new StringBuilder();
        for(String str : answer){
            sb.append(str + "\n");
        }
        System.out.println(sb);
    }
}
