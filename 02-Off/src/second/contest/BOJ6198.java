package second.contest;
import java.io.*;
import java.util.*;
public class BOJ6198 {
//    public static void main(String[] args) throws IOException{
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st;
//        StringBuilder sb = new StringBuilder();
//        int N = Integer.parseInt(br.readLine());
//        long result = 0;
//        Stack<Long> stack = new Stack<>();
//        stack.push(Long.parseLong(br.readLine()));
//        for(int i = 2 ; i <= N ; ++i){
//            Long temp = Long.parseLong(br.readLine());
//
//            //스택의 top보다 작으면 넣고 크거나 같으면 뺀다.
//            while(!stack.isEmpty() && temp >= stack.peek()) stack.pop();
//            //스택에서 뻈으니 temp를 넣고
//            stack.push(temp);
//
//            //관리인이 볼 수 있는 옥상 수는 다음 빌딩을 확인 할 때 마다 스택에 포함된 빌딩 수 - 1 해준다.
//            result += stack.size()-1;
//
//        }
//        System.out.println(result);
//    }

    static long[] stack;
    static int size = 0;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        stack = new long[N+1];
        long result = 0;
        push(Long.parseLong(br.readLine()));
        for(int i = 2 ; i <= N ; ++i){
            long temp = Long.parseLong(br.readLine());
            while( size != 0 && peek() <= temp) pop();

            push(temp);
            result += size-1;
        }
        System.out.println(result);

    }
    static void push(long number){
        stack[size++] = number;
    }
    static void pop(){
        size--;
    }
    static long peek(){
        //push이후 사이즈는 1증가한 상태이니 다음 위치를 가르키고 있음
        return stack[size-1];
    }
}
