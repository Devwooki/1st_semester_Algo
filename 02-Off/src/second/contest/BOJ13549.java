package second.contest;
import java.io.*;
import java.util.*;


public class BOJ13549 {
    static int start, end, result = 0;
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
        visited[start] = true;

        while(!q.isEmpty()){
            Loc now = q.poll();

            if(now.x == end){
                result = now.time;
                return;
            }

            if(now.x -1 >= 0 && !visited[now.x -1])  q.offer(new Loc(now.x-1, now.time+1));
            if(now.x +1 <= 100000 && !visited[now.x+1])  q.offer(new Loc(now.x+1, now.time+1));
            if((0<= now.x * 2 && now.x <= 100000) && !visited[now.x * 2])  q.offer(new Loc(now.x * 2, now.time));
        }
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
