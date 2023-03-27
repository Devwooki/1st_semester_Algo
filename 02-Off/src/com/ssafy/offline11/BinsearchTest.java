package com.ssafy.offline11;

import java.io.*;
import java.util.*;

public class BinsearchTest {
	static int[] data = {7,1,2,10,3,78,33,9,15,19};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		Arrays.sort(data);
		
		System.out.println(Arrays.toString(data));
		//방식 1 반복
		System.out.println("=======반복 이진탐색=====");
		System.out.println(call1(10)+1);
		
		//방식 2 반복
		System.out.println("=======재귀 이진탐색=====");
		System.out.println(call2(10, 0, data.length-1)+1 );
	}
	static int call1(int find) {
		int lo = 0;
		int hi = data.length - 1;
		int mid;
		while(lo <= hi) {
			mid = lo + (hi-lo)/2;
			
			if(data[mid] == find) return mid;
			if(data[mid] < find)
				lo = mid + 1;
			else if(data[mid] > find)
				hi = mid - 1;
		}
		
		return -1;
	}
	
	static int call2(int find, int start, int end) {
		if(start > end) return -1; 

		int mid = start + (end - start) / 2;
		if (data[mid] == find) 	return mid;
		if (data[mid] < find)	return call2(find, mid + 1, end);
		//else if (data[mid] > find)	return call2(find, start, mid - 1);
		else 					return call2(find, start, mid - 1);
	}
	
	static int call3(int find) {
		return Arrays.binarySearch(data, find);
		//못찾을 때 -인덱스 값이 나옴
		//들어갈 idx에 +1 하고 음수를 붙이는 특징
		//음수의 특징 만약, 값을 삽입할 시 들어갈 위치를 제공해줌
		// -의 의미? 0때문에,, 찾았다의 0인지, 인덱스의 0인지 알 수 없기 때문에
		//
	}
}
