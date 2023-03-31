package second.contest;
import java.util.*;
import java.io.*;

/**
 * Input data
 * 10
 * 8 2 4 3 6 11 7 10 14 5
 */
public class LIS {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int[] LIS = new int[N];

        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int max = 0;
        for (int i = 0; i <  N; i++) {
            LIS[i] = 1; //자신만 끝에 세웠을 경우 1의 길이
            for (int j = 0; j < i; j++) {   //앞에서부터 I까지
                if(arr[j] < arr[i] && LIS[i] < LIS[j]+1){
                    // arr[j] < arr[i] :나를 끝으로 하는 증가수열이 가능한 상황
                    // LIS[i] < LIS[j]+1 : 최댓값 갱신.. 이부분 정리 필요
                    LIS[i] = LIS[j]+1;
                }
            }//i를 끝으로하는 LIS 계산 끝
            if(max<LIS[i]) max = LIS[i];
        }
        System.out.println(max);
    }
}
