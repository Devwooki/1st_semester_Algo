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
            if(cnt == 0) return cnt;

            meltTime += 1;
        }
    }

    private static int checkCheese(){
        int cnt= 0;
        for (int i = 0; i < H; i++) for (int j = 0; j < W; j++)
                if(map[i][j] != 0 ) cnt++;
        return cnt;
    }
}
