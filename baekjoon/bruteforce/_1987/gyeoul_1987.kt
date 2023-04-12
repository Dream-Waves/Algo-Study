package baekjoon.bruteforce._1987

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

val dy = intArrayOf(1, 0, -1, 0)
val dx = intArrayOf(0, 1, 0, -1)
var res = 1
lateinit var isVisited: Array<BooleanArray>
fun run(arr: Array<IntArray>, step: HashSet<Int>, count: Int, next: Pair<Int, Int>) {
    if (step.contains(arr[next.first][next.second])) {
//        if (count >= 26) {
//            res = 26
//            return
//        }
//        println(step)
        if (count > res) res = count
        return
    }
    step.add(arr[next.first][next.second])
    for (i in 0..3) {
        val newy = dy[i] + next.first
        val newx = dx[i] + next.second
        if (newy !in 0 until arr.size) continue
        if (newx !in 0 until arr[newy].size) continue
        if (isVisited[newy][newx]) continue
        isVisited[newy][newx] = true
        run(arr, step, count + 1, Pair(newy, newx))
        isVisited[newy][newx] = false
    }
    step.remove(arr[next.first][next.second])
}

fun main() {
    val bf = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val step = HashSet<Int>()
    val st = StringTokenizer(bf.readLine())
    val y = st.nextToken().toInt()
    val x = st.nextToken().toInt()
    val arr = Array(y) { IntArray(x) }//ArrayList<ArrayList<Int>>()
//    val stack = Stack<>
//    val ss = HashSet<Int>()
//    ss.contains()

    isVisited = Array(y) { BooleanArray(x) }

    for (i in 0 until y) {
        val now = bf.readLine().toCharArray()
//        arr.add(ArrayList())
        for (j in 0 until x) {
//            arr.last().add(now[j] - 'A')
            arr[i][j] = now[j] - 'A'
        }
    }

    run(arr, step, 0, Pair(0, 0))

//    for (v in arr) {
//        for (w in v) {
//            bw.write("${w / 10}${w % 10} ")
//        }
//        bw.write("\n")
//    }
//    if (res < 1) res = 1
    bw.write("$res")
    bw.flush()
}