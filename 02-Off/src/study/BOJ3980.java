package study;

import java.io.*;
import java.util.*;

public class BOJ3980 {
    static final int SIZE = 11;
    static int res;
    static boolean[] selected;
    static int[][] map = new int[SIZE][SIZE];
    static List<Integer>[] players = new LinkedList[SIZE];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int C = Integer.parseInt(br.readLine());

        for(int tc = 0 ; tc < C ; ++tc){
            res = 0;
            selected = new boolean[SIZE];
            for (int i = 0; i < SIZE; i++) {
                players[i] = new LinkedList<>();
            }

            //각 리스트별 점수 기록
            for(int i = 0 ; i < SIZE ; ++i){
                map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }

            //백트래킹 수행
            makeTeam(0, 0);

            sb.append(res + "\n");
        }

        System.out.println(sb);
    }

    private static void makeTeam(int depth, int sum) {
        if(depth ==SIZE){
            //모든 포지션에 배치가 완료되었는지 체크
            //if(checkAllSelect()) res = Math.max(sum, res); 포지션을 설정할 때 한 번 필터링하므로 불필요함
            res =  Math.max(sum, res);
            return;
        }

        for(int i = 0 ; i < SIZE ; ++i){
            if(map[i][depth] == 0) continue; //포지션에 재능이 없으면 제
            if(selected[i]) continue; //해당 포지션이 선택되었다면 패스

//            selected[depth] = true;
//            makeTeam(depth+1, sum+map[i][depth]);
//            selected[depth] = false;

            //depth가 아닌 이유
            //sij는 i번선수가 j번 포지션에서 뛸 때의 능력
            selected[i] = true;
            makeTeam(depth+1, sum+map[i][depth]);
            selected[i] = false;
        }

    }

    static boolean checkAllSelect(){
        for(int i = 0 ; i < SIZE ; ++i){
            if(!selected[i]) return false;
        }
        return true;
    }
}
