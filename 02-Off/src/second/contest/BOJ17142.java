package second.contest;
import java.util.*;
import java.io.*;
import java.util.List;

public class BOJ17142 {
    static int N, M, result=Integer.MAX_VALUE;
    static int[][] map;
    static int[][] copyMap;
    static boolean[][] visited;
    static int[] numbers;
    static int[][] dir = {{1,0},{0,1},{-1,0},{0,-1}};
    static List<Virus> viruses = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        numbers = new int[M];
        for(int i = 0 ; i < N ; ++i ){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < N ; ++j){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j]==2) {
                    map[i][j] = -2;
                    viruses.add(new Virus(j, i, 0));
                }else if(map[i][j] == 1) map[i][j] = -1;
            }
        }

        nCr(0,0);

    }

    //바이러스를 배치할 조합 탐색
    private static void nCr(int cnt, int start) {
        if(cnt == M){
            simulation();
            return;
        }
        for(int i = start ; i < viruses.size() ; ++i){
            numbers[cnt] = i;
            nCr(cnt+1, i + 1);
        }
    }

    private static void simulation(){
        copyMap = new int[N][N];
        for(int i = 0 ; i < N ; ++i){
            copyMap[i] = map[i].clone();
        }
        //조합에 따라서 바이러스를 재배치한다.
        for(int i = 0 ; i < M ; ++i) {
            copyMap[viruses.get(numbers[i]).y][viruses.get(numbers[i]).x] = 2;
            //  2 : 활성 바이러스
            // -2 : 비활성 바이러스;
        }
        //배치가 끝나면 bfs탐색 실시
        bfs();
    }

    private static void bfs() {
        Queue<Virus> q = new LinkedList<>();
        for(int num : numbers) q.offer(viruses.get(num));

        int max = -1;
        while(!q.isEmpty()){
            Virus v = q.poll();

            max = Math.max(max, v.cnt);
            for(int i = 0 ; i < 4 ; ++i){
                int nx = v.x + dir[i][0];
                int ny = v.y + dir[i][1];
                if(!checkRange(nx, ny)) continue; //좌표 유효성 체크

                //활성 바이러스 = 2, 비활성 -2
                if(copyMap[ny][nx] == 0){ //방문하지 않은 곳만 탐색
                    copyMap[ny][nx] = v.cnt+1;
                    q.offer(new Virus(nx, ny, v.cnt+1));
                }
            }
        }
        if(getResult()){
            result = Math.min(result, max);
        }

    }

    //getResult()에서 max와 min을 갱신하려했음
    //-> 매 시행마다 갱신하는 과정에서 어려움 발생...
    //boolean으로 바꿔서 처리
    private static boolean getResult(){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(copyMap[i][j] == 0){
                    return false;
                }
            }
        }
        return true;
    }
    private static boolean checkRange(int x, int y){
        return (0 <= x && x < N && 0 <= y && y < N);
    }
    static class Virus{
        int x;
        int y;
        int cnt;

        public Virus(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }

        @Override
        public String toString() {
            return "Virus{" +
                    "x=" + x +
                    ", y=" + y +
                    ", cnt=" + cnt +
                    '}';
        }
    }
}
