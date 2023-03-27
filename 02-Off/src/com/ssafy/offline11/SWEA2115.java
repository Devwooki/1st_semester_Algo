package com.ssafy.offline11;
import java.io.*;
import java.util.*;

/*
 * 벌꿀 수집은 가로로 연속해서
 */
/**
 * @param N : 벌통 크기
 * @param M : 선택할 수 있는 벌통의 수
 * @param C : 채취할 수 있는 꿀의 최대양
 */
public class SWEA2115 {
	static int N, M, C;
	static int[][] map;
	static boolean[][] visited;
	static int[][] collectHoney;
	static int result;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		for(int tc = 1 ; tc <= TC ; ++tc) {
			result = Integer.MIN_VALUE;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			

			map = new int[N][N];
			visited = new boolean[N][N];
			collectHoney = new int[2][M];
			
			for(int i = 0 ; i < N ; ++i) {
				map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			}
			
			selectArea();
			sb.append("#" + tc + " " + result + "\n");
		}
		System.out.println(sb);
	}
	//M == N/2 -> 한 줄에 2개
	//M > N/2 -> 한줄에 하나씩 + 슬라이딩 윈도우
	//M == N 한 줄에 하나
	
	//벌통 수집 - 조합
	static void selectArea() {
		
		for(int i = 0 ; i < N ; ++i) {
			for(int j = 0 ; j <= N-M ; ++j) {
				//꿀벌 1의 수집할 꿀집 리스트 작성
				for(int k = 0 ; k < M ; ++k) {
					collectHoney[0][k] = map[i][j+k];
				}
				
				//꿀벌 1의 부분집합-> C이하이면서 최대 구하기
				int max1 = Integer.MIN_VALUE;
				for(int k = 0 ; k <( 1 << M ) ; ++k) {
					int honeyBowl = 0;
					int profit1 = 0;
					for(int l = 0 ; l < M  ; ++l) {
						if( (k &( 1 << l)) == 0) continue;
						
						honeyBowl += collectHoney[0][l];
						profit1 += collectHoney[0][l] * collectHoney[0][l];
					}
					//꿀통 용량이 C보다 작거나 같을 때 최대값을 지속적으로 갱신
					if(honeyBowl <= C ) max1 = Math.max(profit1, max1);
				}
				
				
				//꿀벌 2의 수집할 꿀집 리스트 
				// M == N/2 -> 한 줄에 2개
				// M > N/2 -> 한줄에 하나씩 + 슬라이딩 윈도우
				// M == N 한 줄에 하나
				for(int k = i ; k < N ; ++k) {//꿀벌1과 같은행에서 시작
					int start;
					//시작 하는 열이 같으면 꿀벌 1이 수집한 영역 다음부터 수집한다
					if(i == k) {
//						//근데 M이 N/2보다 작으면 이어서 할 수 있지만
//						if( M <= N/2 ) start = j + M;
//						//그렇지 않으면 다음  행 부터 꿀벌2는 탐색해야한다.
//						else continue;
						start = j + M;
					}
					//열이 다르면 다음 0부터 시작
					else start = 0;
					for(int l = start ; l < N; ++l) {
						for(int m = 0 ; m < M ; ++m) {
							collectHoney[1][m] = map[i][l+m];
						}
						
						//꿀벌 2의 부분집합 -> 꿀벌2가 수집할 수 있는 최대값
						int max2 = Integer.MIN_VALUE;
						for(int m = 0 ; m < ( 1 << M ) ; ++m) {
							int honeyBowl2 = 0;
							int profit2 = 0;
							for(int n = 0 ; n < M ; ++n) {
								if( (m &( 1 << n)) == 0) continue;
								
								honeyBowl2 += collectHoney[1][n];
								profit2 += collectHoney[1][n] * collectHoney[1][n];
							}
							//꿀통 용량이 C보다 작거나 같을 때 최대값을 지속적으로 갱신
							if(honeyBowl2 <= C ) max2 = Math.max(profit2, max2);
						}
						result = Math.max(result, max1 + max2);
					}
											
				}
				
			}
		}
	}
	
	//점수 계산 - 부분집합
}
