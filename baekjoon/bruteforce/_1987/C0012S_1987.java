package baekjoon.bruteforce._1987;

/*
1987. Gold 4 - 알파벳

    시간 제한       메모리 제한      제출          정답          맞힌 사람       정답 비율
    2초            256MB          97065        31550        19137          29.187%


    문제
        세로 R칸, 가로 C칸으로 된 표 모양의 보드가 있다. 보드의 각 칸에는 대문자 알파벳이 하나씩 적혀 있고, 좌측 상단 칸 (1행 1열) 에는 말이 놓여 있다.
        말은 상하좌우로 인접한 네 칸 중의 한 칸으로 이동할 수 있는데, 새로 이동한 칸에 적혀 있는 알파벳은 지금까지 지나온 모든 칸에 적혀 있는 알파벳과는 달라야 한다. 즉, 같은 알파벳이 적힌 칸을 두 번 지날 수 없다.
        좌측 상단에서 시작해서, 말이 최대한 몇 칸을 지날 수 있는지를 구하는 프로그램을 작성하시오. 말이 지나는 칸은 좌측 상단의 칸도 포함된다.


    입력
        첫째 줄에 R과 C가 빈칸을 사이에 두고 주어진다. (1 ≤ R,C ≤ 20) 둘째 줄부터 R개의 줄에 걸쳐서 보드에 적혀 있는 C개의 대문자 알파벳들이 빈칸 없이 주어진다.


    출력
        첫째 줄에 말이 지날 수 있는 최대의 칸 수를 출력한다.


    예제 입력 1
        2 4
        CAAB
        ADCB
    예제 출력 1
        3

    예제 입력 2
        3 6
        HFDFFB
        AJHGDH
        DGAGEH
    예제 출력 2
        6

    예제 입력 3
        5 5
        IEFCJ
        FHFKC
        FFALF
        HFGCF
        HMCHH
    예제 출력 3
        10


    알고리즘 분류
        그래프 이론
        그래프 탐색
        깊이 우선 탐색
        백트래킹
*/


// 메모리 : 315408KB
// 시간 : 3308ms
// 코드 길이 : 2466B
// 정답

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class C0012S_1987 {
    static int R; // 세로 칸 수 R
    static int C; // 가로 칸 수 C
    static String board[][]; // 보드의 각 칸에 있는 대문자 알파벳을 저장할 배열
    static int dx[] = {-1, 1, 0, 0}; // 상, 하, 좌, 우
    static int dy[] = {0, 0, -1, 1}; // 상, 하, 좌, 우
    static boolean isVisited[][]; // 해당 보드 칸의 방문 여부
    static Set<String> set = new HashSet<>(); // 동일한 알파벳인지 체크하기 위한 Set
    static int count = 1; // 칸 이동 횟수

    public static boolean check(int x, int y) { // 보드의 범위에 있는지 체크
        if (x < 0 || x >= R || y < 0 || y >= C) {
            return false;
        }

        return true;
    }

    public static void passBoard(int x, int y) {
        isVisited[x][y] = true;
        set.add(board[x][y]);

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (check(nx, ny)) { // 이동 가능한 보드 칸일 경우
                if (!isVisited[nx][ny] && !set.contains(board[nx][ny])) { // 해당 보드 칸을 방문하지 않았고, 해당 알파벳을 지난 적이 없을 경우
                    set.add(board[nx][ny]);
                    isVisited[nx][ny] = true;

                    passBoard(nx, ny);
                    count = Math.max(count, set.size()); // 칸 이동 횟수의 최댓값

                    isVisited[nx][ny] = false; // 보드 칸 방문 체크 해제
                    set.remove(board[nx][ny]); // 알파벳 방문 체크 해제
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(bf.readLine());

        R = Integer.parseInt(token.nextToken());
        C = Integer.parseInt(token.nextToken());

        board = new String[R][C];
        for (int i = 0; i < R; i++) {
            String row = bf.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = String.valueOf(row.charAt(j));
            }
        }

        isVisited = new boolean[R][C];
        passBoard(0, 0);
        System.out.println(count);
    }
}
