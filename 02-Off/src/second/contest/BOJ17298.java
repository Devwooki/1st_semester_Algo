package second.contest;
import java.io.*;
import java.util.*;

public class BOJ17298 {
	//스택을 안쓰고 2중 for문으로 풀이-> 37%에서 시간 초과발생
//	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st;
//		StringBuilder sb = new StringBuilder();
//		//오른쪽에 있으면서 자신보다 큰 수 중에 가장 왼쪽에 위치함.
//		
//		int N = Integer.parseInt(br.readLine());
//		int[] arr = new int[N];
//		arr = Arrays.stream(br.readLine().split(" " )).mapToInt(Integer::parseInt).toArray();
//		//Stack<Integer> stack = new Stack<>();
//		Queue<Integer> stack = new LinkedList<>();
//		for(int i = 0 ; i < N ; ++i) {
//			int now = arr[i];
//			for(int j = i + 1; j < N ; ++j ) {
//				if(now < arr[j]) {
//					stack.offer(arr[j]);
//					break;
//				}
//			}
//			if(stack.size() < i+1) stack.offer(-1);
//		}
//		while(!stack.isEmpty()) {
//			sb.append(stack.poll() + " ");
//		}
//		System.out.println(sb.toString().trim);
//	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		arr = Arrays.stream(br.readLine().split(" " )).mapToInt(Integer::parseInt).toArray();
		Stack<Integer> stack = new Stack<>();
		
		//스택에 넣는 데이터는 인덱스임을 기억하자
		stack.push(0);
		for(int i = 1 ; i < N ; ++ i) {
			while(!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
				int idx = stack.pop();
				arr[idx] = arr[i];

			}
			stack.push(i);
		}
		
		while(!stack.isEmpty()) {
			arr[stack.pop()] = -1;
		}
		for(int value : arr) {
			sb.append(value + " ");
		}
		System.out.println(sb);
	}
}
