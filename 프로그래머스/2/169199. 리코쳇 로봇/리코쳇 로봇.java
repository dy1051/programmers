import java.util.*;
class Solution {
    public int solution(String[] board) {
        int answer = 0;
        
        int tX = board.length;
        int tY = board[0].length();
        
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        
        char[][] test = new char[tX][tY];
        int Rx = 0; int Ry = 0;
        int Gx = 0; int Gy = 0;
        for(int i = 0 ; i < tX ; i++) {
            for(int j = 0 ; j < tY ; j++) {
                test[i][j] = board[i].charAt(j);
                if(test[i][j] == 'R') {
                    Rx = i; Ry = j;
                } else if(test[i][j] == 'G') {
                    Gx = i; Gy = j;
                }
            }
        }
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->Integer.compare(a[2], b[2]));
        pq.offer(new int[]{Rx, Ry, 0});
        
        int[][] dist = new int[tX][tY];
        for(int i = 0 ; i < tX ;i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        dist[Rx][Ry] = 0;
        
        while(!pq.isEmpty()) {
            int[] now = pq.poll();
            int r = now[0];
            int c = now[1];
            int cost = now[2];
            
            if(cost > dist[r][c]) continue;
            
            for(int i = 0 ; i < 4 ; i++) {
                int x = r;
                int y = c;
                
                while (true) {
                    int nx = x + dr[i];
                    int ny = y + dc[i];

                    if (nx < 0 || nx >= tX || ny < 0 || ny >= tY
                            || test[nx][ny] == 'D') {
                        break;
                    }

                    x = nx;
                    y = ny;
                }

                if(cost + 1 < dist[x][y]) {
                    dist[x][y] = cost+1;
                    pq.offer(new int[]{x, y, cost+1});
                }
            }
        }
    
        return dist[Gx][Gy] == Integer.MAX_VALUE ? -1 : dist[Gx][Gy];
    }
}