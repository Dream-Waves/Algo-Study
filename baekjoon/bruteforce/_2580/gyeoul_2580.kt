package baekjoon.bruteforce._2580

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.collections.ArrayList

/*
0 3 5 4 6 9 2 7 8
7 8 2 1 0 5 6 0 9
0 6 0 2 7 8 1 3 5
3 2 1 0 4 6 8 9 7
8 0 4 9 1 3 5 0 6
5 9 6 8 2 0 4 1 3
9 1 7 6 5 2 0 8 0
6 0 3 7 0 1 9 5 2
2 5 8 3 9 4 7 6 0

메모 배열 생성시
제작해야할 함수
1. 채워진 정보를 바탕으로 메모 수정
2. 메모에서 값을 체크하고 1,2 개일때 각각 로직 작성
3. 2일경우 백트래킹 고려
 */
fun get3by3(a: Int, b: Int, arr: ArrayList<ArrayList<Int>>): TreeSet<Int> {
    val res = TreeSet<Int>()
    for (i in 0..2) {
        for (j in 0..2) {
            if (arr[i + (3 * (a / 3))][j + (3 * (b / 3))] != 0)
                res.add(arr[i + (3 * (a / 3))][j + (3 * (b / 3))])
        }
    }
    return res
}

fun main() {
    val bf = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val q: Queue<Pair<Int, Int>> = LinkedList()

    val arr = ArrayList<ArrayList<Int>>()

    for (i in 1..9) {
        val st = StringTokenizer(bf.readLine())
        arr.add(ArrayList())
        for (j in 1..9) {
            val n = st.nextToken().toInt()
            if (n == 0) q.add(Pair(i - 1, j - 1))
            arr.last().add(n)
        }
    }
    while (q.isNotEmpty()) {
        val now = q.remove()
        val has = TreeSet<Int>()
        val nums = (1..9).toList() as ArrayList<Int>
        has.addAll(get3by3(now.first, now.second, arr))
        for (i in 0..8) {
            has.add(arr[now.first][i])
            has.add(arr[i][now.second])
        }
        nums.removeAll(has)
        if (nums.size==1)arr[now.first][now.second] = nums[0]
        else q.offer(Pair(now.first, now.second))
    }

    for (v in arr) {
        for (w in v){
            bw.write("$w ")
        }
        bw.write("\n")
    }
    bw.flush()
}