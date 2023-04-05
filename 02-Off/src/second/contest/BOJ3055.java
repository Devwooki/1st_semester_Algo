package second.contest;
import java.awt.*;
import java.util.*;
import java.io.*;
import java.util.List;

/**
 * . : 비어있는 곳
 * * : 물차있는 곳
 * X : 돌
 * D : 비버의 굴
 * S : 고슴도치 위치
 *
 * S는 인접한 4칸으로 이동 가능,
 * *도 인접한 . 4칸으로 확장
 * S와 *는 X를 통과할 수 없다.
 * S는 *으로 이동할 수 없고, *은 D로 이동할 수 없다.
 *
 * 고슴도치가 안전하게 굴로 이동하기 위한 최소시간
 */
public class BOJ3055 {
    static int R,C, result;
    static char[][] map;
    static int[][] dir = {{1,0},{0, 1},{-1, 0},{0, -1}};
    static Queue<Point> qw = new LinkedList<>();
    static Queue<Hedgehog> qh = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];

        for(int i = 0 ; i < R ; ++i){
            map[i] = br.readLine().toCharArray();
            for(int j = 0 ; j < C ; ++j){
                if(map[i][j] == '*')
                    qw.add(new Point(j, i));
                else if(map[i][j] == 'S') {
                    //map[i][j] = '.';
                    qh.add(new Hedgehog(j, i, 1));
                }
            }
        }
        bfs();

        System.out.println(result == 0 ? "KAKTUS" : result);

    }
    //물이 찰 예정인 칸으로 고슴도치는 이동할 수 없음
    // 물 확산 후 고슴도치 이동
    static void bfs(){

        while(!qh.isEmpty()) {
            //물 확산
            int size = qw.size();
            for(int iter = 0 ; iter < size ; ++iter){
                Point p = qw.poll();
                for (int i = 0; i < 4; ++i) {
                    int nx = p.x + dir[i][0];
                    int ny = p.y + dir[i][1];
                    if (!checkRange(nx, ny)) continue; //범위 체크, 갈 수 있는 곳 체크
                    //if (map[ny][nx] == 'X' || map[ny][nx] == '*' || map[ny][nx] == 'D') continue;
                    if(map[ny][nx] == '.'){
                        map[ny][nx] = '*';
                        qw.offer(new Point(nx, ny));
                    }

                    //qw.offer(new Point(nx, ny));

                }
            }


            //고슴도치 이동
            size = qh.size();
            for(int iter = 0 ; iter < size ; ++iter){
                Hedgehog h = qh.poll();

                for (int i = 0; i < 4; ++i) {
                    int nx = h.x + dir[i][0];
                    int ny = h.y + dir[i][1];
                    if(!checkRange(nx, ny)) continue;

                    if(map[ny][nx] == 'D'){
                        result = h.cnt;
                        return;
                    }else if(map[ny][nx] == '.'){
                        map[ny][nx] = 'S';
                        qh.offer(new Hedgehog(nx, ny, h.cnt+1));
                    }

                }
            }
        }
    }
    static boolean checkRange(int x, int y){
        return (0 <= x && x < C && 0 <= y && y < R);
    }

    static class Hedgehog{
        int x;
        int y;
        int cnt;

        public Hedgehog(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
}
