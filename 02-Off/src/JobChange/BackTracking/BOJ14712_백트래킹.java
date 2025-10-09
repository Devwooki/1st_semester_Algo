package JobChange.BackTracking;

import java.io.*;
import java.util.*;
public class BOJ14712_백트래킹 {
    static int N, M, answer = 0;
    static boolean[][] map;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new boolean[N][M];
        /* 문제 이해가 안간다.
        * 네모가 게임을 그만두었을 때 나올 수 있는 배치의 수
        * 0,0부터 N,M까지 이동하면서 2*2인지 체크한다.
        *
        *  - 네모는 두거나 지나치거나 가능
        *  - 매번 탐색 마다 제거가능한 넴모 있는지 체크하기
        * */

        backTracking(0);
        System.out.println(answer);
    }

    private static void backTracking(int depth){
        if(depth == N * M){
            //전체 탐색 후 2*2 만들었는지 체크
            if(check()) answer++;
            return;
        }

        //
        int x = depth % M;
        int y = depth / M;

        //네모 놓는경우
        map[y][x] = true;
        backTracking(depth + 1);

        //네모 놓지 않는 경우
        map[y][x] = false;
        backTracking(depth + 1);
    }

    private static boolean check(){
        // 2 * 2 배열을 검증하기 위해선 제일 마지막 요소는 안해도 된다.
        for(int i = 0 ; i < N - 1 ; ++i){
            for(int j = 0 ; j < M - 1 ; ++j){
                if(map[i][j] && map[i+1][j] && map[i][j+1] && map[i+1][j+1])
                    return false;
            }
        }

        return true;
    }

}
