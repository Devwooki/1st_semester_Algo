
package JobChange.BackTracking;

import java.io.*;
import java.util.*;


/*
* 팀의 능력치는 팀에 속한 모든 쌍의 능력치
* Sij와 Sji는 다를 수 있음
*  i번 사람과 j번 사람이 같은 팀에 속했을 때, 팀에 더해지는 능력치는 Sij와 Sji이다.
* 두 팀의 능력치 차이를 최소
* */

public class BOJ14889 {
    static int[][] map;
    static boolean[] visited;
    static int N, answer = Integer.MAX_VALUE, tt;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N];

        for(int i = 0 ; i  < N ; ++i){
            map[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        combi(0, 0);

        System.out.println(answer);
    }

    private static void combi(int cnt, int start){
        if(cnt == N/2){
            getAnswer();
            return;
        }

        for(int i = start ; i < N ; ++i){
            visited[i] = true;
            combi(cnt+1, i + 1);
            visited[i] = false;
        }
    }

    private static void getAnswer() {
        int start = 0;
        int link = 0;

        for(int i = 0 ; i < N - 1 ; ++i){
            for(int j = i ; j < N ; ++j){
                //스타트팀
                if(visited[i] && visited[j])
                    start += (map[i][j] + map[j][i]);

                //링크팀
                else if(!visited[i] && !visited[j])
                    link += (map[i][j] + map[j][i]);
            }
        }

        int diff = Math.abs(start - link);

        //차이가 0이면 최소이므로 종료
        if(diff == 0){
            System.out.println(0);
            System.exit(0);
        }

        answer = Math.min(diff, answer);
    }
}
