package second.contest;

import java.io.*;
import java.util.*;
import java.awt.*;
import java.util.List;


public class BOJ1198 {
    static int N;
    static Point[] points;
    static Point[] comb;
    static boolean[] visited;
    static double answer = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        comb = new Point[3];
        points = new Point[N];
        visited = new boolean[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            points[i] = new Point(x, y);
        }

        combination(0,0);
        //최악의 경우 35C3 = 65XX
        //3점의 죄표를 통해 최대 값을 구하면 된다.
        System.out.println(answer );
    }

    static void combination(int cnt, int start){
        if(cnt == 3){
            answer = Math.max(answer, getArea());
            //System.out.println(Arrays.toString(comb));
            return;
        }

        for(int i = start ; i < N ; ++i){
            comb[cnt] = points[i];
            combination(cnt + 1, i+1);
        }
    }


    static double getArea(){
        //세 점의 좌표로 넓이 구하는 공식 활용
        // 1/2 * |(x1y2 + x2y3 + x3y1) - (x2y1+x3y2+x1y3)|
        return (Math.abs((comb[0].x * comb[1].y + comb[1].x * comb[2].y + comb[2].x * comb[0].y) -
                         (comb[1].x * comb[0].y + comb[2].x * comb[1].y + comb[0].x * comb[2].y))
                / 2.0);
    }

}
