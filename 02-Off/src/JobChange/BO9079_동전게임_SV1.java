package JobChange;

import java.io.*;
import java.util.*;
/* 2진수
*  1     1  1     1  1 1 111
*  0     0  0     0  0 0 000
*  256 128 64    32 16 8 421
*
* 열 뒤집는 숫자 : 443, 비트를 3칸씩 민다., 3번반복
* 행 뒤집는 숫자 : 292, 선택된 비트를 1칸씩 민다. 3번 반복
* /대각선 : 84
* \대각선 : 273
*
* */
public class BO9079_동전게임_SV1 {
    char[][] map = new char[3][3];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int TC = Integer.parseInt(br.readLine());

        for(int tc = 0 ; tc < TC ; ++tc){
            int val = 0 ;
            //입력값(H, T)에 따라 2진수로 표현한다.
            //ex H T T H T T T H H -> 011 011 100(2) = 220

            for(int i = 0 ; i < 3 ; ++i){
                String[] str = br.readLine().split(" ");

                for(int j  = 0 ; j < 3 ; ++j){

                    val = val << 1; //비트를 한 칸씩 민다.
                    if(str[j].equals("T")){
                        val += 1;   //자릿수를 1 더한다.
                    }
                }
            }
/*            System.out.println(val + " : " + toBinary9(val));

            for(int j = 0 ; j < 3 ; ++j){
                System.out.print((292 >> (1*j)) + " : ");
                System.out.print( val ^ (292 >> (1*j)));
                System.out.println( " -> " + toBinary9( val ^ (292 >> (3*j))));
            }*/
            System.out.println(bfs(val));
        }
    }

    private static int bfs(int val){
        Queue<int[]> q = new ArrayDeque<>();
        boolean[] visited = new boolean[512]; // 9자리 이진수 이므로

        //현재 숫자, 시행횟수
        q.offer(new int[]{val, 0});
        visited[val] = true;

        while(!q.isEmpty()){
            int[] curVal = q.poll();
            //System.out.println(curVal + " : " + toBinary9(curVal[0]));

            //모든 동전이 뒤집어 지면 종료
            if(curVal[0] == 0 || curVal[0] == 511) return curVal[1];

            //행별 뒤집기
            for(int i = 0 ; i < 3 ; ++i){
                int tVal = curVal[0];
                tVal ^= (448 >> (i *3)) ;//i행에 해당하는 비트를 뒤집는다.

                if(!visited[tVal]){
                    visited[tVal] = true;
                    q.offer(new int[]{tVal, curVal[1] + 1});
                }
            }

            //열별 뒤집기
            for(int i = 0 ; i < 3 ; ++i){
                int tVal = curVal[0];
                tVal ^= (292 >> (i * 1)) ;//i행에 해당하는 비트를 뒤집는다.
                if(!visited[tVal]){
                    visited[tVal] = true;
                    q.offer(new int[]{tVal, curVal[1] + 1});
                }
            }

            // (/) 대각선 뒤집기
            int tVal = curVal[0];
            tVal ^= 84;
            if(!visited[tVal]){
                visited[tVal] = true;
                q.offer(new int[]{tVal, curVal[1] + 1});
            }

            // (\)대각선 뒤집기
            tVal = curVal[0];
            tVal ^= 273;
            if(!visited[tVal]){
                visited[tVal] = true;
                q.offer(new int[]{tVal, curVal[1] + 1});
            }

        }

        // q가 빌 때 까지 수행되었다는 것은 완성할 수 없다는 것을 의미
        return -1;
    }

    private static String toBinary9(int val){
        return String.format("%9s", Integer.toBinaryString(val)).replace(' ', '0');
    }
}

/*
* oxo
* xox
* oxo
*
* oxx
* xxx
* xxo
* */