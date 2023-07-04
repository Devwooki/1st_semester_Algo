package second.contest;

import java.util.*;
import java.io.*;

public class BOJ2636 {
    static int[][] map;
    static boolean[][] visited;
    static int H, W, meltTime = 0;
    static int[][] dir = {{1,0},{0,1},{-1,0},{0,-1}}; //상하좌우 체크
    public static void main(String[] args) throws IOException {
        init();
    }
    private static void init() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        map = new int[H][W];
        for (int i = 0; i < H; i++)
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int lastCheese = simulation();
        System.out.println(meltTime + "\n" + lastCheese);
    }

    //시작하기 전에 치즈의 수를 샌다
    //치즈 가장자리 2로 바꾸기
    //값이 2인 치즈(가장자리)를 0으로 바꾸고 1의 갯수를 센다
    //1이 0개면 시뮬레이션 종료 치즈 수를 종료직전의 치즈수를 반환한다.

    private static int simulation() {
        int cntCheese = meltCheese();
        while(true){
            visited = new boolean[H][W];//방문 배열을 매번 초기화한다.
            dfs(0,0);

            int temp = meltCheese();
            //탐색이 끝나면 시간을 1 증가시킨다.
            meltTime += 1;

            if(temp ==0) return cntCheese;
            else cntCheese = temp;

        }
    }

    //가장자리를 발견하고 2로 바꾸는 작업을 수행
    //치즈를 중심으로 이동이아닌, 공기를 중심으로 근처에 치즈가 있으면 2로 만들어야한다.
    //즉 외부와 맞닿은 치즈 발견
    private static void dfs(int x, int y){
        for(int i = 0 ; i < 4 ; ++i){
            int nx = x + dir[i][0];
            int ny = y + dir[i][1];
            if(!checkRange(nx, ny)) continue; //범위 벗어나면 다음 방향 탐색
            if(!visited[ny][nx]){//가보지 않은 곳이면
                visited[ny][nx] = true; //방문체크하고
                if(map[ny][nx]==1) map[ny][nx] = 2;
                else dfs(nx, ny);
            }
        }
    }

    private static int meltCheese(){
        int cnt= 0;
        for (int i = 0; i < H; i++) for (int j = 0; j < W; j++) {
            //가장자리 값이 2면 외각에 있는 치즈 -> 지운다.
            if (map[i][j] == 2) map[i][j] = 0;
            // 남은 치즈의 수를 측정
            if (map[i][j] == 1) cnt++;
        }
        return cnt;
    }

    private static boolean checkRange(int nx, int ny){
        return (0<=nx && nx < W && 0 <= ny && ny < H);
    }
}
