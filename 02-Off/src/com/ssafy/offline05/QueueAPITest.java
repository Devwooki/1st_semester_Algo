package com.ssafy.offline05;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class QueueAPITest {
	public static void main(String[] args) {
		//데이터 수정 위주 -> Linked
				//검색 위주 -> ArrayDeque
		
		Queue<String> q1 = new LinkedList<>(); //노드 구조

		q1 = new ArrayDeque<>();//배열 구조 -> 탐색속도가 인덱스 접근으로 인해 ArrayDeque가 빠름 
		//검색 위주 -> ArrayDeque
		System.out.println(q1.isEmpty() + ", " + q1.size());
		//stack push = queue offer
		//stack pop = queue poll
		q1.offer("Hello");
		q1.offer("World");
		System.out.println(q1.poll()); //데이터 꺼내기, 삭제
		System.out.println(q1.isEmpty() + ", " + q1.size());
		System.out.println(q1.peek()); //데이터 조회, 삭제안함
		System.out.println(q1.isEmpty() + ", " + q1.size());
		
		
		//우선순위큐 - 오름차순 자동 정렬, String은 compareTo로 오름차순 되어잇음
		Queue<String> q2 = new PriorityQueue<>();
		q2.offer("더 글로리2");
		q2.offer("더 글로리1");
		q2.offer("피지컬100");
		q2.offer("일타스캔들");
		while(!q2.isEmpty()) {
			System.out.println(q2.poll());
		}
		
		
		//Student에 comparator / comparable이 구현되지 않아 정렬 불가능
		Queue<Student> q3 = new PriorityQueue<>();
		q3.offer(new Student(80, "정경호, 전도연"));
		q3.offer(new Student(78, "윤성빈, 김강민"));
		q3.offer(new Student(60, "에일리, 다비치"));
		q3.offer(new Student(99, "문동은, 박연진"));
		while(!q3.isEmpty()) {
			System.out.println(q3.poll());
		}
	}
	static class Student implements Comparable<Student>{
		
		int age;
		String name;
		public Student(int age, String name) {
			super();
			this.age = age;
			this.name = name;
		}
		@Override
		public String toString() {
			return "Student [age=" + age + ", name=" + name + "]";
		}
		@Override
		public int compareTo(Student o) {
			return this.name.charAt(0) - o.name.charAt(0);
		}
		
	}
}
