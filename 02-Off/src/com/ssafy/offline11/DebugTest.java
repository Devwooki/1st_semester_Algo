package com.ssafy.offline11;
//실무에서 system사용하는건 지양해야함
/* 실무에선 보이지 않음.
 * 실무 프로그램은 콘솔에서 작업하는게 서버단 콘솔에 출력됨
 * 근데 서버느 동시에 몇 명이 접속,,?
 * 
 * 로그 파일에 떨어지는 데이터의양의 상상이상임 확인이 불가능(초당 몇 천 몇 만개의 출력이 나옴)
 * 그래서 파일로 저장시킴
 * 디버그를 통해 해결할 수 있음
 * 
 * f11 -> 디버그모드
 * ctrl + f11 -> 컴파일
 * 
 * 디버그창으로 전환된 뒤 
 * variables
 * expression : 내가 원하는 데이터들을 조합해서 볼 수 있음
 *  	-> Name에 map[i][j] + ", " + result ->  map[i][j]의 값, result의 값 형식으로 출력됨
 *  
 *  2차원 배열 같은 경우 한 줄에 모두 출력됨 2차원 배열형태로 보고싶을 때
 *  map옆에 삼각형 클릭 -> row들이 나열됨.
 *  -> shift눌러서 특정 구간 데이터를 세로로 나열할 수 있게됨 
 *  
 *  static 변수를 보고싶을 때 Variables창 우측 상단 삼각형 클릭 -> java -> `show static variables`클릭
 *  
 *---------------------------------------  
 *  drop to frame -> 현재 실행중인 디버그 처음으로 돌아감
 *  f5 : 한 줄 씩 실행하며 메소드 발견시 메소드 안으로
 *  f6 : 한 줄 씩 실행하며 메소드 발견해도 다음줄로
 *  f7 : 한 줄 씩 실행하다 메소드 안에 들어갔을 떄 빠져나올수 있음
 *  f8 : 다음 breakPoint로 넘어감
 *  -------------------------------------
 *  시뮬레이션 문제 해결 도중 일정 구간을 넘고 싶을 때 breakPoint 우클릭 -> properties클릭
 *  conditional에서 (조건식) 작성 -> 아이콘 모양이 ?로 바뀌면서 프로퍼티가 적용됨
 *  conditional에서 부터 시작(일정구간 스킵)
 *  
 *  hitconunt : breakPoint에 hitcount만큼 도달 했을 때 결과부터 시작
 */
public class DebugTest {
	static int sum = 0;
	static int[][] map = {
			{1,2,3,4},
			{11,12,13,14},
			{21,22,23,24},
			{31,32,33,34}
	};
	
	public static void main(String[] args) {
		for(int i = 0 ; i < map.length ; ++i) {
			for(int j = 0 ; j < map[i].length ; ++j) {
				sum += map[i][j];
			}
		}
		System.out.println(sum);
		
		debug();
		call2();
	}
	
	private static void call2() {
		int a = 100;
		a += 10;
		a += 20;
		System.out.println(a);
		
	}

	private static void debug() {
		call(10);
		System.out.println(sum);
	}

	private static void call(int n) {
		if(n == 0) return;
		sum += n;
		call(n - 1);
	}
}
