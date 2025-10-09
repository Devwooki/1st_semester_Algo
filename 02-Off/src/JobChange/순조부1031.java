package JobChange;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class 순조부1031 {
    static boolean[] visited;
    static int[] inputs;
    static int[] numbers;
    static int N, R;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        getPrime(N);
//        R = Integer.parseInt(st.nextToken());
//
//        visited = new boolean[N];
//        inputs = new int[N];
//        numbers = new int[R];
//
//        for(int i = 0 ; i < N ; ++i){
//            inputs[i] = i + 1;
//        }

        //List<Integer> list = new ArrayList<>();

        //subset();

        //perm(0);
        //combi(0, 0);
        //getSubset();
        //subsetRec(0);
    }

    public static void perm(int cnt){
        if(cnt == R){
            //순열 출력
            return;
        }

        for(int i = 0 ; i < N ; ++i){
            if(visited[i]) continue;

            visited[i] = true;
            numbers[cnt] = i;

            perm(cnt + 1);
            visited[i] = false;
        }
//        if(cnt == R){
//            print();
//            return;
//        }
//
//        for(int i = 0 ; i < N ; ++i){
//            if(visited[i]) continue;
//
//            visited[i] = true;
//            numbers[cnt] = inputs[i];
//
//            perm(cnt+1);
//            visited[i] = false;
//        }

    }


    public static void combi(int cnt, int start){
        if(cnt == R){
            print();
            return;
        }


        for(int i = start ; i < N ; ++i){
            numbers[cnt] = inputs[i];
            combi(cnt+1, i+ 1);
        }

 /*       for(int i = start; i < N ; ++i){
            numbers[cnt] = inputs[i];
            combi(cnt+1, i + 1);
        }*/

    }

    public static void getSubset(){
        for(int i = 0 ; i < ( 1 << N ) ; ++i){
            for(int j = 0 ; j < N ; ++j){
                if( (i & (1 << j)) != 0){
                    System.out.print(j + " ");
                }
            }
            System.out.println();
        }
    }

    public static void subset(){
        for(int i = 0 ; i < (1 << N )  ; ++i){
            for(int j = 0 ; j < N ; ++j){
                //System.out.println(i + ", " + j + ", " + (1 << j) + ", " + (i & ( 1 << j)));
                if((i & (1 << j)) != 0) System.out.print(j + " ");
            }
            System.out.println();
        }
    }

    public static void subsetRec(int cnt){
        if(cnt == N){
            StringBuilder sb = new StringBuilder();
            IntStream.range(0, N)
                    .filter(i -> visited[i])
                    .map(i -> inputs[i])
                    .forEach(sb::append);

            System.out.println(sb);
            return;
        }

        visited[cnt] = true;
        subsetRec(cnt+1);
        visited[cnt] = false;
        subsetRec(cnt+1);

    }
    public static void print(){
        System.out.println(Arrays.toString(numbers));
    }

    private static void getPrime(int N){
        boolean[] prime = new boolean[N+1];
        prime[0] = prime[1] = true;

        for(int i = 2 ; i <= Math.sqrt(N) ; ++i){

            if(prime[i]) continue;
            //i의 제곱부터 시작해서 i의 배수 전부 제거
            for(int j = i * i ; j < N + 1 ; j = j + i ){
                System.out.println(i + ", " + j);
                prime[j] = true;
            }
        }


    }
}
