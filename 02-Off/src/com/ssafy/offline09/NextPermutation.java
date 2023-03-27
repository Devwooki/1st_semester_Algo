package com.ssafy.offline09;
import java.io.*;
import java.util.*;

public class NextPermutation {
	//static int[] inputs, numbers;
	//static int N,R;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine()); // nPn만 가능함
		//R = Integer.parseInt(br.readLine());
		
		int[] inputs = new int[N];

	
		
		for(int i = 0 ; i < N ; ++i) {
			inputs[i] = i+1;
		}
		
		//전처리
		Arrays.sort(inputs);
		
		do {
			System.out.println(Arrays.toString(inputs));
			
		}while(np(inputs));	
		
		//조합을 np로 구성
		int[] flag = new int[] {0,0,1,1,1};
		int[] test = new int[] {1,2,3,4,5};
		System.out.println("조합 np로");
		do {
			//np로 실행될 때 마다 flag가 나타남
			for(int i = 0 ; i < N ; ++i) {
				//flag가 0이 아니면 출력
				if(flag[i]==1) System.out.print(test[i] + " ");
			}
			System.out.println( );
		}while(np(flag));	
		
		
	}
	static boolean np(int[] inputs) {
		int n = inputs.length;
		
		//step1. 뒤부터 꼭대기 찾기( 꼭대기 앞이 교환할 위치니까)
		int i = n - 1;
		while(i > 0 && inputs[i-1] >= inputs[i]) --i; //뺴다가 i가 0을 넘어가면? 안댐
		if(i==0) return false;//가장 큰 순열의 상태이기 때문에 종료
		
		//step2. 꼭대기 바로 앞(i-1)자리에 교환할 값을 뒤쪽부터 찾는다.
		int j = n - 1;
		while(inputs[i-1] >= inputs[j]) --j; //j는 없으면꼭대기 까지 가기 때문에 조건이 필요없음
		
		//step3. 꼭대기 바로앞 (i-1)자리와 그 자리값보다 한 단계 큰 자리(j)숫자와 교환
		swap(inputs, i-1, j);
		
		//step4. 꼭대기 부터 맨 뒤까지 오름차순으로 정렬
		int k = n-1;
		while( i < k) {
			swap(inputs, i++, k--); //i, k swap 후 i+1 과 k-1을 또 스왑해야해서 후치연산
		}
		// 1 2 3 6 5 4, 꼭대기 6-> 바꾸면 1 2 4 6 5 3-> 
		//이게 가장 작은가?x 6부터 3까지 스왑을 통해 1 2 4 3 5 6이 제일 작음 그래서 스왑  
		
		return true;
	}
	
	static void swap(int[] inputs, int i , int j) {
		int temp = inputs[i];
		inputs[i] = inputs[j];
		inputs[j] = temp;
		
	}
}
