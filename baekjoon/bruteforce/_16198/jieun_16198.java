package baekjoon.bruteforce._16198;
/**
 * 작성자: 이지은
 * 문제: N과 에너지 구슬의 무게가 주어졌을 때, 모을 수 있는 에너지 양의 최댓값을 구하는 프로그램을 작성하시오.
 *     1. 에너지 구슬 하나를 고른다. 고른 에너지 구슬의 번호를 x라고 한다. 단, 첫 번째와 마지막 에너지 구슬은 고를 수 없다.
 *     2. x번째 에너지 구슬을 제거한다.
 *     3. W_x-1 × W_x+1의 에너지를 모을 수 있다.
 *     4. N을 1 감소시키고, 에너지 구슬을 1번부터 N번까지로 다시 번호를 매긴다. 번호는 첫 구슬이 1번, 다음 구슬이 2번, ... 과 같이 매겨야 한다.
 * 해결: 조합 사용. boolean 배열 추가
 * 작성일: 2023-04-11
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class jieun_16198 {
    static int N; //구슬의 개수
    static int R; //조합할 개수
    static List<Integer> list; //에너지 구슬의 무게를 담을 리스트
    static boolean [] isRemove; //구슬을 제거했다는 의미로 사용할 boolean배열

    static boolean [] isCheck; //구슬을 사용했는 지 체크
    static int [] select; //선택할 수
    static int max = Integer.MIN_VALUE; //에너지의 최댓값
    static int sum;

    public static void calc(int r){
        if(r == R){
            sum = 0;
            for(int i=0; i<R; i++){
                int idx = select[i];
                int numA = idx-1;
                int numB = idx+1;
                while(true){
                    //while문 탈출 조건
                    if(isRemove[numA]==false && isRemove[numB]==false) break;
                    if(isRemove[numA]==true){
                        numA--;
                    }
                    if(isRemove[numB]==true){
                        numB++;
                    }
                }
                sum += (list.get(numA)*list.get(numB)); //합 누적
                isRemove[idx] = true; //체크
            }
            max = Math.max(max, sum);
            isRemove = new boolean[N]; //다시 초기화
        }

        for(int i=1; i<N-1; i++){ //처음과 마지막을 제외하고 체크
            if(isCheck[i]) continue;
            isCheck[i] = true;
            select[r] = i;
            calc(r+1);
            isCheck[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N= Integer.parseInt(br.readLine());

        list = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            list.add(Integer.parseInt(st.nextToken()));
        }

        R = N-2; //첫번째와 마지막을 제외
        //배열 초기화
        select = new int[R];
        isCheck = new boolean[N];
        isRemove = new boolean[N];

        calc(0);
        System.out.println(max);
    }
}