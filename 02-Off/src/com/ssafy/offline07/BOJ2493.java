package com.ssafy.offline07;
import java.io.*;
import java.util.*;

public class BOJ2493 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());

		Stack<Integer> tower = new Stack<>();
		Stack<Integer> idx = new Stack<>();
		st = new StringTokenizer(br.readLine()," ");

		//스택에 첫 번째 원소를 넣음
		tower.push(Integer.parseInt(st.nextToken()));
		idx.push(1);
		//첫 번째 원소의 왼쪽엔 늘 아무것도 없으니 0출력
		sb.append("0 ");

		//두 번째 원소부터 비교
		//좌측값(stack.peek)한 것 보다 크면 -> 스택 pop하고 현재 값넣음
		//좌측값(stack.peek)보다 작거나 같음 -> 반복 멈춤, pop하지 않고 현재 위치 stack에 push
		for(int i = 2 ; i <= N ; ++i){
			int now = Integer.parseInt(st.nextToken());

			while(!tower.isEmpty()) {
				if (tower.peek() < now) {
					tower.pop();
					idx.pop();
				} else {
					sb.append(idx.peek() + " ");
					break;
				}
			}
			//스택이 비어있으면 이전 값보다 더 큰게 스택에 위치함.
			// 더 큰 것은 왼쪽에 가르킬 수 있는게 없으므로 0을 붙임.
			if(tower.isEmpty()){
				sb.append("0 ");
			}

			tower.push(now);
			idx.push(i);

		}

		System.out.println(sb);
	}
}
