import java.util.*;
class Solution {
    public int solution(int N, int[][] road, int K) {
        int answer = 0;

        List<List<int[]>> graph = new ArrayList<>();
        
        for(int i = 0 ; i < N+1 ; i++) {
            graph.add(new ArrayList<>());
        }
        
        for(int i = 0 ; i < road.length ; i++) {
            int from = road[i][0];
            int to = road[i][1];
            int cost = road[i][2];
            
            graph.get(from).add(new int[]{to, cost});
            graph.get(to).add(new int[]{from, cost});
        }
        
        int[] dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        dist[1] = 0;
        pq.offer(new int[]{1, 0});
        
        while(!pq.isEmpty()) {
            int[] now = pq.poll();
            
            int node = now[0];
            int cost = now[1];
            
            if(cost > dist[node])
                continue;
            
            for(int[] edge : graph.get(node)) {
                int next = edge[0];
                int weight = edge[1];
                
                int newWeight = cost + weight;
                
                if(newWeight < dist[next]) {
                    dist[next] = newWeight;
                    pq.offer(new int[]{next, newWeight});
                }
            }
        }
        
        for(int i = 1; i < N+1 ; i++) {
            if(dist[i] <= K)
                answer++;
        }
        

        return answer;
    }
}