package study;

import java.io.*;
import java.util.*;

public class BOJ1183 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        //수많은 뻘짓의 결과 -> 절댓값의 최소는 중앙값임
        int N = Integer.parseInt(br.readLine());
        int[] Ts = new int[N];
        for(int i = 0; i < N ; ++i){
            st = new StringTokenizer(br.readLine());
            Ts[i] = (Integer.parseInt(st.nextToken())-Integer.parseInt(st.nextToken()));

        }
        Arrays.sort(Ts);

        if(N%2 == 1) System.out.println(1); //홀수는 중앙값이 1개
        else System.out.println(Ts[N/2] - Ts[N/2 -1] + 1); //짝수는 중앙값이 N/2-1 ~ N/2까지

    }
}
//범위에 해당되는 수가 1개면 N범위 크기만큼
//다른 수가 있다면 그 숫자


/*
2
10 11
20 17
|-1+T| + |3+T| -> -3 ~ 1

2
20 18
30 25
-5 -4 -3 -2
|20-18+T| + |30-25+T| = > |2+T| + |5+T| =
-5 ~ -2

|-3+T | + |5+ T| + |4+T|
-5 ~ 3, -4가 2번 등장

|-4 + T| + |-4 + T| + |-9 + T| + |3 + T|
-3 ? 4 5 6 7 8 9 -> 4가 2개.
-9 -4 -4 3


-3 26
-2 24
-1 22
0 20
1 18
2 16
3 14
4 12
5 14
6 16
7 18
8 20
9 22
 */

/*
* TreeMap<Integer, Integer> cnt = new TreeMap<>();
        TreeSet<Integer> ele = new TreeSet<>();
        List<Integer> list = new ArrayList<>();
        int N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N ; ++i){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int T = -(A-B);

            cnt.put(T, cnt.getOrDefault(T, 0)+1);
            ele.add(T);
            list.add(T);
        }

//        Collections.sort(list);
//        int minRange = list.get(0);
//        int maxRange = list.get(list.size()-1);
//
//        for(int i = minRange ; i <= maxRange ; ++i){
//
//        }

        int max = Integer.MIN_VALUE;
        int maxCnt = 1;
        int minRange = cnt.get(cnt.firstKey());
        int maxRange = cnt.get(cnt.lastKey());

//        for(int i = minRange + 1 ; i < maxRange ; ++i){
//            cnt.put(i, cnt.getOrDefault(i, 0)+1);
//
//            int now = cnt.get(i);
//            if( now > max){
//                max = now;
//                maxCnt = 1;
//                continue;
//            }else if(now == max){
//                maxCnt++;
//            }
//        }

* */