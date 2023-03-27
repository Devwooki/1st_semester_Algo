package day01.recursive;

import java.io.IOException;

/**
 * @author THKim
 */
public class IO3_StringBuilderTest {

	public static void main(String[] args) throws IOException {
		
		StringBuilder sb = new StringBuilder();
		sb.append("Hello ");
		sb.append("SSAFY").append("!!");
		System.out.println(sb.toString()); // Hello SSAFY!!
		
		sb.setLength(sb.length()-2);
		System.out.println(sb.toString()); // Hello SSAFY
		
		sb.insert(0,"aaa ");
		System.out.println(sb.toString());
		
		sb.reverse();
		System.out.println(sb.toString());
	}

}
