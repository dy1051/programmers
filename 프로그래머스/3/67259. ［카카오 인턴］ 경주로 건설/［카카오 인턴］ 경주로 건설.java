import java.util.*;
class Solution {
    public int solution(int[][] board) {
        int N = board.length;
        int[][][] dist = new int[N][N][4];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return a[3] - b[3];
        });
        
        for(int i = 0 ; i < N ; i++) {
            for(int j = 0 ; j < N ; j++) {
                Arrays.fill(dist[i][j], Integer.MAX_VALUE);
            }
        }
        
        Arrays.fill(dist[0][0], 0);
        pq.offer(new int[]{0, 0, -1, 0});
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        
        while (!pq.isEmpty()) {
            int[] now = pq.poll();
            int r = now[0]; int c = now[1];
            int dir = now[2]; int cost = now[3];
            for(int i = 0 ; i < 4 ; i++) {
                int x = r + dr[i];
                int y = c + dc[i];
                
                if(x>=0 && x<N && y>=0 && y<N) {
                    if (board[x][y] == 1) continue;
                    
                    int nextCost = cost + 100; 
                    if (dir != -1 && i/2 != dir/2) {
                        nextCost += 500; 
                    }
                    
                    if(dist[x][y][i] > nextCost) {
                        dist[x][y][i] = nextCost;
                        pq.offer(new int[]{x, y, i, nextCost});
                    }
                }
            }
        }
        
        int answer = dist[N-1][N-1][0];
        
        for(int i = 1 ; i < 4 ; i++) {
            answer = Math.min(answer, dist[N-1][N-1][i]);
        }
        
        return answer;
    }
}