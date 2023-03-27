package com.ssafy.offline06;

public class Test {
	public static void main(String[] args) {
		//IStack<String> stack = new LinkedStack<>();
		LinkedStack<String> stack = new LinkedStack<>();
		System.out.println(stack.size()+" " + stack.getTop());
		stack.push("케냐AA");
		stack.push("과테말라");
		stack.push("인도");
		stack.push("안티구아 수프리모");
			
		System.out.println(stack.size()+" " + stack.getTop());
		
	}
}
