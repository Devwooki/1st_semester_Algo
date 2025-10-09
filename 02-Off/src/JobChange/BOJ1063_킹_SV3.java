package JobChange;

import java.io.*;
import java.util.*;
import java.awt.*;
public class BOJ1063_킹_SV3 {
    static int SIZE = 8;
    static Point stone, king;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] arr = br.readLine().split(" ");

        //입력값들로 돌과 킹을 초기화 한다.
        king = new Point(arr[0].charAt(0) - 'A', arr[0].charAt(1) - '1');
        stone = new Point(arr[1].charAt(0) - 'A', arr[1].charAt(1) - '1');
        int N = Integer.parseInt(arr[2]);

        for(int i = 0 ; i < N ; ++i){
            //이동할 위치에 돌이 있는 경우 진행방향으로 돌을 밀어낸다.
            //만약 움직여서 범위를 초과할 경우 건너 뛴다.
            DIR dir = DIR.getValue(br.readLine());

            int nx = king.x + dir.getX();
            int ny = king.y + dir.getY();

            //이동할 위치에 돌이 있으면 돌 부터 옮긴다.
            if(stone.getX() == nx && stone.getY() == ny){
                int stNx = (int) stone.getX() + dir.getX();
                int stNy = (int) stone.getY() + dir.getY();

                //범위를 초과하면 무시한다.
                if(!checkRange(stNx, stNy)) continue;

                stone = new Point(stNx, stNy);
            }

            if(!checkRange(nx, ny)) continue;
            king = new Point(nx, ny);
        }

        StringBuilder sb = new StringBuilder();

        sb.append((char)(king.getX() + 'A'));
        sb.append((char)(king.getY() + '1'));
        sb.append("\n");
        sb.append((char)(stone.getX() + 'A'));
        sb.append((char)(stone.getY() + '1'));

        System.out.println(sb);
    }

    private static boolean checkRange(int x, int y){
        return (( 0 <= x && x < SIZE ) && ( 0 <= y && y < SIZE ));
    }

    public enum DIR{
        R(1,0), L(-1,0),
        B(0, -1), T(0, 1),
        RT(1, 1), LT(-1,1),
        RB(1,-1), LB(-1,-1);

        DIR(int x, int y){
            this.x = x;
            this.y = y;
        }

        private final int x;
        private final int y;

        public int getX() {return x;}
        public int getY() {return y;}
        public static DIR getValue(String s){
            return DIR.valueOf(s);
        }
    }
}
