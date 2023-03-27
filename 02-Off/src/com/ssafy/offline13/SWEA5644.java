package com.ssafy.offline13;
import java.awt.*;
import java.io.*;
import java.util.*;


//제자리	상	하	좌	우
//	0	1	2	3	4
//한 영역에 n명위치하면 성능 반토막
/*
 * @param M : 총 이동시간
 * @param A : BC의 개수
 * @param move :사용자ab의 이동방향
 * @param bc : Battery Charger정보 ->  x, y, 범위, 성능 순
 */
public class SWEA5644 {
	static int M, A, result;
	static int[][] move, bc;
	static int[][] map;
	static Point user1, user2;
	static int[][] dir = { {0,0}, {-1,0}, {0,1}, {1,0},{0,-1}	};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		for(int tc = 1 ; tc <= TC ; ++tc) {
			st = new StringTokenizer(br.readLine());
			
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			map = new int[11][11];
			
			move = new int[2][M];
			for(int i = 0 ; i < 2 ; ++i) {
				move[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			}
			
			bc = new int[A][4];
			//순서대로 x,y,c-충전범위,p-성능
			for(int i = 0 ; i < A ; ++i) {
				bc[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			}
			user1 = new Point(1,1);
			user2 = new Point(10,10);
			result = 0;
			solve();
			sb.append("#" + tc + " " + result+ "\n");
		}
		System.out.println(sb);
	}
	
	static void solve(){
		for(int time = 0 ; time <= M ; ++time){//시간대별 충전소에 접근해 가장 큰 값을 얻는다.
			//사용자 위치를 이동한다.
			user1.x += dir[move[0][time]][0];
			user1.y += dir[move[0][time]][1];
			user2.x += dir[move[1][time]][0];
			user2.y += dir[move[1][time]][1];
			plusCharge();
		}
	}
	static void plusCharge(){ //중복순열 고름
		int max = 0;
		for(int a = 0 ; a < A ; ++a){//user1선택
			for (int b = 0; b < A; ++b) {
				int u1Sum = getBCPerformance(a ,move[0]);
				int u2Sum = getBCPerformance(b, move[1]);

				//max = 유저1과 유저2가 서로 다른 충전기를 고를 경우 합, 아닐경우 최댓값구하고 그 중 큰값을 또 max 비교
				max = Math.max(max, (a != b) ? u1Sum + u2Sum : Math.max(u1Sum, u2Sum));
			}
		}
		result += max;
	}
	//bc의 원소 4가지 : 순서대로 x,y,c-충전범위,p-성능
	//user의 원소 2가지 : 순서대로 x y
	static int getBCPerformance(int idx, int[] user){
		//충전기부터 유저까지의 거리가  충전범위 안쪽에 있으면 충전기 퍼포먼스 반환, 아니면 0
		return Math.abs(bc[idx][1] - user[1]) + Math.abs(bc[idx][0] - user[0]) <= bc[idx][2]
				? bc[idx][3] : 0;
	}
}
