package study;

import java.io.*;
import java.util.*;

public class BOJ20057 {
    static int[][] dirInfo = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static int[] sandValue = {
            1, 1,
            7, -1, 7,
            2, 10, 0,
            0, 5, 10,
            0, 0, 2};
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

                    //이동한 위치에서 토네이도를 퍼트린다.
                    spreadSand(now, dir);
                    //printResult();

                    //토네이도는 1,1에 도착하면 소멸한다.
                    if (now.x == 0 && now.y == 0) return;
                }
                dir = (dir + 1) % 4;
            }
            dirMove++;
        }
    }
    private static void spreadSand(Loc now, int dir) {
        //먼지를 퍼트리는 사방탐색은 y위치에서 ㅗ 모양으로 총 3번만 BFS를 실시

        int locAValue = calcSpredSandAmount(map[now.y][now.x]);//현재 위치의 모래 양을 기준으로 흩날리는 모래 양을 구함
        //모래를 퍼트렸으니 현재 위치에 모래는 더 이상 없다
        map[now.y][now.x] = 0;

        //사방탐색 수행하기 전, 모래가 1%만 흩어지는 곳을 연산
        //이전 위치에서 수행한다.
        now.x -= dirInfo[dir][0];
        now.y -= dirInfo[dir][1];

        int searchDir = 3;
        int sandValueCnt = 0;
        for (int i = 0; i < 3; i += 2) {
            int nearX = now.x + dirInfo[(searchDir + dir + i) % 4][0];
            int nearY = now.y + dirInfo[(searchDir + dir + i) % 4][1];

            //범위 초과 -> 밖으로 나간 모래에 합산
            if (!checkRange(nearX, nearY)) sum += spreadSandAmount[sandValueCnt++];
            //범위 내부 -> 해당 위치의 모래에 합산
            else map[nearY][nearX] += spreadSandAmount[sandValueCnt++];
        }

        //전방범위의 ㅗ 방향으로 3방 탐색 실시 + 큐에 넣어서 1번만 범위 확장
        now.x += dirInfo[dir][0];
        now.y += dirInfo[dir][1];
        Queue<Loc> q = new LinkedList<>();
        q.offer(now);

        while (sandValueCnt != 14) {
            Loc next = q.poll();

            for (int i = 0; i < 3; ++i) {
                int nearX = next.x + dirInfo[(searchDir + dir + i) % 4][0];
                int nearY = next.y + dirInfo[(searchDir + dir + i) % 4][1];

                //범위 초과 -> 밖으로 나간 모래에 합산
                if (!checkRange(nearX, nearY)) sum += spreadSandAmount[sandValueCnt++];
                //범위 내부 -> 해당 위치의 모래에 합산
                else {
                    //sandValue == 0은 이미 방문한 위치니까 건너뛴다
                    //sandValueCnt++ 가 아닌 이유 -> if문 검사할 때 마다 sandValueCnt가 증가하기 때문
                    if (sandValue[sandValueCnt] == 0) {
                        sandValueCnt++;
                        continue;
                    }
                    map[nearY][nearX] += spreadSandAmount[sandValueCnt++];
                }
                //ㅗ 으로 3방 탐색할 때 이동 할 위치가 범위를 벗어나도 확장해야하므로 큐에 넣는다.
                q.offer(new Loc(nearX, nearY));
            }
        }

        //a지점에 모래는 퍼트리고 남은 모래 만큼 더해진다.
        //a지점도 map의 범위 유무를 체크해서 값을 계산해주자
        int aLocX = now.x + dirInfo[dir][0];
        int aLocY = now.y + dirInfo[dir][1];

        if (!checkRange(aLocX, aLocY))
            sum += locAValue;
        else map[aLocY][aLocX] += locAValue;
    }

    //이동한 위치에서 퍼트릴 모래의 양을 미리 계산하는 메소드
    private static int calcSpredSandAmount(int nowSandAmount) {
        int sum = 0;
        spreadSandAmount = new int[sandValue.length];

        for (int i = 0; i < sandValue.length; ++i) {
            if (sandValue[i] == -1) continue; // y위치는 생략한다.
            spreadSandAmount[i] = (int) Math.floor(nowSandAmount * ((double) sandValue[i] / 100));
            sum += spreadSandAmount[i];
        }

        return nowSandAmount - sum;
    }

    //좌표 유효성 검사 메소드
    private static boolean checkRange(int x, int y) {
        return (0 <= x && x < N && 0 <= y && y < N);
    }

    static void printResult(){
        for (int q = 0; q < N; q++) {
            for (int j = 0; j < N; j++) {
                System.out.print(map[q][j] + " ");
            }
            System.out.println();
        }
        System.out.println("\n==================\n");
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
