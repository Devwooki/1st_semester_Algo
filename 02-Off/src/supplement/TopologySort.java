package supplement;

/*
* 위상정렬 : 선후가 정의된 그래프에서 적용한다.
*
* 위상정렬 그래프는 단방향 그래프 형태
* 진입 차수와 진출 차수를 고려해야하며 그래프에 순환이 있을 경우 불가능함
*
* 구현방법
* 0. 그래프 노드별 진입 차수 계산
* 1. 진입차수가 0인 것(진입 시작) 모두 큐에 넣기
* 2. 큐에서 뽑은 노드의 진입차수 -1(진입 차수 갱신)
* 3. 진입 차수가 0이되면 큐에 넣기
* 4. 큐에 아무것도 없을 때 까지 반복
*
* => 진입 차수가 모두 0 -> 위상정렬 완료
* => 진입 차수 != 0이 존재 -> 위상정렬 불가능
* */

import java.io.*;
import java.util.*;

public class TopologySort {
    static int N, M;
    static ArrayList<Integer>[] graph;
    static int[] into;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        //인접 리스트 생성
        graph = new ArrayList[N+1];
        into = new int[N+1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<Integer>();
        }

        //인접 리스트 초기화
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            into[v]++;
        }

        System.out.println(topologySort() ? sb.toString() : "정렬실패");
    }

    private static boolean topologySort() {
        //진입차수 계산, 진입차수가 0이면 q에 삽입
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if(into[i] == 0) {
                q.offer(i);
                sb.append(i + " "); //진입 차수가 0이면 출력한다.
            }
        }

        while(!q.isEmpty()){
            int cur = q.poll(); //현재노드에 진출차수 있는지 체크

            for(int next : graph[cur]){
                into[next]--;

                if(into[next] == 0 ) { //진입차수가 0이면 큐에 넣는다.
                    q.offer(next);
                    sb.append(next + " ");
                }
            }
        }

        //그래프의 진입차수를 체크한다. 1개라도 사이클이 발생하면 false를 빈환
        for (int i = 1; i <= N; i++) {
            if(into[i] != 0) return false;
        }
        return true;

    }
}
