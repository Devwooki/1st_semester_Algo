package JobChange;

import java.util.Arrays;
import java.util.*;

public class test {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        sb.append("12345");

        int[] temp = Arrays.stream(new int[5]).map(i -> i + 1).toArray();
        System.out.println(Arrays.toString(temp));

        System.out.println(solution2(5));

        System.out.println(solution3(80, new int[][]{{80,20},{50,40},{30,10}}));
    }

    public static long[] solution(long[] numbers) {

        int size = numbers.length;
        long[] answer = new long[size];
        for(int i = 0 ; i < size ; ++i){
            //짝수 : +1만 더해주면 된다(이진법에서 마지막 항만 홀수 이기 때문)
            if(numbers[i] % 2 == 0){
                answer[i] = numbers[i] + 1;
            }else{
                String binStr = Long.toBinaryString(numbers[i]);

                System.out.print(binStr + " -> ");
                int findIndex = binStr.lastIndexOf("01");
                if(findIndex == -1){
                    int tobinLen = binStr.length();
                    String bigMinNo = "10" + binStr.substring(1, tobinLen);
                    System.out.println(bigMinNo);
                    answer[i] = Long.parseLong(bigMinNo, 2);

                }else{
                    binStr = binStr.substring(0, findIndex) + "10" + binStr.substring(findIndex+2);
                    System.out.println(binStr);
                    answer[i] = Long.parseLong(binStr, 2);
                }
            }
        }
        return answer;
    }

    public static int[] solution2(int n) {
        // 1 부터 N까지의 합
        int size = n * (n + 1) / 2;
        int[] answer = new int[size];

        int[][] map = new int[n][n];
        /* 방향은 아래로, 오른쪽, 왼쪽 대각선 순서대로 움직인다
        아래 : (y++, x)
        오른쪽: (y, x++)
        대각선: (y--, x--)
        */

        int num = 1;
        int x = 0, y = -1;
        for(int i = 0 ; i < n ; ++i){ //방향회전은 n번만큼 발생한다
            for(int j = i; j < n ; ++j){//j의 횟수는 n, n-1, n-2 ... 1로 점차 줄어든다

                if(i % 3 == 0){
                    y++;
                }else if(i % 3 == 1){
                    x++;
                }else if(i % 3 == 2){
                    x--;
                    y--;
                }

                map[y][x] = num++;
            }
        }

        int k = 0;
        for(int i = 0 ; i < n ; ++i){
            for(int j = 0 ; j <= i ; ++j){
                answer[k++] = map[i][j];
            }
        }
        return answer;
    }

    static boolean[] visited;
    static int[] seq;
    static int[] inputs;
    static int N, R, answer = Integer.MIN_VALUE;
    static int userPIRODO;
    static int[][] dungeons;

    public static int solution3(int k, int[][] map) {
        //완전 탐색을 위한 변수 선언
        N = R = map.length;

        visited = new boolean[N];
        seq = new int[R];
        inputs = new int[N];

        userPIRODO = k;
        //dungeons = map;

        for(int i = 0 ; i < N ; ++i){
            inputs[i] = i;
        }

        perm(0);

        return answer;
    }

    public static void perm(int cnt){
        if(cnt == R){
            calc();
            //System.out.println(Arrays.toString(seq));
            return;
        }

        for(int i = 0 ; i < N ; ++i){
            if(visited[i]) continue;

            seq[cnt] = inputs[i];
            visited[i] = true;

            perm(cnt+1);
            visited[i] = false;
        }
    }

    public static void calc(){
        int cnt = 0;
        int tempPiro = userPIRODO;

        for(int i = 0 ; i < R ; ++i){

            if(tempPiro >= dungeons[seq[i]][0]){
                tempPiro -= dungeons[seq[i]][1];
                cnt++;
            }else{
                break;
            }

        }

        answer = Math.max(cnt, answer);
    }
}
