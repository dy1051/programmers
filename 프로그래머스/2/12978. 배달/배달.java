import java.util.*;
class Solution {
    // 1. 상태 현재 위치에서 K시간 이하로 갈 수 있는 마을. 현재 위치에 오는 비용이 얼마인지
    // 2. 이동 현재 위치의 비용이 K 이하면 연결되어 있는 다른 마을을 갈 수 있다.
    // 3. 종료 갈 수 있는 마을 이 없을 때 종료.
    List<List<int[]>> list = new ArrayList<>();
    public int solution(int N, int[][] road, int K) {
        int answer = 0;

        for(int i = 0 ; i < N+1 ; i++) {
            list.add(new ArrayList<>());
        }
        
        for(int i = 0 ; i < road.length ; i++) {
            list.get(road[i][0]).add(new int[]{road[i][1], road[i][2]});
            list.get(road[i][1]).add(new int[]{road[i][0], road[i][2]});
        }
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        pq.offer(new int[]{1, 0});
        dist[1] = 0;
        
        while (!pq.isEmpty()) {
            int[] n = pq.poll();
            int nowId = n[0];
            int nowCost = n[1];
            
            if(dist[nowId] < nowCost) continue;
            
            for(int[] nt : list.get(nowId)) {
                int nextId = nt[0];
                int nextCost = nt[1];
                
                if(nowCost + nextCost <= K && dist[nextId] > nowCost + nextCost) {
                    dist[nextId] = nowCost + nextCost;
                    pq.offer(new int[]{nextId, nowCost + nextCost});
                } 
            }
        }
        
        for(int i = 1 ; i < dist.length ; i++) {
            if(dist[i] <= K) answer++;
        }

        return answer;
    }
    
   
}