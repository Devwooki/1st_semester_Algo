package JobChange.binSearchTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class BOJ7662_sec {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int tc = 0 ; tc < T ; ++tc){
            TreeMap<Integer, Integer> q = new TreeMap<>();
            int k = Integer.parseInt(br.readLine());

            while(k-- > 0){
                String[] str = br.readLine().split(" ");

                int num = Integer.parseInt(str[1]);
                switch(str[0]){
                    case "I" :
                        q.put(num ,q.getOrDefault(num, 0) + 1);

                        break;
                    case "D" :
                        if(q.size() == 0) continue;

                        int key = num == 1 ? q.lastKey() : q.firstKey();

                        if(q.get(key) == 1) q.remove(key);
                        else q.put(key, q.get(key) -1);

                        break;
                }
            }

           if(q.isEmpty() )
               sb.append("EMPTY\n");
           else
               sb.append(q.lastKey() + " " + q.firstKey() + "\n");
        }
        System.out.println(sb.toString());
    }
}
