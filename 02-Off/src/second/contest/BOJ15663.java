package second.contest;
import java.io.*;
import java.util.*;


public class BOJ15663 {
	static int[] numbers, inputs;
	static boolean[] visited;
	static int N, M;
	static LinkedHashSet<String> result;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		inputs = new int[N];
		visited = new boolean[N];
		numbers = new int[M];
		result = new LinkedHashSet<>();
		inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		
		Arrays.sort(inputs);
		nPr(0, 0);
		
		for(String str : result) {
			System.out.print(str);
		}
	}
	
	static void nPr(int cnt, int flag) {
		if(cnt == M) {
			StringBuilder sb = new StringBuilder();
			for(int num : numbers) {
				sb.append(num + " ");
			}
			sb.append("\n");
			result.add(sb.toString());
			return;
		}
		
		for(int i = 0 ; i < N  ; ++i) {
			if((flag & (1 << i)) != 0) continue;
			numbers[cnt] = inputs[i];
			nPr(cnt+1, flag | (1 << i) );
		}
	}
}
