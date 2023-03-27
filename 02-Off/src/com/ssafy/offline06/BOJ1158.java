package com.ssafy.offline06;
import java.io.*;
import java.util.*;

public class BOJ1158 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		LinkedList<Integer> list = new LinkedList<>();
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		for(int i = 1 ; i <= N ; ++i) {
			list.add(i);
		}
		
		sb.append("<");
		int cnt = 0;
		//idx는 K-1만큼 이동하고 초과하는 경우 리스트 사이즈의 % 연산 실시
		K--;
		int idx = 0;
		while(cnt != N) {
			idx = (idx+K)%list.size();
			sb.append(list.remove(idx) + ", ");
			cnt++;
		}
	
		System.out.println(sb.substring(0, sb.length()-2) + ">");
	}
}
