package programmers;

import java.util.*;
import java.io.*;

public class 오픈채팅방 {
    static String[] record = {
            "Enter uid1234 Muzi",
            "Enter uid4567 Prodo",
            "Leave uid1234",
            "Enter uid1234 Prodo",
            "Change uid4567 Ryan"};
    public static void main(String[] args) throws IOException {
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<String, String> map = new HashMap<>();
        ArrayList<String> logs = new ArrayList<>();


        //아이디를 최신화한다.
        for(String log : record){
            String[] temp = log.split(" ");
            if(temp.length == 2) continue;

            map.put(temp[1], temp[2]);
        }

        for(String log : record){
            String[] temp = log.split(" ");
            switch(temp[0]){
                case "Enter" :
                    logs.add(map.get(temp[1]) + "님이 들어왔습니다.");
                    break;
                case "Leave" :
                    logs.add(map.get(temp[1]) + "님이 나갔습니다.");
                    break;
            }
        }

        String[] answer = logs.toArray(new String[0]);
    }
}

/*
기존풀이 한계 -> 로그를 생성한 뒤 업데이트를 수행
-> 오래걸릴 수 밖에 없음


        Map<String, String> map = new HashMap<>();
        ArrayList<String[]> log = new ArrayList<>();

        for(int i = 0 ;  i < record.length; ++i){
            String[] temp = record[i].split(" ");
            switch(temp[0]){
                case "Enter" :
                    map.put(temp[1], temp[2]);

                    //로그를 남길 때 닉네임이 아닌 아이디로 저장한다.
                    log.add(new String[]{temp[1], "님이 나갔습니다."});
                    break;
                case "Leave" :
                    log.add(new String[]{temp[1], "님이 나갔습니다."});
                    break;
                case "Change" :
                    map.put(temp[1], temp[2]);
                    break;
            }
        }

        //로그가 작성되면 update된 값으로 덮어씌운다.
        //이때 uid를 닉네임으로 변경
        for(int i = 0 ; i < log.size() ; ++i){
            String[] uid = log.get(i);

            for(String key : map.keySet()){
                if(key.equals(uid[0])) {
                    log.set(i, new String[]{map.get(key), uid[1]});
                }
            }

        }
        String[] answer = new String[log.size()];
        for(int i = 0 ; i < log.size(); ++i){
            answer[i] = log.get(i)[0] + log.get(i)[1];
        }
 */
