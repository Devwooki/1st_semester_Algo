package AnimeCup2nd;

import java.io.*;
import java.util.*;
public class BSleepInLab {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        boolean[] journalPages = new boolean[N+1];

        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 1 ; i <= M ; ++i){
            journalPages[Integer.parseInt(st.nextToken())] = true;
        }

        //페이지가 XOOX면 2개까지는 O 2개 까지는 연속해서 출력는게 더 잉크 아낄 수 있음
        /*
        T가 연속해서 3번 등장 -> cntF로 계산
        2 1
        oxoxoxoxoxo
         */

        int result = 0 ;
        int cntT = 0; //true 갯수 파악
        int startPoint = -1;
        int endPoint = 1;
        boolean flag = false;
        for(int i = 1 ; i <= N ; ++i){
            if(journalPages[i]){
                flag = true;
                cntT++;
            }else{
                if(flag && cntT >= 3){//true가 3개이상이면 true 등장 전 범위에서 잉크 값구함
                    result += 5 + ( 2 *(endPoint - startPoint+1));
                    startPoint = i;
                }
                //이후 현재위치는 false이므로 flag종료
                flag = false;
                cntT = 0;
                endPoint = i;

                if(startPoint == -1) // 2 1 \n 1 해결을 위한 임시방편,,,
                    startPoint = i;
            }
        }
        result += 5 + ( 2 *(endPoint - startPoint+1));
        System.out.println(result);
    }
}
