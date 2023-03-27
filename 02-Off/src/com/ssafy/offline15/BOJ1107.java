package com.ssafy.offline15;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class BOJ1107 {
	static int target;
	static boolean[] isBreak = new boolean[10];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		target = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		if (M != 0) {
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; ++i)
				isBreak[Integer.parseInt(st.nextToken())] = true;
		}
		
		int result = Math.abs(target-100);
		for(int i = 0 ; i <= 999999; ++i) {
			String pressButton = String.valueOf(i);
			int len = pressButton.length();
			
			boolean stop = false;
			for(int j = 0 ; j  < len ; ++j) {
				if(isBreak[pressButton.charAt(j) - '0']) {
					stop = true;
					break;
				}
			}
			if(stop) continue;
			
			int min = Math.abs(target - i) + len;
			result =  Math.min(min, result);
		}
		System.out.println(result);
		
	}

//	// 누를 수 있는 버튼 중 가장 가까운 것 일의 자리부터 접근한다
//	static int getTemp() {
//		int number = 0;
//		for (int i = 0; i < targetArr.length; ++i) {
//			int min = 1 << 30;
//			int closeButton = -1;
//			int compare = getCompareNum(i);
//			//System.out.println(i + ", " + compare);
//			for (int j = 9; j >= 0; --j) {
//				if (isBreak[j])
//					continue;
//				
//				if (min > Math.abs(compare - (number + j * (int)Math.pow(10, i)))) {
//					min = Math.abs(compare - (number + j * (int)Math.pow(10, i)));
//					closeButton = j;
//				}
//
//			}
//			number += closeButton * Math.pow(10, i);
//		}
//		String numberLen = String.valueOf(number);
//		//System.out.println(number);
//		return Math.abs(target-number) + numberLen.length();
//	}

//	static int getCompareNum(int range) {
//		
//		//5457
//		//range 0 -> 5457 % 5450 -> 7
//		//ragee 1 -> 5457 % 5400 -> 57
//		//range 2 -> 5457 % 5000 -> 457
//		//range 3 -> 5457		 -> 5457
//		if(range == targetArr.length -1 ) return target;
//		else return target % ((target / (int) Math.pow(10, range + 1)) * (int)Math.pow(10, range + 1));
//
//	}
//	static int getTemp() {
//		int[] numbers = new int[targetArr.length];
//		for (int i = 0; i < targetArr.length; ++i) {
//			int min = 1 << 30;
//			int closeButton = -1;
//			int comp = makeNum(numbers, i);
//			for (int j = 9; j >= 0; --j) {
//				if (isBreak[j]) continue;
//
//				int origin = makeNum(targetArr, i);
//
//				if (min > Math.abs(origin - (comp+j))) {
//					min = Math.abs(origin - (comp+j));
//					closeButton = j;
//				}
//			}
//			numbers[i] = closeButton;
//		}
//		String numbersLen = String.valueOf(makeNum(numbers, numbers.length-1));
//		int channel = Math.abs(makeNum(numbers, numbers.length-1) - target)
//						+ numbersLen.length();
//		return channel;
//
//	}
//	static int makeNum(int[] arr, int range) {
//		int num = 0;
//		int cnt = 0;
//		while(range >= 0) {
//			num += arr[cnt++] * Math.pow(10, range--);
//		}
//		return num;
//	}
}
