package second.contest;

import java.io.*;
import java.util.*;


public class BOJ1124 {

    static int A, B;
    static boolean[] isPrime;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        isPrime = new boolean[B+1];

        findPrime();

        int answer = 0;

        for (int i = A; i <= B; i++) {
            if (getUnderPrime(i)) answer++;
        }

        System.out.println(answer);
    }

    private static boolean getUnderPrime(int value) {
        int primeCnt = 0; //소수 갯수 파악
        int divNum = 2; //나눌 값

        while (value > 1) {
            if(isPrime[divNum]){ //나눌 값이 소수인지 체크
                divNum++;
                continue;
            }

            if(value%divNum == 0) { //나눠떨어지면
                value /= divNum;
                primeCnt++;
            }else{
                divNum++;
                continue;
            }
        }


        return !isPrime[primeCnt];
    }

    private static void findPrime() {
        isPrime[0] = isPrime[1] = true;

        //제곱근까지 체크한다. 곱하면 끝이니까.
        for (int i = 2; i <= Math.sqrt(B+1); ++i) {

            //만약 소수(false)라면 소수가 곱해진 값들은 소수가 아니다(true)
            if (!isPrime[i]) {
                for (int j = i * i; j < B+1; j += i) {
                    isPrime[j] = true;
                }
            }
        }
    }

}
