package JobChange;

import java.io.*;
import java.util.*;

public class BOJ20207 {
    private static int[] map;
    private static ArrayList<int[]> record;
    private static int N, calSize  = 365;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[calSize];
        record = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            int stt = Integer.parseInt(st.nextToken()) -1;
            int end = Integer.parseInt(st.nextToken()) -1;

            for(int start = stt ; start < end ; ++start){
                map[start]++;
            }
        }

        recordFilm();
        System.out.println("test");
    }

    private static void recordFilm(){
        boolean flag = false;
        for(int i = 0 ; i < calSize ; ++i){
            if(!flag && map[i] != 0){
                record.add(new int[]{i, map[i]});
                flag = true;
            }else if(flag && map[i] == 0){
                record.add(new int[]{i-1, map[i-1]});
                flag = false;
            }
        }
    }
}
