package day09.greedy;
import java.io.*;
import java.util.*;

public class GreedyTest {
	private static class Meeting implements Comparable<Meeting> {
		int start, end;

		public Meeting(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}
		@Override
		public int compareTo(Meeting o) {
			//종료시간이 빠른 순서대로 정렬하고 싶음
			//시작 시간이 같을 경우 빨리 끝나는 것 우선으로
			return this.start == o.start ? this.end - o.end : this.start - o.start;
			/* this.end = 10 , o.end = 11 -> 차이는 음수 : 오름차순(default)
			 * this.end = 7 ,  o.end = 7  -> 차이는 0 : 오름차순(default) 
			 * this.end = 13 , o.end = 10 -> 차이는 양수 : this와 o의 위치가 바뀜 -> 내림차순이 됨
			 */
			//교수님 코드 ver1
			// return this.end != o.end ? this.end-o.end : this.start - o.start;
			//교수님 코드 ver2
			// return this.end != o.end ? Integer.compare(this.end, o.end) 
			//							: Integer.compare(this.start, o.start)
		}
		@Override
		public String toString() {
			return "Meeting [start=" + start + ", end=" + end + "]";
		}
		
	}
	public static void main(String[] args) throws IOException {
		//입력 데이터10 1 4 1 6 6 10 5 7 3 8 5 9 3 5 8 11 2 13 12 14
		Scanner sc = new Scanner(System.in);
		int cnt = sc.nextInt();
		Meeting[] m = new Meeting[cnt];
		
		for(int i = 0 ; i < cnt ; ++i) {
			m[i] = new Meeting(sc.nextInt(), sc.nextInt());
		}
		
		Arrays.sort(m);
		List<Meeting> result = new ArrayList<Meeting>();
		result.add(m[0]);
		
		for(int i = 1 ; i < cnt ; ++i) {
			//마지막 회의의 종료시간보다 현재 회의의 시작 시간이 같거나 크다연 양립가능
			if(result.get(result.size()-1).end <= m[i].start) {
				result.add(m[i]);
			}
		}
		System.out.println(result.size());
		for(Meeting meet : result) {
			System.out.println(meet);
		}
		
	}

}
