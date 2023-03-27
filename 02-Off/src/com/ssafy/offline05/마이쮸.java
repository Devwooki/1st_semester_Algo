package com.ssafy.offline05;

import java.util.LinkedList;
import java.util.Queue;

public class 마이쮸 {

	public static void main(String[] args) {
		int cnt = 20;
		Queue<int[]> q = new LinkedList<>();
		
		int idx = 1;
		while(cnt != 0 ) {
			q.add(new int[] {idx, 0});
			idx++;
			
			//poll하면 다음 인덱스가 들어온다.
			//temp[0] = 사람 번호
			//temp[1] = 가져갈 마이쮸 수
			int[] temp = q.poll();
			
			cnt -= ++temp[1];
			
			if(cnt <= 0) {
				System.out.println("마지막 마이쮸를 가져간 사원" + temp[0] + " 가져간 개수 : " + (cnt + temp[1]));
				return;
			}
			System.out.println(temp[0] +"번이 " + temp[1] +"개수만큼 가져갑니다. 남은 수 : " + cnt);
			
			q.add(temp);
			
			
		
		}

	}

}
