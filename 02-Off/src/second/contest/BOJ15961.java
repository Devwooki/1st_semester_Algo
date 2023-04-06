package second.contest;


import java.util.*;
import java.io.*;

/*
접시의 수 N,
초밥의 가짓수 d,
연속해서 먹는 접시의 수 k,
쿠폰 번호 c
초밥의 가짓수 최댓ㄱ밧
 */
public class BOJ15961 {
    static int N, d, k, c;
    static int[] chobap;
    static int[] eatCheck;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        chobap = new int[N+(k-1)];


        //컨베이어 벨트 초기셋팅
        for(int i = 0 ; i < N ; ++i){
            chobap[i] = Integer.parseInt(br.readLine());
        }

        

        int dupleSize = 0; //중복을 제외하고 최대한 먹을 수 있는 크기
        eatCheck = new int[d+1];
        for(int i = 0 ; i < k ; ++i){
            //중복이 없으멸 리스트 크기를 늘린다.
            if(eatCheck[chobap[i]] == 0) dupleSize++;
            eatCheck[chobap[i]]++;
        }

        int result = dupleSize;
        for(int i = 1 ; i < N ; ++i){
            //중복이 없을 때 -> dupleSize
            if(result <= dupleSize){
                //쿠폰으로 먹을 수 있는 것도 중복이 안되면
                if(eatCheck[c] == 0) result = dupleSize+1;
                else result = dupleSize;
            }

            //슬라이드 이동 -> 앞에꺼 제거
            eatCheck[chobap[i-1]]--;
            //제거했을 때 중복 값이 0이면 슬라
            if(eatCheck[chobap[i-1]] == 0) dupleSize--;
            //중복아 아니면 뒤에 붙은 스시를 고려한다.
            if(eatCheck[chobap[(i+k-1)%N]]==0) dupleSize++;
            eatCheck[chobap[(i+k-1)%N]]++;

        }
        System.out.println(result);
    }
}
