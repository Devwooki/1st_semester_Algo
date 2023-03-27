package com.ssafy.offline06.selfStack;

public class wookStackTest {
	public static void main(String[] args) {
		wookStack<Integer> st = new wookStackImpl<>();
		
		st.push(10);
		st.push(20);
		st.push(30);
		st.push(40);
		
		while(!st.isEmpty()) {
			System.out.println(st.pop());
		}
	}
}
