package com.ssafy.offline07;

import java.io.*;
import java.util.*;

public class PickmePickme {
	static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		System.out.println("몇 명을 뽑으시겠습니까.");
		
		stuSelected = new boolean[studentNum];
		gameSelected = new boolean[gameNum];
		
		N = Integer.parseInt(br.readLine());		
		Random r = new Random();
		
		for (int i = 0; i < N; i++) {
            int student = r.nextInt(studentNum)+1;
            while (stuSelected[student]){
                student = r.nextInt(studentNum)+1;
            }
            int subject = r.nextInt(gameNum);
            while (gameSelected[subject]){
                subject = r.nextInt(gameNum);
            }
            stuSelected[student] = true;
            gameSelected[subject] = true;
            System.out.printf("%s이(가) %s을(를) 풀겠습니다.%n", sixban[student], game[subject]);
        }
		
	}
		
		
	static boolean[] stuSelected;
	static boolean[] gameSelected;
	static String[] sixban = new String[]{
			"강동표",
			"강현곤",
			"김동민",
			"김은서",
			"김하영",
			"박민희",
			"송병훈",
			"송찬환",
			"송혁준",
			"신창학",
			"이강호",
			"이다영",
			"이대경",
			"이지영",
			"이현욱",
			"인영교",
			"전영준",
			"정민",
			"정수정",
			"정현모",
			"정형준",
			"최하영",
			"한민서",
			"허다은",
			"홍유빈",
			"황윤선"
		};
		static String[] game = new String[]{
			"순열",
			"조합",
			"부분비트",
			"부분재귀",
			"BFS"		
		};
	static int studentNum = sixban.length;
	static int gameNum = game.length;
	
}
