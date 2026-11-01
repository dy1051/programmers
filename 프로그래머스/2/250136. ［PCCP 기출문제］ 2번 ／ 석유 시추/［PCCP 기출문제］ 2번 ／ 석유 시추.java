import java.util.*;
class Solution {
    int[] dist;
    boolean[][] visited;
    int M; int N;
    public int solution(int[][] land) {
        int answer = 0;
        
        M = land.length; N = land[0].length;
        dist = new int[N];
        visited = new boolean[M][N];
        
        for(int i = 0 ; i < M ; i++) {
            for(int j = 0 ; j < N ; j++) {
                if(visited[i][j]) continue;
                if(land[i][j] == 0) continue;
                bfs(i, j, land);
            }
        }
        
        for(int i = 0 ; i < dist.length ; i++)
            answer = Math.max(answer, dist[i]);
        
        return answer;
    }
    
    void bfs(int startX, int startY, int[][] land) {
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offerLast(new int[]{startX, startY});
        visited[startX][startY] = true;
        
        int[] max = new int[N];
        Arrays.fill(max, Integer.MAX_VALUE);
        
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        
        int res = 1;
        max[startY] = startX;
        
        while(!queue.isEmpty()) {
            int[] now = queue.pollFirst();
            int r = now[0];
            int c = now[1];
            
            for(int i = 0 ; i < 4 ; i++) {
                int x = r + dr[i];
                int y = c + dc[i];
                
                if(x>=0 && x<M && y>=0 && y<N && !visited[x][y] && land[x][y] == 1) {
                    queue.offerLast(new int[]{x, y});
                    visited[x][y] = true;
                    max[y] = Math.min(max[y], x);
                    res++;
                }
            }
        }
        
        for(int i = 0 ; i < N ; i++) {
            dist[i] += max[i] == Integer.MAX_VALUE ? 0 : res;
        }
    }
   
}