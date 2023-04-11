package baekjoon.bruteforce._1182;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 전상희, 2023-04-11
 * Bruteforce, Backtracking, Subset
 * */
public class ybwi0912_1182 {
    static int N;
    static int S;
    static int count = 0;
    static int[] arr;
    static boolean[] isSelected;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(token.nextToken());
        S = Integer.parseInt(token.nextToken());
        arr = new int[N];
        isSelected = new boolean[N];

        token = new StringTokenizer(bf.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(token.nextToken());
        } // input

        subset(0);

        if(S==0) count--;

        System.out.println(count);
        // output
    }

    static void subset(int num) {
        // 종료 조건
        if(num == N) {
            int sum = 0;
            for(int i=0; i<N; i++) {
                if(isSelected[i]) sum+=arr[i];
                // isSelected = true인 값만 출력
            }

            if(sum==S) count++;

            return;
        }

        // 분할
        // 선택 ㅇ
        isSelected[num] = true;
        subset(num+1); // 다음 원소를 향해.. 넘겨줌

        // 선택 ㄴ
        isSelected[num] = false;
        subset(num+1);
    }
}
