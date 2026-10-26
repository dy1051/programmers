import java.util.*;
class Solution {
    public int[] solution(int m, int n, int[][] picture) {
        int[] answer = new int[2];
        
        visited = new boolean[m][n];
        
        for(int i = 0 ; i < m ; i++) {
            for(int j = 0 ; j < n ; j++) {
                if(picture[i][j] == 0 || visited[i][j]) continue;
                int result = bfs(m, n, picture, i, j);
                if(result > 0) {
                    answer[0]++;
                    answer[1] = Math.max(answer[1], result);
                }
            }
        }
        
        return answer;
    }
    
    boolean[][] visited;
    int bfs(int m, int n, int[][] picture, int startX, int startY) {
        Deque<int[]> queue = new ArrayDeque<>();
        
        queue.offerLast(new int[]{startX, startY});
        visited[startX][startY] = true;
        
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        
        int result = 0;
        while(!queue.isEmpty()) {
            int[] now = queue.pollFirst();
            int r = now[0];
            int c = now[1];
            result++;
                
            for(int i = 0 ; i < 4 ; i++) {
                int x = r + dr[i];
                int y = c + dc[i];
                
                if(x >= 0 && x < m && y >= 0 && y < n && !visited[x][y]) {
                    if(picture[x][y] == picture[startX][startY]) {
                        visited[x][y] = true;
                        queue.offerLast(new int[]{x, y});
                    }
                }
            }
        }
        
        return result;
    }
}