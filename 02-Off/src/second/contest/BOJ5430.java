package second.contest;
import java.io.*;
import java.util.*;
/*
R : 뒤집기 - 뒤집기
D : 버리기 - 첫번째 꺼냄
 */
public class BOJ5430 {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        
        int TC = Integer.parseInt(br.readLine());
        for (int i = 0; i < TC; i++) {
            String cmd = br.readLine();
            int n = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine(), "[],");
            //LinkedList<Integer> list = new LinkedList<>();
            int[] list = new int[n];
            //원소 입력받음
            for(int j = 0 ; j < n ; ++j){
                //list.add(Integer.parseInt(st.nextToken()));
                list[j] = Integer.parseInt(st.nextToken());
            }

            //명령 수행
            //cmd의 R갯수 체크
            boolean checkDirection = false;
            int cntR = 0;
            int start = 0;
            int end = n-1;
            boolean isBreak = false;
            for (int j = 0; j < cmd.length(); j++) {
                char c = cmd.charAt(j);
                if(c == 'R'){
                    checkDirection = !checkDirection;
                    cntR++;
                }else{
//                    if(list.isEmpty()){
//                        isBreak = true;
//                        break;
//                    }
                    if(start > end || list.length == 0){
                        isBreak = true;
                        break;
                    }
                    if(!checkDirection){
                        //list.pollFirst();
                        start++;
                    }else{
                        //list.pollLast();
                        end--;
                    }
                }
            }

            if(isBreak) sb.append("error\n");
            else makeResult(list,cntR,start,end);
        }
        System.out.println(sb);
    }

    static void makeResult(int[] list, int cnt, int start, int end){
        sb.append("[");
        if(cnt % 2 == 0){
            for(int i = start ; i <= end ; ++i){
                sb.append(list[i]);
                if(i != end) sb.append(",");
            }
        }else{
            for(int i = end ; i >= start ; --i){
                sb.append(list[i]);
                if(i != start) sb.append(",");
            }
        }
        sb.append("]\n");
    }
}
/* 16퍼 시간 초과 코드 -> p의 길이가 100,000이라 그런듯
for (int i = 0; i < TC; i++) {
            String cmd = br.readLine();
            int n = Integer.parseInt(br.readLine());

            String arrStr = br.readLine();
            int[] list = new int[n];
            //원소 입력받음
            for(int j = 0 ; j < n ; ++j){
                list[j] = arrStr.charAt(2*j + 1) - '0';
            }

            //명령 수행
            boolean isBreak = false;
            int start = 0;
            int end = n-1;
            for (int j = 0; j < cmd.length(); j++) {
                char c = cmd.charAt(j);
                if(c == 'R') {
                    for(int k = 0 ; k < (n - start)/2; ++k){
                        int front = list[start+k];
                        int back = list[n-1-k];
                        list[start+k] = back;
                        list[n-1-k] = front;
                    }
                }else{
                    if(start >= n || list.length == 0) { //start가 배열 크기를 넘어가거나 배열 크기가 0일떄
                        isBreak = true;
                        break;
                    }
                    start++;
                }
            }
            if(isBreak) sb.append("error\n");
            else sb.append(Arrays.toString(Arrays.copyOfRange(list, start, n)) + "\n");
        }
 */
