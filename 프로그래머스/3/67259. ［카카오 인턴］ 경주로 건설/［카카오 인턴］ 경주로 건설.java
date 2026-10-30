import java.util.*;
class Solution {
    int N;
    public int solution(int[][] board) {
        N = board.length;
        
        dist = new int[N][N][4];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                Arrays.fill(dist[r][c], Integer.MAX_VALUE);
            }
        }
        Arrays.fill(dist[0][0], 0);
        
        bfs(board);  
        
        int answer = Integer.MAX_VALUE;

        for (int i = 0; i < 4; i++) {
            answer = Math.min(answer, dist[N-1][N-1][i]);
        }
        return answer;
    }
    
    int[][][] dist;
    void bfs(int[][] board) {
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offerLast(new int[]{0, 0, -1, 0});
        
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        
        while (!queue.isEmpty()) {
            int[] now = queue.pollFirst();
            int r = now[0];
            int c = now[1];
            int dir = now[2];
            int cost = now[3];
            
            for(int i = 0 ; i < 4 ; i++) {
                int x = r + dr[i];
                int y = c + dc[i];
                
                if(x>=0 && x<N && y>=0 && y<N) {
                    if(board[x][y] == 1) continue;
                    
                    int nextCost = cost + 100;
        
                    if (dir != -1 && dir != i) {
                        nextCost += 500;
                    }

                    if (nextCost < dist[x][y][i]) {
                        dist[x][y][i] = nextCost;
                        queue.offerLast(new int[]{x, y, i, nextCost});
                    }
                }
            }
        }
        
    }
}