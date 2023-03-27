package com.ssafy.offline15;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class SWEA7333 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int tc = sc.nextInt();
		for (int t = 1; t <= tc; t++) {
            int n = sc.nextInt();
            PriorityQueue<Integer> big = new PriorityQueue<>(Collections.reverseOrder());
            PriorityQueue<Integer> small = new PriorityQueue<>();
            for (int i = 0; i < n; i++) {
                int nums = sc.nextInt();
                big.add(nums);
                small.add(nums);
            }

			
			//인덱스로 접근해서 값을 구할 것
			int end = big.poll();
			int start = small.poll();
			int result = 0;
			while(end - start > 0) {
				int temp = 49/end + 1;
				if(end - start + 1 < temp) break;
				else {
					if(temp == 1) {
						result++;
						end = big.poll();
					}else {
						result++;
						small.remove(end);
						end = big.poll() ;
						for(int i = 0 ; i < temp-1 ; ++i) {
							big.remove(start);
							start = small.poll();
						}
					}
				}		
			}
			//ㅇ
			if(start == end) {
				if(end >= 50) result++;
			}
			
			sb.append("#" + t + " " + result + "\n");
		}
		System.out.println(sb);
	}
}