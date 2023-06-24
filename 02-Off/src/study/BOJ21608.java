package study;

import java.io.*;
import java.util.*;

public class BOJ21608 {
    //r,c기준 상하좌우 -> 인접하다고 표현
    /* 1. 비어있는 칸중 종하하는 학생이 학생이 인접한 칸에 가장 많은 칸으로 자리를 정한다
        -> 상하좌우가 가장 많이 차있으면 좋은자리
       2. 1의 조건을 만족하는 칸이 여러개 -> 비어있는 칸이 가장 많은 칸으로 자리 지정
       3. 2의 조건을 만족하는 칸도 여러개 -> 행의 번호가 가장 작은칸, 열의 번호가 가장 작은칸

       자리 배치 후 만족도 구해야한다.
       학생과 인접한 칸에 앉은 좋아하는 학생 수 구하기
       0명 : 0
       1명 : 1
       2명 : 10
       3명 : 100
       4명 : 1000
     */
    static int N;
    static int[][] map;
    static HashMap<Integer, Set<Integer>> students;
    static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static int[] seq;

    //0: 학생, 1,2,3,4 -> 좋아하는 학생
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        students = new HashMap<>();

        //입력 순서대로 자리에 배치한다.
        seq = new int[N * N + 1];

        //입력 순서 저장
        for (int i = 1; i <= N * N; ++i) {
            st = new StringTokenizer(br.readLine());
            //순서기록
            seq[i] = Integer.parseInt(st.nextToken());

            //좋아하는 학생 기록
            HashSet<Integer> favorite = new HashSet<>();
            for (int j = 0; j < 4; ++j) {
                favorite.add(Integer.parseInt(st.nextToken()));
            }
            students.put(seq[i], favorite);
        }

        //처음 입장하는 아이는 무조건 1,1에 배치된다 -> 인접한 빈칸 4개, 행이 가장작고, 열도 가장 작기 떄문
        map[1][1] = seq[1];

        for(int i = 2 ; i <= N * N ; ++i){
            simulataion(i);
        }

        System.out.println(getAnswer());
    }

    private static void simulataion(int now) {
        int nowStudent = seq[now];
        //최적 위치를 선별하기 위한 pq

        //조건1 체크 : 비어있는 칸중 좋아하는 학생이 학생이 인접한 칸에 가장 많은 칸으로 자리를 정한다
        List<int[]> filter = condition1(nowStudent);

        //조건2 체크 : 1의 조건을 만족하는 칸이 여러개 -> 비어있는 칸이 가장 많은 칸으로 자리 지정
        if(filter.size() > 1) filter = condtion2(filter);

        //조건3 체크 : 2의 조건을 만족하는 칸도 여러개 -> 행의 번호가 가장 작은칸, 열의 번호가 가장 작은칸
        if(filter.size() > 1) {
            int[] result = condition3(filter);
            map[result[1]][result[0]] = nowStudent;
            return;
        }

        map[filter.get(0)[1]][filter.get(0)[0]] = nowStudent;
        return;
    }

    private static int[] condition3(List<int[]> condition2List) {
        condition2List.sort((o1, o2) -> {
            return o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0];
        });

        return condition2List.get(0);
    }

    private static List<int[]> condtion2(List<int[]> condition1List) {
        List<int[]> list = new ArrayList<>();

        int condition2Max = Integer.MIN_VALUE;
        for(int[] loc : condition1List){
            int emptyCnt = 0;
            for(int i = 0 ; i < 4 ; ++i){
                int nx = loc[0] + dir[i][0];
                int ny = loc[1] + dir[i][1];
                if(!checkRange(nx, ny)) continue;   //좌표 유효성 체크
                if(map[ny][nx]!=0) continue;        //비어있는칸인지 체크

                emptyCnt++;
            }

            //인접한 칸에 비어있는 칸이 더 많을 경우 새롭게 갱신한다.
            if(emptyCnt >= condition2Max){
                if(emptyCnt > condition2Max){
                    condition2Max = emptyCnt; //인접한 학생 수 최댓 값 갱신
                    //우선순위 큐 초기화 후 현재 위치 다시 삽입
                    list.clear();
                }
                list.add(new int[] {loc[0], loc[1]});
            }else continue; //작으면 의미없다 버려
        }
        return list;
    }

    private static List<int[]> condition1(int nowStudent){
        List<int[]> list = new ArrayList<>();
        //조건1 체크 : 비어있는 칸중 좋아하는 학생이 학생이 인접한 칸에 가장 많은 칸으로 자리를 정한다
        int condition1Max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++){
                if(map[i][j]!=0) continue;
                int cnt = 0;

                //사방탐색을 통해 인접한 칸에 좋아하는 학생이 있는지 체크
                for (int k = 0; k < 4; k++) {
                    int nx = j + dir[k][0];
                    int ny = i + dir[k][1];
                    if (!checkRange(nx, ny)) continue;

                    if(students.get(nowStudent).contains(map[ny][nx])) cnt++;
                }

                //인접한 칸에 좋아하는 학생이 더 많을 경우 새롭게 갱신한다.
                if(cnt >= condition1Max){
                    if(cnt > condition1Max){
                        condition1Max = cnt; //인접한 학생 수 최댓 값 갱신
                        //우선순위 큐 초기화 후 현재 위치 다시 삽입
                        list.clear();
                    }
                    list.add(new int[] {j, i});
                }else continue; //작으면 의미없다 버려
            }
        }

        return list;
    }

    //합산한다.
    private static int getAnswer() {
        int answer = 0;
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                answer += satisfy(j, i);
            }
        }
        return answer;
    }

    private static int satisfy(int x, int y) {
        int cnt = -1;

        //상하좌우 체크해서 좋아하는 학생이 있으면 점수를 매긴다.
        for (int i = 0; i < 4; ++i) {
            int nx = x + dir[i][0];
            int ny = y + dir[i][1];
            if (!checkRange(nx, ny)) continue;

            if (students.get(map[y][x]).contains(map[ny][nx]))
                cnt++;
        }
        /*
       학생과 인접한 칸에 앉은 좋아하는 학생 수 구하기
       0명 : 0
       1명 : 1
       2명 : 10
       3명 : 100
       4명 : 1000
        */
        return (int) Math.pow(10, cnt);
    }

    private static boolean checkRange(int x, int y) {
        return (0 <= x && x < N && 0 <= y && y < N);
    }
}
