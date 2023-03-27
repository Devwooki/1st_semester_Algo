package day06.linkedlist;

import java.io.*;
import java.util.*;
/**
 * 연결리스트에서 순서 : 리스트에 원하는 위치에 추가 했을 때, 
 * 				   정확하게 해당 위치에서 원소가 조회되어야 함
 * 
 * set : 순서 없음 <-> treeSet : 원소가 정렬된 집합
 * ----------LinkedList 메소드-------------
 * add(E) : index를 이용해 해당 위치에 원소 추가
 * 데이터 접근 - get메소드
 * offset : 첫 번째 원소 기준 얼마나 멀리있는지 반환 int형
 * 
 * 
 * 1. 노드를 생성하고 data에 저장
 * 2. 새 노드의 link값을 head값으로 채움
 * 3. head는 새 노드로 연결
 * 
 * addFirst(L, i )
 * 		new <- createNode(); 새로운 노드 생성
 * 		new.data = i;		 데이터 필드 작성
 * 		new.link = L;		 링크 필드 작성
 * 		L = new 			 리스트 처음으로 지정
 * end 
 * 
 * 마지막 노드를 탐색하지 않게 하기 위에 tail을 사용함
 * addToLast(L, i )
 * 		new <- createNode(); 새로운 노드 생성
 * 		new.data = i;		 
 * 		new.link = null;
 * 		if( L == null){	//빈 노드면 최초 노트 추가
 * 			L = new;
 * 			return;
 * 		}
 * 		temp = L;			//노드 링크를 이용해 리스트 순회
 * 		while(temp.link != null){ //마지막 노드 찾을 떄 까지 이동
 * 			temp = temp.link;
 * 		}
 * 		temp.link = new; 	//마지막 노드추가		  
 * 
 */
public class LinkedListImpt {	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		Node now = new Node();
		Node prev = new Node();
		
		
	}
}
