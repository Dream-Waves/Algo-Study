package baekjoon.bruteforce._2580

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun calculate(arr: ArrayList<ArrayList<Int>>): Boolean {
    for (i in 0..8) {
        for (j in 0..8) {
            if (arr[i][j] != 0) continue
            for (k in 1..9) {
                if (!isAvailable(arr, Pair(i, j), k)) continue
                arr[i][j] = k
                if (calculate(arr)) return true
                arr[i][j] = 0
            }
            return false
        }
    }
    return true
}

fun isAvailable(arr: ArrayList<ArrayList<Int>>, loc: Pair<Int, Int>, num: Int): Boolean {
    for (i in 0..8) {
        if (arr[loc.first][i] == num || arr[i][loc.second] == num) return false
        if (arr[(loc.first / 3) * 3 + i / 3][(loc.second / 3) * 3 + i % 3] == num) return false
    }
    return true
}

fun main() {
    val bf = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val arr = ArrayList<ArrayList<Int>>()

    for (i in 0..8) {
        val st = StringTokenizer(bf.readLine())
        arr.add(ArrayList())
        for (j in 0..8) {
            val now = st.nextToken().toInt()
            arr.last().add(now)
        }
    }
    calculate(arr)

    for (v in arr) {
        for (w in v) {
            bw.write("$w ")
        }
        bw.write("\n")
    }
    bw.flush()
}
