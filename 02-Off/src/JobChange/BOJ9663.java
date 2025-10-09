package JobChange;

import java.io.*;


/*N-Queen으로 일반적으로 좌표 압축의 형태를 가짐
*
* index
*
*
* */
public class BOJ9663 {
    static int N, result = 0;
    static int[] map;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N];

        nQueen(0);

        System.out.println(result);
    }

    private static void nQueen(int cnt){
        if(cnt == N ){
            result++;
            return;
        }

        for(int i = 0 ; i < N ; ++i){
            map[cnt] = i;
            if(isPossible(cnt)) nQueen(cnt+1);
        }
    }

    private static boolean isPossible(int cnt){
        //현재위치까지 문제 없는지 열, 대각선 전체 체크한다.
        for(int i = 0 ; i < cnt ; ++i){
            //값이 같다 -> 열이 같다.
            if(map[cnt] == map[i]) return false;
            //-> 대각선이 같다.
            else if(Math.abs(cnt-i) == Math.abs(map[cnt] - map[i])) return false;
        }

        return true;
    }
}
