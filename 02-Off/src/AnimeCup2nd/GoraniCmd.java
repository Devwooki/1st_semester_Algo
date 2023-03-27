package AnimeCup2nd;

import java.io.*;
import java.util.*;
public class GoraniCmd {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];


        int minR = Integer.MAX_VALUE;
        int minC = Integer.MAX_VALUE;

        int minRidx = 0;
        int minCidx = 0;


        for(int i = 0; i < N ; ++i){
            if(i == N-1) {
                st = new StringTokenizer(br.readLine(), " ");
                for(int j = 0 ; j < M ; ++j){
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if(map[i][j] < minC){
                        minC = map[i][j];
                        minCidx = j;
                    }

                    if(map[i][0] < minR){
                        minR = map[i][0];
                        minRidx = i;
                    }

                }
            }

            else {
                map[i][0] = Integer.parseInt(br.readLine());
                if(map[i][0] < minR){
                    minR = map[i][0];
                    minRidx = i;
                }
            }
        }

        System.out.println((minRidx +1) + " " + (minCidx+1));

    }
}
