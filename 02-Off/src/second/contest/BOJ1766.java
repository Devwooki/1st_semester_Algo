package second.contest;
import java.util.*;
import java.io.*;

/**
 * 방향 그래프에서 간선으로 주어진 정점 간 선후관계를 위배하지 않도록 하는 정렬
 * - 사이클이 존재할 경우 올바른 위상정렬 불가능 => 방향이 존재하지 않아야함
 * - DAG라고 일컫음
 *
 * 0. 그래프를 순차탐색해 진입차수 갯수를 배열에 저장
 * 1. 진입 차수가 0인것을 큐에 저장
 * 2. 큐에서 정점을 꺼내서 위상 정렬 결과에 추가
 * 3. 해당 정점으로 부터 연결된 모든 정점의 indegree 1감소, 0이면 큐에 추가
 * 4. 큐가 빌 떄 까지 반복
 *
 */
public class BOJ1766 {
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
    }
}
