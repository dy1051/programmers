import java.util.*;
class Solution {
    public int solution(int[][] land) {
        int answer = 0;
        
        visited = new boolean[land.length][land[0].length];
        columnSum = new int[land[0].length];
        
        for(int i = 0 ; i < land.length ; i++) {
            for(int j = 0 ; j < land[i].length ; j++) {
                if(land[i][j] == 0 || visited[i][j]) continue;
                bfs(i, j, land);
            }
        }
        
        for (int sum : columnSum) {
            answer = Math.max(answer, sum);
        }
        
        return answer;
    }
    
    boolean[][] visited;
    int[] columnSum;
    void bfs(int startX, int startY, int[][] land) {
        Deque<int[]> queue = new ArrayDeque<>();
        Set<Integer> columns = new HashSet<>();
        
        columns.add(startY);
        queue.offerLast(new int[]{startX, startY});
        visited[startX][startY] = true;
        
        int result = 1;
        
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        while (!queue.isEmpty()) {
            int[] now = queue.pollFirst();
            int r = now[0]; int c = now[1];
            
            for(int i = 0 ; i < 4 ; i++) {
                int x = r + dr[i];
                int y = c + dc[i];
                
                if (x>=0 && x<land.length && y>=0 && y<land[0].length && !visited[x][y] && land[x][y] == 1) {
                    queue.offerLast(new int[]{x, y});
                    visited[x][y] = true;
                    result++;
                    columns.add(y);
                }
            }
        }
        
        for (int col : columns) {
            columnSum[col] += result;
        }

    }
}
