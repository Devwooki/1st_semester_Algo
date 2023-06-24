package study;

import java.io.*;
import java.util.*;

public class BOJ2696 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuffer sb = new StringBuffer();

        int TC = Integer.parseInt(br.readLine());

        while (TC-- > 0) {

            //중앙값을 뽑으려면 최대힙에서 뽑아야한다.
            // 최소힙 : 오름차순 정렬
            // 최대힙 : 내림차순 정렬 -> peek, poll 한 번 하면 중앙값 출력가능능
            PriorityQueue<Integer> minpq = new PriorityQueue<>();
            PriorityQueue<Integer> maxpq = new PriorityQueue<>(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2 - o1;
                }
            });

            int M = Integer.parseInt(br.readLine());

            //중앙값을 먼저 계산해서 출력한다.
            sb.append((int) (M + 1) / 2 + "\n");

            for (int i = 1; i <= M; ++i) {
                if (i % 10 == 1) st = new StringTokenizer(br.readLine());

                int input = Integer.parseInt(st.nextToken());

                //최소힙 : 중앙 값 보다 작은 숫자 오름차순
                //최대힙 : 중앙 갑 이상인 숫자 내림차순
                //서로 교차로 최대힙 -> 최소힙에 넣는다.
                if (i % 2 == 1) maxpq.offer(input);
                else minpq.offer(input);

                //두 개의 큐가 비어있지 않을 때, minpq.peek > maxpq.peek일 경우 swap 해준다
                //중앙값을 찾기 위해서(minpq는 앞서 설명했 듯 중앙값보다 작은 값을 가져야 하므로)
                if (!minpq.isEmpty() && !maxpq.isEmpty()) {
                    if (maxpq.peek() > minpq.peek()) {
                        int temp = maxpq.poll();
                        maxpq.offer(minpq.poll());
                        minpq.offer(temp);
                    }
                }

                //중앙값을 출력하자
                if (i % 2 == 1) sb.append(maxpq.peek() + " ");

                // 출력값이 10개 면 줄바꿈을 수행한다.
                if (i % 20 == 0) sb.append("\n");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}


//package study;
//
//import java.io.*;
//import java.util.*;
//public class BOJ2696 {
//    public static void main(String[] args) throws IOException{
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st;
//        StringBuffer sb =new StringBuffer();
//
//        int TC = Integer.parseInt(br.readLine());
//
//        PriorityQueue<Integer> insertEl;   //홀수번째 숫자 저장
//        Queue<Integer> popEl;      //중앙값 출력을 위한 pq
//        for(int tc = 0 ; tc < TC ; ++tc){
//            insertEl = new PriorityQueue<>();
//            popEl = new LinkedList<>();
//
//            int M = Integer.parseInt(br.readLine());
//
//            sb.append((int)(M+1)/2 + "\n");
//            int iter = M/10;
//            if(M%10 > 0) iter += 1;
//
//            for(int i = 0 ; i < iter ; ++i){ //10개 단위로 줄바꿈이 이뤄지니 읽어야하는 라인 수를 지정해준다.
//                st = new StringTokenizer(br.readLine());
//
//                while(st.hasMoreTokens()){
//                    int now = Integer.parseInt(st.nextToken());
//                    insertEl.offer(now);
//                    int size = insertEl.size();
//
//                    if(size%2 == 1){ //홀수 일 떄 중앙 값을 구한다.
//                        //cnt 1, 3, 5, 7, 9
//                        //v   1, 2, 3, 4, 5
//
//                        //중앙값은 popEl의 cnt/2 + 1에 위차함
//                        //중앙값 이전까지 뺴기
//                        for(int j = 0 ; j < size/2 ; ++j){
//                            popEl.offer(insertEl.poll());
//                        }
//                        //중앙값 출력z
//                        int temp = insertEl.poll();
//                        sb.append(temp+ " ");
//                        insertEl.offer(temp);
//                        //다시 pq에 삽입
//                        while(!popEl.isEmpty()) insertEl.offer(popEl.poll());
//                    }
//                    if(size%20 == 0) sb.append("\n");
//                }
//            }
//            System.out.print(sb);
//            sb = new StringBuffer();
//        }
//    }
//}
