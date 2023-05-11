package baekjoon.bfs._9019

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main() {
    data class Node(val num: Int, val text: String)

    val bf = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    repeat(bf.readLine().toInt()) {
        val st = StringTokenizer(bf.readLine())
        val from = st.nextToken().toInt()
        val to = st.nextToken().toInt()
        val q: Queue<Node> = ArrayDeque()
        val visited = BooleanArray(10000) { false }
        q.add(Node(from, ""))
        visited[from] = true
        while (q.isNotEmpty()) {
            val now = q.poll()
            for (v in arrayOf(
                now.copy(text = now.text.plus("D"), num = now.num * 2 % 10000),
                now.copy(text = now.text.plus("S"), num = if (now.num == 0) 9999 else now.num - 1),
                now.copy(text = now.text.plus("L"), num = now.num % 1000 * 10 + now.num / 1000),
                now.copy(text = now.text.plus("R"), num = now.num % 10 * 1000 + now.num / 10),
            )) {
                if (visited[v.num]) continue
                if (v.num == to) bw.write("${v.text}\n")
                q.add(v)
                visited[v.num] = true
            }
            if (visited[to]) {
                q.clear()
                break
            }
        }
    }
    bw.flush()
}
