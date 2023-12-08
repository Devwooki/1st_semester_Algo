package practiceTest;

import java.io.*;
import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.IntStream;

public class 순조부 {
    static int N, M;
    static StringBuilder sb = new StringBuilder();
    static int[] inputs, results;
    static boolean[] visited;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        inputs = new int[N];
        results = new int[M];
        visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            inputs[i] = i + 1;
        }

//        sb.setLength(0);
//        System.out.println("====== 조합 ======");
//        nPr(0);
//        System.out.print(sb);
//
//        sb.setLength(0);
//        System.out.println("====== 조합 ======");
//        nCr(0, 0);
//        System.out.print(sb);
//
//        sb.setLength(0);
//        System.out.println("====== 중복 순열 ======");
//        중복순열(0);
//        System.out.print(sb);
//
//        sb.setLength(0);
//        System.out.println("====== 중복 조합 ======");
//        중복조합(0, 0);
//        System.out.print(sb);
        부분집합(0);
        System.out.println(sb);

        부분집합비트(0);
    }

    public static void nPr(int cnt){
        if(cnt == M){
            for(int i = 0; i < M ; ++i){
                sb.append(results[i] + " ");
            }
            sb.append("\n");
            return;
        }

        for(int i = 0 ; i < N ; ++i){
            if(visited[i]) continue;

            visited[i] = true;
            results[cnt] = inputs[i];

            nPr(cnt+1);
            visited[i] = false;
        }

    }

    public static void nCr(int cnt, int start){
        if(cnt == M){
            for(int i = 0; i < M ; ++i){
                sb.append(results[i] + " ");
            }
            sb.append("\n");
            return;
        }
        for(int i = start ; i < N ; ++i){
            results[cnt] = inputs[i];
            nCr(cnt+1, i+1);
        }
    }

    public static void 중복순열(int cnt){    if(cnt == M){
        for(int i = 0; i < M ; ++i){
            sb.append(results[i] + " ");
        }
        sb.append("\n");
        return;
    }
        for(int i = 0 ; i < N ; ++i){
            results[cnt] = inputs[i];
            nPr(cnt+1);
        }
    }

    public static void 중복조합(int cnt, int start){
        if(cnt == M){
            for(int i = 0; i < M ; ++i){
                sb.append(results[i] + " ");
            }
            sb.append("\n");
            return;
        }
        for(int i = start ; i < N ; ++i){
            results[cnt] = inputs[i];
            nCr(cnt+1, i);
        }
    }

    public static void 부분집합(int cnt){
        if(cnt == N){
            printSubSet();
            return;
        }
        visited[cnt] = true;
        부분집합(cnt+1);
        visited[cnt] = false;
        부분집합(cnt+1);
    }

    public static void 부분집합비트(int t){
        for(int i = 0 ; i < ( 1 << N) ; ++i){ // 만들 수 있는 부분 집합의 갯수
            for(int j = 0 ; j < N ; ++j){ //출력할 부분집합의 인덱스  체크
                if( (i & ( 1 << j )) != 0){
                    System.out.print(inputs[j] +  " ");
                }
            }
            System.out.println();
        }
    }


    public static void printSubSet(){
        IntStream.range(0, N)
                .filter(i -> visited[i])
                .map(i -> inputs[i])
                .forEach(sb::append);
        sb.append("\n");
    }
}
