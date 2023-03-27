package day11.dvidenconquer;

import java.util.Scanner;

/**
 * @author THKim
 */
public class EX_공간만들기 {
	static int white = 0;
	static int green = 0;
	static int[][] spaces;

	// 주어진 영역의 공간의 셀 체크하여 모두 초록색이나 하얀색으로 이루어져있는지 확인 후
	// 4개로 쪼개기.
	// 하얀색 0 , 초록색 1
	static void cut(int x, int y, int n) {
		// 체크해서 맞으면 white나 green 갯수 증가 일정 영역이면
		// 체크해서 틀리면 분할
		if (check(x, y, n)) {
			if(spaces[y][x] == 1) green++;
			else white++;
		} else {
			for (int i = 0; i < 2; ++i) {
				for (int j = 0; j < 2; ++j) {
					cut(x + j * n/2 , y + i * n/2, n/2);
				}
			}
		}
	}

	static boolean check(int x, int y, int n) {
		int base = spaces[y][x];
		for(int i = y ; i < y+n ; ++i) {
			for(int j = x ; j < x+n  ; ++j) {
				if(base != spaces[i][j]) return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		spaces = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				spaces[i][j] = sc.nextInt();
			}
		}

		cut(0, 0, n);

		System.out.println(white);
		System.out.println(green);
		sc.close();
	}
}
