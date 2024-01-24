package second.contest;

import java.io.*;
import java.util.*;

public class BOJ1015 {
    //문제 결국 출력순서를 보장해라, 단 오름차순으로 정렬하며 t숫자가 같은 경우, 앞에께 더 낮은 순위
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        Value[] arr = new Value[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = new Value(Integer.parseInt(st.nextToken()), i);
        }

        Arrays.sort(arr);

        int[] answer = new int[N];

        for (int i = 0; i < N; i++) {
            answer[arr[i].idx] = i;
        }
        for(int temp : answer) sb.append(temp + " ");

        System.out.println(sb);
    }

    static class Value implements Comparable<Value> {

        int value;
        int idx;

        public Value(int value, int idx) {
            this.value = value;
            this.idx = idx;
        }

        @Override
        public int compareTo(Value v) {
            return this.value == v.value ? Integer.compare(this.idx, v.idx) : Integer.compare(this.value, v.value);
        }
    }
}
