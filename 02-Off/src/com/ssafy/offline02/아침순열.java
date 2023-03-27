package com.ssafy.offline02;
import java.util.Arrays;
import java.util.Scanner;

//package com.ssafy.offline02;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.Arrays;
//
//// 아침에 순열 만드는 코드 다시 치기
//// 점심에 순열 만드는 코드 다시 치기
//// 퇴실전에 순열 다시 치기
//// 순열은 그냥 외워서 나오도록 만들어야함
//
///**
// * 순열 필수 요소
// * N과 R
// * boolean[] isSelected : 뽑힌건지 체크 -> N만큼
// * int[] numbers : 결과값을 저장할
// * int[] input : 입력값 저장할 배열
// */
//
//public class 아침순열 {
//	public static int N, R;
//	public static int[] numbers, inputs;
//	public static boolean[] isSelected;
//	public static int howMany = 0;
//	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		
//		N = Integer.parseInt(br.readLine());
//		R = Integer.parseInt(br.readLine());
//		
//		isSelected = new boolean[N]; //선택할 가지수가 N개
//		numbers = new int[R]; // 뽑아야 하는 수 만큼
//		inputs = new int[N];  // 입력 데이터
//		
//		for(int i = 0 ; i < N ; ++i) {
//			inputs[i] = Integer.parseInt(br.readLine());
//		}
//		
//		perm(0);
//		System.out.println(howMany);
//	}
//	
//	private static void perm(int cnt) {
//		if (cnt == R) {
//			System.out.println(Arrays.toString(numbers));
//			howMany++;
//			return;
//		}
//		
//		for(int i = 0 ; i < N ; ++i) {
//			if(isSelected[i]) continue; //사용중이라면 continue
//			
//			isSelected[i] = true;	//사용 안된거면 사용했다고 체크
//			numbers[cnt] = inputs[i]; //결과에 첨부
//			
//			perm(cnt+1);	//재귀 실행
//			isSelected[i] = false;	//r개 만큼 체크가 끝났으니 사용 안한거로 반환
//			//상태 트리 그려보기
//		}
//	}
//}






























public class 아침순열{
	public static int N, R;
	public static boolean[] isSelected;
	public static int[] inputs, numbers;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		R = sc.nextInt();
		
		inputs = new int[N];
		numbers = new int[R]; //결과는 r개니까
		isSelected = new boolean[N];
		
		for(int i = 0 ; i < N ; ++i) {
			inputs[i] = sc.nextInt();
		}
		
		perm(0);
	}
	
	public static void perm(int cnt) {
		if(cnt == R) {
			System.out.println(Arrays.toString(numbers)); //결과값 출력
			return;
		}
		
		for(int i = 0 ; i < N ; ++i) {
			if(isSelected[i]) continue; //현재 값이 선택되면 패스
			
			isSelected[i] = true;
			numbers[cnt]= inputs[i];
			
			perm(cnt+1);
			isSelected[i] = false;
		}
		
	}
}