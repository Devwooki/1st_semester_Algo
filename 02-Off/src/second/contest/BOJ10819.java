package second.contest;

import java.io.*;
import java.util.*;

public class BOJ10819 {
    static int N, R, answer = Integer.MIN_VALUE;
    static boolean[] visited;
    static int[] inputs;
    static int[] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        R = N;
        result = new int[N];
        inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        visited = new boolean[N];

        permitation(0);
        System.out.println(answer);
    }

    private static void permitation(int cnt) {
        if (cnt == R) {
            answer = Math.max(getResult(), answer);
            return;
        }

        for (int i = 0; i < N; ++i) {
            if (visited[i]) continue;

            result[cnt] = inputs[i];
            visited[i] = true;

            permitation(cnt + 1);
            visited[i] = false;
        }
    }

    private static int getResult() {
        int sum = 0;
        for (int i = 0; i < N - 1; i++) {
            sum += Math.abs(result[i] - result[i + 1]);
        }
        return sum;
    }
}
