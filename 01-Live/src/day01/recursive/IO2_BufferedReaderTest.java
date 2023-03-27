package day01.recursive;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author THKim
 */
/*
hello
ssafy
8 2
*/
public class IO2_BufferedReaderTest {

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String a = in.readLine();
		System.out.println(a);
		char[] ch = in.readLine().toCharArray();
		for (char c : ch) {
			System.out.println(c);
		}
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int i = Integer.parseInt(st.nextToken());
		int j = Integer.parseInt(st.nextToken());
		System.out.println(i+"//"+j);

	}

}
