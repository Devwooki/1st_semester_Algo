package com.ssafy.offline17;
import java.io.*;
import java.util.*;

public class BOJ16236 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; ++tc) {
			st = new StringTokenizer(br.readLine());
			
			sb.append("#" + tc + " " + "\n");
		}
		System.out.println(sb);
	}
}
