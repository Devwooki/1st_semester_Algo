package com.ssafy.offline06.selfStack;

public interface wookStack<T> {
	
	void push(T data);
	T pop();
	T peek();
	boolean isEmpty();
	int size();

}
