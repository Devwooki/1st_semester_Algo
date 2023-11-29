package TOSSNEXT;
/**
 원래 알고 있던 친구 -> 5원
 새롭게 알게된 친구-> 10원
 */

import java.io.*;
import java.util.*;

public class PRO2 {
    public static void main(String[] args) throws IOException{
        ArrayList<Integer>[] list = new ArrayList[5];
        for(int i = 0 ; i < 5 ; ++i){
            list[i] = new java.util.ArrayList<>();
        }

        list[0].add(1);

        System.out.println(list[1].get(0));
    }

}


