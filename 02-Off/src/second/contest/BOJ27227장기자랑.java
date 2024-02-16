package second.contest;

import java.io.*;
import java.util.*;

public class BOJ27227장기자랑 {
    private static int N;
    private static int[] soldiers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        soldiers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(soldiers);

        solution();
    }
    private static void solution() {
        int answer = 0;

        int front = N / 2 - 1;
        int back = N / 2;

        if (N % 2 == 1) { //홀수라면
            back += 1;
            answer = soldiers[N / 2];
        }

        for (int i = 0; i < N / 2; ++i) {
            answer += Math.max(0, soldiers[back + i] - soldiers[front - i]);
        }

        if (N % 2 == 0) answer += soldiers[front];

        System.out.println(answer);

    }
}

