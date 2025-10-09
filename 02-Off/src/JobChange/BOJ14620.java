package JobChange;
import java.io.*;
import java.util.*;
import java.awt.Point;

/* 주의 사항
* 1. 씨앗은 총3개
* 2. 모든 꽃 잎은 펴야한다. 지면 안됨
*   - 범위가 1 ~ N-1까지로 한정된다.
* */
public class BOJ14620{
    static int N;
    static int answer = Integer.MAX_VALUE;
    static int[][] costMap;
    static boolean[][] V;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        costMap = new int[N][N];
        V = new boolean[N][N];

        for(int i = 0 ; i < N ; ++i){
            costMap[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        dfs(0,0);

        System.out.println(answer);
    }

    private static void dfs(int depth, int sum){
        //백트레킹 부분
        // 현재 시행의 sum이 answer(지금까지 구한 최솟값)보다 크면 의미 없으므로 종료
        if(sum >= answer) return;

        //세 번째면 종료한다.
        if(depth == 3){
            answer = Math.min(answer, sum);
            return;
        }

        //완전 탐색 수행
        for(int i = 1 ; i < N -1 ; ++i){
            for(int j = 1 ; j < N - 1 ; ++j){
                //방문 안했고, 심을 수 있는 곳(꽃잎 겹치는 곳 없음)
                if(isPossible(j,i) && !V[i][j]){
                    int nowCost = getCost(j, i);
                    dfs(depth + 1, sum + nowCost);

                    //백트래킹된 요소가 있는 경우 방문처리를 취소한다.
                    clearVisit(j,i);
                }

            }
        }
    }

    //현재 점의 방문 여부
    private static boolean isPossible(int x, int y){
        for(int i = 0 ; i < 4; ++i) {
            if(V[y + dy[i]][x + dx[i]])
                return false;
        }

        return true;
    }

    //현재 점에서 구할 수 있는 금액
    private static int getCost(int x, int y){
        //현재점 방문 처리
        int sum = costMap[y][x];
        V[y][x] = true;

        for(int i = 0 ; i < 4; ++i) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            sum += costMap[ny][nx];
            V[ny][nx] = true;
        }
        return sum;
    }

    private static void clearVisit(int x, int y){
        V[y][x] = false;
        for(int i = 0 ; i < 4; ++i) {
            V[y + dy[i]][x + dx[i]] = false;
        }
    }
}