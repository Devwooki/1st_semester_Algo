package JobChange;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ20365_블로그2_SV3 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Solution sol = new Solution();
        int n = Integer.parseInt(br.readLine());
        String str = br.readLine();

        System.out.println(sol.solution(n, str));

    }

    static class Solution{

        public int solution(int n, String str){
            int blue = 0;
            int red = 0;
            char base = '0'; //무의미한 임의값

            for(int i = 0 ; i < n ; ++i){
                char cur = str.charAt(i);
                if(base != cur) {
                    if(cur == 'B')blue++;
                    else red++;
                }
                base = str.charAt(i);
            }

            System.out.println(blue + " " + red);
            return Math.min(blue, red) + 1;
        }
    }

    //연속적인 부분을 하나로 보고 적은 수의 색을 가진 값 + 1 하는게 결과
    // +1 하는 이유는 전체를 색칠할 수 있기 때문
}
