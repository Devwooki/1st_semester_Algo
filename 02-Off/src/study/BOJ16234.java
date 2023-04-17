package study;
import java.io.*;
import java.util.*;

//2^괄호 쌍 수 - 1, 총 경우의 수
public class BOJ16234 {
    static int[] numbers;
    static List<int []> list;
    static char[] str;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        str = br.readLine().toCharArray();


        list = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        for(char c : str){
            if(c == '(') stack.add(i);
            if(c == ')'){
                int start = stack.pop();
                list.add(new int[]{start, i});
            }
        }

        int size = list.size();
        numbers = new int[size];

        //부분집합인데 모두가 선택되는 경우는 제외한다
        subSetBit(size);
    }

    private static void subSetBit(int size){
        for(int i = 1 ; i < (1 << size) ; ++i){
            for(int j = 0 ; j < size; ++j){
                if((i & (1 << j)) != 0 ) {
                    //부분
                }
            }
            System.out.println();
        }
    }
}
