package com.ssafy.offline14;
import java.io.*;
import java.util.*;
/*
 * 시뮬순서
 * 1. 적들 위치 저장
 * 2. 궁수들의 위치를 저장 - 조합3명 -> 반복문으로 구현// 재귀할 필요 없음
 * 3. 적 처지
 * 		3-1. 맵 복사 - 궁수 조합이 여러개 나오기 때문
 * 		3-2. 선택된 궁수 위치 기반 적 처지
 * 			- 거리를 넘지 않아야함
 * 			- 가장 가까운 적 찾음
 * 			- 만약, 가장 가까운 적이라면 왼쪽 선택
 * 		3-2-2 1줄 내린다.
 * 			- 내린 적이 성에 도달하면(N) 적을 제거한다.
 * 		3-2-3. 현재 조합에서 처치된 적과 이전 조합에서 처치된 적들의 수중 큰 수 저장.
 * 	4. 처치한 적의 수 출력
 * 
 * 4. 
 */
public class BOJ17135_PF {
	static class Enemy{
		int r, c;

		public Enemy(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
		
	}
	static int N, M,D, ans;
	static List<Enemy> enemies = new LinkedList<>();
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        
        //적들 위치 저장
        for(int i = 0 ; i < N ; ++i){
        	st = new StringTokenizer(br.readLine());
        	for(int j = 0 ; j < M ; ++j){
        		int x = Integer.parseInt(st.nextToken());
        		if(x == 1) enemies.add(new Enemy(i,j)); 
            }
        }
        
        //2. 궁수의 위치 선정(조합)
        for(int i = 0 ; i < M-2 ; ++i) {
        	for(int j = i+1 ; j < M-1 ; ++j) {
        		for(int k = j+1 ; j < M ; ++k) {
        			List<Enemy> copyEnemies = new LinkedList<>();
        			for(Enemy e : enemies) copyEnemies.add(new Enemy(e.r, e.c));
        			//add(e)를 하게 되면 주소값을 넣기에 enemis        			
        			
        			//적 처치하는 메소드생성 
        			killEnemies(copyEnemies, new int[] {i, j, k});
        		}
        	}
        }
        System.out.println(ans);
		
	}
	
    private static void killEnemies(List<Enemy> copyEnemies, int[] archers) {
    	int cnt = 0 ; //적들의 수 
    	
    	//리스트가 빌 때 까지
    	while(!copyEnemies.isEmpty()) {
    		Set<Enemy> removeEnemies = new HashSet<>();
    		for(int archer : archers) {
    			//해당 궁수가 처치할 적의 수 찾기
    			Enemy e = findEnemy(copyEnemies, archer);
    			if(e != null)removeEnemies.add(e);
    		}
    		//현재 턴에서 찾은 적들의 카운트 세기
    		cnt += removeEnemies.size();
    		
    		//현재 턴에사 찾은 적들을 리스트에서 제거
    		copyEnemies.removeAll(removeEnemies);
    		
    		//적들을 다하강 시키자..
    		downEnemy(copyEnemies);
    		
    	}
		
    	//모든 적이 처치 되었다.
    	ans = Math.max(ans,  cnt);
	}

	private static void downEnemy(List<Enemy> copyEnemies) {
		//리스트를 반복하면서 적의 r의 좌표를 ++ 증가시킨다.
		//증가시킨 좌표가 N과 같다면 적은 제거한다..
		Iterator<Enemy> iter = copyEnemies.iterator();
		while(iter.hasNext()) {
			Enemy e = iter.next();
			e.r++;//참조 객체기때문에 참조값에 변화를 주면 영향을 준다
			
			if(e.r==N) //제거대상
				iter.remove();
		}
		
	}

	private static Enemy findEnemy(List<Enemy> copyEnemies, int archer) {
		int minD = Integer.MAX_VALUE; //리스트에 적이 많으니까 최소거리 구하기
		int minC = 50;
		
		Enemy find = null;
		for(Enemy e : copyEnemies) {
			int d = (N - e.r) + Math.abs(archer - e.c); //-> 궁수의 위치는 늘 N에 있기 때문에
			if(d > D || d > minD) continue; //궁수가 잡을 수 없으니까, 잡을 수 있는 적보다 거리가 멀면 의미없음-> 가장 가까운 걸 사냥하기 위해
			
			//새롭게 찾은 적과의 거리가 기존에 찾은 적과의 거리보다 적거나 같다.
			if(d < minD) { 
				minD = d;
				minC = e.c; //만약 거리가 같으면 왼쪽 놈을 잡기 위해 minC를 구함
				find = e;
				continue;
			}
			
			//새롭게 찾은 적의 거리가 현재의 최소 거리와 같은 것 > 혹은 여러개라 제일 왼쪽
			if(minC > e.c) {
				//현재 놈이 지금까지 찾은놈보다 왼쪽에 있으면 타겟을 바꾼다.
				minC = e.c;
				find = e;
			}
		}
		//적을 찾기 시작한다.
		return null;
	}
}
