package JobChange;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.io.IOException;

public class BOJ20300_서강근육맨_SV3 {
    static int N;
    static long[] machine;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        machine = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();

        long M = Long.MIN_VALUE;
        Arrays.sort(machine);

        int size = machine.length;
        int head = 0;
        int tail = size - 1;

        if(size % 2 == 1) {
            M = Math.max(M, machine[tail--]);
        }
        for(int i = 0 ; i < size/2 ; ++i){
            M = Math.max(M, machine[head++] + machine[tail--]);
        }
        System.out.println(M);
    }
    /*
    운동기구는 최대 2개씩만 사용한다.
    * 근손실이 문제,
    근손실 정도가 M을 넘지 않도록 하고 싶다.
    => PT를 여러번 받는데, 그중 가장 큰 근손실값(M)이 최대한 작아야함
    => 운동기구를 적절히 짝지어서, 여러 조합 중, 가장큰 값이 최소인 케이스를 찾아야함

    case 1
    * 1, 23 45 -> 1, 5, 9 => 가장 큰값 9
    * 1, 24 35 -> 1. 6, 8 => 가장 큰 값 8
    * 1, 25 34 -> 1, 7  7 => 가장 큰 값
    *
    * 2 13 45 -> 2 4 9
    * 2 14 35 -> 2 5 8
    * 2 15 34 -> 2 6 7
    *
    * 3 12 45 -> 3 3 9
    * 3 14 25 -> 3 5 7
    * 3 15 24 -> 3 6 6
    *
    * 4 12 35 -> 4 3 8
    * 4 13 25 -> 4 4 7
    * 4 15 23 -> 4 6 5
    *
    * 5 12 34 -> 5 3 7
    * 5 13 24 -> 5 4 6
    * 5 14 23 -> 5 5 5
    *
    * 홀수인 경우 가장큰수를 제외하고 2개씩 짝지어서
    * */
}
