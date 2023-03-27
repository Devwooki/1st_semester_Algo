package com.ssafy.offline15;
import java.io.*;
import java.util.*;

public class BOJ1759 {
	static char[] element;
	static char[] combi;
	static List<String> list = new LinkedList<>();
	static int L, C;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		System.out.println(4/1);
		System.out.println(4/2);
		System.out.println(4/3);
		System.out.println(4/4);
		System.out.println(4/5);
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		element = new char[C];
		combi = new char[L];
		 
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < C ; ++i) {
			element[i] = st.nextToken().charAt(0);
		}
		Arrays.sort(element);
		possiblity(0,0);
		
		//System.out.println(sb);
	}
	
	static void possiblity(int cnt, int start) {
		if(cnt == L) {
			String str = new String(combi);
			if(checkPW(str)) {
				list.add(str);
				System.out.println(combi);
			}
			return;
		}
		for(int i = start ; i < C ; ++i) {
			combi[cnt] = element[i];
			possiblity(cnt+1, i + 1);
		}
		
	}
	
	static boolean checkPW(String s) {
		int gather = 0; //모음
		int cons = 0; //자음
		for(int i = 0 ; i < s.length() ; ++i){
			char c = s.charAt(i);
			
			if(c == 'a' || c == 'e'|| c == 'i'|| c == 'o'|| c == 'u')
				gather++;
			else
				cons++;
			
			if(gather >= 1 && cons >=2) {
				return true;
			}
		}
		
		return false;
	}
}
