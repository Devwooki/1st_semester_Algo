package second.contest;
import java.util.*;
import java.io.*;

public class BOJ2239 {
    static int[][] sudoku = new int[9][9];
    static boolean[] visited;
    static boolean end = false;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for(int i = 0 ; i < 9 ; ++i){
            String str = br.readLine();
            for(int j = 0 ; j < 9 ; ++j){
                sudoku[i][j] = str.charAt(j)-'0';
            }
        }

        dfs(0);
        print();
        System.out.println(sb);
    }

    static void dfs(int depth){
        if(depth == 81) {

            end = true;
            return;
        }

        int x = depth % 9;
        int y = depth / 9;

        if(sudoku[y][x] != 0){//0이 아니면 값이 있으니 패스
            dfs(depth+1);
        }else{//0일경우 가로, 세로, 작은 사각형을 체크해야한다.
//            visited = new boolean[10];
//            checkNumbers(x, y);
//
//            for(int i=1 ; i <= 9 ; ++i){
//                if(visited[i]) continue;
//
//                sudoku[y][x] = i;
//                dfs(depth+1);
//                if(end) return;
//                sudoku[y][x] = 0;
//            }
            //visited을 체크하는 과정에서 매번 visited가 dfs 재귀마다 변경됨 -> 다른 방식으로 처리
            for(int i =  1 ; i <= 9 ; ++i){
                if(!checkNumbers2(x, y, i)){//값이 없을 때 dfs 실시

                    sudoku[y][x] = i;
                    dfs(depth + 1);
                    if(end) return;
                    sudoku[y][x] = 0;

                }
            }

        }
    }

    static void print() {
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; j++) {
                sb.append(sudoku[i][j]);
            }
            sb.append("\n");
        }
    }

    static boolean checkNumbers2(int x, int y, int n){
        //가로, 세로 열부터 체크한다.
        for(int i = 0 ; i < 9 ; ++i){
            if(sudoku[y][i] == n || sudoku[i][x] == n) return true;
        }
        int newX = x/3 * 3;
        int newY = y/3 * 3;
        for(int i = newY; i < newY + 3 ; ++i ){
            for(int j = newX ; j < newX + 3 ; ++j){
                if(sudoku[i][j] == n) return true;
            }
        }
        return false;
    }
    static void checkNumbers(int x, int y){
        //가로, 세로를 체크한다
        for(int i = 0; i < 9 ; ++i){
            if(sudoku[y][i] != 0) visited[sudoku[y][i]] = true;
            if(sudoku[i][x] != 0) visited[sudoku[i][x]] = true;
        }

        int newX = x/3 * 3;
        int newY = y/3 * 3;
        for(int i = newY; i < newY + 3 ; ++i ){
            for(int j = newX ; j < newX + 3 ; ++j){
                visited[sudoku[i][j]] = true;
            }
        }
    }

}
