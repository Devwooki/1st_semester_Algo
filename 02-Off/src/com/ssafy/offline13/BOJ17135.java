package com.ssafy.offline13;
import java.awt.Point;
import java.io.*;
/*
  시뮬 순서
	1. 적들의 위치를 저장
	2. 궁수들의 위치를 선정(조합 - 3명) : 3명은 고정되어 있으므로 반복문을 활용하자
	3. 적들을 처치하자
		3-1. 적들의 위치를 복사
		3-2. 선택된 궁수의 위치를 기반으로 적들을 모두 처치하자
			3-2-1. 선택된 궁수의 위치와 가장 가까운 적을 찾아서 처치
				- 지정된 거리를 넘지 않아야 한다.
				- 가장 가까운 적을 찾는다.
				- 만약, 가장 가까운 적이 여러명이라면 왼쪽의 적을 선택한다.
			3-2-2. 적을 처치 후 적들을 한줄 내린다.
				- 내린 적이 성에 도달하면 (N) 적을 제거한다.
			3-2-3. 현재 조합에서 처치된 적과 이전 조합에서 처치된 적들의 수중에 가장 큰수를 저장한다.
	4. 처치한 적의 수를 출력
 */
import java.util.*;
public class BOJ17135 {
    static int N, M, D, result=0;
    static int[][] map, copyMap;
    static List<Point> originEnemies = new LinkedList<>();
    static int[] batch;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        
        for(int i = 0 ; i < N ; ++i){
        	st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < M ; ++j) {
            	int x = Integer.parseInt(st.nextToken());
            	if(x == 1) originEnemies.add(new Point(j, i));
            }
        }
        
        comb();
        
        
        System.out.println(result);
    }
    
    
    //궁수 위치 만들기
    static void comb() {
    	for(int i = 0 ; i < M-2 ; ++i) {
    		for(int j = i ; j < M - 1 ; ++j) {
    			for(int k = j ; k < M ; ++k) {
    				List<Point> copyEnemies = new LinkedList<>();
    				for(Point e : originEnemies) {
    					//객체 참조타입이기 때문에 e를 넣는것이 아닌 e가 참조하는 값들을 넣어준다.
    					copyEnemies.add(new Point(e.x, e.y));
    				}
    				
    				killEnemy(new int[] {i,j,k}, copyEnemies);
    			}
    		}
    	}
    }
    
    //적을 처치
    /*
	- 지정된 거리를 넘지 않아야 한다.
	- 가장 가까운 적을 찾는다.
	- 만약, 가장 가까운 적이 여러명이라면 왼쪽의 적을 선택한다.
     */
    static void killEnemy(int[] archers, List<Point> enemies) {
    	int cnt = 0;
    	while(!enemies.isEmpty()) {
    		//궁수별로 잡아야하는 적들을 담아둔다. 잡을 적들을 매번 갱신하기 때문에 set활용
    		HashSet<Point> removeEnemies = new HashSet<>();
    		for(int archer : archers) {
    			Point e = findEnemy(enemies, archer);
    			if(e != null) {
    				removeEnemies.add(e);
    			}
    		}
    		
    		cnt += removeEnemies.size();
    		enemies.removeAll(removeEnemies);
    		downEnemy(enemies);
    	}
    	result = Math.max(cnt,  result);
    	
    }
    private static void downEnemy(List<Point> enemies) {
    	Iterator<Point> iter = enemies.iterator();
    
    	while(iter.hasNext()) {
    		Point e = iter.next();
    		e.y++;
    		if(e.y == N ) iter.remove();
    	}
    }


	//적들을 한 줄 내린다.


	private static Point findEnemy(List<Point> enemies, int archer) {
		int minD = 1 << 30, minX = 50;
		Point find = null;
		for(Point e : enemies) {
			//궁수와 몬스터의 거리, 궁수위치 (archer, N), 몬스터 위치 (x, y);
			int distance = N - e.y + Math.abs(archer-e.x);
			//궁수와 몬스터거리가 사정거리보다 크면 의미없음
			//몬스터와의 거리가 최소거리보다 크면 의미없음
			if( distance > D || distance > minD ) continue;
			
			//새롭게 찾은 적과 거리가 기존에 찾은 적의 거리보다 적은경우
			if( distance < minD) {
				minD = distance;
				minX = e.x;
				find = e;
				continue; //더 가까운 적들이 있을 수 있으니까.
			}
			
			//여기까지 도달 시 적의 거리는 최소 거리와 같으니 제일 왼쪽에 위치한 적 잡기
			if( minX > e.x) {
				minX = e.x;
				find = e; //적 새롭게 갱신
			}
		}
		
		return find;
	}
}
