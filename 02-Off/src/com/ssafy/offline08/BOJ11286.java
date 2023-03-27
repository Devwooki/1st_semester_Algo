package com.ssafy.offline08;
import java.io.*;
import java.util.*;


public class BOJ11286 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		//우선순위 : 절댓값이 같으면 -> 값끼리 비교해서 작은 것 출력
		// 			절댓값이 다르면 -> 절댓값끼리 비교
		PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return Math.abs(o1) == Math.abs(o2) ? o1-o2 : Math.abs(o1) - Math.abs(o2);
			}
			
		});
		
		int N = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < N ; ++i) {
			int cmd = Integer.parseInt(br.readLine());
			
			//명령어가 0 -> 큐가 비어있으면 0출력, 아니면 큐에서 뽑음
			if(cmd == 0) {
				sb.append( !pq.isEmpty() ? pq.poll() + "\n" : "0\n" );				
			}
			else pq.add(cmd);
		}
		
		System.out.println(sb);
	}
}
