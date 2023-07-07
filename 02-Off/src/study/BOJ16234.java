package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
- 국경을 공유하는 두나라 인구차이가 L이상 R이하면 국경선 하루 연다
- 국경선 열리면 인구이동 가능 -> 그날 하루 연합
- 연합을 이루고 있는 각 칸의 인구수
  (연합의 인구수) / 연합을 이루고 있는 칸의 개수
- 소수 버림
*/
public class BOJ16234 {
    static int N, L, R;
    static int[][] map;
    static int[][] dir = {{1,0},{0,1},{-1,0},{0,-1}};
    static boolean[][] visited;
     static List<Point> union;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        for(int i = 0 ; i < N ; ++i){
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        System.out.println(simulation());
    }

    private static int simulation() {
        int result = 0;

        while(true){
            //매번 시행마다 방문 배열과 조합을 찾을 수 있는지 체크
            visited = new boolean[N][N];
            boolean isChange = false;

            //n번째 시행 : 연합 찾기
            for (int i = 0; i < N; ++i) {
                for(int j = 0 ; j < N ; ++j){
                    if(!visited[i][j]) {// 방문하지 않은 곳이면 연합 가능성체크
                        bfs(j, i);

                        //bfs탐색이 끝나면 인구이동 후 재조합 한다.
                        //재조합 할 때 list가 자기 자신밖에 없으면 무시
                        if(union.size() > 1) {
                            rePopulation();
                            isChange= true;
                        }
                    }
                }
            }

            //끝까지 탐색을 마치고 나왔는데 인구수 변화가 없으면 종료
            if(!isChange) return result;
            result++;
        }
    }

    private static void bfs(int x, int y) {
        Queue<Point> q = new LinkedList<>(); //BFS 탐색을 위한 큐
        union = new ArrayList<>();  //연합 정보를 저장할 리스트

        q.offer(new Point(x, y));
        union.add(new Point(x, y));
        visited[y][x] = true;

        while(!q.isEmpty()){
            Point now = q.poll();

            for(int i = 0 ; i < 4 ; ++i){
                int nx = now.x + dir[i][0];
                int ny = now.y + dir[i][1];
                if(!checkRange(nx, ny))continue;
                if(visited[ny][nx]) continue;

                if(isUnion(now.x, now.y, nx, ny)){
                    visited[ny][nx] = true;
                    q.offer(new Point(nx, ny));
                    union.add(new Point(nx, ny));
                }
            }
        }
    }

    static void rePopulation(){
        int sum = 0;
        for(Point p : union) sum += map[p.y][p.x];

        int avg = sum/union.size();
        for(Point p : union) map[p.y][p.x] = avg;
    }

    static boolean checkRange(int x, int y){
        return ( 0 <= x && x < N && 0 <= y && y < N );
    }

    static boolean isUnion(int x, int y, int nx, int ny){
        int diff = Math.abs(map[y][x] - map[ny][nx]);
        return (L <=  diff && diff <= R);
    }
}

