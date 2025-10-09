package JobChange;

import java.util.*;
import java.io.*;
public class BOJ33574 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader( new InputStreamReader(System.in));

        int Q = Integer.parseInt(br.readLine());
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0 ; i < Q ; ++i){
            int[] cmd = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            if(cmd[0] == 1){
                list.sort(cmd[1] == 1 ? null : Comparator.reverseOrder());
            }else{
                if(cmd[1] == list.size()) list.add(cmd[2]);
                else list.add(cmd[1], cmd[2]);
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(list.size()+"\n");

        for(int ele : list){
            sb.append(ele+ " ");
        }

        System.out.println(sb.toString());

    }
}
