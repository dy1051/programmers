import java.util.*;
class Solution {
    List<List<Integer>> graph = new ArrayList<>();
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length];
        
        for(int i = 0; i<=n ; i++) {
            graph.add(new ArrayList<>());
        }
        
        for(int i = 0 ; i < roads.length ; i++) {
            graph.get(roads[i][0]).add(roads[i][1]);
            graph.get(roads[i][1]).add(roads[i][0]);
        }
        
        dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        
        bfs(destination, n);
        
        
        for(int i = 0 ; i < sources.length ; i++) {
            int idx = sources[i];
            answer[i] = dist[idx] == Integer.MAX_VALUE ? -1 : dist[idx];
        }
        
        return answer;
    }
    int[] dist;
    void bfs(int start, int n) {
        Deque<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[n+1];
        
        queue.offerLast(start);
        visited[start] = true;
        dist[start] = 0;
        
        while (!queue.isEmpty()) {
            int now = queue.pollFirst();
            
            for(int next : graph.get(now)) {
                if(visited[next]) continue;
                
                if(dist[next] < dist[now]+1) continue;
                
                visited[next] = true;
                dist[next] = dist[now] + 1;
                queue.offerLast(next);
            }
        }
        
        
    }
    
}