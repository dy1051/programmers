import java.util.*;
class Solution {
    List<List<Integer>> graph = new ArrayList<>();
    public int solution(int n, int[][] edge) {
        int answer = 0;
        
        for(int i = 0 ; i <= n ; i++) {
            graph.add(new ArrayList<>());
        }
        
        for(int i = 0 ; i < edge.length ; i++) {
            graph.get(edge[i][0]).add(edge[i][1]);
            graph.get(edge[i][1]).add(edge[i][0]);
        }
        
        dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;
        
        bfs(1, n); 
        
        int tmp = 0;
        
        for(int i = n ; i >= 1 ; i--) {
            tmp = Math.max(tmp, dist[i]);
        }
        
        for(int i = n ; i >= 1 ; i--) {
            if(tmp == dist[i])
                answer++;
        }
        
        return answer;
    }
    int[] dist;
    
    void bfs(int start, int n) {
        Deque<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[n+1];
        
        visited[start] = true;
        queue.offerLast(start);
        dist[start] = 0;
        
        while (!queue.isEmpty()) {
            int now = queue.pollFirst();
            int cost = dist[now];
            
            for(int next : graph.get(now)) {
                if(visited[next]) continue;
                
                if (dist[next] < cost+1) continue;
                
                visited[next] = true;
                dist[next] = cost+1;
                queue.offerLast(next);
            }
        }
        
    }
}