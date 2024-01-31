package second.contest;

import java.io.*;
import java.util.*;

public class BOJ1058 {
    static int N;
    static ArrayList<Integer>[] map;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        map = new ArrayList[N];

        for(int i = 0 ; i < N ; ++i){
            map[i] = new ArrayList();
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                if(str.charAt(j) == 'Y') map[i].add(j);
            }
        }

        int answer = Integer.MIN_VALUE;

        //친구별 최대값 찾기
        for (int i = 0; i < N; i++) {

            Queue<Friend> q = new LinkedList<>();
            q.offer(new Friend(i, 0));

            boolean[] visited = new boolean[N];


            //큐에서 꺼낸 경우 저
            while(!q.isEmpty()){
                Friend now = q.poll();
                if(now.depth >= 2) break;

                for(int next : map[now.idx]){
                    if(visited[next]) continue; //방문 했으면 건너 뛴다.
                    if(i == next) continue;

                    //아닐 경우
                    visited[next] = true;
                    q.offer(new Friend(next, now.depth + 1));
                }
            }

            int friendCnt = 0;
            for(boolean isFriend : visited){
                if(isFriend) friendCnt++;
            }

            answer = Math.max(answer, friendCnt);
        }

        System.out.println(answer);

    }

    static class Friend{
        int idx;
        int depth;

        public Friend(int idx, int depth) {
            this.idx = idx;
            this.depth = depth;
        }
    }
}
