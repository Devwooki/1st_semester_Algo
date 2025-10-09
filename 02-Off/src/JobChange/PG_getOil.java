package JobChange;

import java.io.*;
import java.util.*;
import java.awt.*;

public class PG_getOil {
    public static void main(String[] args) {
        Solution sol = new Solution();

        int[][] param = new int[][]{{0, 0, 0, 1, 1, 1, 0, 0}, {0, 0, 0, 0, 1, 1, 0, 0}, {1, 1, 0, 0, 0, 1, 1, 0}, {1, 1, 1, 0, 0, 0, 0, 0}, {1, 1, 1, 0, 0, 0, 1, 1}};

        System.out.println(sol.solution(param));
    }
    static class Solution {

        //boolean[][] visited;
        int N, M;
        int answer = Integer.MIN_VALUE;
        int oilIndex = 2;

        HashMap<Integer, HashSet<Integer>> map = new HashMap<>();
        HashMap<Integer, Integer> indexPerAmount = new HashMap<>();
        public int solution(int[][] land) {
            N = land.length;
            M = land[0].length;

            for(int j = 0 ; j < M ; ++j){
                HashSet<Integer> idx = new HashSet<>();
                for(int i = 0 ; i < N ; ++i){
                    //석유 인덱스 당 시추량
                    if(land[i][j] == 1){
                        int amount = oilIndexing(land, j, i);
                        indexPerAmount.put(oilIndex++, amount);
                    }

                    //매장량이 모두 인덱스 처리 되었으므로
                    if(land[i][j] != 0){
                        idx.add(land[i][j]);
                    }
                }
                map.put(j, idx);
            }

            //최대 시추량 구하기
            for(int key : map.keySet()){
                HashSet<Integer> set = map.get(key);
                Iterator<Integer> iter = set.iterator();

                int amount = 0;
                while(iter.hasNext()){
                    amount += indexPerAmount.get(iter.next());
                }

                answer = Math.max(amount, answer);
            }

            return answer;
        }

        private int oilIndexing(int[][] land, int x, int y){
            int[] dx = {0, 1, 0, -1};
            int[] dy = {1, 0, -1, 0};

            Queue<Point> q = new ArrayDeque<>();

            q.offer(new Point(x, y));
            //visited[y][x] = true;
            land[y][x] = oilIndex;
            int oilAmount = 1;

            while(!q.isEmpty()){
                Point cur = q.poll();

                for(int j = 0 ; j < 4 ; ++j){
                    int nx = cur.x + dx[j];
                    int ny = cur.y + dy[j];

                    //유효값 체크
                    if(!checkRange(nx, ny)) continue; //범위 체크
                    if(land[ny][nx] == 0) continue; //석유 유무 체크
                    if(land[ny][nx] == oilIndex) continue;//시추 유무

                    oilAmount += 1;
                    land[ny][nx] = oilIndex;
                    q.offer(new Point(nx, ny));
                }
            }

            return oilAmount;
        }

        private boolean checkRange(int x, int y){
            return ( 0 <= x && x < M && 0 <= y && y < N);
        }
    }
}
