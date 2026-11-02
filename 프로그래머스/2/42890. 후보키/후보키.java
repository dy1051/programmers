import java.util.*;

class Solution {
    int M;
    int N;

    List<Set<Integer>> candidateKeys = new ArrayList<>();

    public int solution(String[][] relation) {
        M = relation.length;
        N = relation[0].length;

        bfs(relation);

        return candidateKeys.size();
    }

    void bfs(String[][] relation) {
        Deque<String> queue = new ArrayDeque<>();

        // 한 개의 열로 이루어진 조합부터 시작
        for (int i = 0; i < N; i++) {
            queue.offerLast(i + "");
        }

        while (!queue.isEmpty()) {
            String now = queue.pollFirst();

            Set<Integer> current = new HashSet<>();
            for (String s : now.split("")) {
                current.add(Integer.parseInt(s));
            }

            // 1. 최소성 검사:
            // 이미 찾은 후보키를 포함하면 검사·확장하지 않음
            boolean skip = false;

            for (Set<Integer> key : candidateKeys) {
                if (current.containsAll(key)) {
                    skip = true;
                    break;
                }
            }

            if (skip) continue;

            // 2. 유일성 검사:
            // 모든 행을 구별하면 후보키로 저장
            if (check(now, relation)) {
                candidateKeys.add(current);
                continue;
            }

            // 3. 후보키가 아니면 다음 열을 붙여 확장
            int last = Integer.parseInt(
                now.substring(now.length() - 1)
            );

            for (int i = last + 1; i < N; i++) {
                queue.offerLast(now + i);
            }
        }
    }

    boolean check(String idx, String[][] relation) {
        String[] list = idx.split("");
        Set<List<String>> rows = new HashSet<>();

        for (int i = 0; i < M; i++) {
            List<String> values = new ArrayList<>();

            for (String jstr : list) {
                int j = Integer.parseInt(jstr);
                values.add(relation[i][j]);
            }

            rows.add(values);
        }

        return rows.size() == M;
    }
}