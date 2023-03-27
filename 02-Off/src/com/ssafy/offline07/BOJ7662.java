package com.ssafy.offline07;

import java.io.*;
import java.util.*;

public class BOJ7662 {
	// https://www.acmicpc.net/problem/7662
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; ++tc) {
//			PriorityQueue<Integer> minHeap = new PriorityQueue<>();
//			PriorityQueue<Integer> maxHeap = new PriorityQueue<>();
			// 최대 힙은 최소 힙에 저장할 값에 부호를 반대로 지정 -> 둘다 최소힙이 된다
			TreeMap<Integer, Integer> q = new TreeMap<>();

			int N = Integer.parseInt(br.readLine());

			for (int i = 0; i < N; ++i) {
				String cmd = br.readLine();
				int num = Integer.parseInt(String.valueOf(cmd.substring(2)));
				// 기준이 되는 우선순위큐는 minHeap -> 자바는 기본 최소힙이기 때문
				// 예제 1번 기준
				// minHeap : -5643, 16
				// maxHeap : -16, 5643
//                if(cmd.charAt(0) == 'I'){
//                    minHeap.add(num);
//                    maxHeap.add(-num);//부호를 반대로 해서 넣는다
//                }else if(!minHeap.isEmpty() && cmd.equals("D -1")){//최솟값 삭제
//                        maxHeap.remove(-minHeap.poll());
//                }else if(!minHeap.isEmpty() && cmd.equals("D 1")){//최댓값 삭제
//                        //예시 minHeap에서 최댓값인 16을 지우기 위해선 maxHeap에서 -16을꺼내 부호 반대로
//                        minHeap.remove(-maxHeap.poll());
//                }
				if (cmd.charAt(0) == 'I') {
					q.put(num, q.getOrDefault(num, 0)+1);
				} else {
					if(q.size() == 0 ) continue;
					else {
						// num이 -1 -> 최솟값, num이 1-> 최댓값
						int temp = num == -1 ? q.firstKey() : q.lastKey();
						
						//최대값이나, 최솟값 뺴고, 1개 남은 상태였으면 제거
						if(q.put(temp, q.get(temp)-1) == 1) q.remove(temp);
					}
				}

			}
			sb.append(q.size() == 0 ? "EMPTY\n" : q.lastKey() + " " + q.firstKey() + "\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}

//
/* treeSet으로 구현해보고자 했음 실패 -> treeMap으로 풀어야함.
 * 동일한 데이터가 입력될 수 있으니
 
                switch(cmd){
                    case "I" :
                        empty = false;
                        dual.add(num);
                        break;
                    case "D" :
                        if(num == -1){ //최솟값 삭제
                            if(dual.size() != 0){
                                dual.remove(dual.first());
                                break;
                            }
                            else empty = true;

                        }else if(num == 1){ //최댓값 삭제
                            if(dual.size() != 0){
                                dual.remove(dual.last());
                                break;
                            }
                            else empty = true;
                        }
                        break;
                }
 */