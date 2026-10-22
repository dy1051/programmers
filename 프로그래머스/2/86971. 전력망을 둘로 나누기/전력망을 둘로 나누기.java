import java.util.*;

class Solution {
    List<List<Integer>> graph;
    public int solution(int n, int[][] wires) {
        graph = new ArrayList<>();
        
        for(int i = 0 ; i <= n ; i++) {
            graph.add(new ArrayList<>());
        }
        
        for(int[] wire : wires) {
            graph.get(wire[0]).add(wire[1]);
            graph.get(wire[1]).add(wire[0]);
        }
        
        List<Integer> answer = new ArrayList<>();
        for(int[] wire : wires) {
            int x = wire[0];
            int y = wire[1];
            int left = bfs(n, x, x, y);
            int right = bfs(n, y, y, x);
            
            answer.add(Math.abs(left-right));
        }
        
        Collections.sort(answer);
        
        return answer.get(0);
    }
    
    int bfs(int n, int start, int x, int y) {
        int cnt = 0;
        boolean[] visited = new boolean[n + 1];
        Deque<Integer> queue = new ArrayDeque<>();
        
        visited[start] = true;
        queue.addLast(start);
        
        while(!queue.isEmpty()) {
            int now = queue.pollFirst();
            cnt++;
            
            for(int i : graph.get(now)) {
                if(now == x && i == y) continue;
                if(!visited[i]) {
                    visited[i] = true;
                    queue.addLast(i);
                }
            }
        }
        return cnt;
    }
}