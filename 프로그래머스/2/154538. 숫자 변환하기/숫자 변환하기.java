import java.util.*;
class Solution {
    int answer = -1;
    public int solution(int x, int y, int n) {
        
        bfs(x, y, n);
        
        return answer;
    }
    
    void bfs(int x, int y, int n) {
        Deque<int[]> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[y + 1];
        
        queue.addLast(new int[]{x, 0});
        visited[x] = true;
        
        while(!queue.isEmpty()) {
            
            int[] now = queue.pollFirst();
            
            if (now[0] == y) {
                answer = now[1];
                break;
            }
            
            int f = now[0] + n;
            if (f <= y && !visited[f]) {
                queue.addLast(new int[]{f, now[1]+1});
                visited[f] = true;
            }
            
            int s = now[0] * 2;
            if (s <= y && !visited[s]) {
                queue.addLast(new int[]{s, now[1]+1});
                visited[s] = true;
            }
            int t = now[0] * 3;
            if (t <= y && !visited[t]) {
                queue.addLast(new int[]{t, now[1]+1});
                visited[t] = true;
            }
            
        }
        
        
    }
}