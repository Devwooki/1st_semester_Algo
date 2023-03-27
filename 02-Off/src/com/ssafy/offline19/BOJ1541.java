package com.ssafy.offline19;
import java.io.*;
import java.util.*;

public class BOJ1541 {
	 public static void main(String[] args) throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String str = br.readLine();
			StringBuilder sb = new StringBuilder();
			ArrayList<Integer> number = new ArrayList<>();
			ArrayList<Character> symbol = new ArrayList<>();
			for (int i = 0; i < str.length(); ++i) {
				char c = str.charAt(i);
				//문자열 한글자씩 받아옴

				//수식을 만나면
				if (c == '+' || c == '-') {
					//숫자들 리스트에넣고
					number.add(Integer.parseInt(String.valueOf(sb)));

					symbol.add(c);
					sb.setLength(0);
				}else{
					sb.append(c);
				}
			}

			//맨 마지막엔 수식이 없으므로 숫자들 리스트에 넣음
			number.add(Integer.parseInt(String.valueOf(sb)));
			//리스트 사이즈는 number가 +1임

			boolean startDiff = false;
			int result = 0;
			for(int i = 0 ; i < symbol.size(); ++i){

				if(startDiff) result -= number.get(i);
				else result += number.get(i);

				if(symbol.get(i) == '-') startDiff = true;

			}

			if(startDiff) result -= number.get(number.size()-1);
			else result += number.get(number.size()-1);

			System.out.println(result);
	    }
}
