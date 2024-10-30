package JobChange;
import java.io.*;
import java.util.*;


public class 오큰수디버깅 {
    private static int answer = Integer.MAX_VALUE;
    public static void main(String[] args) {
        System.out.println('6');
        int[] numbers = new int[]{9, 1, 5, 3, 6, 2};
        //int[] numbers = new int[]{2, 3, 3, 5};
        int SIZE = numbers.length;
        int idx = 0;
        int[] answer = new int[SIZE];

        //배열 초기화
        Arrays.fill(answer, -1);

        Stack<Integer> stack = new Stack<>();
        for(int i = 0 ; i <SIZE ; ++i){
            while(!stack.isEmpty() && numbers[stack.peek()] < numbers[i]){
                answer[stack.pop()] = numbers[i];
            }
            stack.push(i);
        }

        System.out.println(Arrays.toString(answer));

        bfs(10,40, 5);
    }

    public static void bfs(int x, int y, int n){
        Queue<Calc> q = new LinkedList<>();

        q.offer(new Calc(x, 0));

        while(!q.isEmpty()){
            Calc curr = q.poll();

            int currX = curr.x;
            int currCnt = curr.cnt;

            if(currX > y) continue;
            if(currX == y) answer = Math.min(currCnt, answer);

            q.offer(new Calc(currX + n, currCnt + 1));
            q.offer(new Calc(currX * 2, currCnt + 1));
            q.offer(new Calc(currX * 3, currCnt + 1));
        }

        ArrayList<Integer> temp = new ArrayList<>();


        System.out.println(answer);
    }

    static class Calc{
        int x;
        int cnt;
        public Calc(int _x, int _cnt){
            this.x = _x;
            this.cnt = _cnt;
        }
    }
}
