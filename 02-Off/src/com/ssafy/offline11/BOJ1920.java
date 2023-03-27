package com.ssafy.offline11;

import java.io.*;
import java.util.*;

public class BOJ1920 {
	static int[] result;
	static int[] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		arr = new int[N];

		arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		Arrays.sort(arr);
		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0 ; i < M ; ++i) {
			if(binSearch(Integer.parseInt(st.nextToken())))
				sb.append(1 + "\n");
			else sb.append(0 + "\n");
		}
		System.out.println(sb);
	}

	static boolean binSearch(int find) {

		int lo = 0;
		int hi = arr.length-1;
		int mid;
		while(lo <= hi) {
			mid = lo + (hi - lo) / 2;

			if(arr[mid] == find) return true;
			if(arr[mid] < find) lo = mid + 1;
			else if(arr[mid] > find) hi = mid - 1;

		}

		return false;
	}
}
