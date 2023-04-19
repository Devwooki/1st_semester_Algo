package study;
import java.io.*;
import java.util.*;

/*
1 i x : i번째 기차 x좌석에 탑승 -> 있으면 패스
2 i x : i번째 기차 x좌석 하차  -> 빈자리 패스

3 i : i번째 기차에 앉은 승객 모두 한칸 뒤로 당김
      k번째 -> K+1로 이동, 20번째에 자리 있으면 명령 후 하차
4 i : i번째 기차에 앉은 승객 모두 한칸 앞으로 당김
      k번째 -> K-1로 이동, 1번째에 사람 있으면 명령 후 하차

*/
public class BOJ15787 {
    static int N, M;
    static int[] train;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        train = new int[N];

        while(M-- > 0){
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());

            switch(cmd){
                case 1 : take(
                        Integer.parseInt(st.nextToken()) - 1,
                        Integer.parseInt(st.nextToken())-1
                        );
                break;
                case 2 :
                    out(
                            Integer.parseInt(st.nextToken()) - 1,
                            Integer.parseInt(st.nextToken())-1
                    );
                    break;
                case 3 :
                    mvRight(Integer.parseInt(st.nextToken())-1);
                    break;
                case 4 :
                    mvLeft(Integer.parseInt(st.nextToken())-1);
                    break;
            }
        }


        //set에 넣고 중복 검사
        HashSet<Integer> set = new HashSet<>();
        for(int num : train){
            set.add(num);
        }

        System.out.println(set.size());
    }

    static void take(int num, int sit){
        //있으면 패스. train[num] == 1이면 or 연산을 해도 1이다.
        //sit 비트 위치의 값을 1로
        train[num] |= 1 << sit;
    }
    static void out(int num, int sit){
        //없으면 패스
        //&연산은 reset, ->
        train[num] &= ~(1 << sit);
    }

    static void mvRight(int num){
        train[num] = train[num] << 1; //좌석들을 한칸씩 민다. 비트 인덱스는 일반 배열과 반대이므로 << 연산

        //20번째 비트를 지운다 ->
        train[num] &= (1 << 20) -1; //비트 자릿수가 증가 했으므로 20번째 비트는 &연산으로 무시
    }
    static void mvLeft(int num) {
        train[num] = train[num] >> 1; //0인 비트는 자동으로 지워진다.
    }
}
