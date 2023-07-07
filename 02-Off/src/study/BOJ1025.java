package study;

import java.io.*;
import java.util.*;

public class BOJ1025 {
    static int result = -1;
    static int N, M;
    static int[][] arr;
    static TreeSet<Integer> set = new TreeSet<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        for(int i = 0 ; i < N ; ++i){
            arr[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        /*
        체크 하는 방법은 매우 많다
        1. 행마다
        2. 열마다
        3. 오른쪽 아래 대각선 -> 수열 만들 때 마다 reverse
        4. 오른쪽 위쪽 대각선 -> 수열 만들 때 마다 reverse
        */

        //외부 for문은  수열의 시작 지점을 잡아준다
        for(int i = 0 ; i < N ; ++i){
            for(int j = 0 ; j < M ; ++j){
                makeNumSeq(j,
                        i);
            }
        }
        for (Iterator<Integer> it = set.iterator(); it.hasNext(); ) {
            Integer i = it.next();
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println("사이즈 : " + set.size());
        System.out.println("결과 : "+ result);
    }

    private static void makeNumSeq(int x, int y) {

        //등차 수열은 증가만 하는 것 아닌 감소도 등차수열이기에 -n ~ n, -m ~ m의 범위를 탐색해야한다.
        for(int i = -N ; i < N ; ++i){
            for(int j = -M ; j < M ; ++j){

                //등차 수열은 숫자가 적어도 2개 이상이어야한다 -> 자기 자신은 건너뛴다.
                if(i == 0 && j == 0) continue;

                int nx = x;
                int ny = y;
                StringBuilder sb = new StringBuilder();
                while (checkRange(nx, ny)){
                    sb.append(arr[ny][nx]);
                    getSQRT(Integer.parseInt(sb.toString()));
                    set.add(Integer.parseInt(sb.toString()));
                    nx += j;
                    ny += i;
                }
            }
        }
    }

    private static boolean checkRange(int nx, int ny) {
        return ( 0 <= nx && nx < M && 0 <= ny && ny <N);
    }

    //제곱근인지 판별하는 함수
    private static void getSQRT(int x){
        int isSqrt = (int) Math.sqrt(x);
        if(isSqrt * isSqrt == x) result = Math.max(result, x);
    }
}
