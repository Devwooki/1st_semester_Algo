package day01.recursive;

import java.io.*;
import java.util.*;

/**
 * 
 * @author THKim
 *
 */
public class IO5_BufferedReaderScannerTest {

	static String path = ".\\src\\com\\ssafy\\live01\\dist\\input.txt";
	public static void main(String[] args) throws IOException {

		System.setIn(new FileInputStream(path));
		//Scanner sc = new Scanner(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		long start = System.nanoTime();
		//int TC = sc.nextInt();
		int TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			int N = sc.nextInt();
			int sum = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					sum += sc.nextInt();
				}
			}
			System.out.println("#" + tc + " " + sum);
		}
		long end = System.nanoTime();
		System.out.println((end - start) / 1_000_000_000.0 + "s");
	} 

	
}
