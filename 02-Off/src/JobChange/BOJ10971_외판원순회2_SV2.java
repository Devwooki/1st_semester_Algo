package JobChange;

import java.io.*;
import java.util.*;

public class BOJ10971_외판원순회2_SV2 {
    static int N, answer = Integer.MAX_VALUE;
    static int[][] weight;
    static boolean[] visited;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        weight = new int[N][N];
        for(int i = 0 ; i < N ; ++i){
            weight[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        //각 정점별로 시작해서 외판원순회 시작
        for(int i = 0 ; i < N ; ++i){
            //visited 배열을 매번 새롭게 선언하는 것 보다. 함수가 종료되었을 때 초기화하는게 더 빠르다.. N크기가 다양하니
            //visited = new boolean[N];
            //visited[i] = true;

            visited[i] = true;
            TPS(0,0,i,i);
            visited[i] = false;
        }

        System.out.println(answer);
    }

    private static void TPS(int cnt, int cost, int start, int cur){
        //순회를 마친 경우
        if(cnt == N - 1){
            if(weight[cur][start] != 0){ //마지막 지점에서 시작지점으로 갈 수 있어야한다. 못가면 0이므로
                cost += weight[cur][start];
                answer = Math.min(answer, cost);
            }
            return;
        }

        for(int i = 0 ; i < N ; ++i){
            if(weight[cur][i] != 0 && !visited[i]) { //방문안하고, 갈 수 있는 경우에 원순회 시작
                visited[i] = true;
                TPS(cnt + 1, cost + weight[cur][i], start, i);
                visited[i] = false;
            }
        }
    }
}
/**
 * 한 지점에서 N개의 도시를 거쳐 원래도시로 돌아오는 경로 A -> B -> C -> A
 * 한번 간 곳은 돌아갈 수 없다. -> 방문체크
 *
 * 가장 적은 비용이 들어야한다.
 *  - weight[i][j] -> i에서 j로 가는 비용, 비용은 대칭적이지 않기에 i->j와 j -> i의 값으느 다를 숭 ㅣㅆ다.
 *  - 이동 비용은 양의 정수
 *  - 자기 자신에게 가는 것은 0
 *  - 갈 수 없는 경우 W[i][j] == 0
 * */