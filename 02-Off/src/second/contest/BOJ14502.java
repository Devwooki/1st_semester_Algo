package second.contest;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

/**
 * 0 빈칸
 * 1은 벽
 * 2 바이러스
 *
 * 바이러스는 상하좌우 인접한 칸으로 확산됨
 */

//안전 영역의 최댓값을 구하기, 벽은 총 3개 놓을 수 이있다.
public class BOJ14502 {
    private static int N, M, result = Integer.MIN_VALUE;
    private static int[] numbers;
    private static int[][] map, copyMap;
    private static boolean[][] visited;
    private static int[][] dir = {{1,0},{0,1},{-1,0},{0,-1}};
    private static List<Point> wall = new ArrayList<>();
    private static List<Point> viruses = new ArrayList<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        numbers = new int[3];
        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M ; ++j){
                map[i][j] = Integer.parseInt(st.nextToken());

                if(map[i][j] == 0) wall.add(new Point(j, i));
                else if(map[i][j] == 2) viruses.add(new Point(j, i));
            }
        }
        //System.out.println(wall.size());
        nCr(0,0);
        System.out.println(result);
    }
    //벽을 놓을 수 있는 조합을 구한다.
    private static void nCr(int cnt, int start){
        if(cnt == 3){
            //System.out.println(Arrays.toString(numbers));
            simulation();
            return;
        }
        for(int i = start ; i < wall.size() ; ++i){
            numbers[cnt] = i;
            nCr(cnt+1, i+1);
        }
    }
    //조합을 구한 뒤 맵 복사 -> 복사한 맵에 가벽을 세운다.
    private static void simulation() {
        //맵을 copy
        copyMap = new int[N][M];
        for(int i = 0 ; i < N ; ++i){
            copyMap[i] = map[i].clone();
        }
        //맵을 새롭게 그린다.
        for(int i = 0 ; i < 3 ; ++i){
            copyMap[wall.get(numbers[i]).y][wall.get(numbers[i]).x] = 1;
        }
        //그린 맵을 중심으로 bfs를 진행
        bfs();
    }

    private static void bfs(){
        Queue<Point> q = new LinkedList<>();
        for(Point p : viruses) q.offer(p);

        while(!q.isEmpty()){
            Point virus = q.poll();
            for(int i = 0 ; i < 4 ; ++i){
                int nx = virus.x + dir[i][0];
                int ny = virus.y + dir[i][1];
                if(!checkRange(nx,ny)) continue;
                if(copyMap[ny][nx] == 0 ){
                    copyMap[ny][nx] = 2;
                    q.offer(new Point(nx, ny));
                }
            }
        }
        cntSafetyZone();
    }
    private static void cntSafetyZone(){
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(copyMap[i][j]==0) cnt++;
            }
        }
        result = Math.max(result, cnt);
    }

    private static boolean checkRange(int x, int y){
        return ( 0 <= x && x < M && 0 <= y && y < N);
    }

}
//1 조합으로 풀어본다.
//2 dfs로 풀어본다.
