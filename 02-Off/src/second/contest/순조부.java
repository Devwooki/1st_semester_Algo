package second.contest;
import java.io.*;
import java.util.*;

public class 순조부 {
	static int N, R, totalCnt=0;
	static int[] numbers, inputs;
	static boolean[] visited;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		R = Integer.parseInt(br.readLine());
		
		inputs = new int[N];
		numbers = new int[R];
		visited = new boolean[N];
		
		for(int i = 0 ; i < N ; ++i) {
			inputs[i] = i + 1;
		}
		
		nPr(0);
		System.out.println("순열 " + totalCnt);
		totalCnt = 0;
		
		nCr(0,0);
		System.out.println("조합 " + totalCnt);
		totalCnt = 0;
	}
	
	static void nPr(int cnt) {
		if(cnt == R) {
			System.out.println(Arrays.toString(numbers));
			totalCnt++;
			return;
		}
		
		for(int i = 0 ; i < N ; ++i) {
			if(visited[i]) continue;
			
			visited[i] = true;
			numbers[cnt]=inputs[i];
			
			nPr(cnt+1);
			visited[i] = false;
		}
	}
	static void nCr(int cnt, int start) {
		if(cnt == R) {
			System.out.println(Arrays.toString(numbers));
			totalCnt++;
			return;
		}
		for(int i = start ; i < N ; ++i) {
			numbers[cnt] = inputs[i];
			nCr(cnt+1, i+1);
		}
	}
}
