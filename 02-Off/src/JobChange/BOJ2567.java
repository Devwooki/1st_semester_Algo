package JobChange;

import java.io.*;
import java.util.*;

public class BOJ2567 {

    private static boolean[][] map = new boolean[110][110];
    private static int N;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        N = Integer.parseInt(br.readLine());
        int cnt = 0 ;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken())-1;
            int y = Integer.parseInt(st.nextToken())-1;

            for(int dx = x; dx < x + 10 ; ++dx){
                for(int dy = y ; dy < y + 10 ; ++dy){
                    map[dy][dx] = true;
                }
            }
        }

        System.out.println(solution());
    }

    /**둘레를 구해야한다.
     * 해당변의 상하좌우 중 false 부분이 있다면 cnt 증가
     *
     * */
    private static int solution(){
        int[][] direction = {
                {0, 1}, {1,0},{0,-1},{-1,0}
        };
        int answer = 0;

        for(int i = 0 ; i < 100 ; ++i){
            for (int j = 0; j < 100; j++) {
                //색칠된 영역의 상하좌우를 탐색해, 색칠안된곳이면 cnt++;
                if (map[i][j]) {
                    //상하좌우 검증 수행
                    for (int k = 0; k < 4; ++k) {
                        //상하좌우가 범위 밖을 나갔으면 cnt++해주
                        if (!checkRange(i + direction[k][0], j + direction[k][1])){
                            answer += 1;
                            continue;
                        }

                        if (!map[i + direction[k][0]][j + direction[k][1]]) answer += 1;

                    }

                }
            }
        }


        return answer;
    }



    private static boolean checkRange(int x, int y){
        return((0 <= x && x < 100) &&(0 <= y && y < 100) );
    }
}
