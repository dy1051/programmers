import java.util.*;
class Solution {
    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        
        for (int idx = 0 ; idx < places.length ; idx++) {
            String[] map = places[idx];
            boolean result = true;
            
            for (int r = 0 ; r < 5&&result ; r++) {
                for (int c = 0 ; c < 5 ; c++) {
                    if(map[r].charAt(c) == 'P') {
                        result = bfs(map, r, c);
                        if(!result) break;
                    }
                }
            }
            answer[idx] = result ? 1 : 0;
        }
        
        return answer;
    }
    
     boolean bfs(String[] map, int startR, int startC) {
         Deque<int[]> queue = new ArrayDeque<>();
         boolean[][] visited = new boolean[5][5];
         
         queue.addLast(new int[]{startR, startC, 0});
         visited[startR][startC] = true;
         
         int[] dr = {-1, 1, 0, 0};
         int[] dc = {0, 0, -1, 1};
         
         while (!queue.isEmpty()) {
             int[] now = queue.pollFirst();
             
             int r = now[0];
             int c = now[1];
             int distance = now[2];
             
             if (distance > 0 && map[r].charAt(c) == 'P') {
                 return false;
             }
             
             if (distance == 2) continue;
             
             for(int i = 0 ; i < 4 ; i++) {
                 int x = r + dr[i];
                 int y = c + dc[i];
                 
                 if(x>=0 && x<5 && y>=0 && y<5 && !visited[x][y] && map[x].charAt(y) != 'X') {
                     visited[x][y] = true;
                     queue.addLast(new int[]{x, y, distance+1});
                 }
             }
         }
         
         return true;
     }
}