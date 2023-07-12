package TOSSNEXT;

import java.util.*;

public class PRO1 {
    public int solution(String s, int N) {

        int answer = -1;
        HashSet<Integer> set = new HashSet<>();

        for(int i = 1 ; i <= N ; ++i){
            set.add(i);
        }

        for(int i = 0 ; i <= s.length()-N; ++i){
            String temp = s.substring(i, i + N);

            if(checkCondition(set, temp, N)) answer = Math.max(answer, Integer.parseInt(temp));
        }
        return answer;


    }

    static boolean checkCondition(HashSet<Integer> set ,String subStr, int N){
        boolean[] duplCheck = new boolean[N+1];

        for(int j = 0 ; j < N; ++j ){
            int now = subStr.charAt(j) - '0';
            if(now > N) return false; //N보다 크면 종료
            if(duplCheck[now] || !set.contains(now)) return false; //이미 체크된 값이거나 포함되지 않으면 종료

            duplCheck[now] = true;
        }
        return true;
    }
}