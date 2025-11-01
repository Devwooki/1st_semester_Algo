package programmers;


import java.util.*;
import java.awt.*;
public class LV2석유시추 {
    public static void main(String[] args) {
        int[][] inputs = {
                {0, 0, 0, 1, 1, 1, 0, 0}, {0, 0, 0, 0, 1, 1, 0, 0}, {1, 1, 0, 0, 0, 1, 1, 0}, {1, 1, 1, 0, 0, 0, 0, 0}, {1, 1, 1, 0, 0, 0, 1, 1}
        };

//        Solution sol = new Solution();
//        sol.solution(inputs);

        Sol2 sol = new Sol2();
        sol.solution(10);

    }

    int[] inputs;
    int[] numbers;
    boolean[] visited;

    private static void perm(int cnt){

    }

    static class Sol2{

        int[] numbers;
        boolean[] visited;

        TreeSet<Integer> set = new TreeSet<>();
        public int solution(int n) {
            numbers = new int[5];
            visited = new boolean[n];


            //getPerm(0, n);
            getCombi(0,0, n);

            return 0;
        }

        public void getPerm(int cnt, int N){
            if(cnt == 5  ){
                System.out.println(Arrays.toString(numbers));
                return;
            }

            for(int i = 0 ; i < N  ; ++i){
                if(visited[i]) continue;

                visited[i] = true;
                numbers[cnt] = i+1;

                getPerm(cnt+1, N);
                visited[i] = false;
            }
        }

        public void getCombi(int cnt, int start, int N){
//            if(cnt == 5){
//                System.out.println(Arrays.toString(numbers));
//                return;
//            }

            if(set.size() == 5){
                StringBuilder sb = new StringBuilder();
                for(int a : set){
                    sb.append(a + " ") ;
                }
                System.out.println(sb.toString());
                return;
            }
            for(int i = start ; i < N  ; ++i){
                //numbers[cnt] = i+1;
                set.add(i+1);
                getCombi(cnt+1, i+1, N);
                set.remove(i+1);
            }
        }
    }

    static class Solution {
        int N, M;
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        boolean[][] visited;
        HashMap<Integer, Integer> oilAmount = new HashMap<>(); //area별 석유 매장량 저장
        HashMap<Integer, TreeSet<Integer>> areaExistIdx = new HashMap<>(); //특정 area가 가지는 x축 index

        public int solution(int[][] land) {
            N = land.length;
            M = land[0].length;
            visited = new boolean[N][M];

            int area = 1;
            for(int i = 0 ; i < N ; ++i){
                for(int j = 0 ; j < M ; ++j){
                    if(land[i][j] == 1 && !visited[i][j]){//방문 하지 않은 석유 매장지역이면 체크
                        areaExistIdx.put(j, new TreeSet<>());
                        bfs(j, i, area++, land);
                    }
                }
            }

            // area 디버깅
            // for(int a : oilAmount.keySet()){
            //     System.out.println(a + " : " + oilAmount.get(a));
            // }

            return 0;
        }

        void bfs(int x, int y, int area, int[][] land){
            Queue<Point> q = new ArrayDeque<>();
            q.offer(new Point(x, y));

            land[y][x] = -area;
            visited[y][x] = true;
            int cnt = 1;

            try{
                TreeSet<Integer> existIdx = areaExistIdx.get(x);
                existIdx.add(x);
            }catch(Exception e){
                e.printStackTrace();
            }



            while(!q.isEmpty()){
                Point cur = q.poll();

                for(int i = 0 ; i < 4 ; ++i){
                    int nx = cur.x + dx[i];
                    int ny = cur.y + dy[i];

                    if(!checkRange(nx, ny)) continue;
                    if(visited[ny][nx]) continue;
                    if(land[ny][nx] <= 0) continue; //0은 빈땅, -는 이미 방문한 곳

                    land[ny][nx] = -area;
                    visited[ny][nx] = true;
                    cnt++;

                    q.offer(new Point(nx, ny));


                }
            }

            oilAmount.put(area, cnt);
        }

        boolean checkRange(int x, int y){
            return ( 0 <= x && x < M && 0 <= y && y < N);
        }
    }
/*
풀이
1. 시추 가능한 영역을 찾아(BFS) 번호를 매기고, 번호별로 영역을 표시
2.

*/
}

