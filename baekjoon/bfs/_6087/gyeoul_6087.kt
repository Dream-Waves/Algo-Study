package baekjoon.bfs._6087

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.collections.ArrayList

fun main() {
    data class Loc(val y: Int, val x: Int)

    val bf = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val (w, h) = bf.readLine().split(" ").map { it.toInt() }
    val arr = ArrayList<ArrayList<Int>>()
    val q: Queue<Loc> = ArrayDeque()
    val target = Array(2) { IntArray(2) }
    var idx = 0
    repeat(h) { arr.add(bf.readLine().toList().map { it.code - '.'.code } as ArrayList<Int>) }
    repeat(2) {
        for (i in arr.indices) {
            for (j in arr[i].indices) {
                if (arr[i][j] == 0) continue
                if (arr[i][j] > 0) {
                    target[idx][0] = i
                    target[idx++][1] = j
                    arr[i][j] = 0
                }
            }
        }
    }
    q.add(Loc(target[0][0], target[0][1]))
    while (q.isNotEmpty()) {
        val now = q.poll()
        val count = arr[now.y][now.x] + 1
        for (a in arrayOf(now.x - 1 downTo 0, now.x + 1 until w)) {
            for (i in a) {
                if (arr[now.y][i] < 0) break
                if (arr[now.y][i] == 0 || arr[now.y][i] > count) {
                    arr[now.y][i] = count
                    q.add(Loc(now.y, i))
                }
            }
        }
        for (a in arrayOf(now.y - 1 downTo 0, now.y + 1 until h)) {
            for (i in a) {
                if (arr[i][now.x] < 0) break
                if (arr[i][now.x] == 0 || arr[i][now.x] > count) {
                    arr[i][now.x] = count
                    q.add(Loc(i, now.x))
                }
            }
        }
    }
    bw.write("${arr[target[1][0]][target[1][1]] - 1}\n")
    bw.flush()
}