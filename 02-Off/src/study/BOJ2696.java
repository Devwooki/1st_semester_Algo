package study;
import java.io.*;
import java.util.*;
public class BOJ2696 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb =new StringBuilder();

        int TC = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq;
        for(int tc = 0 ; tc < TC ; ++tc){
            pq = new PriorityQueue<>();

            int M = Integer.parseInt(br.readLine());
            int cnt = 1;
            for(int i = 0 ; i <= M%10 ; ++i){
                st = new StringTokenizer(br.readLine());


                while(st.hasMoreTokens()){
                    pq.offer(Integer.parseInt(st.nextToken()));
                    if(cnt%2 == 1)
                    cnt++;

                }
            }

            while(!pq.isEmpty()) System.out.println(pq.poll());
        }
    }
}
