package day07.tree;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Stack;

public class CompleteBinSearch<T> {
	private Object[] nodes;
	private final int SIZE;
	private int lastIndex;
	
	public CompleteBinSearch(int size) {
		SIZE = size;
		nodes = new Object[size+1];
	}
	
	private boolean isFull() {
		return lastIndex == SIZE;
	}
	
	public void add(T e) {
		if(isFull()) return;
		
		nodes[++lastIndex] = e;
		//자식 노드에 붙이는 것이 아닌 완전 이진 트리로 만들기
		//완전 이진트리는 부모 -> 왼쪽 자식 -> 오른쪽 자식
		//채워질 위치는 정해져 있음
	}
	
	public void bfs() {
		Queue<Integer> q = new ArrayDeque<Integer>();
		q.offer(1);
		
		int current = 0;
		int lv = 0;
//		while(!q.isEmpty()) {
//			//큐 대기순서대로 저장
//	
//			System.out.print("레벨 : " + lv++ + " ");
//			for(int i = 0, size = q.size() ; i < size ; ++i){
//				current = q.poll();
//				System.out.print(nodes[current] + " ");
//				
//				//현재 노드의 자식 노드들을 큐에 넣어 순서 기다리기
//				//완전 이진으티  :자식이 최대 2
//				
//				//큐의 길이 만큼만 뽑으면 현재 높이의 값들을 얻을 수 있음
//				
//				//왼쪽 자식
//				if(current*2 <= lastIndex) {
//					q.offer(current * 2);
//				}
//				if((current*2 + 1)<= lastIndex) {
//					q.offer(current * 2 + 1);
//				}
//			}	
//			System.out.println();
//		}
		int size;
		while(!q.isEmpty()) {
			size = q.size();
			System.out.println("lv : " + lv);
			while(--size >= 0) {
				current = q.poll();
				System.out.println(nodes[current] + "\t");
				if(current*2 <= lastIndex) {
					q.offer(current * 2);
				}
				if((current*2 + 1)<= lastIndex) {
					q.offer(current * 2 + 1);
				}
			}
			lv++;
			System.out.println();
			
		}
		
	}
	
	@Override
	public String toString() {
		return "CompleteBinSearch [nodes=" + Arrays.toString(nodes) + ", SIZE=" + SIZE + ", lastIndex=" + lastIndex
				+ "]";
	}
	
	public  void dfsByPreOrder() {
		System.out.print("Preorder : ");
		dfsByPreOrder(1);//시작점을 불러줌 -> 자동으로 메소드스택에 쌓이기 시작
		System.out.println();
	}
	
	private   void dfsByPreOrder(int current) {
		System.out.print(nodes[current]);
		
		//왼쪽자식
		if(current*2 <= lastIndex) {
			dfsByPreOrder(current * 2);
		}//오른쪽 자식
		if((current*2 + 1)<= lastIndex) {
			dfsByPreOrder(current * 2 + 1);
		}
	}
	
	public  void dfsByInOrder() {
		System.out.print("Preorder : ");
		dfsByInOrder(1);//시작점을 불러줌 -> 자동으로 메소드스택에 쌓이기 시작
		System.out.println();
	}
	private   void dfsByInOrder(int current) {
		//왼쪽자식
		if(current*2 <= lastIndex) {
			dfsByInOrder(current * 2);
		}//오른쪽 자식
		System.out.print(nodes[current]);
		if((current*2 + 1)<= lastIndex) {
			dfsByInOrder(current * 2 + 1);
		}
	}
	
	
	public  void dfsByPostOrder() {
		System.out.print("Preorder : ");
		dfsByPostOrder(1);//시작점을 불러줌 -> 자동으로 메소드스택에 쌓이기 시작
		System.out.println();
	}
	private   void dfsByPostOrder(int current) {
		//왼쪽자식
		if(current*2 <= lastIndex) {
			dfsByPostOrder(current * 2);
		}//오른쪽 자식
		if((current*2 + 1)<= lastIndex) {
			dfsByPostOrder(current * 2 + 1);
		}
		System.out.print(nodes[current]);
	}
	
	public void dfs() {
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(1);

		int current = 0;
		int lv = 0;

		int size;
		while (!stack.isEmpty()) {

			current = stack.pop();
			System.out.print(nodes[current] + "\t");

			if ((current * 2 + 1) <= lastIndex) {
				stack.push(current * 2 + 1);
			}

			if (current * 2 <= lastIndex) {
				stack.push(current * 2);
			}
		}
		System.out.println();

	}
}
