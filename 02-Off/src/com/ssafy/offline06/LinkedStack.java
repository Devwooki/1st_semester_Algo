package com.ssafy.offline06;

public class LinkedStack<E> implements IStack<E> {
	private Node<E> top;
	
	public Node<E> getTop() {
		return top;
	}

	@Override
	public void push(E data) {
		top = new Node<E>(top, data);
	}

	@Override
	public E pop() {
		if(isEmpty()) {
			System.out.println("없음");
			return null;
		}
		
		Node<E> node = top;
		top = top.getLink();
		node.setLink(null);
		
		return node.getData();
	}

	@Override
	public E peek() {
		if(isEmpty()) {
			System.out.println("없음");
			return null;
		}
				
		return top.getData();
	}

	@Override
	public boolean isEmpty() {
		return top == null;
	}

	@Override
	public int size() {
		int cnt = 0;
		Node<E> node = top;
		while(node != null) {
			cnt++;
			node = node.getLink();
		}
		return cnt;
	}
	

}
