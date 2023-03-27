package com.ssafy.offline01;

public class Test04 {
	public static void main(String[] args) {
		int[] arr = new int[] {1,3,5,7,9};
		System.out.println(solve(arr, arr.length));

	}

	private static int solve(int[] arr, int length) {
		if(length == 1) return arr[0];
		return solve(arr, length-1) + arr[length-1]; 
	}
}
