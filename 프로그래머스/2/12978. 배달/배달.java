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
        
        Deque<int[]> queue = new ArrayDeque<>();
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        queue.addLast(new int[]{1, 0});
        dist[1] = 0;
        
        while (!queue.isEmpty()) {
            int[] now = queue.pollFirst();
            int nowId = now[0];
            int nowCost = now[1];
            
            if(nowCost >= K) continue;
            
            for(int[] next : list.get(nowId)) {
                int newId = next[0];
                int newCost = next[1];
                
                if(nowCost + newCost <= K) {
                    if(dist[newId] > nowCost + newCost) {
                        dist[newId] = nowCost + newCost;
                        queue.addLast(new int[]{newId, nowCost+newCost});
                    }
                } 
            }   
        }
        
        for(int i = 1 ; i < dist.length ; i++) {
            if(dist[i] <= K)
                answer++;
        }

        return answer;
    }
    
   
}