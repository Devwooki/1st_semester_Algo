package second.contest;

import java.util.*;
import java.io.*;
//가능한 부분 집합을 모두 만드는게 좋다
//계단은 총 2개
//1. 계단 배정(PowerSer)
//2. 계단 별 사람 리스트 만들기 - 맨하탄 거리를 이용해서 계단 도착시간 계산
//3. 사람 리스트를  도착시간 빠른 순으로 정렬
//=====시뮬레이션 시작=====
//4. 시작 시간 : 첫 사람 도착시간
public class SWEA2383 {
    static int result = 0;
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        	StringTokenizer st;
        	StringBuilder sb = new StringBuilder();
            int TC = Integer.parseInt(br.readLine());
            for(int tc = 1 ; tc <= TC ; ++tc){

                sb.append("#" + tc + " " + result + "\n");
            }
        }
}
