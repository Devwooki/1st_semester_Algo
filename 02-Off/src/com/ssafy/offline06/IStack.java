package com.ssafy.offline06;

public interface IStack<E> {
	void push(E data);
	E pop();
	E peek();
	boolean isEmpty();
	int size();

}
