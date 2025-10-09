package study;

import java.io.*;
import java.util.*;

public class BOJ14888 {
    static int[] operation;
    static int[] num;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());

        num = new int[N];
        operation = new int[N-1];

        num = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int cnt = 0;
        st = new StringTokenizer(br.readLine());

        //연산자를 순서대로 정렬한다.
        for(int i = 0 ; i < 4 ; ++i){
            int temp = Integer.parseInt(st.nextToken());
            while(temp-- > 0) operation[cnt++] = i;
        }

        int maxV = Integer.MIN_VALUE;
        int minV = Integer.MAX_VALUE;
        do{
            int sum = num[0];

            for(int i = 0 ; i < N-1 ; ++i){
                switch(operation[i]){
                    case 0 : sum += num[i+1]; break;
                    case 1 : sum -= num[i+1]; break;
                    case 2 : sum *= num[i+1]; break;
                    case 3 : sum /= num[i+1];break;
                }
            }
            maxV = Math.max(maxV, sum);
            minV = Math.min(minV, sum);
        }while(nextP());

        System.out.println(maxV + "\n" + minV);
    }

    private static boolean nextP() {
        int n = operation.length; //배열 길이

        int i = n-1; //
        //i가 0보다 크고 오름차순이 아니게 되는 순간 발견
        while (i > 0 && operation[i-1] >= operation[i]) --i;
        if(i == 0) return false;

        //현재 가장큰 i의 값찾음

        //가장크면서 작은거 찾기
        int j = n-1;
        while (operation[i-1] >= operation[j]) --j;

        swap(i-1, j);

        int k = n - 1;

        while(i < k) {
            //오름차순 계속 교차
            swap(i++, k--);
        }

        return true;
    }

    static void swap(int front, int back){
        int temp = operation[front];
        operation[front] = operation[back];
        operation[back] = temp;
    }
}