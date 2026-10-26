import java.util.*;
class Solution {
    ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    public int solution(int n, int[][] computers) {
        int answer = 0;
        
        visited = new boolean[n];
        
        for(int i = 0 ; i < n ; i++) {
            graph.add(new ArrayList<>());
        }
        
        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < n ; j++) {
                if (i==j || computers[i][j] == 0) continue;
                graph.get(i).add(j);
            }
        }
        
        for(int i = 0 ; i < n ; i++) {
            if(visited[i]) continue;
            bfs(i);
            answer++;
        }
        
        
        return answer;
    }
    
    boolean[] visited;
    void bfs(int start) {
        Deque<Integer> queue = new ArrayDeque<>();
        
        queue.offerLast(start);
        visited[start] = true;
        
        while(!queue.isEmpty()) {
            int now = queue.pollFirst();
            
            for(int next : graph.get(now)) {
                if(!visited[next]) {
                    queue.offerLast(next);
                    visited[next] = true;
                }
            }
        }
    }
}