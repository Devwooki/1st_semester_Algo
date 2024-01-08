package practiceTest;

import java.io.*;
import java.util.*;

//점점 높은 돌을 밟으면서 개울을 지나가려고 한다. 최대 개수 고르기 -> LIS
public class 소프티어_징검다리 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] stones = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] LIS = new int[N];

        int size = 0;
        for(int i = 0 ; i < N ; ++i){
            // 이진 탐색을 통해 들어갈 위치 찾는다.
            int insertIdx = Arrays.binarySearch(LIS, 0, size, stones[i]);
            insertIdx = Math.abs(insertIdx) -1;

            //LIS에 최장수열에 적합한  값을 순서대로 입력한다..
            /*
            stones 3 2 1 4 5
            LIS : [0, 0, 0, 0, 0]

            i = 0 -> stones[i] = 3
            insertIdx = 0
            LIS : [3, 0, 0, 0, 0]

            i = 1 -> stones[i] = 2
            insertIdx = 0
            LIS : [2, 0, 0, 0, 0]

            i = 2 -> stones[i] = 1
            insertIdx = 0
            LIS : [1, 0, 0, 0, 0]

            i = 3 -> stones[i] = 4
            insertIdx = 1
            LIS : [1, 4, 0, 0, 0]

            i = 4 -> stones[i] = 5
            insertIdx = 2
            LIS : [1, 4, 5, 0, 0]
             */
            LIS[insertIdx] = stones[i];

            if(size ==  insertIdx)size++;
        }
        System.out.println(size);
    }
}
