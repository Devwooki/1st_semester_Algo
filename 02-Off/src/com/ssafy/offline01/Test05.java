package com.ssafy.offline01;

public class Test05 {
	public static void main(String[] args) {
		int[] arr = new int[] {1,3,5,7,9};
		System.out.println(search(arr, 0, 3));
		//3을 -> 1의 인덱스 위치를 주면 됨
		//없으면 -> -1 반환

	}

	private static int search(int[] arr, int now, int find) {
		if(now == arr.length -1) return -1;
		
		if(arr[now] == find) return now;
		else {
			return search(arr, now+1, find);
		} 
	}
}
