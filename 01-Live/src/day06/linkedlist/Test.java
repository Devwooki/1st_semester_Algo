package day06.linkedlist;

public class Test {
	public static void main(String[] args) {
		//IStack<String> stack = new LinkedListStack<String>();
		LinkedListStack<String> stack  = new LinkedListStack<String>();
		stack.push("김싸피");
		stack.push("이싸피");
		stack.push("박싸피");
		stack.push("최싸피");
		stack.push("백싸피");
		stack.push("장싸피");
		
		System.out.println(stack.top);
		System.out.println(stack.size() + " " + stack.isEmpty());
		System.out.println(stack.peek() + " " + stack.size());
		System.out.println(stack.pop() + " " + stack.size());
		System.out.println(stack.pop() + " " + stack.size());
		System.out.println(stack.pop() + " " + stack.size());
		System.out.println(stack.pop() + " " + stack.size());
		System.out.println(stack.pop() + " " + stack.size());
		System.out.println(stack.pop() + " " + stack.size());
		System.out.println(stack.pop() + " " + stack.size());
		
		
	}
}
