package practiceTest;

import java.io.*;
import java.util.*;
import java.awt.*;
public class BOJ14891_톱니바퀴 {
    static final int GEAR_SIZE = 8;
    static final int GEAR_CNT = 4;
    static int[][] gears = new int[4][8];
    static boolean[] visited;

    static int[] gearsTop = {0, 0, 0, 0};
    static int[] gearsL = {6, 6, 6, 6};
    static int[] gearsR = {2, 2, 2, 2};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        //서로 맞닿은 톱니바퀴의 극 != 서로 반대 방향으로 돈다.
        // 12시 부터 순서대로 주어진다.
        for(int i = 0 ; i < GEAR_CNT ; ++i){
            gears[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        int TC = Integer.parseInt(br.readLine());
        for(int i = 0 ; i < TC ; ++i){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int gearNum = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken());

            visited  = new boolean[GEAR_CNT];
            simulation(gearNum, dir);
            for(int h = 0 ; h < GEAR_CNT ; ++h){
                System.out.println(gearsTop[h] + ", " + gearsL[h] + ", " + gearsR[h]);
            }
            System.out.println("====================");

        }

        System.out.println(getScore());
    }

    /**@Param gearNum : 기어번호
     * @Param dir : 방향(1 시계, -1 반시계)*/
    private static void simulation(int gearNum,  int dir){
        /*sue
         * if gearNum이 0보다 작거나 3보다 작으면 반환
         * if 이미 회전한 기어면 멈춤
         *
         * 1. 현재 기어 방문 처리
         * 2. 현재기어의 접점 극성 체크
         * 3. 현재 기어 방향에 맞춰 회전
         * 4. 접점 극점에 따라 좌우 톱니 회전 시키기
         *   - 방문안했는지 체크 -> 안했으면 dfs
         *   - 극점 다른지 체크 -> 다르면 dir * (-1)
         * */
        int[] rotate= new int[GEAR_CNT];
        rotate[gearNum] = dir;

        //기어 왼쪽 체크
        int tempDir = dir;
        int nowL = gears[gearNum][gearsL[gearNum]];
        for(int i = gearNum-1 ; i >= 0; --i){
            if(nowL == gears[i][gearsR[i]]) continue;

            //다르면 반대 방향으로 회전해야한다.
            tempDir *= -1;
            rotate[i] = tempDir;

        }
        //기어 오른쪽 체크
        tempDir = dir;
        int nowR = gears[gearNum][gearsR[gearNum]];
        for(int i = gearNum+1 ; i < GEAR_CNT ; ++i){
            if(nowR == gears[i][gearsL[i]]) continue;

            //다르면 반대 방향으로 회전해야한다.
            tempDir *= -1;
            rotate[i] = tempDir;
        }

        //기어들 전부 회전
        for(int i = 0 ; i < GEAR_CNT ; ++i){
            rotateGear(i, rotate[i]);
        }
    }

    private static void rotateGear(int gearNum,  int dir){
        if(dir == 1){ //시계 방향 +1씩
            gearsTop[gearNum] = (Math.abs(gearsTop[gearNum]+1)) % 8;
            gearsL[gearNum] = (Math.abs(gearsL[gearNum]+1)) % 8;
            gearsR[gearNum] = (Math.abs(gearsR[gearNum]+1)) % 8;
        }else if(dir == -1){
            gearsTop[gearNum] = (Math.abs(gearsTop[gearNum]-1)) % 8;
            gearsL[gearNum] = (Math.abs(gearsL[gearNum]-1)) % 8;
            gearsR[gearNum] = (Math.abs(gearsR[gearNum]-1)) % 8;
        }
    }

    private static int getScore(){
        int result = 0;
        for(int i = 0 ; i < GEAR_CNT; ++i){
            System.out.print(gears[i][gearsTop[i]] + " ");
            result += gears[i][gearsTop[i]] == 0 ? 0 : (1 << i);
        }
        return result;
    }
}
