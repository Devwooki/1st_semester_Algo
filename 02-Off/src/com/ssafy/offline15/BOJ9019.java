package com.ssafy.offline15;
import java.io.*;
import java.util.*;
/*
레지스터에 0 ~ 9999 십진수 n 저장가능
n = 즉 자릿수에따라 d1, d2, d3, d4로표현
D : 2n으로만듬, 9999보다 클 경우 % 10000 (2 * n % 10000)
S : n - 1, 0에서 -1 할 경우 9999
L : 자릿수를 왼쪽으로 회전 > d2, d3, d4, d1
R : 자릿수 오른쪽 회전 > d4, d1, d2, d3


String 으로 받아서 -> 리스트 -> l, R연산 용이 / 배열
 */
public class BOJ9019 {
    static class Register{
        int num;
        String cmd;
        public Register(int num, String cmd) {
            this.num = num;
            this.cmd = cmd;
        }
        int calD(){ return (this.num*2) %10000;}
        int calS(){ return this.num == 0 ? 9999 : this.num - 1; }
        //d2, d3, d4, d1
        //5678 -> 5 => 5678/1000, 678 -> (5678 % 1000)
        int calL(){return (this.num % 1000) * 10 + this.num / 1000;}
        //d4, d1, d2, d3
        //5678 -> 5678 / 10 , 8 -> 5678 % 10
        int calR(){ return (this.num % 10) * 1000 + this.num / 10; }
    }
    static int origin, result;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        System.out.println();
        int TC = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < TC; tc++) {
            st = new StringTokenizer(br.readLine());
            origin = Integer.parseInt(st.nextToken());
            result = Integer.parseInt(st.nextToken());
            visited = new boolean[10000];
            bfs();
        }
        System.out.println(sb);
    }

    static void bfs(){
        Queue<Register> q = new LinkedList<>();
        q.offer(new Register(origin, ""));
        visited[origin] = true;

        while(!q.isEmpty()){
            Register cur = q.poll();

            if(cur.num == result){
                sb.append(cur.cmd + "\n");
                return;
            }

            if(!visited[cur.calD()]){
                q.offer(new Register(cur.calD(), cur.cmd + "D"));
                visited[cur.calD()] = true;
            }
            if(!visited[cur.calS()]){
                q.offer(new Register(cur.calS(), cur.cmd + "S"));
                visited[cur.calS()] = true;
            }
            if(!visited[cur.calL()]){
                q.offer(new Register(cur.calL(), cur.cmd + "L"));
                visited[cur.calL()] = true;
            }
            if(!visited[cur.calR()]){
                q.offer(new Register(cur.calR(), cur.cmd + "R"));
                visited[cur.calR()] = true;
            }
        }
    }
}
