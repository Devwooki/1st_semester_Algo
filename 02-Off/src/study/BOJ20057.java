package study;

import java.io.*;
import java.util.*;

/*
한 칸 전진
-> 앞칸에
*/
public class BOJ20057 {
    static int[][] dirInfo = {{-1, 0}, {0,1}, {1, 0}, {0, -1}};
    static int[] sandValue = {1,1, 7,-1,7, 2,10,0, 0,5,10, 0,0,2};
    static int[] spreadSandAmount;
    static int N, sum;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];


        for (int i = 0; i < N; i++)
           map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        simulataion();

//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < N; j++) {
//                System.out.print(map[i][j] + " ");
//            }
//            System.out.println();
//        }

        System.out.println(sum);
    }

    private static void simulataion() {
        //첫 시작 위치
        Loc now = new Loc(N / 2, N / 2);

        int dir = 0;  //현재 방향을 타나낸다
        int dirMove = 1; //현재 방향으로 나아갈 횟수

        while (true) {
            for (int dirChangeCnt = 0; dirChangeCnt < 2; ++dirChangeCnt) {
                for (int i = 0; i < dirMove; ++i) {

                    //토네이도가 움직인다
                    now.x += dirInfo[dir][0];
                    now.y += dirInfo[dir][1];

                    //토네이도는 1,1에 도착하면 소멸한다.
                    if (now.x == 0 && now.y == 0) return;

                    //아닌 경우 모레를 퍼트린다.
                    spreadSand(now, dir);
                }
                dir = (dir+1)%4;
            }
            dirMove++;
        }
    }

    private static void spreadSand(Loc now, int dir) {
        //먼지를 퍼트리는 사방탐색은 ㅗ 모양으로 총 4번만 BFS를 실시한다.
        //맵 범위를 초과한 경우 sum에 +하고 이동한 위치의 모래양에서 제외
        //그렇지 않은 경우 기존 map에 모래를 합산하며 소수점 첫째 자리에 버림

        //현재 위치의 모래 양을 기록
        int nowSandAmount = map[now.y][now.x];
        int locAValue = calcSpredSandAmount(nowSandAmount);

        //첫 사방탐색을 수행한다.
        //사방탐색 수행하기 전, 모래가 1%만 이동하는 곳을 쉽게 연산하기 위해 이전 위치로 돌아간다.
        now.x -= dirInfo[dir][0];
        now.y -= dirInfo[dir][1];

        int searchDir = 3;
        int sandValueCnt = 0;
        for(int i = 0 ; i < 3 ; i+=2){
            int nearX = now.x + dirInfo[(searchDir + dir + i)%4][0];
            int nearY = now.y + dirInfo[(searchDir + dir + i)%4][1];

            //범위를 벗어나면 sum에다가 더해주자
            if(!checkRange(nearX, nearY)) {
                //소숫점 아래는 버린다
                sum += spreadSandAmount[sandValueCnt++];
            }else{//모래가 흩어졌으니 모래 양을 더한다.
                map[nearY][nearX] += spreadSandAmount[sandValueCnt++];
            }
        }

        //현재 위치 부터 ㅗ 방향만큼 탐색 1번씩 실시
        now.x += dirInfo[dir][0];
        now.y += dirInfo[dir][1];
        Queue<Loc> q = new LinkedList<>();
        q.offer(now);
        //while(!q.isEmpty()){
        while(sandValueCnt!=14){
            Loc next = q.poll();

            for(int i = 0 ; i < 3 ; ++i){
                int nearX = now.x + dirInfo[(searchDir + dir + i)%4][0];
                int nearY = now.y + dirInfo[(searchDir + dir + i)%4][1];

                //범위를 벗어나면 sum에다가 더해주자
                if(!checkRange(nearX, nearY)) {
                    //소숫점 아래는 버린다
                    sum += spreadSandAmount[sandValueCnt++];
                }else{//모래가 흩어졌으니 모래 양을 더한다.
                    //sandValue가 0이면 방문해서 계산한 곳이니 패스
                    if(sandValue[sandValueCnt] == 0){
                        sandValueCnt++;
                        continue;
                    }

                    //위치별 모래를 갱신해주고 큐에 다음 위치를 넣어준다.
                    map[nearY][nearX] += spreadSandAmount[sandValueCnt++];
                    q.offer(new Loc(nearX, nearY));
                }
            }
        }

        //모래를 퍼트렸으니 현재 위치에 모래는 더 이상 없다
        map[now.y][now.x] = 0;

        //a지점에 모래는 퍼트리고 남은 모래 만큼 더해진다.
        //a지점도 map의 범위 유무를 체크해서 값을 계산해주자
        if(!checkRange( now.x + dirInfo[dir][0], now.y += dirInfo[dir][1]))
            sum += locAValue;
        else map[now.y + dirInfo[dir%4][1]][now.x + dirInfo[dir%4][0]] += locAValue;
    }

    private static int calcSpredSandAmount(int nowSandAmount){
        int sum = 0;
        spreadSandAmount = new int[sandValue.length];

        for(int i = 0 ; i < sandValue.length ; ++i){
            if(sandValue[i] == -1) continue;
            spreadSandAmount[i] = (int) Math.floor(nowSandAmount * ((double) sandValue[i] /100));
            sum += spreadSandAmount[i];
        }

        return nowSandAmount-sum;
    }
    private static boolean checkRange(int x, int y){
        return ( 0 <= x && x < N && 0 <= y && y < N);
    }
}

class Loc {
    int x;
    int y;

    public Loc(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
