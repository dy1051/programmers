import java.util.*;
class Solution {
    int M; int N;
    char[][] graph;
    
    public int solution(String[] maps) {
        M = maps.length;
        N = maps[0].length();
        
        graph = new char[M][N];
        
        int sR = 0; int sC = 0;
        int eR = 0; int eC = 0;
        int lR = 0; int lC = 0;
        for(int i = 0; i < M ; i++) {
            for (int j = 0 ; j < N ; j++) {
                graph[i][j] = maps[i].charAt(j);
                if(graph[i][j] == 'S') {
                    sR = i; sC = j;
                } else if(graph[i][j] == 'E') {
                    eR = i; eC = j;
                } else if(graph[i][j] == 'L') {
                    lR = i; lC = j;
                }
            }
        }
        
        int result = bfs(M, N, sR, sC, lR, lC, 0);
        if(result == 0) return -1;
        result = bfs(M, N, lR, lC, eR, eC, result);
        if(result == 0) return -1;
        
        return result;
    }
    
    int bfs(int M, int N, int startX, int startY, int endX, int endY, int ff) {
        Deque<int[]> deque = new ArrayDeque<>();
        deque.addLast(new int[]{startX, startY});
        
        boolean[][] visited = new boolean[M][N];
        int[][] check = new int[M][N];
        
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        
        visited[startX][startY] = true;
        check[startX][startY] = ff;
        
        while(!deque.isEmpty()) {
            int[] now = deque.pollFirst();
            int r = now[0];
            int c = now[1];
            
            if(r == endX && c == endY) break;
            
            for(int i = 0 ; i < 4 ; i++) {
                int x = r + dr[i];
                int y = c + dc[i];
                
                if(x>=0 && x<M && y>=0 && y<N && !visited[x][y] && graph[x][y] != 'X') {
                    check[x][y] = check[r][c] + 1;
                    visited[x][y] = true;
                    deque.addLast(new int[]{x, y});
                }
            }
            
        }
        return check[endX][endY];
    }
}