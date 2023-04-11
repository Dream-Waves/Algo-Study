from collections import defaultdict
N = int(input())
li = list(map(int, input().split()))
tp = []
visited = defaultdict(bool)
def dfs(level):
    if level == N:
        visited[sum(tp)]=True
        return
    tp.append(li[level])
    dfs(level+1)
    tp.pop()
    dfs(level+1)
dfs(0)
i=0
while True:
    i+=1
    if not visited[i]:
        print(i)
        exit(0)