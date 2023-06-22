package study;

import java.io.*;
import java.util.*;
public class BOJ2696 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuffer sb =new StringBuffer();

        int TC = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> insertEl;   //입력값 기록을 위한 pq
        Queue<Integer> popEl;      //중앙값 출력을 위한 pq
        for(int tc = 0 ; tc < TC ; ++tc){
            insertEl = new PriorityQueue<>();
            popEl = new LinkedList<>();

            int M = Integer.parseInt(br.readLine());

            sb.append((int)(M+1)/2 + "\n");
            int cnta = M/10;
            if(M%10 > 0) cnta += 1;

            for(int i = 0 ; i < cnta ; ++i){ //10개 단위로 줄바꿈이 이뤄지니 읽어야하는 라인 수를 지정해준다.
                st = new StringTokenizer(br.readLine());

                while(st.hasMoreTokens()){
                    int now = Integer.parseInt(st.nextToken());
                    insertEl.offer(now);
                    int size = insertEl.size();

                    if(size%2 == 1){ //홀수 일 떄 중앙 값을 구한다.
                        //cnt 1, 3, 5, 7, 9
                        //v   1, 2, 3, 4, 5


                        //중앙값은 popEl의 cnt/2 + 1에 위차함
                        //중앙값 이전까지 뺴기
                        for(int j = 0 ; j < size/2 ; ++j){
                            popEl.offer(insertEl.poll());
                        }
                        //중앙값 출력
                        int temp = insertEl.poll();
                        sb.append(temp+ " ");
                        insertEl.offer(temp);
                        //다시 pq에 삽입
                        while(!popEl.isEmpty()) insertEl.offer(popEl.poll());
                    }
                    if(size%20 == 0) sb.append("\n");
                }
            }
            System.out.print(sb);
            sb = new StringBuffer();
        }
    }
}
