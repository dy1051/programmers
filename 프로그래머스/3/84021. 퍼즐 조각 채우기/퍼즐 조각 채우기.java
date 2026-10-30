import java.util.*;

class Solution {
    int L;
    boolean[][] visited;

    List<List<int[]>> glist = new ArrayList<>();
    List<List<int[]>> tlist = new ArrayList<>();

    public int solution(int[][] game_board, int[][] table) {
        int answer = 0;
        L = table.length;

        // 1. 게임 보드의 빈 공간 추출
        visited = new boolean[L][L];
        for (int r = 0; r < L; r++) {
            for (int c = 0; c < L; c++) {
                if (!visited[r][c] && game_board[r][c] == 0) {
                    glist.add(bfs(r, c, game_board, 0));
                }
            }
        }

        // 2. 테이블의 조각 추출
        visited = new boolean[L][L];
        for (int r = 0; r < L; r++) {
            for (int c = 0; c < L; c++) {
                if (!visited[r][c] && table[r][c] == 1) {
                    tlist.add(bfs(r, c, table, 1));
                }
            }
        }

        // 각 조각은 한 번만 사용
        boolean[] used = new boolean[tlist.size()];

        // 3. 빈 공간마다 맞는 조각 찾기
        for (List<int[]> blank : glist) {
            for (int i = 0; i < tlist.size(); i++) {
                if (used[i]) continue;

                List<int[]> piece = tlist.get(i);
                if (blank.size() != piece.size()) continue;

                boolean matched = false;

                // 원래 모양, 90도, 180도, 270도 비교
                for (int count = 0; count < 4; count++) {
                    if (sameShape(blank, piece)) {
                        matched = true;
                        break;
                    }
                    piece = rotate(piece);
                }

                if (matched) {
                    used[i] = true;
                    answer += blank.size();
                    break;
                }
            }
        }

        return answer;
    }

    // 연결된 칸들의 좌표를 모아서 반환
    List<int[]> bfs(int startX, int startY,
                    int[][] board, int target) {
        Deque<int[]> queue = new ArrayDeque<>();
        List<int[]> shape = new ArrayList<>();

        queue.offerLast(new int[]{startX, startY});
        visited[startX][startY] = true;
        shape.add(new int[]{startX, startY});

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            int[] now = queue.pollFirst();

            for (int i = 0; i < 4; i++) {
                int x = now[0] + dr[i];
                int y = now[1] + dc[i];

                if (x < 0 || x >= L || y < 0 || y >= L) continue;
                if (visited[x][y] || board[x][y] != target) continue;

                visited[x][y] = true;
                queue.offerLast(new int[]{x, y});
                shape.add(new int[]{x, y});
            }
        }

        normalize(shape);
        return shape;
    }

    // 최소 행·열을 빼서 위치를 맞추고 정렬
    void normalize(List<int[]> shape) {
        int minR = Integer.MAX_VALUE;
        int minC = Integer.MAX_VALUE;

        for (int[] point : shape) {
            minR = Math.min(minR, point[0]);
            minC = Math.min(minC, point[1]);
        }

        for (int[] point : shape) {
            point[0] -= minR;
            point[1] -= minC;
        }

        shape.sort((a, b) -> {
            if (a[0] == b[0]) {
                return Integer.compare(a[1], b[1]);
            }
            return Integer.compare(a[0], b[0]);
        });
    }

    // 90도 회전한 새로운 좌표 목록 반환
    List<int[]> rotate(List<int[]> shape) {
        List<int[]> rotated = new ArrayList<>();

        for (int[] point : shape) {
            int r = point[0];
            int c = point[1];

            rotated.add(new int[]{c, -r});
        }

        // 회전 후에도 위치 보정과 정렬 필요
        normalize(rotated);
        return rotated;
    }

    // 보정·정렬된 두 모양 비교
    boolean sameShape(List<int[]> a, List<int[]> b) {
        if (a.size() != b.size()) return false;

        for (int i = 0; i < a.size(); i++) {
            if (a.get(i)[0] != b.get(i)[0] ||
                a.get(i)[1] != b.get(i)[1]) {
                return false;
            }
        }

        return true;
    }
}