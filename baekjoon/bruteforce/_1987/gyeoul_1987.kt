package baekjoon.bruteforce._1987

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

val dy = intArrayOf(1, 0, -1, 0)
val dx = intArrayOf(0, 1, 0, -1)
var res = 1
lateinit var arr: Array<IntArray>

fun run(step: HashSet<Int>, count: Int, now: Pair<Int, Int>) {
    if (now.first !in arr.indices || now.second !in arr[now.first].indices || step.contains(arr[now.first][now.second])) {
        if (count > res) res = count
        return
    }
    step.add(arr[now.first][now.second])
    for (i in 0..3) {
        val nexty = dy[i] + now.first
        val nextx = dx[i] + now.second
        run(step, count + 1, Pair(nexty, nextx))
    }
    step.remove(arr[now.first][now.second])
}

fun main() {
    val bf = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val step = HashSet<Int>()
    val st = StringTokenizer(bf.readLine())
    val y = st.nextToken().toInt()
    val x = st.nextToken().toInt()
    arr = Array(y) { IntArray(x) }
    for (i in 0 until y) {
        val now = bf.readLine().toCharArray()
        for (j in 0 until x) {
            arr[i][j] = now[j] - 'A'
        }
    }
    run(step, 0, Pair(0, 0))
    bw.write("$res")
    bw.flush()
}