package second.contest;
import java.io.*;
import java.util.*;

//() - 2
//[] - 3
//(X) -> x값 2배
//[X] -> x값 3배
//(XY) -> X값 2배 + Y값 2배
/*
 * (()[[]])([])
 * 
 * 꺼내고 스택이 empty -> +2
 * 꺼내고 스택이 차 있으면 *3
 */
public class BOJ2504 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<Character> stack = new Stack();
		String cmd = br.readLine();
		int result = 0;

		int temp = 1;
		boolean flag= true;
		for(int i = 0 ; i < cmd.length();++i) {
			char c = cmd.charAt(i);

			if(c == '(' || c == '[')  {
				stack.push(c);
				temp *=  (c=='(') ? 2 : 3;
				continue;
			}


			else if(c == ')'){
				if(stack.isEmpty() || stack.peek() != '('){
					flag = false;
					break;
				}
				if(cmd.charAt(i-1) == '(') result += temp;

				stack.pop();
				temp /=2;
			}else if(c == ']'){
				if(stack.isEmpty() || stack.peek() != '[') {
					flag = false;
					break;
				}
				if(cmd.charAt(i-1) == '[') result += temp;

				stack.pop();
				temp /=3;
			}


		}
		
		if(!stack.isEmpty() || !flag) System.out.println(0);
		else System.out.println(result);
		
	}
}
