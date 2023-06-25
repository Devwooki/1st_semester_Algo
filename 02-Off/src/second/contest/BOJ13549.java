package second.contest;
import java.io.*;
import java.util.*;


public class BOJ13549 {
    static int start, end, result = Integer.MAX_VALUE;
    static boolean[] visited = new boolean[100001];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        bfs();
        System.out.println(result);
    }

    static void bfs(){
        Queue<Loc> q = new LinkedList<>();
        q.add(new Loc(start , 0));

        while(!q.isEmpty()){
            Loc now = q.poll();
            visited[now.x] = true;

            if(now.x == end){
                result = Math.min(result, now.time);
            }

            int next1 = now.x-1;
            int next2 = now.x+1;
            int next3 = now.x * 2;
            if(checkRange(next1) && !visited[next1]) q.offer(new Loc(next1, now.time+1));
            if(checkRange(next2) && !visited[next2]) q.offer(new Loc(next2, now.time+1));
            if(checkRange(next3) && !visited[next3]) q.offer(new Loc(next3, now.time));

        }
    }

    private static boolean checkRange(int x){
        return ( 0 <= x && x <= 100000);
    }
    static class Loc{
        int x;
        int time;


        public Loc(int x, int time) {
            this.x = x;
            this.time = time;
        }
    }
}
