package supplement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
서로소 집합
- 교집합이 없는 집합들
- 집합 연산은 크게 3가지
MakeSet - 집합을 만든다
FindSet - 대표자를 찾는 연산
Union - 두 집합을 합친다. 한쪽에 대표자를 부여
* */
public class UnionFind {
    static int[] p;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        p = new int[N];
        MakeSet();


    }

    //서로소 집합을 만든다
    static void MakeSet(){
        for(int i = 0 ; i < N ; ++i){
            p[i] = i;
        }
    }

    //a의 대표자를 출력해준다.
    static int findSet(int a){
        if(p[a] == a) return a; //대표자가 자기자신이면 반환

        //return findSet(p[a]); 는 최적화가 되지 않아서 U
        return p[a] = findSet(p[a]);
    }

    //a와 b를 합친다, a를 대표자로 한다.
    static boolean union(int a, int b){
        int aRoot = findSet(a);
        int bRoot = findSet(b);
        if(aRoot == bRoot) return false; //두 대표자가 같으면 false

        //A를 대표자로 하기 때문에 bRoot의 인덱스 값에 aRoot로
        p[bRoot] = aRoot;
        return true;
    }
}
