package second.contest;


import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class 모닝순조부 {
	static int N, R;
	static int[] numbers, input;
	static boolean[] selected;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		R = sc.nextInt();
		numbers = new int[R];
		input = new int[N];
		selected = new boolean[N];
		
		for (int i = 0; i < N; i++) input[i] = sc.nextInt();
		
		System.out.println("순열");
		perm(0);
		
		System.out.println("조합");
		comb(0, 0);
		
		System.out.println("부분집합 - 재귀");
		subset(0);
		
		System.out.println("부분집합 - 비트");
		subset();
		
		System.out.println("순열 - 비트");
		perm2(0, 0);
		
		System.out.println("순열-Next");
		nextPerm();

		System.out.println("조합-Next");
		nextComb();
	}

	private static void perm(int cnt) {
		if(cnt == R) {
			System.out.println(Arrays.toString(numbers));
			return;
		}
		for(int i=0; i < N; i++) {
			if(selected[i]) continue;
			
			numbers[cnt] = input[i];
			selected[i] = true;
			perm(cnt + 1);
			selected[i] = false;
		}
	}

	private static void comb(int cnt, int start) {
		if(cnt == R) {
			System.out.println(Arrays.toString(numbers));
			return;
		}
		
		for(int i=start; i < N; i++) {
			numbers[cnt] = input[i];
			comb(cnt + 1, i + 1);
		}
	}
	
	private static void subset() {
		for(int i=0; i < (1 << N); i++) {
			StringBuilder sb = new StringBuilder();
			for(int j=0; j < N; j++)
				if((i & (1 << j)) !=0) sb.append(input[j] + " ");
			System.out.println(sb.toString());
		}
	}
	
	private static void subset(int cnt) {
		if(cnt == N) {
			StringBuilder sb = new StringBuilder();
			for(int i=0; i < N; i++)
				if(selected[i]) sb.append(input[i] + " ");
			
			System.out.println(sb.toString());
			return;
		}
		selected[cnt] = true;
		subset(cnt + 1);
		selected[cnt] = false;
		subset(cnt + 1);
	}
	
	private static void perm2(int cnt, int flag) {
		if(cnt == N) {
			System.out.println(Arrays.toString(numbers));
			return;
		}
		for(int i = 0 ; i < 1 << N ; ++i) {
			if( (flag & (1 << N )) == 0) continue;
			numbers[cnt] = input[i];
			perm2(cnt+1, flag | (1 << i));
			
		}
		
	}
	
	private static void nextPerm() {
		Arrays.sort(input);
		
		do {
			System.out.println(Arrays.toString(input));
		}while(np(input));
	}
	
	private static void nextComb() {
		int[] arr = new int[N];
		for (int i = 0; i < R; i++) {
			arr[N-1-i] = 1;
		}
		do {
			// selected
			for(int i = 0 ; i < N ; ++i) {
				if(arr[i]!= 0) System.out.print(input[i] + " ");
			}
			System.out.println();
			
		}while(np(arr));
	}
	
	private static boolean np(int[] input) {
		
		int n = input.length;
		
		int i = n - 1;
		while (i > 0 && input[i-1] >= input[i]) --i;
		
		if (i == 0) return false;
		
		int j = n - 1;
		while (input[i-1]>= input[j]) --j;
		
		swap(input, i-1, j);
		
		int k = n - 1;
		while (i < k) {
			swap(input, i++, k--);
		}
		return true;
	}

	private static void swap(int numbers[], int i, int j) {
		int temp = numbers[i];
		numbers[i] = numbers[j];
		numbers[j] = temp;
	}	
}