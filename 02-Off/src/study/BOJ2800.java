package study;

import java.io.*;
import java.util.*;

//유효한 괄호만 남긴다
public class BOJ2800 {
    static int[] numbers;
    static List<int []> list;
    static char[] str;
    static boolean[] brackets;
    static TreeSet<String> set = new TreeSet<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        str = br.readLine().toCharArray();

        list = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        for(int i = 0 ; i < str.length ; ++i){
            if(str[i] == '(') stack.add(i);
            if(str[i] == ')'){
                int start = stack.pop();
                list.add(new int[]{start, i});
            }
        }

        int size = list.size();
        numbers = new int[size];

        //부분집합인데 모두가 선택되는 경우는 제외한다
        subSetBit(size);

        for(String answer : set){
            sb.append(answer+"\n");
        }

        System.out.println(sb);
    }

    private static void subSetBit(int size){
        for(int i = 1 ; i < (1 << size) ; ++i){
            brackets = new boolean[str.length];
            for(int j = 0 ; j < size; ++j){
                if((i & (1 << j)) != 0 ) {
                    brackets[list.get(j)[0]] = true;
                    brackets[list.get(j)[1]] = true;
                    makeFormula();
                }
            }
        }
    }

    private static void makeFormula(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < str.length ; ++i){
            if(brackets[i])continue;
            sb.append(str[i]);
        }
        set.add(sb.toString());
    }
}
