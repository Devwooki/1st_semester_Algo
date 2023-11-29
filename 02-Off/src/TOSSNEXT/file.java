package TOSSNEXT;
import java.io.*;
import java.util.*;

public class file {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        while(true){
            String input =br.readLine();
            if(input.equals("exit")) break;
            String[] temp = input.split(",");

            for(int i = 2; i < temp.length ; i+=2){
                sb.append(temp[1] +",").append(temp[i] + "," + temp[i+1]).append("\n");
            }
        }

        System.out.println("=============================");
        System.out.println(sb);


    }
}
