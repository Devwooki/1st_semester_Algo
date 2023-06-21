package study;

import java.io.*;
import java.util.*;
public class BOJ14719 {
    static int H, W;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        int[] blocks = new int[W];
        int[] rain = new int[W];

        // 풀이 방법
        // 왼쪽 -> 오른쪽 : 더 큰 높이를 만날 때 까지 현재 최대 값으로 갱신
        // 오른쪽 -> 왼쪽 : 위와 동
        // 이후 실제 블록 높이와 차를 통해 빗물의 양을 구한다.

        st = new StringTokenizer(br.readLine());
        int maxH = 0;
        for(int i =  0; i< W ; ++i){
            blocks[i] = Integer.parseInt(st.nextToken());
            maxH = Math.max(blocks[i], maxH);
            rain[i] = maxH;
        }

        // blocks :3 1 2 3 4 1 1 2
        // rains : 3 3 3 3 4 4 4 4

        // 원하는 결과 3 3 3 3 4 2 2 2

        //오른쪽 -> 왼쪽으로 탐색하며 다시 높이를 재정의 한다.
        // 1. maxH blocks 비교 -> 큰 값을 추출
        // 2. rains와 1번의 결과를 비교 -> 비가 담기는 그릇의 높이를 구한다.
        // 3. 차를 구하기
        maxH = 0;
        int answer = 0;
        for(int i = W-1 ; i >= 0 ; --i ){
            maxH = Math.max(blocks[i], maxH);//이전 값과 현재 블록 비교
            rain[i] = Math.min(rain[i], maxH);//1번 결과와 왼쪽에서 탐색했던 결과를 비교
            answer += (rain[i] -blocks[i]);//차만큼 계산

        }

        System.out.println(answer);
    }
}
