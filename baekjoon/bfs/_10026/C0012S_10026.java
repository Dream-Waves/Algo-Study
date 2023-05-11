package baekjoon.bfs._10026;

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
