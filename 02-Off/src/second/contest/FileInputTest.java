package second.contest;

import java.io.*;
import java.util.StringTokenizer;

public class FileInputTest {
    public static void main(String[] args) throws IOException {
        System.out.println("Current directory: " + System.getProperty("user.dir"));
        try{
            BufferedReader br = new BufferedReader(new FileReader("/Users/wook/SSAFY/SSAFY_workspace/1st_semester_Algo/02-Off/test.txt"));
            String line;
            while((line = br.readLine()) != null){
                String[] arr = line.split("/");

                int sum = 0;
                for (int i = 0; i < 3; i++) {
                    sum += Integer.parseInt(arr[i+1]);
                }

                System.out.println(arr[0] + "총점 : " + sum + ", 평균 : " + Math.round((sum/3.0)/100.0));


            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
