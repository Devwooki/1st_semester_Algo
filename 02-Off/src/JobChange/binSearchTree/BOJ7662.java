package JobChange.binSearchTree;

import java.awt.*;
import java.io.*;
import java.util.*;

public class BOJ7662 {
    static int T;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        PriorityQueue<Long> minQ = new PriorityQueue<>();
        PriorityQueue<Long> maxQ = new PriorityQueue<>((o1, o2) -> {return (int) (o2-o1);});
        //숫자의 위치는 큐마다 달라서 해당 숫자 정보를 업데이트 해줘야함
        Map<Long, Long> map = new HashMap<>();

        T = Integer.parseInt(br.readLine());

        for(int tc = 0 ; tc < T ; ++tc){

            int k = Integer.parseInt(br.readLine());

            while(k-- > 0){
                String[] str = br.readLine().split(" ");

                long num = Long.parseLong(str[1]);
                switch(str[0]){
                    case "I" :
                        maxQ.offer(num);
                        minQ.offer(num);
                        map.put(num, map.getOrDefault(num,0L) + 1);

                        break;
                    case "D" :
                        //비어있는 경우 무시
                        if(map.size() == 0 ) continue;

                        //최대값 삭제한다.
                        if(num == 1) delete(maxQ, map);
                        //최솟값 삭제한다.
                        else if(num == -1) delete(minQ, map);
                        break;
                }
            }

            if(map.size() == 0){
                sb.append("EMPTY");
            }else{
                sb.append(maxQ.peek() + " " + minQ.peek() + "\n");
            }
        }
        System.out.println(sb.toString());
    }

    private static void delete(PriorityQueue<Long> pq, Map<Long, Long> map){
        long num = pq.poll();

        long cnt = map.getOrDefault(num, 0L);
        if(cnt > 0){
            if(cnt == 1) map.remove(num);
            else map.put(num, cnt - 1);
        }
    }
}
