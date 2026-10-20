import java.util.*;
class Solution {
    public int solution(int[][] maps) {
        int rows = maps.length;
        int cols = maps[0].length;
        
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        
        boolean[][] visited = new boolean[rows][cols];
        Deque<int[]> queue = new ArrayDeque<>();
        
        visited[0][0] = true;
        queue.addLast(new int[]{0,0});
        
        while(!queue.isEmpty()) {
            int[] now = queue.pollFirst();
            
            for(int i = 0 ; i < 4 ; i++) {
                int x = now[0] + dr[i];
                int y = now[1] + dc[i];
                
                if(x >= 0 && x < rows && y >= 0 && y < cols && !visited[x][y] && maps[x][y] != 0) {
                    visited[x][y] = true;
                    maps[x][y] += maps[now[0]][now[1]];
                    queue.addLast(new int[]{x, y});
                }
            }
        }
      
        
        return maps[rows-1][cols-1] <= 1 ? -1 : maps[rows-1][cols-1];
    }
}