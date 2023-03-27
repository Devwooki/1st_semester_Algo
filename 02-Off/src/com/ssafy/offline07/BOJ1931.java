package com.ssafy.offline07;
import java.io.*;
import java.util.*;


public class BOJ1931 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		int[][] time = new int[N][2];
		
		for(int i = 0 ; i < N ; ++i) {
			st = new StringTokenizer(br.readLine().concat(" "));
			time[i][0] = Integer.parseInt(st.nextToken());
			time[i][1] = Integer.parseInt(st.nextToken());
			
		}
		//서로 겹치지 않는 활동에 대해 종료시간이 빠름-> 더 많은 활동 가능
		// 그래서 종료 시간 기준으로 정렬한다.
		// 이전 종료 시간과 시작 시간이 겹치면 건너뜀
		
		//Arrays.sort(time, (o1,o2) -> o1[1]-o2[1]);
		Arrays.sort(time, new Comparator<int[]>(){

			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[1] == o2[1])
					return o1[0] - o2[0]; 
				return o1[1] - o2[1];
			}});
		
		int cnt = 0 ;
		int nowStart = -1;
		int nowEnd = -1;
		//회의가 겹치지 않으면서 회의실을 사용할 수 있는 최대 개수 == 연속적
		for(int i = 0 ; i < N ; ++i) {
			if(nowEnd <= time[i][0] ) {
				nowStart = time[i][0];
				nowEnd = time[i][1];
				cnt++;
			}
		}
		System.out.println(cnt);
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       

		
	}
}
