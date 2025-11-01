package JobChange;
import java.io.*;
import java.util.*;


public class BOJ14712_넴모넴모Easy_GD5 {
    static int N, M, answer = 0;
    static boolean[][] map;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new boolean[N][M];

        //넴모들이 올라간 칸이 2*2를 이루지 않는 모든 경우의 수
        //nemmonemmo(0);
        nemmonemmo2(0,0);

        System.out.println(answer);
    }

    private static void nemmonemmo(int depth){
        if(depth == N * M){
            if(check()) answer++;
            return;
        }

        //최대 수행횟수는 아래와 같이 정의한다.
        int x = depth % M;
        int y = depth / N;

        //넴모를 놓고 가는 경우
        map[y][x] = true;
        nemmonemmo(depth + 1);

        //넴모를 놓지 않고 가는 경우
        map[y][x] = false;
        nemmonemmo(depth + 1);
    }

    private static void nemmonemmo2(int x, int y){
        if(y == N){
            if(check()) answer++;
            return;
        }

        //다음 좌표 계산
        int nextY = y;
        int nextX = x + 1;
        if(nextX == M){//x가 끝에 다다르면 y를 1증가시킨다.
            nextX = 0;
            nextY += 1;
        }

        //넴모를 놓고 가는 경우
        map[y][x] = true;
        nemmonemmo2(nextX, nextY);

        //넴모를 놓지 않고 가는 경우
        map[y][x] = false;
        nemmonemmo2(nextX, nextY);
    }

    private static boolean check(){
        // 현재 x,y 좌표의 x+1, y+1까지 체크해서 2*2가 잇는지 체크
        for(int i = 0 ; i < N - 1 ; ++i){
            for(int j = 0 ; j < M- 1 ; ++j){
                if(map[i][j] && map[i][j+1] && map[i+1][j] && map[i+1][j+1]) return false;
            }
        }
        return true;
    }
}
