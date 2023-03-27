package com.ssafy.offline01;
/**
 * 재귀가 멈추는 조건 = 기저조건
 * 자기 자신을 호출하는 함수
 * 
 * 재귀와 반복 서로 변환이 가능함.(정해진 크기를 가지고 있을 때)
 * -> 반복으로 안될 때 보통 재귀르 호출함
 * 
 * 재귀함수는 콜스택이 계속 쌓임 -> 범위 초과시 프로그램 종료됨
 */
public class Test01 {
	public static void main(String[] args) throws InterruptedException {
		System.out.println("main");
		Thread.sleep(100);
		main(null);
	}
}
