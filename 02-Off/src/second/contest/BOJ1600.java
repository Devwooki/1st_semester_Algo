package second.contest;
import java.util.*;
import java.io.*;
/*말은 장애물을 뛰어넘을 수 있다.
    - 직선이 아닌 3칸 떨어진 곳으로 이동할 수 있음
    - 원숭이는 k번만 움직일 수 있다, 그 이외는 인접한 상하좌우 이동
    - (0,0) -> (N-1, N-1)로 가야한다.
    - 최소한의 동작으로 시작지점엣 ㅓ도착지머으로 가는 방법
    - 0은 유효한 길, 1은 못가는 길
    - 갈 수 없다면 -1로 출력
*/

/*
#SC2. visited배열을 [ny][nx]로만 체크
-> 다양한 방법으로 통해 ny,nx에 도착시 horseDir을 몇 번 사용했는지 체크 불가
-> 3차원 배열로바 꿔 [ny][nx][horse]를 통해 체크하자.
*/

public class BOJ1600 {
    static int K, W, H, result = Integer.MAX_VALUE;
    static int[][] map;
    //static boolean[][] visited;
    static boolean[][][] visited;
    static int[][] dir = {{1,0},{0,1},{-1,0},{0,-1}};
    static int[][] horseDir = {
            {-2,-1},{-1,-2},{1,-2},{2,-1},
            {-2,1},{-1,2},{1,2},{2,1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        K = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H][W];
        visited = new boolean[H][W][K+1];
        //K : k번 이동한 횟수가 된다.
        //K + 1 해주는 이유, 입력된 K가 1일 때
        //K=1, K=0 일때 비교를 해야하므로

        for(int i = 0 ; i < H ; ++i){
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        System.out.println(bfs());
    }

    static int bfs(){
        Queue<Point> q = new LinkedList<>();

        q.add(new Point(0,0,0, 0));

        while(!q.isEmpty()){
            Point now = q.poll();

            if(now.x==W-1 && now.y==H-1){
                /*
                * 최소값 계산할 때 한 번 방문한 위츠는 재방문해서는 안된다.
                    -> visited 방문을 체크해 백트래킹
                * */
                return result = now.cnt;
            }

            for(int i = 0 ; i < 4 ; ++i){
                int nx = now.x + dir[i][0];
                int ny = now.y + dir[i][1];
                if(!checkRange(nx,ny)) continue;
                if(visited[ny][nx][now.horse]) continue;
                if(map[ny][nx] == 1) continue;

                visited[ny][nx][now.horse] = true;
                q.add(new Point(nx, ny, now.cnt+1, now.horse));
            }

            if(now.horse < K){
                for(int i = 0 ; i < 8 ; ++i){
                    int nx = now.x + horseDir[i][0];
                    int ny = now.y + horseDir[i][1];
                    if(!checkRange(nx,ny)) continue;
                    if(visited[ny][nx][now.horse+1]) continue;
                    //해당 위치로 이동할 거기 때문에 now.horse+1해준다.
                    if(map[ny][nx] == 1) continue;

                    visited[ny][nx][now.horse+1] = true;
                    q.add(new Point(nx, ny, now.cnt+1, now.horse+1));
                }
            }
        }
        return -1;
    }
    public static boolean checkRange(int nx, int ny){
        return (0 <= nx && nx < W && 0 <= ny && ny < H);
    }

    static class Point{
        int x;
        int y;
        int cnt;
        int horse;

        public Point(int x, int y, int cnt, int horse) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.horse = horse;
        }
    }
}
