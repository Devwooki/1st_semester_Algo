package second.contest;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class 순조부1011 {
    private static int N, R, TOTAL_CNT = 0;
    private static int[] inputs;
    private static int[] resArr;
    private static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //순열과 조합은 N개의 숫자에서 R개를 가지고 순열/조합을 만드는 것


        N = Integer.parseInt(br.readLine());
        R = Integer.parseInt(br.readLine());

        inputs = IntStream.range(0, N).map(i -> i + 1).toArray();
        visited = new boolean[N];
        resArr = new int[R];

        System.out.println("순열");
        getPerm(0);

        visited = new boolean[N];
        System.out.println("조합");
        getCombi(0, 0);

        System.out.println("중복순열");
        getRePerm(0);
        System.out.println("총 갯수 : " + TOTAL_CNT);

        System.out.println("중복조합");
        TOTAL_CNT = 0;
        getReCombi(0, 0);
        System.out.println("총 갯수 : " + TOTAL_CNT);


        System.out.println("부분집합 - 재귀");
        getSubSetRec(0);


        System.out.println("부분집합 - 비트");
        getSubSetBit();
    }
    private static void getPerm(int cnt){
        if(cnt == R ){
            printRes();
            return;
        }

        //순열은 순서를 고려하지 않는다. 따라서 1,2나 2,1은 별개의 것으로 본다
        for(int i = 0 ; i < N ; ++i ) {
            if(visited[i]) continue;

            resArr[cnt] = inputs[i];
            visited[i] = true;

            getPerm(cnt+1);
            visited[i] = false;
        }
    }

    private static void getCombi(int start, int cnt){
        if(cnt == R){
            printRes();
            return;
        }

        for(int i = 0 ; i < N ; ++i){
            resArr[cnt] = inputs[i];

            getCombi(i+1, cnt+1);
        }
    }

    private static void getRePerm(int cnt){
        if(cnt == R){
            printRes();
            TOTAL_CNT += 1;
            return;
        }

        for(int i = 0 ; i < N ; ++i){
            resArr[cnt] = inputs[i];
            getRePerm(cnt+1);

        }

    }

    private static void getReCombi(int start, int cnt){
        if(cnt == R){
            printRes();
            TOTAL_CNT += 1;
            return;
        }

        for(int i = start ; i < N ; ++i) {
            resArr[cnt] = inputs[i];
            getReCombi(i, cnt + 1);
        }
    }

    private static void getSubSetRec(int cnt){
        if(cnt == N){
            System.out.println(
                    Arrays.toString(IntStream.range(0, N)
                                            .filter(i -> visited[i])
                                            .map(i -> inputs[i])
                                            .toArray())
            );
            return;
        }
        visited[cnt] = true;
        getSubSetRec(cnt+1);
        visited[cnt] = false;
        getSubSetRec(cnt+1);
    }

    private static void getSubSetBit(){
        for(int i = 0 ; i < (1 << N) ; ++i ){
            for (int j = 0; j < N; ++j) {
                //System.out.println( i + "," + j + ", " + (i & (1 << j)));
                if( (i & (1 << j) ) != 0) System.out.print(inputs[j] + " ");
            }
            System.out.println();
        }

    }

    private static void printRes(){
        System.out.println(Arrays.toString(resArr));
    }
}
