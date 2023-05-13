package baekjoon.bfs._10026;

/*
10026. Gold 5 - 적록색약

        시간 제한       메모리 제한      제출      정답      맞힌 사람       정답 비율
        1초            128MB          50166    28826     22061         56.468%


        문제
            적록색약은 빨간색과 초록색의 차이를 거의 느끼지 못한다. 따라서, 적록색약인 사람이 보는 그림은 아닌 사람이 보는 그림과는 좀 다를 수 있다.
            크기가 N×N인 그리드의 각 칸에 R(빨강), G(초록), B(파랑) 중 하나를 색칠한 그림이 있다. 그림은 몇 개의 구역으로 나뉘어져 있는데, 구역은 같은 색으로 이루어져 있다. 또, 같은 색상이 상하좌우로 인접해 있는 경우에 두 글자는 같은 구역에 속한다. (색상의 차이를 거의 느끼지 못하는 경우도 같은 색상이라 한다)

            예를 들어, 그림이 아래와 같은 경우에
                RRRBB
                GGBBB
                BBBRR
                BBRRR
                RRRRR
                적록색약이 아닌 사람이 봤을 때 구역의 수는 총 4개이다. (빨강 2, 파랑 1, 초록 1) 하지만, 적록색약인 사람은 구역을 3개 볼 수 있다. (빨강-초록 2, 파랑 1)

            그림이 입력으로 주어졌을 때, 적록색약인 사람이 봤을 때와 아닌 사람이 봤을 때 구역의 수를 구하는 프로그램을 작성하시오.


        입력
            첫째 줄에 N이 주어진다. (1 ≤ N ≤ 100)
            둘째 줄부터 N개 줄에는 그림이 주어진다.


        출력
            적록색약이 아닌 사람이 봤을 때의 구역의 개수와 적록색약인 사람이 봤을 때의 구역의 수를 공백으로 구분해 출력한다.


        예제 입력 1
            5
            RRRBB
            GGBBB
            BBBRR
            BBRRR
            RRRRR
        예제 출력 1
            4 3


        알고리즘 분류
            그래프 이론
            그래프 탐색
            너비 우선 탐색
            깊이 우선 탐색
*/


// 메모리 : 15400KB
// 시간 : 164ms
// 코드 길이 : 2420B
// 정답

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class C0012S_10026 {
    static int N; // 그리드의 크기
    static char colorList[][]; // 그림의 색을 나타내는 문자를 담는 배열
    static boolean isVisited[][]; // 해당 좌표의 방문 여부
    static int dx[] = {-1, 1, 0, 0};
    static int dy[] = {0, 0, -1, 1};

    public static boolean check(int x, int y) {
        if ((x >= 0 && x < N) && (y >= 0 && y < N)) { // 유효한 좌표라면
            if (!isVisited[x][y]) { // 해당 좌표를 방문하지 않았다면
                return true;
            }
        }

        return false;
    }

    public static void findColor(int r, int c) {
        Queue<Integer[]> que = new ArrayDeque<>();
        que.add(new Integer[] {r, c});
        isVisited[r][c] = true;

        while (!que.isEmpty()) {
            Integer[] now = que.poll();

            for (int d = 0; d < 4; d++) {
                int nx = now[0] + dx[d];
                int ny = now[1] + dy[d];

                if (check(nx, ny)) { // 해당 좌표가 유효하고 방문하지 않았다면
                    if (colorList[now[0]][now[1]] == colorList[nx][ny]) { // 현재의 좌표의 색과 다음 좌표와 색이 같다면
                        isVisited[nx][ny] = true; // 해당 좌표 방문 처리
                        que.add(new Integer[] {nx, ny});
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token;

        N = Integer.parseInt(bf.readLine());

        colorList = new char[N][N];
        for (int i = 0; i < N; i++) {
            token = new StringTokenizer(bf.readLine());
            String colors = token.nextToken();

            for (int j = 0; j < N; j++) {
                colorList[i][j] = colors.charAt(j);
            }
        }

        int count[] = {0, 0}; // 구역의 수
        for (int p = 0; p < 2; p++) { // 적록색약이 아닌 사람과 적록색약인 사람 순서로 구역 찾기
            isVisited = new boolean[N][N];

            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (!isVisited[r][c]) { // 방문하지 않은 좌표라면
                        findColor(r, c);
                        count[p] += 1;
                    }

                    if (colorList[r][c] == 'R') { // 적록색약인 사람이 빨간색을 봤을 경우
                        colorList[r][c] = 'G'; // 빨간색을 초록색으로 변경
                    }
                }
            }

            System.out.print(count[p] + " ");
        }
    }
}
