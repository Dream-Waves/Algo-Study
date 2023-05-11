package baekjoon.bfs._1963

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main() {
    data class num(val i: Int, val count: Int)

    val bf = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val n = bf.readLine().toInt()
    val q: Queue<num> = ArrayDeque()
    val arr = ArrayList<Pair<Int, Int>>()
    val set = TreeSet<Int>()

    var next: Int
    var ans: Int

    fun check(t: Int): Boolean {
        for (i in 3 until t) {
            if (t % i == 0) return false
        }
        return true
    }

    fun bfs(from: Int, to: Int) {
        set.clear()
        ans = -1
        q.add(num(from, 0))
        while (q.isNotEmpty()) {
            val now = q.poll()
            if (now.i == to) {
                q.clear()
                ans = now.count
                break
            }
            next = now.i - (now.i / 1000 % 10 * 1000)
            for (nn in 1..9) {
                val nnn = next + nn * 1000
                if (set.contains(nnn)) continue
                set.add(nnn)
                if (check(nnn)) q.add(num(nnn, now.count + 1))
            }
            next = now.i - (now.i / 100 % 10 * 100)
            for (nn in 0..9) {
                val nnn = next + nn * 100
                if (set.contains(nnn)) continue
                set.add(nnn)
                if (check(nnn)) q.add(num(nnn, now.count + 1))
            }
            next = now.i - (now.i / 10 % 10 * 10)
            for (nn in 0..9) {
                val nnn = next + nn * 10
                if (set.contains(nnn)) continue
                set.add(nnn)
                if (check(nnn)) q.add(num(nnn, now.count + 1))
            }
            next = now.i - (now.i / 1 % 10 * 1)
            for (nn in 1..10 step 2) {
                val nnn = next + nn * 1
                if (set.contains(nnn)) continue
                set.add(nnn)
                if (check(nnn)) q.add(num(nnn, now.count + 1))
            }
        }
        if (ans < 0)
            bw.write("Impossible\n")
        else
            bw.write("${ans}\n")
    }

    for (i in 1..n) {
        val (from, to) = bf.readLine().split(" ").map { it.toInt() }
        arr.add(Pair(from, to))
    }

    while (arr.isNotEmpty()) {
        val now = arr.removeFirst()
        bfs(now.first, now.second)
    }
    bw.flush()
}