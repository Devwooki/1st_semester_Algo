package TOSSNEXT;
import java.io.*;
import java.util.*;


public class 순조부 {
    static int[] numbers;
    static int[] inputs;
    static boolean[] visited;

    static int r =3;
    static int n =5;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        inputs = new int[]{1,2,3,4,5};
        visited = new boolean[n];
        numbers = new int[r];

        nPr(0);
        System.out.println("\n");
        nCr(0,0);
    }

    static void nPr(int cnt){
        if(cnt == r){
            System.out.println(Arrays.toString(numbers));
            return;
        }

        for(int i = 0 ; i < n ; ++i){
            if(visited[i]) continue;
            numbers[cnt] = inputs[i];
            visited[i] = true;
            nPr(cnt+1);
            visited[i] = false;
        }

    }

    static void nCr(int cnt, int start){
        if(cnt == r){
            System.out.println(Arrays.toString(numbers));
            return;
        }

        for (int i = start; i < n; i++) {
            numbers[cnt] = inputs[i];
            nCr(cnt+1, i+1);
        }

    }

}
