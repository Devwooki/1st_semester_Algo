package second.contest;
import java.io.*;
import java.util.*;


public class BOJ5525 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        //StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        char[] arr = br.readLine().toCharArray();

//        int size = 2*N+1;
//        for(int i = 0; i < N ; ++i){
//            sb.append("IO");
//        }
//        sb.append("I");

        int cnt = 0;
        int answer = 0;
        for(int i = 1 ; i < M-1 ; ++i){
            if(arr[i-1] == 'I' && arr[i] == 'O' && arr[i+1] == 'I'){
                cnt++;
                if(cnt == N){
                    cnt--;
                    answer++;
                }
                i++; //OI 건너뛴다
            }else{
                cnt = 0;
            }
        }
        System.out.println(answer);
    }
}
