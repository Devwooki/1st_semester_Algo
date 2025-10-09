package JobChange.BackTracking;

import java.io.*;
import java.util.*;


public class BOJ1174 {
    static int N;
    static int[] descNum = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
    static ArrayList<Long> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        //생각해보니 가장 큰 줄어드는 수는 9876543210순서
        //부분집합..? 해보니 역시나 -> 비트마시킹
        solution2();
        //solution(0,0);
        Collections.sort(list);
        //공집합 때문에 0이 두번 들어가서 i = 1부터
        list.remove(0);

        int size = list.size();
        System.out.println(Arrays.toString(list.toArray()));
        System.out.println(N > size ? -1 : list.get(N-1));
    }

    private static void solution(int idx, long sum) {

        //리스트에 숫자가 줄어드는 수가 없으면 추가한다.
        if (!list.contains(sum)) {
            list.add(sum);
        }


        if (idx >= 10) {
            return;
        }

        //지금 숫자에 작아지는 수를 더하는 케이스
        solution(idx + 1, (sum * 10) + descNum[idx]);
        //더하지 않는 케이스
        solution(idx + 1, sum);
        ;
    }

    private static void solution2() {
        for (int i = 0; i < (1 << 10); ++i) {
            long num = 0;
            for (int j = 0; j < 10; ++j) {
                //System.out.println(i + ", " + j + ", " + (i & ( 1 << j)));
                if ((i & (1 << j)) != 0)
                    num = num * 10 + descNum[j];
                //System.out.print(descNum[j] + " ");
            }
            //System.out.println();
            list.add(num);
        }
    }
}
