package day06.linkedlist;

public interface IStack<T> {
	void push(T e); //어떤 자료형이 든 받기위해 제네릭 사용
	T pop();
	T peek();
	boolean isEmpty();
	int size();
}
