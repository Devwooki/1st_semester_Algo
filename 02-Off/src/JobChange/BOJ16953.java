package JobChange;
import java.io.*;
import java.util.*;


public class BOJ16953 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        int answer = 1;

        while(B != A){
            if(B < A){//B가 A보다 작으면 계산이 불가능하다.
                answer = -1;
                break;
            }


            String tempB = String.valueOf(B);
            char lastValue = tempB.charAt(tempB.length() -1);
            //B가 1의 자리 숫자가 3,5,7,9인경우 만들 수 없다
            if(lastValue != '1' && B % 2 != 0){
                answer = -1;
                break;
            }

            if(B % 2 == 0){
                B /= 2;

            }else {
                tempB = tempB.substring(0, tempB.length() -1);
                B = Long.parseLong(tempB);
            }
            answer += 1;
        }

        System.out.println(answer);
    }
}

