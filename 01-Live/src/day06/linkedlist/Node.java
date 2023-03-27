package day06.linkedlist;

public class Node<T>{
	T data;
	Node<T> link;
	
	@Override
	public String toString() {
		return "Node [data=" + data + ", node=" + link + "]";
	}
	public Node() {
		super();
		this.data = null;
		this.link = null;
	}
	
	public Node(T data, Node<T> node) {
		super();
		this.data = data;
		this.link = node;
	}
	
	public Node(T data) {
		super();
		this.data = data;
	}
	
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public Node getNode() {
		return link;
	}
	public void setNode(Node<T> node) {
		this.link = node;
	}
	
}
