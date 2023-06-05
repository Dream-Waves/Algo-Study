package baekjoon.bfs._14395;

/*
14395. Gold 5 - 4연산

        시간 제한       메모리 제한      제출      정답      맞힌 사람       정답 비율
        2초            512MB          7111     2231      1562          28.256%


        문제
            정수 s가 주어진다. 정수 s의 값을 t로 바꾸는 최소 연산 횟수를 구하는 프로그램을 작성하시오.

            사용할 수 있는 연산은 아래와 같다.
                s = s + s; (출력: +)
                s = s - s; (출력: -)
                s = s * s; (출력: *)
                s = s / s; (출력: /) (s가 0이 아닐때만 사용 가능)


        입력
            첫째 줄에 s와 t가 주어진다. (1 ≤ s, t ≤ 109)


        출력
            첫째 줄에 정수 s를 t로 바꾸는 방법을 출력한다. s와 t가 같은 경우에는 0을, 바꿀 수 없는 경우에는 -1을 출력한다. 가능한 방법이 여러 가지라면, 사전 순으로 앞서는 것을 출력한다.
            연산의 아스키 코드 순서는 '*', '+', '-', '/' 이다.


        예제 입력 1
            7 392
        예제 출력 1
            +*+

        예제 입력 2
            7 256
        예제 출력 2
            /+***

        예제 입력 3
            4 256
        예제 출력 3
            **

        예제 입력 4
            7 7
        예제 출력 4
            0

        예제 입력 5
            7 9
        예제 출력 5
            -1

        예제 입력 6
            10 1
        예제 출력 6
            /


        알고리즘 분류
            그래프 이론
            그래프 탐색
            너비 우선 탐색
*/


// 메모리 : 14388KB
// 시간 : 132ms
// 코드 길이 : 2767B
// 정답

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.StringTokenizer;

public class C0012S_14395 {
    static long s;
    static long t;
    static String[] operators = {"*", "+", "/"}; // 아스키 코드 순서대로 연산자를 저장하는 배열

    public static void calculate() {
        HashSet<Long> set = new HashSet<>(); // 중복된 숫자를 체크할 Set
        Queue<Object[]> que = new ArrayDeque<>(); // {연산의 결과 숫자, 해당 숫자까지 연산했던 연산자 문자열} Object 배열을 저장하는 Queue
        que.add(new Object[] {s, ""});

        while (!que.isEmpty()) {
            Object[] now = que.poll();
            long nowValue = (long) now[0];
            String nowOperators = (String) now[1];

            if (nowValue == t) { // 목표 숫자인 t와 값이 같아지면 지금까지 연산했던 연산자 출력
                System.out.println(nowOperators);
                return;
            }

            long nextValue = 0; // 연산 후의 숫자
            for (int o = 0; o < 3; o++) {
                switch (o) { // 아스키 코드 순서대로 연산
                    case 0:
                        nextValue = nowValue * nowValue;
                        break;
                    case 1:
                        nextValue = nowValue + nowValue;
                        break;
                    case 2:
                        if (nowValue != 0) {
                            nextValue = nowValue / nowValue;
                        }
                        break;
                }

                if (!set.contains(nextValue) && nextValue <= t) { // 연산 후의 숫자가 중복된 숫자가 아니고 목표 숫자인 t보다 값이 작거나 같으면
                    set.add(nextValue); // Set에 삽입
                    que.add(new Object[] {nextValue, nowOperators + operators[o]}); // Queue에 삽입
                }
            }
        }

        System.out.println(-1); // 정수 s를 t로 바꿀 수 없는 경우 -1 출력
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(bf.readLine());

        s = Integer.parseInt(token.nextToken());
        t = Integer.parseInt(token.nextToken());

        if (s == t) { // s와 t가 같을 경우 0 출력
            System.out.println(0);
        }
        else if (t == 1) { // t가 1일 경우, / 연산이 가장 빠르다.
            System.out.println("/");
        }
        else {
            calculate();
        }
    }
}
