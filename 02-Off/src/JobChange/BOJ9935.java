package JobChange;
import java.io.*;
import java.util.*;


public class BOJ9935 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        String boomStr = br.readLine();

//        while(true){
//            if(str.trim().length() == 0){
//                System.out.println("FRULA");
//                break;
//            }
//            if(str.indexOf(boomStr) == -1){
//                System.out.println(str);
//                break;
//            }else{
//                str = str.replaceAll(boomStr, "");
//            }
//        }

        //문자열길이가 100만이기 때문에 replaceAll은 메모리 초과
        // -> 스택을 활용해, 해당 문자열이 일치하면 바로 제거하는 형식으로 push, pop 수행
        Stack<Character> stck = new Stack<>();

        int strLen = str.length();
        int boomLen = boomStr.length();

        for(int i = 0 ; i < strLen ; ++i){
            char c = str.charAt(i);

            //무작정 문자열을 넣는다.
            //스택의 크기가 폭탄문자열의 길이보다 크거나 같으면
            //폭탄 문자열이 들어있는지 검증 수행
            stck.push(c);
            if(stck.size() >= boomLen){
                boolean flag = true;
                int stkSize = stck.size();

                for(int j = 0 ; j < boomLen ; ++j){
                    if(boomStr.charAt(j) != stck.get(stkSize - boomLen + j)){
                        flag = false;
                        break;
                    }
                }

                //stack
                if(flag){
                    for(int j = 0 ; j < boomLen ; ++j){
                        stck.pop();
                    }
                }
            }

        }

        if(stck.isEmpty()){
            System.out.println("FRULA");
        }else{
            StringBuilder sb = new StringBuilder();
            for(char c : stck){
                sb.append(c);
            }
            System.out.println(sb.toString());
        }
    }
}
