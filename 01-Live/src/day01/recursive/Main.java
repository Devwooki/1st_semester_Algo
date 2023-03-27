package day01.recursive;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;

public class Main {
	public static boolean[] isSelected;
	public static int[] numbers ;
	public static int[] inputs;
	public static int r, N, numOfCase = 0;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		r = Integer.parseInt(br.readLine());
		
		inputs = new int[N];
		for(int i = 0 ; i < N ;++i) {
			inputs[i] = i + 1;
		}
		numbers = new int[r];
		isSelected = new boolean[N];
		
		print();
		
		int mode = Integer.parseInt(br.readLine());
		switch(mode) {
		case 1 : nPIr(0); System.out.println("경우의 수 : " + numOfCase); break;
		case 2 : nPr(0); System.out.println("경우의 수 : " + numOfCase);  break;
		case 3 : nCr(0,0); System.out.println("경우의 수 : " + numOfCase); break;
		case 4 : nHr(0,0); System.out.println("경우의 수 : " + numOfCase);break;
		}
		

	}
	private static void nPIr(int cnt) {
		if(cnt == r) {
			numOfCase++;
			System.out.println(Arrays.toString(numbers));
			return;
		}
		
		for(int i = 0 ; i < N ; ++i) {
			if(isSelected[i]) continue;
			
			numbers[cnt] = inputs[i];
			nPIr(cnt+1);
		}
		
	}
	private static void nPr(int cnt) {
		if(cnt == r) {
			numOfCase++;
			System.out.println(Arrays.toString(numbers));
			return;
		}
		
		for(int i = 0 ; i < N ; ++i) {
			if(isSelected[i]) continue;
			
			isSelected[i] = true;
			numbers[cnt] = inputs[i];
			nPIr(cnt+1);
			isSelected[i] = false;
		}
		
	}
	/**
	 * 
	 * @param cnt : 현재까지 뽑은 조합 원소 개수
	 * @param start : 시도할 원소의 시작 인덱스
	 */
	private static void nCr(int cnt, int start) {
		if(cnt == r ){
			numOfCase++;
			System.out.println(Arrays.toString(numbers));
			return;
		}
		
		
		for(int i = start ; i < N ; ++i) {
			numbers[cnt] = inputs[i];
			nCr(cnt+1, i+1);
		}
	}
	
	private static void nHr(int cnt, int start) {
		if(cnt == r ){
			numOfCase++;
			System.out.println(Arrays.toString(numbers));
			return;
		}
		
		
		for(int i = start ; i < N ; ++i) {
			numbers[cnt] = inputs[i];
			nHr(cnt+1, i);
		}
		
	}
	
	public static void print() {
		System.out.println("1번. 중복순열");
		System.out.println("2번. 일반순열");
		System.out.println("3번. 일반조합");
		System.out.println("4번. 중복조합 ");
		
	}

}
