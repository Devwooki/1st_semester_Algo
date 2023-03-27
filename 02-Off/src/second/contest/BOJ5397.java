package second.contest;
import java.io.*;
import java.util.*;

public class BOJ5397 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		List<String> res = new LinkedList<>();
		int N = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < N ; ++i) {
			String str = br.readLine();
			Stack<Character> stack = new Stack<>();
			Stack<Character> temp = new Stack<>();
			
			for(int j = 0 ; j < str.length() ; ++j) {
				char c = str.charAt(j);
				if(c == '<') {
					if(!stack.isEmpty()) {
						temp.push(stack.pop());
					}
				}else if( c == '>') {
					if(!temp.isEmpty()) {
						stack.push(temp.pop());
					}
				}else if( c == '-') {
					if(!stack.isEmpty()) stack.pop();
				}else {
					stack.push(c);
				}
			}
			while(!stack.isEmpty()) {
				sb.append(stack.pop());
			}
			sb.reverse();
			while(!temp.isEmpty()) {
				sb.append(temp.pop());
			}
			res.add(sb.toString());
			sb.setLength(0);
		}
		for(String cmd : res)
		System.out.println(cmd);
	}
}
