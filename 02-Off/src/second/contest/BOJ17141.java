package second.contest;
import java.awt.*;
import java.util.*;
import java.io.*;
import java.util.List;

public class BOJ17141 {
    static int N, M, result=Integer.MAX_VALUE;
    static int[][] map;
    static int[][] copyMap;
    static int[] numbers;
    static int[][] dir = {{1,0},{0,1},{-1,0},{0,-1}};
    static List<Point> viruses = new LinkedList<>();

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
                    map[i][j] = 0;
                    viruses.add(new Point(j ,i));
                }
            }
        }

        nCr(0,0);

    }

    private static void nCr(int cnt, int start) {
        if(cnt == M){
            //simulation();
            System.out.println(Arrays.toString(numbers));
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
            copyMap[i] = map[i];
        }

        for(int i = 0 ; i < M ; ++i){
            map[viruses.get(numbers[i]).y][viruses.get(numbers[i]).y] = 2;
        }
        bfs();
    }

    private static void bfs() {
        Queue<Virus> q = new LinkedList<>();
        for(Point p : viruses) q.offer(new Virus(p.x, p.y, 0));

        while(!q.isEmpty()){
            Virus v = q.poll();
            for(int i = 0 ; i < 4 ; ++i){
                int nx = v.x + dir[i][0];
                int ny = v.y + dir[i][1];
                if(!checkRange(nx, ny)) continue;
            }
        }

    }
    private static void getResult(){
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(copyMap[i][j] == 0){
                    result=-1;
                    return;
                }
            }
        }
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
    }
}
