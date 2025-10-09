package JobChange.Binary;

import java.io.*;
import java.util.*;

/*
* 왼쪽에서 오른쪽으로만 움직일 수 있음
* 왼쪽에서 오른쪽으로 갈 수 있는 모든 경우에서 K가 최소인 값
*
* */
public class BOJ22871 {
    static int N, K = Integer.MAX_VALUE;
    static int[] power;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        power = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();


    }
}
