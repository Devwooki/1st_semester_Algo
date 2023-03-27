package com.ssafy.offline06;
import java.io.*;
import java.util.*;


public class BOJ1927 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		PriorityQueue<Integer> minHeap = new PriorityQueue<>(Collections.reverseOrder());
		int N = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < N ; ++i) {
			int x = Integer.parseInt(br.readLine());
			if(x != 0) {
				minHeap.add(x);
			}else {
				if(minHeap.isEmpty()) sb.append(0 + "\n");
				else sb.append(minHeap.poll()+"\n");
			}
		}
		System.out.println(sb);
	}
}
