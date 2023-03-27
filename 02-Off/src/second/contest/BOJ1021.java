package second.contest;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class BOJ1021 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int result = 0;

        LinkedList<Integer> dq = new LinkedList<>();
        //앞에서 빼면
        for (int i = 1; i <= N; i++) {
            dq.addLast(i);
        }


        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int findValue = Integer.parseInt(st.nextToken());

            while (true) {
                if (dq.peekFirst() == findValue) {
                    dq.pollFirst();
                    break;
                }
                else {
                    int idx = dq.indexOf(findValue);
                    int dqhalf = dq.size() / 2;
                    //dq.size() = 짝수 -> 어디든 상관없음
                    //dq.size() = 홀수 -> 앞에서 poll하는게 이득
                    if (idx <= dqhalf) { //앞에서 빼서 뒤로 넣기
                        while(dq.peekFirst() != findValue){
                            dq.addLast(dq.pollFirst());
                            result++;
                        }
                    } else {  //뒤에서 빼서 앞으로 넣기
                        while(dq.peekFirst() != findValue){
                            dq.addFirst(dq.pollLast());
                            result++;
                        }
                    }
                }
            }
        }
        System.out.println(result);
    }
}
