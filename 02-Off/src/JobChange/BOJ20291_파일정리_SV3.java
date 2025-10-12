package JobChange;

import java.io.*;
import java.util.*;


public class BOJ20291_파일정리_SV3 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        TreeMap<String, Integer> map = new TreeMap<>(
//                (o1, o2) ->{
//                    return o2.compareTo(o1);
//                }
        );

        int N = Integer.parseInt(br.readLine());

        //확장자 별로 몇개씩
        //확장자는 사전순
        while(N-- > 0){
            StringTokenizer st = new StringTokenizer(br.readLine(), ".");
            st.nextToken();

            String exe = st.nextToken();
            map.put(exe, map.getOrDefault(exe, 0) + 1);
        }

        StringBuilder sb = new StringBuilder();
        for(String key : map.keySet()){
            sb.append(key + " " + map.get(key)).append("\n");
        }

        System.out.println(sb);
    }
}
