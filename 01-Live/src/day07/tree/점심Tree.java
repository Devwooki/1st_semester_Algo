package day07.tree;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

class MyTree<T>{
	private Object[] nodes;
	private final int SIZE;
	private int lastSize;
	
	public MyTree(int size) {
		SIZE = size;
		nodes = new Object[SIZE + 1];
		//부모, 자식 노드 접근에 용이하기 위해 선언
	}
	
	void offer(T data) {
		if(isFull()) {
			System.out.println("트리가 가득 찾습니다.");
			return;
		}
		
		nodes[++lastSize] = data;
	}
	
	void add(T data) {
		if(isFull()) {
			System.out.println("트리가 가득 찾습니다.");
			return;
		}
		
		nodes[++lastSize] = data;
	}
	
	boolean isFull() {
		return lastSize == SIZE;
	}
	
	void bfs() {
		Queue<Integer> q = new ArrayDeque<>();
		int now = 0;
		int lv = 0;
		q.offer(1);
		
		while(!q.isEmpty()) {
			System.out.print("레벨 : " + lv  + " ");
			
			int size = q.size();
			for(int i = 0 ; i < size; ++i) {
				System.out.print(q.poll() + " ");
			}
			System.out.println();
		}
		
		
		
	}
	
	
	
}

public class 점심Tree {
	
}
