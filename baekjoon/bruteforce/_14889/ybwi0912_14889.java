package baekjoon.bruteforce._14889;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ybwi0912_14889 {
    static int N; // N은 무조건 짝수
    static int[][] arr;
    static int MIN = Integer.MAX_VALUE;
    static boolean[] isSelected;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        arr = new int[N][N];
        isSelected = new boolean[N];

        for(int i=0; i<N; i++){
            StringTokenizer token = new StringTokenizer(bf.readLine());
            for(int j=0; j<N; j++){
                arr[i][j] = Integer.parseInt(token.nextToken());
            }
        }
        // input

        permutation(0, 0);

        System.out.println(MIN);
        // output
    }

    static void permutation(int idx, int r){
        if(r==N/2){
            diff(); // 결정된 팀별로 점수 구한 뒤 최솟값에 반영
            return;
        }

        for(int i=idx; i<N; i++){
            if(isSelected[i]) continue;

            isSelected[i] = true;
            permutation(i+1, r+1);
            isSelected[i] = false;
        }
    }

    static void diff(){
        int teamA = 0, teamB = 0;

        for(int i=0; i<N-1; i++){
            for(int j=i+1; j<N; j++){ // 최대한 반복 줄이기 위한 인덱스 설정 ..
                if(isSelected[i]==true && isSelected[j]==true){
                    teamA = teamA + arr[i][j] + arr[j][i];
                }
                else if(isSelected[i]==false && isSelected[j]==false){
                    teamB = teamB + arr[i][j] + arr[j][i];
                }
            }
        }

        MIN = Math.min(Math.abs(teamA - teamB), MIN);
    }
}
