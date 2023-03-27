package com.ssafy.offline06;
import java.io.*;
import java.util.*;

public class BOJ27446 {
	 public static void main(String[] args) throws IOException{
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        StringTokenizer st = new StringTokenizer(br.readLine());
	        StringBuilder sb = new StringBuilder();

	        int N = Integer.parseInt(st.nextToken());
	        int M = Integer.parseInt(st.nextToken());

	        boolean[] journalPages = new boolean[N+1];

	        st = new StringTokenizer(br.readLine(), " ");
	        int cnt = 0;
	        for(int i = 1 ; i <= M ; ++i){
	            int temp = Integer.parseInt(st.nextToken());
	            if(journalPages[temp]) cnt++;
	            journalPages[temp] = true;
	           
	        }
	        if(cnt == M){
	            System.out.println(0);
	            return;
	        } 

	        //페이지가 XOOX면 2개까지는 O 2개 까지는 연속해서 출력는게 더 잉크 아낄 수 있음
	        /*
	        T가 연속해서 3번 등장 -> cntF로 계산
	        oxoxoxoxoxo
	         */
	        //fasle면 출력, true 개수
	        int result = 0 ;
	        int cntT = 0;
	        int startPoint = -1;
	        int endPoint = 1;
	        boolean flag = false;
	        for(int i = 1 ; i <= N ; ++i){
	            if(journalPages[i]){
	                flag = true;
	                cntT++;
	            }else{
	                if(flag && cntT >= 3){
	                    result += 5 + ( 2 *(endPoint - startPoint+1));
	                    startPoint = i;
	                }
	                flag = false;
	                cntT = 0;
	                endPoint = i;

	                if(startPoint == -1)
	                    startPoint = i;
	            }

	        }
	        result += 5 + ( 2 *(endPoint - startPoint+1));
	        System.out.println(result);
	    }

}
