package JobChange;

import java.io.*;
import java.util.*;

public class BOJ2615 {
    static int[][] map;
    static final int SIZE = 19;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        map = new int[SIZE][SIZE];
        //map 초기화
        for(int i = 0 ; i < SIZE ; ++i){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0 ; j < SIZE ; ++j){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //오목 찾기
        System.out.println(findWinner());
    }

    public static String findWinner(){
        StringBuilder sb = new StringBuilder();

        for(int i = 0 ; i < SIZE ; ++i){
            for(int j = 0 ; j < SIZE ; ++j){
                if(map[i][j] != 0){
                    if(isOmok(map[i][j], j, i)) {
                        sb.append(map[i][j] + "\n")
                                .append((i + 1) + " " + (j + 1));

                        return sb.toString();
                    }
                }
            }
        }

        return "0";
    }

    public static boolean isOmok(int egg, int x, int y){
        //상하 좌하, 우ㅎ상
        int[] dx = {1, 0, 1, 1};
        int[] dy = {0, 1, 1, -1};

        for(int dir = 0 ; dir < 4 ; ++dir){
            int cnt = 1;

            int nx = x;
            int ny = y;
            for(int i = 0 ; i < 5 ; ++i) {
                nx += dx[dir];
                ny += dy[dir];

                //좌표값 유효성 체크
                if(!checkRange(nx, ny)) break;
                //다음 위치의 값이 바둑돌과 다르면
                if(map[ny][nx] != egg) break;

                cnt++;
            }
            if(cnt == 5) {
                //육목인지 체크해야한다.
                nx = x - dx[dir];
                ny = y - dy[dir];

                if(!checkRange(nx, ny)) return true;

                if(map[ny][nx] == egg) continue;
                else return true;
            }
        }
        return false;
    }

    public static boolean checkRange(int x, int y){
        return ( 0 <= x && x < SIZE && 0 <= y && y < SIZE);
    }
}
