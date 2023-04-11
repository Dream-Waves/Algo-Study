package baekjoon.bruteforce._6603;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 전상희, 2023-04-11
 * Bruteforce, Backtracking, Combination
 * */
public class ybwi0912_6603 {
    static int[] arr;
    static int N; // 수의 개수
    static int k = 6; // N개 중 k개
    static int[] selection;
    static boolean[] isSelected;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            StringTokenizer token = new StringTokenizer(bf.readLine());
            N = Integer.parseInt(token.nextToken());
            if(N==0) break;

            arr = new int[N];
            selection = new int[k];
            isSelected = new boolean[N];

            for(int i=0; i<N; i++){
                arr[i] = Integer.parseInt(token.nextToken());
            } // input

            combination(0, 0);
            System.out.println();
        }
    }

    static void combination(int r, int start){
        if(r==k){
            for(int i=0; i<k; i++) {
                System.out.print(arr[selection[i]] + " ");
            }
            System.out.println();
            return;
        }

        for(int i=start; i<N; i++){
            if(isSelected[i]) continue;

            isSelected[i] = true;
            selection[r] = i;
            combination(r+1, i);
            isSelected[i] = false;
        }
    }
}
