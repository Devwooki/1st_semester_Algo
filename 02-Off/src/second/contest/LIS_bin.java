package second.contest;
import java.util.*;
import java.io.*;

/*
10
8 2 4 3 6 11 7 10 14 5
 */
public class LIS_bin {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int[] LIS = new int[N];

        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int size = 0;
        for (int i = 0; i < N; i++) {
            // 리턴값 : -insertPoint -1
            // binsearch -> 배열, start, end, key
            int temp = Arrays.binarySearch(LIS, 0, size, arr[i]);
            //Arrays. binarySearch -> 찾으려는 값이 없으면 들어가기 적절한 위치를 반환한다.
            // 적잘한 위치. : key보다 큰 최초의 위치
            // i = 1 일 떄 c = [8,0,0,0,0,0,0,0]
            // -> arr[1] = 2, 2가 없으니 2보다 큰 최초의 위치 -> 8의 idx = 0 ; -(0+1)
            // return mid; // key found
            // return -(low + 1);  // key not found.

            temp = Math.abs(temp) - 1;// 삽입위치


            LIS[temp] = arr[i]; // temp 자리에 값을 update 하면 그 의미는
            // 0인덱스 위치부터 temp위치까지의 원소 갯수가 temp위치에 저장된 그 값을 마지막으로 하는 LIS 길이가 됨
            // 배열의 원소가 LIS를 이루는 구성요소와는 관계가 없다.
            System.out.println(size + "," + temp);
            if (size == temp) { // 삽입위치의 인덱스와 크기가 같으면(결국,마지막이 삽입위치라는 얘기임) 크기 1늘림.
                size++;
            }
        }


    }
}