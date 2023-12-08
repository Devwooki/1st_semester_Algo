package practiceTest;

import java.awt.*;
import java.io.*;
import java.util.*;

public class BOJ2667_단지번호붙이기 {
    static int N, areaCnt;
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Integer> result = new ArrayList<>();
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        for(int i = 0 ; i < N ; ++i){
            for (int j = 0; j < N; j++) {
                //방문 완료된 곳이면 건너뜀
                if(visited[i][j]) continue;
                if(map[i][j] == 0) continue;

                //안된 곳인데 1 -> 검사 시작
                result.add(getArea(j, i));
                areaCnt++;
            }
        }

        StringBuilder sb =  new StringBuilder();
        sb.append(areaCnt + "\n");
        Collections.sort(result);
        result.stream().forEach(i -> {sb.append(i + "\n");});

        System.out.print(sb);
    }

    private static int getArea(int x, int y){
        int cnt = 1;
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};

        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(x, y));
        visited[y][x] = true;

        while(!q.isEmpty()){
            Point now = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(!checkRange(nx, ny)) continue;
                if(visited[ny][nx]) continue;
                if(map[ny][nx] == 0) continue;

                visited[ny][nx] = true;
                q.offer(new Point(nx, ny));
                cnt++;
            }
        }
        return cnt;
    }

    private static boolean checkRange(int x, int y){
        return ( 0 <= x  && x < N && 0 <= y && y < N);
    }
}
