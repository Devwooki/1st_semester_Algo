package programmers;

import java.util.*;
public class LV2서버증설 {
    public static void main(String[] args) throws Exception{
        //Solution sol = new Solution();
        Solution2 sol = new Solution2();

        int[] input = new int[]{0, 2, 3, 3, 1, 2, 0, 0, 0, 0, 4, 2, 0, 6, 0, 4, 2, 13, 3, 5, 10, 0, 1, 5};

        System.out.println(sol.solution(input, 3,5));
    }
    static class Solution {
        int[] server;
        int SIZE;
        int answer = 0;
        public int solution(int[] players, int m, int k) {
            //[0] : 서버종료시간, [1] : 증설된 서버 수
            PriorityQueue<int[] > pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);

            int answer = 0; //증설횟수
            int now = 0; // 현재 서버 수

            for(int i = 0 ; i < 24 ; ++i){

                //서버 종료시간이 되면, 현재 운영중인 서버의 증설량을 뺀다.
                while(!pq.isEmpty() && pq.peek()[0] == i){
                    now -= pq.poll()[1];
                }

                int need = players[i] / m; //필요한 서버 수
                int more = now - need;  //확보 해야하는 수

                if(more < 0){
                    more = Math.abs(more);
                    now += Math.abs(more);
                    answer += Math.abs(more);
                    pq.add(new int[]{i + k, more});
                }

                System.out.println(i + ":\t" + players[i] + "\t" + now + "\t" + more);
            }

            return answer;
        }
    }

    static class Solution2 {
        public int solution(int[] players, int m, int k) {
            int answer = 0;
            int[] work = new int[players.length + k]; // 반납 시간 저장
            int worker = 0; // 운영 중 서버

            for(int i = 0; i < players.length; i++) {
                worker -= work[i]; // 반납 된 서버 빼기

                int required = players[i] / m;

                if(required > worker) {
                    int server = required - worker;
                    answer += server;
                    worker += server;
                    work[i + k] += server;
                }
            }

            return answer;
        }
    }

}
