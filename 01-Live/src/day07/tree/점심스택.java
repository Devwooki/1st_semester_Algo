package day07.tree;


class Node<E>{
	E data;
	Node<E> link;
	Node(){
		this.link = null;
	}
	
	Node(E data, Node<E> link) {
		this.data = data;
		this.link = link;
	}	
}

class MyLinkedList<E>{
	private Node<E> top;
	
	void push(E data) {
		top = new Node(data, top);
	}
	
	E peek() {
		if(isEmpty()) {
			System.out.println("연결리스트가 비었습니다.");
			return null;
		}	
		
		return top.data;
	}
	E poll() {
		if(isEmpty()) {
			System.out.println("연결리스트가 비었습니다.");
			return null;
		}
		
		Node<E> node = top;
		top = top.link;
		return node.data;
	}
	
	boolean isEmpty() {
		return top == null;
	}
	
	int size() {
		int cnt = 0;
		Node<E> node = top;
		while( node != null) {
			cnt++;
			node = node.link;
		}
		return cnt;
	}
}
public class 점심스택 {

}
