package second.contest;
import java.awt.*;
import java.util.*;
import java.io.*;
public class BOJ9205 {
    //맥주를 마시며 걷다가 빈 병을 버리고 새 맥주를 살 수 있다.
    //하지만 박스에 들어있는 맥주는 20병으 넘을 수 없기 때문에 50미터당 1병 마셔야한다.
    // 좌표 사이의 거리는 x좌표차이 + y좌표 차이

    static int n;
    static int[][] costs;
    static Point[] arr;
    static final int INF = 1<<20;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        for(int tc = 0 ; tc < t ; ++t){
            n = Integer.parseInt(br.readLine());

            costs = new int[n+2][n+2]; //집과 페스티벌의 좌표도 필요하기 때문에 +2
            for(int i = 0 ; i < n ; ++i){
                Arrays.fill(costs[i], INF);
                costs[i][i] = 0;
            }
            arr = new Point[n+2];

            for(int i = 0 ; i < n + 2 ; ++i){
                st = new StringTokenizer(br.readLine());
                arr[i] = new Point(
                        Integer.parseInt(st.nextToken()),
                        Integer.parseInt(st.nextToken()));
            }

            //cost를 그리는 과정은 전체를 그리기 보다 삼각형 영역만 그리면 된다.
            //i == j인 영역은 skip하고 유효한 곳만 구하기
            // _ * * *
            //   _ * *
            //     _ *
            //       _
            for(int i = 0 ; i < n+2 ; ++i){
                for(int j = i ; j < n + 2 ; ++j){
                    if(i == j) continue;
                    //해당 영역으로 갈 수 있는지 체크
                    if(checkDist(i,j)){
                        costs[i][j]= costs[j][i] = 1;
                    }
                }
            }
        }
    }

    static boolean checkDist(int i, int j){
        return Math.abs(arr[i].x - arr[j].x) + Math.abs(arr[i].y-arr[j].y) <= 1000;
    }

    static void floyd(){
        for (int k = 0; k < n+2; k++) {
            for (int i = 0; i < n+2; i++) {
                if(i == k) continue;
                for (int j = 0; j < n+2; j++) {
                    costs[i][j] = Math.min(costs[i][j],
                            costs[i][k] + costs[k][j]);
                }
            }
        }
    }
}
