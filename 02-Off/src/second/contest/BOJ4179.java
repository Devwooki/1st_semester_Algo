package second.contest;
import java.awt.*;
import java.io.*;
import java.util.*;

public class BOJ4179 {
    static class Jihun{
        int x;
        int y;
        int cnt = 0;
        public Jihun(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
   
    static int R, C, result;
    static char[][] map;
    static int[][] direction = {{1,0},{0,1},{-1,0},{0,-1}};
    static Queue<Point> fires = new LinkedList<>();
    static Queue<Jihun> jh = new LinkedList<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        Point fire = null;
        Jihun jihun = null;
        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
                if(map[i][j] == 'J') jh.add(new Jihun(j,i,0));
                if(map[i][j] == 'F') fires.add(new Point(j,i));
            }
        }


        if(bfs()) System.out.println(result);
        else  System.out.println("IMPOSSIBLE");
    }
    //지훈이와 불은 매 분마다 1칸씩이동
    //불 4방향이동 후 지훈이 이동
    // 불 : 범위 이내 + #아닌 곳, F인 곳 제외 -> 불이 없을 수도 있다.
    // 지훈 : 가장자리 도착시 count 출력
    //       #랑 F인 곳 제외
    static boolean bfs(){

        while(!jh.isEmpty()){

            int numCase = fires.size();
            for(int test = 0 ; test < numCase; ++test){
                Point cur = fires.poll();
                //불 4방향으로 확산
                for (int i = 0; i < 4; i++) {
                    int nx = cur.x + direction[i][0];
                    int ny = cur.y + direction[i][1];
                    if(checkRange(nx,ny)){//범위체크
                        if(map[ny][nx]=='.' || map[ny][nx] =='J'){ //불이 갈 수 있는 곳은 .이거나 J인 곳 밖에 없다.
                            map[ny][nx] = 'F';
                            fires.offer(new Point(nx,ny));
                        }
                    }
                }
            }


            //지훈이 위치 옮김
            //지훈이의 위치는 현재큐의 사이즈 만큼만 돈다
            //현재 큐 사이즈 : 지훈이가 cnt만큼 움직인 횟수
            numCase = jh.size();
            for(int i = 0 ; i < numCase ; ++i){
                Jihun jhCur = jh.poll();
                for (int j = 0; j < 4; j++) {
                    int nx = jhCur.x + direction[j][0];
                    int ny = jhCur.y + direction[j][1];
                    if(!checkRange(nx,ny)){//다음위치가 범위를 벗어남 -> 현재위치가 가장자리
                        result = jhCur.cnt+1;
                        return true;
                    }

                    if(map[ny][nx] =='.'){
                        map[ny][nx] = 'J';
                        jh.offer(new Jihun(nx, ny, jhCur.cnt+1));
                    }
                }
            }
        }
        return false;
    }
    static boolean checkRange(int x, int y){
        return (0 <= x && x < C && 0 <= y && y < R);
    }

}
