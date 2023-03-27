package com.ssafy.offline12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA2115_PF {
    static int[][] honey, profit;
    static int n, m, c, answer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        // Test Case
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken()); // 벌 통의 크기
            m = Integer.parseInt(st.nextToken()); // 벌 통의 개수
            c = Integer.parseInt(st.nextToken()); // 채취 한도
            answer = 0;                           // 매 테스트 케이스의 정답
            honey = new int[n][n];
            profit = new int[n][n];
            for (int i = 0; i < n; i++)
                honey[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            setProfit();
            setPosition();

            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }

    static void setProfit() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= n - m; j++) {
                setMaxProfit(i, j, 0, 0, 0);
            }
        }
    }

    static void setMaxProfit(int i, int j, int count, int cSum, int pSum) {
        if (cSum > c) return; // 한도 넘어갔을 때
        if (count == m) {
        	profit[i][j - m] = Math.max(profit[i][j - m], pSum);
        	return;
        }
        
        setMaxProfit(i, j + 1, count + 1, cSum, pSum);  // 비 선택
        setMaxProfit(i, j + 1, count + 1, cSum + honey[i][j], pSum + honey[i][j] * honey[i][j]);  // 선택
    }

    static void setPosition() {
    	//일꾼 1의 위치map[i][j]
        for (int i = 0; i < n; i++) {
        	for (int j = 0; j <= n - m; j++) {         
            	
            	//일꾼2가 같은 행일 때
            	for (int c = j + m ; c <= n - m; c++) {
            		answer = Math.max(answer, profit[i][j] + profit[i][c]);
            	}               
            	//일꾼2가 다른 행일 때
            	for (int r = i + 1; r < n; r++) {         
            		for (int c = 0; c < n; c++) {
            			answer = Math.max(answer, profit[i][j] + profit[r][c]);
            		}
            	}
            }
        }
    }
}
