package com.ssafy.offline19;
import java.io.*;
import java.util.*;
public class Expert1 {
    static class Apple implements Comparable<Apple>{
        int x;
        int y;
        int priority;
        public Apple(int x, int y, int priority) {
            this.x = x;
            this.y = y;
            this.priority = priority;
        }

        @Override
        public int compareTo(Apple o) {
            return this.priority-o.priority;
        }
    }
    static class User{
        int x;
        int y;
        int dir;
        public User(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }
    static int N, result = 0;
    static PriorityQueue<Apple> apples = new PriorityQueue<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                if(num != 0) apples.add(new Apple(j, i, num));
            }
        }
        getTurnCnt();
        System.out.println(result);
    }

    static void getTurnCnt(){
        User user = new User(0,0,0);
        /*
        오른쪽 : 0
        아래 : 1
        왼쪽 : 2
        위 : 3 순서로 움직인다
         */
        while(!apples.isEmpty()){
            Apple eat = apples.poll();

            int nextDir = checkAppleDir(user, eat);
            user.x = eat.x;
            user.y = eat.y;
            user.dir = (user.dir + nextDir)%4;
            result += nextDir;
        }
    }
    //왼쪽에 위치했을 때는 3번만 회전하면 된다.
    //왼쪽 앞에 다음 사과가 있을 경우 사과 앞 1칸에서 오른쪽으로 3번 회전하면 먹을 수 있음
    //왼쪽 뒤에 사과가 있을 경우 그냥 오른쪽으로 3번회전
    static int checkAppleDir(User u, Apple a){
        switch(u.dir){
            case 0 : //사람 오른쪽으로 나갈 때 사과가 왼쪽에 위치.
                if( u.y >  a.y) return 3;
                else{ //오른 쪽 방향에 사과 있을 경우
                    //우측 전방 - 같은 열에서 오른쪽 한 번 회전
                    if(u.x < a.x) return 1;
                    //우측 후방
                    else return 2;
                }
            case 1: //사람이 아래쪽으로 나갈 때 사과가 왼쪽에 위치
                if( u.x < a.x) return 3;
                else{
                    if(u.y < a.y) return 1;
                    else return 2;
                }
            case 2: //사람이 왼쪽으로 나갈 때
                if( u.y <  a.y) return 3;
                else{
                    if(u.x > a.x) return 1;
                    else return 2;
                }
            case 3: //사람이 위쪽으로 나갈 때
                if( u.x > a.x) return 3;
                else{
                    if(u.y > a.y) return 1;
                    else return 2;
                }
            default : return -1;
        }

    }
}
