import java.util.*;

class Solution {
    char[][] graph;
    int M, N;

    public int solution(String[] storage, String[] requests) {
        M = storage.length;
        N = storage[0].length();

        // 창고 바깥을 빈칸 한 겹으로 감싸기
        graph = new char[M + 2][N + 2];
        for (char[] row : graph) {
            Arrays.fill(row, '.');
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                graph[i + 1][j + 1] = storage[i].charAt(j);
            }
        }

        for (String request : requests) {
            char target = request.charAt(0);

            if (request.length() == 2) {
                crane(target);
            } else {
                forklift(target);
            }
        }

        int answer = 0;
        for (int i = 1; i <= M; i++) {
            for (int j = 1; j <= N; j++) {
                if (graph[i][j] != '.') answer++;
            }
        }
        return answer;
    }

    // 크레인: 위치에 상관없이 해당 종류를 모두 제거
    void crane(char target) {
        for (int i = 1; i <= M; i++) {
            for (int j = 1; j <= N; j++) {
                if (graph[i][j] == target) {
                    graph[i][j] = '.';
                }
            }
        }
    }

    // 지게차: 외부와 연결된 빈칸을 탐색
    void forklift(char target) {
        Deque<int[]> queue = new ArrayDeque<>();
        List<int[]> remove = new ArrayList<>();
        boolean[][] visited = new boolean[M + 2][N + 2];

        queue.offerLast(new int[]{0, 0});
        visited[0][0] = true;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            int[] now = queue.pollFirst();

            for (int i = 0; i < 4; i++) {
                int x = now[0] + dr[i];
                int y = now[1] + dc[i];

                if (x < 0 || x >= M + 2 || y < 0 || y >= N + 2) {
                    continue;
                }
                if (visited[x][y]) continue;

                visited[x][y] = true;

                if (graph[x][y] == '.') {
                    queue.offerLast(new int[]{x, y});
                } else if (graph[x][y] == target) {
                    remove.add(new int[]{x, y});
                }
            }
        }

        // 요청 순간 접근 가능했던 컨테이너만 한꺼번에 제거
        for (int[] pos : remove) {
            graph[pos[0]][pos[1]] = '.';
        }
    }
}