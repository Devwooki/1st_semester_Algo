package com.ssafy.offline06;
import java.io.*;
import java.util.*;

public class SWEA1228 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		for(int tc = 1 ; tc <= 10 ; ++tc) {
			int N =Integer.parseInt(br.readLine());
			LinkedList<String> origin = new LinkedList<>();
			st = new StringTokenizer(br.readLine());
			for(int i = 0 ; i < N ; ++i) {
				origin.add(st.nextToken());
			}
			
			int cmdNum = Integer.parseInt(br.readLine());
			String[] cmd = new String[cmdNum];
			st = new StringTokenizer(br.readLine());
			for(int i = 0 ; i < cmdNum ; ++i) {
				st.nextToken();
				
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				for(int j = 0 ; j < y ; ++j) {
					origin.add(x+j, st.nextToken());
				}
			}
			
			sb.append("#" + tc + " " );
			for(int i = 0 ; i < 10 ; ++i) {
				sb.append(origin.get(i) +" ");
			}
			sb.append("\n");
			
		}
		System.out.println(sb);
	}
}