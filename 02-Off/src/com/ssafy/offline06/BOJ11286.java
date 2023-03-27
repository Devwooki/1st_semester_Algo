package com.ssafy.offline06;

import java.io.*;
import java.util.*;


public class BOJ11286 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		PriorityQueue<Integer> absHeap = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				if(Math.abs(o1) == Math.abs(o2))
					return o1-o2;
//				else if(Math.abs(o1) > Math.abs(o2))
//					return Math.abs(o1) - Math.abs(o2);
//				else return -1;
				else return Math.abs(o1) - Math.abs(o2);
			}
			
		});
		int N = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < N ; ++i) {
			int x = Integer.parseInt(br.readLine());
			if(x != 0) {
				absHeap.add(x);
			}else {
				if(absHeap.isEmpty()) sb.append(0 + "\n");
				else sb.append(absHeap.poll()+"\n");
			}
		}
		System.out.println(sb);
	}
}
