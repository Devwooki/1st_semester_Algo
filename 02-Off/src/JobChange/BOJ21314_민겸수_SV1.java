package JobChange;

import java.io.*;
import java.util.*;


public class BOJ21314_민겸수_SV1 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        StringBuilder max = new StringBuilder();

        String str = br.readLine();
        int size = str.length();

        System.out.println(getMax(size, str));
        System.out.println(getMin(size, str));
    }
    
    private static String getMin(int size, String str){
        StringBuilder min = new StringBuilder();
        int mCnt = 0;
        for(int i = 0 ; i < size; i++) {
            if (str.charAt(i) == 'M') {
                mCnt = i;
                while (mCnt < size && str.charAt(mCnt) == 'M') {
                    mCnt++;
                }

                //최소 구하기
                min.append("1");
                for (int j = i + 1; j < mCnt; ++j) min.append("0"); //맨앞에 1을 붙였으므로 i + 1 해준다.

                i = mCnt - 1; //m의 연속된 수만큼 스킵, mCnt 중 1개는 무조건 1을 표햔하기 때문세 스킵

            } else {
                //최소 구하기
                min.append("5");
            }
        }
        return min.toString();
    }

    private static String getMax(int size, String str){
        StringBuilder max = new StringBuilder();
        int mCnt = 0;
        for(int i = 0 ; i < size; i++){
            if(str.charAt(i) == 'M'){
                mCnt = i;
                while( mCnt < size && str.charAt(mCnt) == 'M'){
                    mCnt++;
                }
                if(mCnt == size){
                    max.append("1".repeat(mCnt - i));
                }else{
                    //최소 구하기
                    max.append("5").append("0".repeat(mCnt - i));
                    //for( int j = i; j < mCnt ; ++ j  ) max.append("0"); //맨앞에 1을 붙였으므로 i + 1 해준다.
                }

                i = mCnt; //m의 연속된 수 + 맨마지막 K 인덱스까지 스킵

            }else {
                //최소 구하기
                max.append("5");
            }
        }
        return max.toString();
    }


    /*
    * M끼리 조합하면 1, 10, 100.. 으로 커진다.
    * 반면 _K로 조합하면 5, 50, 500으로 가장 큰 값이다
    *
    *
    * 가장 작은 값음 _M끼리 묶는 것이고
    * 가장 큰 값은 _MK끼리 묶는것이다
    * MKKMMK
    *   -> m끼리      M, K, K, MM, K -> 1 5 5 10 5
    *   -> MK 끼리    M  K, K, MMK -> 50 5 500
    *
    * */
}
