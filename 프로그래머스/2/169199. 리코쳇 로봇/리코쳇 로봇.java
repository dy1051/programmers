import java.util.*;
class Solution {
    public int solution(String[] board) {
        int answer = 0;
        
        int R = board.length; int C = board[0].length();
        
        char[][] graph = new char[R][C];
        int[][] dist = new int[R][C];
        int rR = 0; int rC = 0; int gR = 0; int gC = 0;
        for (int i = 0 ; i < R ; i++) {
            for (int j = 0 ; j < C ; j++) {
                graph[i][j] = board[i].charAt(j);
                if(graph[i][j] == 'R') {
                    rR = i; rC = j;
                } else if (graph[i][j] == 'G') {
                    gR = i; gC = j;
                }
            }
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));
        dist[rR][rC] = 0;
        pq.offer(new int[]{rR, rC, 0});
        
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        while(!pq.isEmpty()) {
            int[] now = pq.poll();
            int r = now[0];
            int c = now[1];
            int cost = now[2];
            
            if(cost > dist[r][c]) continue;
            
            for (int i = 0 ; i < 4 ; i++) {
                int x = r;
                int y = c;
                
                while (true) {
                    int nx = x + dr[i];
                    int ny = y + dc[i];
                    if(nx < 0 || nx >= R || ny < 0 || ny >= C || graph[nx][ny] == 'D') {
                        break;   
                    }
                    
                    x = nx;
                    y = ny;
                }
                
                if (cost+1 < dist[x][y]) {
                    dist[x][y] = cost+1;
                    pq.offer(new int[]{x, y, cost+1});
                }
            }
            
        }
        
        
        return dist[gR][gC] == Integer.MAX_VALUE ? -1 : dist[gR][gC];
    }
}