package baekjoon.bfs._16928

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main() {
    val bf = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val q: Queue<Int> = ArrayDeque()
    val ans = IntArray(101) { 0 }
    val arr = IntArray(101)
    repeat(101) { arr[it] = it }

    val (a: Int, b: Int) = bf.readLine().split(" ").map { it.toInt() }
    repeat(a + b) {
        var (from: Int, to: Int) = bf.readLine().split(" ").map { it.toInt() }
        while (to != arr[to]) to = arr[to]
        arr[from] = to
    }
    q.add(1)
    while (q.isNotEmpty()) {
        val now = q.poll()
        for (i in 1..6) {
            if (now + i > 100) continue
            val next = arr[now + i]
            if (ans[next] != 0) continue
            ans[next] = ans[now] + 1
            q.add(next)
        }
    }
    bw.write("${ans[100]}")
    bw.flush()
}
