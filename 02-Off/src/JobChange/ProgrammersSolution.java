package JobChange;

import java.io.*;
import java.sql.SQLOutput;
import java.util.*;

public class ProgrammersSolution {
    //선공 O, 후공X
    static int SIZE = 3;
    static int Ocnt = 0, Xcnt = 0;
    public static void main(String[] args) {
        String[] board = {"...", ".X.", "..."};

        int[][] inputs1 = {{40, 10000}, {25, 10000}};
        int[] inputs2 = {7000, 9000};
        //System.out.println(solution(board));
        //System.out.println(Arrays.toString(solution2(inputs1, inputs2)));
        System.out.println(solution(100015));

        HashMap<Integer, Integer> map = new HashMap<>();
        HashSet<Integer> set = new HashSet<>();
        map.values();
        ArrayList<Integer> tangerineCnt = new ArrayList<>(map.values());

        int[] arr = new int[5];

        Arrays.stream(arr).sum();

        System.out.println(Integer.toBinaryString(4));

    }

    public static int solution(String[] board) {
        //연산자 초기화
        for(int i = 0 ; i < SIZE ; ++i){
            String str = board[i];
            for(int j = 0 ; j < SIZE ; ++j){
                char c = str.charAt(j);
                if(c == 'O') Ocnt++;
                else if(c == 'X') Xcnt++;
            }
        }

        // Case1. Xcnt가 더 많으면 실패
        if( Ocnt < Xcnt ) return 0;

        // Case2. O가 X보다 2개 이상일 떄 -> 번갈아가면서 수행하지 않음
        if( Ocnt > Xcnt+1 ) return 0;

        // Case3. O가 이긴 상황일 때, X와 같으면
        if(winner(board, 'O')){
            if(Ocnt == Xcnt) return 0;
        }

        // Case4. X가 이긴 상황일 때, O가 1개 이상
        if(winner(board, 'X')){
            if(Ocnt >= Xcnt + 1) return 0;
        }

        return 1;
    }

    public static boolean winner(String[] board, char winner){
        Integer.compare(1,2);
        //가로
        for(int i = 0 ; i < SIZE ; ++i){
            if(board[i].charAt(0) == winner
                    && board[i].charAt(1) == winner
                    && board[i].charAt(2) == winner )
                return true;
        }

        //세로
        for(int i = 0 ; i < SIZE ; ++i){
            if(board[0].charAt(i) == winner
                    && board[1].charAt(i) == winner
                    && board[2].charAt(i) == winner )
                return true;
        }

        //왼쪽 대각선
        if(board[0].charAt(0) == winner
                && board[1].charAt(1) == winner
                && board[2].charAt(2) == winner )
            return true;

        //오른쪽 대각선
        if(board[2].charAt(0) == winner
                && board[1].charAt(1) == winner
                && board[0].charAt(2) == winner )
            return true;

        return false;
    }

    static int N, R;
    static int[] discounts;
    static int[][] usersCopy;
    static int[] emoticonsCopy;
    static PriorityQueue<int[]> pq;
    public static int[] solution2(int[][] users, int[] emoticons) {
        //idx 0 -> 가입자수
        //idx 1 -> 구매금액
        //가입자수가 동일하면, 판매액이 높은 것을 우선순위로 둔다
        pq = new PriorityQueue<>((o1, o2) -> {

            if(o1[0] == o2[0]) return Integer.compare(o2[1], o1[1]);
            return Integer.compare(o2[0], o1[0]);
        });

        usersCopy = users;
        emoticonsCopy = emoticons;

        N = users.length;
        R = emoticons.length;
        discounts = new int[R];

        perm(0);


        return pq.poll();
    }

    public static void perm(int cnt){
        if(cnt == R){
            calc();
            return;
        }
        //할인율이 10, 20, 30, 40
        //실제 가격과 곱해야하므로
        for(int i = 1 ; i <= 4; ++i){
            discounts[cnt] = i * 10; //이모티콘 별
            perm(cnt+1);
        }

    }

    public static void calc(){
        //인원별로 총 구매 금액 구한다.
        int[] payments = new int[N];
        for(int i = 0 ; i < N ; ++i){
            for(int j = 0 ; j < R ; ++j){
                //사용자가 생각하는 할인율 이상인 경우 구매
                if(usersCopy[i][0] <= discounts[j])
                    payments[i] += emoticonsCopy[j] * (100 - discounts[j]) / 100;
            }
        }

        //현재 할인비율의 가입자 수와 총 구매액을 구한다.
        int subCnt = 0;
        int total = 0;
        for(int i = 0 ; i < N ; ++i){
            //구매액이 사용자 임계치 이상일 경우 가입한다
            //그렇지 않을 경우 총 구매액
            if(usersCopy[i][1] <= payments[i]) subCnt += 1;
            else total += payments[i];
        }

        pq.offer(new int[]{subCnt, total});
    }

    public static int solution(int storey) {
        int answer = 0;

        while(storey > 0){
            int digit = storey % 10;
            storey /= 10;

            /*
            각 자리수에서 반올림이 가능한
            1 ~ 4 -> 빼기
            5면 앞자리 수 보기
            6 ~ 9 -> 더하기
            */
            if(digit == 5){//앞자리 수를 본다. 5이상이면 반올림해서 6이상이 되므로 더하기 연산
                if(storey % 10 >= 5){
                    answer += (10 - digit);
                    storey += 1;
                }else {
                    answer += digit;
                }
            }else if(digit > 5){
                answer += (10 - digit);
                storey += 1;
            }else{
                answer += digit;
            }
        }
        return answer;

        /*
        String storeyStr = String.valueOf(storey);
        int size = storeyStr.length();

        storeyArr = new int[size];
        for(int i = 0 ; i < size ; ++i){
            storeyArr[i] = Integer.parseInt(storeyStr.substring(i, i+1));
        }

        //일의자리 부터 반올림을 수행한다
        for(int i = size -1 ; i > 0 ; --i){
            if(storeyArr[i] == 0) continue;
            else if(storeyArr[i] > 5){
                answer += (10 - storeyArr[i]);
                storeyArr[i-1] += 1; //반올림 처리

            }else if(storeyArr[i] < 5){
                answer += storeyArr[i];
            }else{
                if(storeyArr[i-1] >= 5) {
                    answer += storeyArr[i];
                    storeyArr[i-1] += 1; //반올림 처리
                }else{
                    answer += storeyArr[i];
                }
            }
        }


        return answer + storeyArr[0];
        */
    }
}
