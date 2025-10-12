package JobChange;
import java.io.*;
import java.util.*;


public class BOJ11286_절댓값힙_SV1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Long> absHeap = new PriorityQueue<>((o1,o2)->{
            if(Math.abs(o1) != Math.abs(o2)) return Long.compare(Math.abs(o1), Math.abs(o2));
            else return Long.compare(o1,o2);
        });
        /* 2.절댓값이 가장 작은 값을 출력하고, 그 값을 제거한다.
        *  2-1. 절댓값이 작은게 여러개인 경우, 가장 작은 수를 출력하고 그 값을 제거한다.
        * */

        for(int i = 0 ; i < N ; ++i ){
            long n = Long.parseLong(br.readLine());

            if(n == 0L){
                sb.append(!absHeap.isEmpty() ? absHeap.poll() + "\n" : "0\n");
            }else{
                absHeap.add(n);
            }
        }

        System.out.println(sb);
    }
}
