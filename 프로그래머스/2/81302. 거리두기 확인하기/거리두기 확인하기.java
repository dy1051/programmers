import java.util.*;
class Solution {
    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        
        for (int idx = 0 ; idx < answer.length ; idx++) {
            String[] place = places[idx];
            
            char[][] map = new char[5][5];
            Deque<int[]> queue = new ArrayDeque<>();
            for(int i = 0 ; i < 5 ; i++) {
                for (int j = 0 ; j < 5 ; j++) {
                    map[i][j] = place[i].charAt(j);
                    if (map[i][j] == 'P')
                        queue.add(new int[]{i, j});
                }
            }
            
            answer[idx] = bfs(map, queue);
            
        }
        
        return answer;
    }
    
    int bfs(char[][] map, Deque<int[]> queue) {
        if(queue.size() == 0) return 1;
        
        boolean[][] visited = new boolean[5][5];
        
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        
        int[] dr2 = {-1, -1, 1, 1};
        int[] dc2 = {-1, 1, -1, 1};
        
        int[][] tmp = {{0, 2}, {0, 3}, {1, 2}, {1, 3}};
        
        
        while (!queue.isEmpty()) {
            int[] now = queue.pollFirst();
            visited[now[0]][now[1]] = true;
            
            for (int i = 0 ; i < 4 ; i++) {
                int x = now[0] + dr[i];
                int y = now[1] + dc[i];
                
                if (x>=0 && x<5 && y>=0 && y<5 && !visited[x][y]) {
                    if (map[x][y] == 'P') {
                        return 0;
                    }
                }
            }
            
            for (int i = 0 ; i < 4 ; i++) {
                int x = now[0] + dr[i] * 2;
                int y = now[1] + dc[i] * 2;
                
                int xx = now[0] + dr[i];
                int yy = now[1] + dc[i];
                
                if (x>=0 && x<5 && y>=0 && y<5 && !visited[x][y] && map[x][y] == 'P') {
                    if (map[xx][yy] != 'X') {
                        return 0;
                    }
                }
            }
            
            for(int i = 0 ; i < 4 ; i++) {
                int x = now[0] + dr2[i];
                int y = now[1] + dc2[i];
                
                if (x>=0 && x<5 && y>=0 && y<5 && !visited[x][y] & map[x][y] == 'P') {
                    int[] t = tmp[i];                 
                    
                    int[] one = new int[]{now[0] + dr[t[0]], now[1] + dc[t[0]]};
                    int[] two = new int[]{now[0] + dr[t[1]], now[1] + dc[t[1]]};
                    
                    if (map[one[0]][one[1]] == 'X' && map[two[0]][two[1]] == 'X'){}
                    else {
                        return 0;
                    }
                }
            }
        }
        return 1;
    }
    
}