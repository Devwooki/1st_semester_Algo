
package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class BOJ11503 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        int[] LIS = new int[n];
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();


        /*
        최장길이 수열
        LIS[i]의 값은 2가지이다
        case 1 : arr[i]를 포함할 때
            j < i 와 arr[j] < arr[j]를 만족한다면 LIS[i] = LIS[j] + 1 이다.
            0 <= j < i

        case 2 : arr[i]를 포함하지 않을 때
            - 앞에서 증가하다가 arr[i]가 arr[i-1]보다 작아서 증가하지 않음
            => LIS[i] = LIS[i-1]
         */


        // 방법1 n^2으로 구하기
        int result = Integer.MIN_VALUE;
        for(int i = 0 ; i < n ; ++i){
            LIS[i] = 1; //처음엔 자기 자신만 포함한다. - 자기 자신만을 고려
            for(int j = 0 ; j < i ; ++j){
                if (arr[j] < arr[i] && LIS[i] < LIS[j] + 1){
                    LIS[i] = LIS[j] + 1;
                }
            }

            //LIS[i]가 정해졌으니 max값을 찾아보자
            //시간 복잡도가 O(n^2)임
            //이진탐색을 사용하면 더 빨리 줄일 수 있다.
            result= Math.max(LIS[i], result);
        }

        //방법 2 이진탐색 사용
        //arrays.binSearch를쓰는데 값이 없을 경우 반환하기 아주 적절한 위치를 반환한다. -(적절한 위치 idx + 1)
        int[] LIS2 = new int[n];
        int LISsize = 0;
        for(int i = 0 ; i < n ; ++i){
            int index = Arrays.binarySearch(LIS2, 0, LISsize, arr[i]);

            index = Math.abs(index) - 1; //적절한 위치 -(index+1)를 반환했으니 제대로된 인덱스 값으로 바꿔줌

            LIS2[index] = arr[i];
            System.out.println(LISsize + "," + index);
            if (LISsize == index) { // 삽입위치의 인덱스와 크기가 같으면(결국,마지막이 삽입위치라는 얘기임) 크기 1늘림.
                LISsize++;
            }
        }

        System.out.println(result);
    }
}
