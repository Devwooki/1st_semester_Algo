package JobChange;

import java.io.*;
import java.util.*;

public class BOJ3986 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int answer = 0;
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            if(checkGoodWork(str)) answer++;
        }

        System.out.println(answer);
    }

    private static boolean checkGoodWork(String str){
        Stack<Character> stack = new Stack<>();
        int strLen = str.length();

        stack.push(str.charAt(0));

        for(int i = 1 ; i < strLen ; ++i){
            char c = str.charAt(i);

            if(!stack.isEmpty()){
                if(stack.peek() == c){
                    stack.pop();
                }else{
                    stack.push(c);
                }
            }else{
                stack.push(c);
            }


        }

        return stack.size() == 0;
    }
}
