"""
작성자: 이지은
문제: 입력은 항상 네 자리 소수만(1000 이상) 주어진다고 가정하자. 주어진 두 소수 A에서 B로 바꾸는 과정에서도 항상 네 자리 소수임을 유지해야 하고,
     ‘네 자리 수’라 하였기 때문에 0039 와 같은 1000 미만의 비밀번호는 허용되지 않는다.
문제해결: '에라토스테네스의 체' 사용, bfs 사용
작성일: 2023-05-09
"""

from collections import deque

#소수구하기 - 에라토스테네스의 체
def checkPrime(n):
    if n < 2:
        return False

    for i in range(2, int(n**0.5) + 1): # 소수의 배수 체크
        if n%i == 0: #나누어떨어질 때
            return False
    return True #소수일 떄만

def bfs(a, b):
    queue = deque()
    queue.append([a, 0])
    isVisited = set()
    isVisited.add(a)

    while queue:
        num, cnt = queue.popleft()

        if num == b: #종료조건
            return cnt #소수 사이의 변환에 필요한 최소 회수

        for i in range(4):
            for j in range(10):
                if i == 0 and j == 0: continue

                num_list = list(str(num))
                num_list[i] = str(j) #해당 자릿수에서 업
                next_num = int("".join(num_list))

                if checkPrime(next_num) and next_num not in isVisited: #소수체크 및 방문체크
                    queue.append([next_num, cnt + 1]) #소수와 카운트를 큐에 삽입
                    isVisited.add(next_num) #방문체크

t = int(input())

for i in range(t):
    a, b = map(int, input().split())
    answer = bfs(a,b)
    print(answer if answer != None else "Impossible") #값이 존재하지 않으면 impossible
