package com.ssafy.offline03;

import java.io.*;
import java.util.Arrays;

import javax.sound.midi.Soundbank;

public class 아침순열조합 {
	static int N, R,  totalCnt = 0;
	static int[] inputs, numbers;
	static boolean[] isSelected;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		R = Integer.parseInt(br.readLine());
		
		inputs = new int[N];
		isSelected = new boolean[N];
		numbers = new int[R];
		
		for(int i = 0 ; i < N ; ++i) {
			inputs[i] = i+1;
		}
		
		print();
		int select = Integer.parseInt(br.readLine());
		menu(select);
		
	}

	private static void menu(int select) {
		switch(select) {
		case 1:nPIr(0); System.out.println(totalCnt); break;
		case 2:nPr(0); System.out.println(totalCnt); break;
		case 3:nCr(0,0); System.out.println(totalCnt); break;
		case 4:nHr(0,0); System.out.println(totalCnt); break;
		}
	}
	private static void nPr(int cnt) {
		if(cnt == R) {
			totalCnt++;
			System.out.println(Arrays.toString(numbers));
			return;
		}
		
		for(int i = 0 ; i < N ; ++i) {
			if(isSelected[i]) continue;
			
			isSelected[i] = true;
			numbers[cnt] = inputs[i];
			
			nPr(cnt+1);
			isSelected[i] = false;
		}
		
	}

	private static void nPIr(int cnt) {
		if(cnt == R) {
			totalCnt++;
			System.out.println(Arrays.toString(numbers));
			return;
		}
		
		for(int i = 0 ; i < N ; ++i) {
			if(isSelected[i]) continue;
		
			numbers[cnt] = inputs[i];
			
			nPIr(cnt+1);
		}
		
	}
	private static void nCr(int cnt, int start) {
		if(cnt == R) {
			totalCnt++;
			System.out.println(Arrays.toString(numbers));
			return;
		}
		
		for(int i = start ; i < N ; ++i) {		
			numbers[cnt] = inputs[i];
			nCr(cnt+1, start+1);
		}
		
	}
	private static void nHr(int cnt, int start) {
		if(cnt == R) {
			totalCnt++;
			System.out.println(Arrays.toString(numbers));
			return;
		}
		
		for(int i = start ; i < N ; ++i) {		
			numbers[cnt] = inputs[i];
			nHr(cnt+1, start);
		}
		
	}

	private static void print() {
		
		System.out.println("1번. 중복순열\r\n" + 
				"2번. 일반순열\r\n" + 
				"3번. 일반조합\r\n" + 
				"4번. 중복조합 ");
	}
}
