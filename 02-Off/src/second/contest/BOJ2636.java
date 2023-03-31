package second.contest;

import com.sun.xml.internal.ws.api.ha.StickyFeature;

import java.util.*;
import java.io.*;

public class BOJ2636 {
    static int[][] map;
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

    //시작하기 전에 치즈의 수를 샌다 -> 모두 녹기전 치즈 조각의 수를 세야하므로
    //치즈 가장자리 2로 바꾸기
    //2를 0으로 바꾸기
    private static int simulation() {
        int cnt = 0;
        while(true){

            cnt = checkCheese();
            //치즈가 0이면 모든 치즈가 녹은 것
            if(cnt == 0) return cnt;

            for(int i = 0 ; i < H ; ++i)  for(int j = 0 ; j < W; ++j) dfs(j, i);

            meltTime += 1;
        }
    }

    private static void dfs(int x, int y){
        for(int i = 0 ; i < 4 ; ++i){
            int nx = x + dir[i][0];
            int ny = y + dir[i][1];
            if(!checkRange(nx, ny)) continue; //범위 벗어나면 다음 방향 탐색


        }

    }

    private static int checkCheese(){
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
