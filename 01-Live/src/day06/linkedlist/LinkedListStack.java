package day06.linkedlist;

public class LinkedListStack<T> implements IStack<T> {
	public Node<T> top; //연결리스트의 head 부분

	@Override
	public void push(T e) {
		top = new Node<T>(e, top);
	}

	@Override
	public T pop() {
		if(isEmpty()) {
			System.out.println("스택이 비어있어 불가능합니다.");
			return null;
		}
		
		Node<T> popNode = top;
		top = popNode.link;
		popNode.link = null;
		
		return popNode.data;
	}

	@Override
	public T peek() {
		if(isEmpty()) {
			System.out.println("스택이 비어있어 불가능합니다.");
			return null;
		}
			
		return top.data;
		
	}

	@Override
	public boolean isEmpty() {
		return top == null; //스택이니 top이 null이면 비어있음
	}

	@Override
	public int size() {//top부터 노드 따라가서 마지막 노드 까지의 수
		int res = 0;
		for(Node<T> temp = top ; temp != null ; temp = temp.link) {
			res++;
		}
		
		return res;
	}


}
