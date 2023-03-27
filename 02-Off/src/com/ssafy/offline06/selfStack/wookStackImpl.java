package com.ssafy.offline06.selfStack;

public class wookStackImpl<T> implements wookStack<T>{
	
	class Node<T> {
		T data;
		Node<T> link;
		
		Node(){
			this.link = null;
		}
		Node(T data, Node<T> link){
			this.data = data;
			this.link = link;
		}
	
		
		public String toString() {
			return "Node [data=" + data + ", link=" + link + "]";
		}
		
		
	}
	
	Node<T> top;
	@Override
	public void push(T data) {
		top = new Node<T>(data, top);
	}

	
	
	@Override
	public T pop() {
		if(isEmpty()) {
			System.out.println("연결리스트가 비었습니다.");
			return null;
		}
		
		Node<T> node =top;
		top = top.link; //top.link가 가르키는 것은 이전 노드
		return node.data;
	}

	@Override
	public T peek() {
		if(isEmpty()) {
			System.out.println("연결리스트가 비었습니다.");
			return null;
		}
		return top.data;
	}

	@Override
	public boolean isEmpty() {
		return top == null;
	}

	@Override
	public int size() {
		int cnt = 0;
		Node<T> node = top;
		while(node!= null) {
			cnt++;
			node = node.link;
		}
		return cnt;
	}



	
}
