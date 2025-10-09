package JobChange;

import java.io.*;
import java.util.*;

public class BOJ1991_트리순회 {
    static int N;
    static Node[] binTree;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        binTree = new Node[N+1];

        for(int i = 0 ; i < N ; ++i){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            char root = st.nextToken().charAt(0);
            char left = st.nextToken().charAt(0);
            char right = st.nextToken().charAt(0);

//            System.out.println((int)root + ", " + (int)left + ", " + (int)right);

            //부모가 없는 경우, 부모부터 생성
            if(binTree[root - 'A'] == null){
                binTree[root - 'A'] = new Node(root);
            }
            //왼쪽 자식이 있는 경우
            if(left != '.') {
                binTree[left - 'A'] = new Node(left); //left값이 루트인 트리 생성
                binTree[root - 'A'].left = binTree[left - 'A']; //루트 left에 연결
            }
            //오른쪽 자식이 없는 경우
            if(right != '.') {
                binTree[right - 'A'] = new Node(right);
                binTree[root - 'A'].right = binTree[right - 'A'];
            }
        }

        preOrder(binTree[0]);
        sb.append("\n");
        inOrder(binTree[0]);
        sb.append("\n");
        postOrder(binTree[0]);

        System.out.println(sb.toString());


    }

    private static void preOrder(Node root){
        if(root == null) return;
        sb.append(root.val);
        preOrder(root.left);
        preOrder(root.right);
    }
    private static void inOrder(Node root){
        if(root == null) return;

        inOrder(root.left);
        sb.append(root.val);
        inOrder(root.right);

    }
    private static void postOrder(Node root){
        if(root == null) return;

        postOrder(root.left);
        postOrder(root.right);
        sb.append(root.val);

    }

    private static class Node{
        char val;
        Node left;
        Node right;

        public Node(char val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }
}


/*
            A
        B       C
    D         E      F
                        G


 */