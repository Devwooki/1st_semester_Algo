package second.contest;
import java.io.*;
import java.util.*;


public class BOJ1254 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        int strLen = str.length();

        int appendCharCnt = 0;

        //문자를 앞에서 부터 한칸씩 자른다.
        //자른 문자열이 펠린드롬이 되면, 그
        for(int i = 0 ;  i < strLen ; ++i){
            if(isPalind(str.substring(i))){
                break;
            }
            appendCharCnt += 1;
        }

        System.out.println(strLen + appendCharCnt);
    }

    public static boolean isPalind(String subStr){
        int start = 0;
        int end = subStr.length() - 1;

        while(start <= end){
            if(subStr.charAt(start) != subStr.charAt(end)) return false;
                start +=1;
                end -=1;
        }
        return true;
    }
}
