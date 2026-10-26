import java.util.*;
class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        
        visited = new boolean[n];
        
        for(int i = 0 ; i < n ; i++) {
            if(visited[i]) continue;
            bfs(i, computers);
            answer++;
        }
        
        
        return answer;
    }
    
    boolean[] visited;
    void bfs(int start, int[][] computers) {
        Deque<Integer> queue = new ArrayDeque<>();
        
        queue.offerLast(start);
        visited[start] = true;
        
        while(!queue.isEmpty()) {
            int now = queue.pollFirst();
            
            for(int i = 0 ; i < visited.length ; i++) {
                if(!visited[i] && computers[now][i] != 0) {
                    queue.offerLast(i);
                    visited[i] = true;
                }
            }
        }
    }
}