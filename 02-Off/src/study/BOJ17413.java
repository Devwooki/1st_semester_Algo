package study;

import java.io.*;
import java.util.*;

public class BOJ17413 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder result = new StringBuilder();
        StringBuilder words = new StringBuilder();

        char[] str = br.readLine().toCharArray();

        boolean isTag = false;
        for(char c : str){
            if(c == '<') {
                result.append( words.reverse());
                words.setLength(0);
                isTag = true;
            }
            else if(c == '>') {
                result.append(c);
                isTag = false;
                continue;

            }

            if(isTag) result.append(c);
            else {
                if(c == ' ') {
                    result.append(words.reverse());
                    words.setLength(0);
                    result.append(c);
                }
                else words.append(c);
            }

        }
        if(words.length() != 0) result.append(words.reverse());
        System.out.println(result);
    }
}
