package com.ssafy.offline13;
import java.io.*;
import java.util.*;


public class SWEA1247 {
	static int[][] map;
	static int[] company, home;
	static int[][] customers;
	static int N, shortest;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int TC = Integer.parseInt(br.readLine());
		for(int tc = 1 ; tc <= TC ; ++tc) {
			shortest = Integer.MAX_VALUE;
			//getData();	
			N = Integer.parseInt(br.readLine());
			company =  new int[2];
			home = new int[2];
			customers = new int[N][2];
			
			st = new StringTokenizer(br.readLine());
			company[0] = Integer.parseInt(st.nextToken());
			company[1] = Integer.parseInt(st.nextToken());
			home[0] = Integer.parseInt(st.nextToken());
			home[1] = Integer.parseInt(st.nextToken());
			
			for(int i = 0 ; i < N ; ++i) {
				customers[i][0]  = Integer.parseInt(st.nextToken());
				customers[i][1]  = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(customers, new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					return o1[0] == o1[1] ? o1[1]-o2[1] : o1[0]-o2[0];
				}
			});
			
			getDistance();
			
			sb.append("#" + tc + " " + shortest + "\n");
		}
		System.out.println(sb);
	}
	
	static void getData() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		company =  new int[2];
		home = new int[2];
		customers = new int[N][2];
		
		st = new StringTokenizer(br.readLine());
		company[0] = Integer.parseInt(st.nextToken());
		company[1] = Integer.parseInt(st.nextToken());
		home[0] = Integer.parseInt(st.nextToken());
		home[1] = Integer.parseInt(st.nextToken());
		
		for(int i = 0 ; i < N ; ++i) {
			customers[i][0]  = Integer.parseInt(st.nextToken());
			customers[i][1]  = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(customers, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] == o1[1] ? o1[1]-o2[1] : o1[0]-o2[0];
			}
		});
	}
	
	static void getDistance() {
		do {
			int sum = 0;
			int startX = company[0];
			int startY = company[1];
			for(int i = 0 ; i < N ; ++i) {
				sum += Math.abs(customers[i][0]-startX)
						+ Math.abs(customers[i][1]-startY);
				startX = customers[i][0];
				startY = customers[i][1];
			}
			sum += Math.abs(home[0]-startX)
					+ Math.abs(home[1]-startY);
			shortest = Math.min(shortest, sum);
		}while(nextPerm());
	}
	
	static boolean nextPerm() {
		int i = N-1;
		while(i > 0 && customers[i-1][0] >= customers[i][0] ) --i;
		if(i == 0) return false;
		
		int j = N - 1;
		while(customers[i-1][0] >= customers[j][0]) --j;
		
		swap(i-1, j);
		
		int k = N - 1;
		while( i < k) swap(i++, k--);
		
		return true;
	}
	static void swap(int front, int back) {
		int[] temp = customers[front];
		customers[front] = customers[back];
		customers[back] = temp;
	}
}
