package second.contest;
import java.io.*;
import java.util.*;


public class BOJ14627파닭파닭_binsearch {
    static long S, C, maxPa = Integer.MIN_VALUE;
    static long[] paLen;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        //파닭에 같은 양만 넣음 -> 넣을 수 있는 최대한 넣기
        //하나의 파닭에는 1개의 파만

        S = Integer.parseInt(st.nextToken()); //파 수
        C = Integer.parseInt(st.nextToken()); //파닭 수

        paLen = new long[(int) S]; //파 길이
        long sum = 0;
        for (int i = 0; i < S; i++) {
            paLen[i] = Integer.parseInt(br.readLine());
            maxPa = Math.max(maxPa, paLen[i]);
            sum += paLen[i];
        }

        //파를 최대한 길게 뽑아야 한다.
        binSearch();

        //파 길이 합 - 소비한 대파 양
        System.out.println(sum - (C *  maxPa));
    }

    private static void binSearch(){
        long left = 1;
        long right = maxPa;

        while(left <= right){
            long mid = left + ((right - left) / 2);

            int cnt = 0;
            //현재 중앙값으로 몇개의 파를 만들 수 있는지 계산
            for(int i = 0 ; i < S ; ++i){
                cnt += paLen[i] / mid;
            }

            if(cnt >= C){ //파닭 수 보다 많이 나옴 -> 파를더 길게 잘라야함
                maxPa = mid;
                left = mid + 1;
            }else if(cnt < C){ //파닭 수 보다 적게 나옴 -> 파를 더 짧게
                right = mid - 1;
            }
        }
    }

}
