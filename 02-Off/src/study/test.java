package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class test {
    static int N, M, table[][];
    static int numbers[];
    static int ans;
    static TreeSet<Integer> set = new TreeSet<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        table = new int[N][M];
        for(int r = 0; r < N; r++) {
            char[] tmp = br.readLine().toCharArray();
            for(int c = 0; c < M; c++) table[r][c] = tmp[c] - '0';
        }

        ans = -1;
        solution(1);
        for (Iterator<Integer> it = set.iterator(); it.hasNext(); ) {
            Integer i = it.next();
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println("사이즈 : " + set.size());
        System.out.println("결과 : "+ ans);
    }

    private static void solution(int no) {  // no: 자리수
        if(no > Math.max(M, N)) return;

        numbers = new int[no];

        int cnt = 0;
        for(int rDiff = 0; rDiff <= Math.ceil((double)N / no); rDiff++) {  // 행번호에 대한 공차
            for(int cDiff = 0; cDiff <= Math.ceil((double)M / no); cDiff++) {  // 열번호에 대한 공차
                if(rDiff == 0 && cDiff == 0 && no > 1) continue;

                makeRowSeq(rDiff, cDiff, no);
            }
        }
        solution(no + 1);
    }

    private static void makeRowSeq(int rDiff, int cDiff, int no) {
        for(int rStart = 0; rStart < N - rDiff * (no - 1); rStart++) {  // 시작 행 결정(증가하는 방향)

            makeColSeq(rDiff, cDiff, no, rStart);
        }
        for(int rStart = N - 1; rStart >= rDiff * (no - 1); rStart--) {  // 감소하는 방향
            makeColSeq(-1 * rDiff, cDiff, no, rStart);
        }
    }

    private static void makeColSeq(int rDiff, int cDiff, int no, int rStart) {
        colStart:
        for(int cStart = 0; cStart < M - cDiff * (no - 1); cStart++) {  // 시작 열 결정
            for(int i = 0; i < no; i++) {
                int r = rStart + (rDiff * i);
                int c = cStart + (cDiff * i);

                if(r < 0 || r > N || c < 0 || c > M) continue colStart;
                numbers[i] = table[r][c];
            }

            int num = 0;  // 열 증가하는 방향
            int numRev = 0;  // 열 감소하는 방향
            for(int i = 0; i < no; i++) {
                num += numbers[i] * (int)Math.pow(10, i);
                numRev += numbers[no - i - 1] * (int)Math.pow(10, i);
            }

            set.add(num);
            set.add(numRev);
            if(isSquare(num) && num > ans) ans = num;
            if(isSquare(numRev) && numRev > ans) ans = numRev;
        }
    }

    private static boolean isSquare(int number) {
        int sqrt = (int) Math.sqrt(number);
        if(number == sqrt * sqrt) return true;
        else return false;
    }
}