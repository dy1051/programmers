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
        
        int answer = n;
        for(int[] wire : wires) {
            int count = bfs(n, wire[0], wire[0], wire[1]);
            int diff = Math.abs(count - (n - count));
            
            answer = Math.min(answer, diff);
        }
        
        return answer;
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
                if((now == x && i == y) || (now == y && i == x)) continue;
                if(!visited[i]) {
                    visited[i] = true;
                    queue.addLast(i);
                }
            }
        }
        return cnt;
    }
}