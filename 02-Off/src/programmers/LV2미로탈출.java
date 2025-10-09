package programmers;

import java.awt.*;
import java.util.*;
public class LV2미로탈출 {
    public static void main(String[] args) throws Exception{
        Solution sol = new Solution();
        //String[] input = {"SOOOL","XXXXO","OOOOO","OXXXX","OOOOE"};
        String[] input =  {"SOOOO", "OOOLO", "OOOOE", "OOOOO", "OOOOO"};
        System.out.println(sol.solution(input));
    }

/*
출발 지점 -> 레버 -> 출구
    - 레버를 당기지 않았으면 출구는 그냥 스킵 가능하다.

1. 시작지점에서 레버까지 최소 시간을 구한다.
2. 레버에서 출구까지 최소시간을 구한다
, 탈출할 수 없으면 -1
*/
static class Solution {
    char[][] map;
    int[][] visited;
    int rSIZE;
    int cSIZE;

    int[] start;
    int[] end;
    int[] lever;

    int[] dx = {1, 0, -1, 0};
    int[] dy = {0, 1, 0, -1};


    public int solution (String[] maps) {

        rSIZE = maps.length;
        cSIZE = maps[0].length();
        map = new char[rSIZE][cSIZE];
        visited = new int[rSIZE][cSIZE];

        for(int i = 0 ; i < rSIZE ; ++i){
            for(int j = 0 ; j < cSIZE ; ++j){
                map[i][j] = maps[i].charAt(j);
                if(map[i][j] == 'S') start = new int[]{j, i};
                else if(map[i][j] == 'L'){
                    lever = new int[]{j, i};
                    visited[lever[1]][lever[0]] = Integer.MAX_VALUE;
                }
                else if(map[i][j] == 'E') {
                    end = new int[]{j, i};
                }

            }
        }

        //레버까지 최단거리를 계산한다.
        bfs(start[0], start[1], 'L');
        int toLeverDist = visited[lever[1]][lever[0]];

        //레버에 도달할 수 없다면 -1을 반환한다.
        if(toLeverDist == Integer.MAX_VALUE) return -1;

        //레버에서 도착지까지 최단거리를 계산한다.
        visited = new int[rSIZE][cSIZE];

        visited[end[1]][end[0]] = Integer.MAX_VALUE;
        bfs(lever[0], lever[1], 'E');
        int toEndPointdist = visited[end[1]][end[0]];

        if(toEndPointdist == Integer.MAX_VALUE) return -1;

        return toLeverDist + toEndPointdist;
    }
    //Queue에 넣기전에 방문체크하자
    public void bfs(int x, int y, char endPoint){
        Queue<Point> q = new ArrayDeque<>();

        visited[y][x] = 0;
        q.offer(new Point(x, y));

        while(!q.isEmpty()){

            Point cur = q.poll();
            if(map[cur.y][cur.x] == endPoint) return;

            for(int i = 0 ; i < 4 ; ++i){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                //유효값 검사
                if(!checkLoc(nx, ny)) continue;    //범위초과
                if(map[ny][nx] == 'X') continue;   //벽
                if(visited[ny][nx] > 0 && visited[ny][nx] != Integer.MAX_VALUE) continue; //방문한 곳
                if(visited[ny][nx] == 0 && (nx == x && ny == y))
                    continue; //시작지점이면 건너뛴다

                //방문한 위치의 최소값
                if(visited[ny][nx] == 0)visited[ny][nx] = visited[cur.y][cur.x] + 1;
                else visited[ny][nx] = Math.min(visited[cur.y][cur.x] + 1, visited[ny][nx]);
                q.offer(new Point(nx, ny));
            }
        }

    }

    public boolean checkLoc(int x, int y){
        return ( ( 0 <= x && x < cSIZE ) && ( 0 <= y && y < rSIZE ));
    }
}

}

