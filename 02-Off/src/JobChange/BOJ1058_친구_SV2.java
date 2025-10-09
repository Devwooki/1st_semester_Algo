package JobChange;

import sun.reflect.ReflectionFactory;

import java.io.*;
import java.util.*;

public class BOJ1058_친구_SV2 {
    static int N;
    static ArrayList<Integer>[] friends;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        friends = new ArrayList[N];

        // 2-친구 case1 : 서로 친구
        // 2-친구 case2 : 한다리 건너 친구

        // 2-친구가 가장 많은 사람을 찾아야함.
        for(int i = 0 ; i < N ; ++i){
            friends[i] = new ArrayList<>();
            String line = br.readLine();
            for(int j = 0 ; j < N ; ++j){
                if(line.charAt(j) == 'Y') friends[i].add(j);
            }
        }

        int answer = Integer.MIN_VALUE;

        //사람마다 2-친구의 수를 구한다
        for(int i = 0 ; i < N ; ++i){
            Queue<Friend> q = new ArrayDeque<>();
            boolean[] visited = new boolean[N];

            q.offer(new Friend(i, 0));
            //첫 시작에 방문체크하지 않는다. 자기자신도 친구로 보게 되므로

            while(!q.isEmpty()){
                Friend bas = q.poll();
                if(bas.depth >= 2) break; //depth가 2보다 크면 2-친구가 아니다.

                //현재 친구의 2-친구를 찾는다.
                for(int friendIdx : friends[bas.idx]){
                    if(i ==  friendIdx) continue; //자기자신 간너뛴다
                    if(visited[friendIdx]) continue; //방문한 친구 건너뜀

                    visited[friendIdx] = true; //친구임을 체크한다.
                    q.offer(new Friend(friendIdx, bas.depth + 1));
                }
            }

            int cnt = 0 ;
            for(boolean isFriend : visited){
                if(isFriend) cnt++;
            }
            answer = Math.max(answer, cnt);
        }

        System.out.println(answer);
    }

    static class Friend{
        int idx;
        int depth;

        Friend(int idx, int depth){
            this.idx = idx;
            this.depth = depth;
        }
    }
}
