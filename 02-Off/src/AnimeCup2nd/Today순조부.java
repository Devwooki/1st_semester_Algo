package AnimeCup2nd;
import java.io.*;
import java.sql.SQLOutput;
import java.util.*;

public class Today순조부 {
    static int[] numbers, inputs;
    static boolean[] isSelected;
    static int N, R, total = 0;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        R = Integer.parseInt(br.readLine());

        inputs = new int[N];
        isSelected = new boolean[N];
        numbers = new int[R];

        for(int i = 0 ; i < N ; ++i){
            inputs[i] = i +1;
        }

        System.out.println("일반 순열");
        nPr(0);
        System.out.println("일반 순열 수 : " + total);
        total = 0 ;
        System.out.println("중복 순열");
        nPIr(0);
        System.out.println("중복 순열 수 : " + total);
        total = 0 ;
        System.out.println("일반 조합");
        nCr(0, 0);
        System.out.println("일반 조합 수 : " + total);
        total = 0 ;
        System.out.println("중복 조합");
        nHr(0, 0);
        System.out.println("중복 조합 수 : " + total);
        total = 0 ;

        System.out.println("부분집합 비트마스크");
        subSetBit();
        System.out.println("부분집합 재귀");
        subSetRec(0);
    }

    private static void subSetBit() {
        for(int i = 0 ; i < ( 1<< N ) ; ++i){
            for(int j = 0 ; j < N ; ++j){
                if(( i & 1 << j ) == 0) continue;

                System.out.print(inputs[j] + " ");
            }
            System.out.println();
        }
    }

    private static void subSetRec(int cnt) {
        if(cnt == N) {
            for (int i = 0 ; i < N ; ++i) {
                if (isSelected[i]) System.out.print(inputs[i] + " ");
            }
            System.out.println();
            return;
        }

        isSelected[cnt] = true;
        subSetRec(cnt + 1);
        isSelected[cnt] = false;
        subSetRec(cnt + 1);

    }

    private static void nHr(int cnt, int start) {
        if(cnt == R) {
            total++;
            System.out.println(Arrays.toString(numbers));
            return;
        }

        for(int i = start ; i<N ; ++i){
            numbers[cnt] = inputs[i];
            nHr(cnt+1, i);
        }
    }

    private static void nCr(int cnt, int start) {
        if(cnt == R) {
            total++;
            System.out.println(Arrays.toString(numbers));
            return;
        }

        for(int i = start ; i<N ; ++i){
            numbers[cnt] = inputs[i];
            nCr(cnt+1, i+1);
        }

    }

    private static void nPIr(int cnt) {
        if(cnt == R) {
            total++;
            System.out.println(Arrays.toString(numbers));
            return;
        }
        for(int i = 0 ; i < N ; ++i){

            numbers[cnt] = inputs[i];
            nPIr(cnt+1);
        }

    }

    private static void nPr(int cnt) {
        if(cnt == R) {
            total++;
            System.out.println(Arrays.toString(numbers));
            return;
        }

        for(int i = 0 ; i < N ; ++i){
            if(isSelected[i]) continue;

            numbers[cnt] = inputs[i];
            isSelected[i] = true;
            nPr(cnt+1);
            isSelected[i] = false;
        }

    }
}
