import java.util.*;
class Solution {
    char[][] graph;
    boolean[][] visited;
    int[][] check;
    
    public int solution(String[] maps) {
        int rows = maps.length;
        int cols = maps[0].length();
        
        graph = new char[rows][cols];
        
        int[] start = new int[2];
        int[] end = new int[2];
        int[] lever = new int[2];
        
        for(int i = 0 ; i < rows; i++) {
            for(int j = 0 ; j < cols ; j++) {
                graph[i][j] = maps[i].charAt(j);
                
                if (graph[i][j] == 'S') {
                    start[0] = i;
                    start[1] = j;
                } else if (graph[i][j] == 'E') {
                    end[0] = i;
                    end[1] = j;
                } else if (graph[i][j] == 'L') {
                    lever[0] = i;
                    lever[1] = j;
                }
            }
        }
        
        visited = new boolean[rows][cols];
        check = new int[rows][cols];
        
        int lRes = bfs(start, lever, rows, cols, 'L');
        
        if(lRes == 0)
            return -1;
        
        visited = new boolean[rows][cols];
        check = new int[rows][cols];
        
        check[lever[0]][lever[1]] = lRes;
        int eRes = bfs(lever, end, rows, cols, 'E');
        
        return eRes == 0 ? -1 : eRes;
    }
    
    int bfs(int[] s, int[] e, int R, int C, char type) {
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        
        Deque<int[]> queue = new ArrayDeque<>();
        
        queue.addLast(new int[]{s[0], s[1]});
        visited[s[0]][s[1]] = true;
        
        while(!queue.isEmpty()) {
            int[] now = queue.pollFirst();
            
            if(graph[now[0]][now[1]] == type) {
                break;
            }
            
            for(int i = 0 ; i < 4 ; i++) {
                int x = now[0] + dr[i];
                int y = now[1] + dc[i];
                
                if(x>=0 && x<R && y>=0 && y<C && !visited[x][y] && graph[x][y] != 'X') {
                    visited[x][y] = true;
                    queue.addLast(new int[]{x, y});
                    check[x][y] = check[now[0]][now[1]] + 1;
                }
            }
        }
        
        return check[e[0]][e[1]];
    }
}