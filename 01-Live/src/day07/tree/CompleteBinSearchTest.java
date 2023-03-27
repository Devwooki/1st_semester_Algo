package day07.tree;

import java.io.*;
import java.util.*;

public class CompleteBinSearchTest {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int size = 9;
		CompleteBinSearch<Character> tree = new CompleteBinSearch<>(size);
		
		for(int i = 0 ; i < size ; ++i) {
			tree.add((char) (65+i));
			
		}
		System.out.println(tree);
//		tree.bfs();
		
		tree.dfsByPreOrder();
		//tree.dfsByInOrder();
		//tree.dfsByPostOrder();
		tree.dfs();
		}
}
