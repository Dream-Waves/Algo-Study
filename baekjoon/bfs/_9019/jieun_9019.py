"""
작성자: 이지은
문제: 네 개의 명령어 D, S, L, R 을 이용하는 간단한 계산기가 있다. 이 계산기에는 레지스터가 하나 있는데, 이 레지스터에는 0 이상 10,000 미만의 십진수를 저장할 수 있다.
     1. D: D 는 n을 두 배로 바꾼다. 결과 값이 9999 보다 큰 경우에는 10000 으로 나눈 나머지를 취한다. 그 결과 값(2n mod 10000)을 레지스터에 저장한다.
     2. S: S 는 n에서 1 을 뺀 결과 n-1을 레지스터에 저장한다. n이 0 이라면 9999 가 대신 레지스터에 저장된다.
     3. L: L 은 n의 각 자릿수를 왼편으로 회전시켜 그 결과를 레지스터에 저장한다. 이 연산이 끝나면 레지스터에 저장된 네 자릿수는 왼편부터 d2, d3, d4, d1이 된다.
     4. R: R 은 n의 각 자릿수를 오른편으로 회전시켜 그 결과를 레지스터에 저장한다. 이 연산이 끝나면 레지스터에 저장된 네 자릿수는 왼편부터 d4, d1, d2, d3이 된다.
문제 해결: BFS, 효율성이 떨어짐
작성일: 2023-05-06
"""

from collections import deque

def bfs(x, y):
    q = deque([(x, "")])
    isVisited = set() #방문한 곳은 계산하지않기 위해 set을 사용

    while q:
        num, oper = q.popleft()
        if num == y:
            return oper #연산자 반환

        if num not in isVisited:  # 방문하지 않은 숫자일 경우
            isVisited.add(num)  # 방문 처리
            q.append((num * 2 % 10000, oper + "D"))  # D 연산
            q.append(((num - 1) % 10000, oper + "S"))  # S 연산
            q.append((num % 1000 * 10 + num // 1000, oper + "L"))  # L 연산
            q.append((num % 10 * 1000 + num // 10, oper + "R"))  # R 연산

t = int(input())

for i in range(t):
    a, b = map(int, input().split()) #a: 레지스터의 초기 값, B: 최종 값
    print(bfs(a, b))