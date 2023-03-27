package com.ssafy.offline06;

public class Node<T> {
	private Node link;
	private T data;
	@Override
	public String toString() {
		return "Node [link=" + link + ", data=" + data + "]";
	}
	
	public Node(Node<T> link, T data) {
		super();
		this.link = link;
		this.data = data;
	}
	
	public Node() {
		super();
		this.link = null;
	}
	
	public Node getLink() {
		return link;
	}
	public void setLink(Node link) {
		this.link = link;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
	
}
