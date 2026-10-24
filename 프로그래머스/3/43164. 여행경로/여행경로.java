import java.util.*;
class Solution {
    // 1. 상태: 현재 공항 위치와 현재까지 들린 공항
    // 2. 이동: 현재 공항에서 출발 할 수 있는 미사용 항공권
    // 3. 종료: 현재까지 들린 공항의 크기가 tickes.legnth + 1
    HashMap<String, List<String[]>> hm = new HashMap<>();
    boolean[] visited;
    
    public String[] solution(String[][] tickets) { 
        for(int i = 0; i < tickets.length ; i++) {
            List<String[]> list = hm.getOrDefault(tickets[i][0], new ArrayList<>());
            list.add(new String[]{i+"", tickets[i][1]});
            hm.put(tickets[i][0], list);
        }
        
        for (List<String[]> list : hm.values()) {
            list.sort((a, b) -> a[1].compareTo(b[1]));
        }
        
        visited = new boolean[tickets.length + 1];
        queue.addLast("ICN");
        dfs("ICN", tickets.length + 1);
        
        return answer.stream().toArray(String[]::new);
    }
    
    ArrayList<String> answer = new ArrayList<>();
    Deque<String> queue = new ArrayDeque<>();
    void dfs(String now, int L) {
        if (!answer.isEmpty()) return; // 이미 정답을 찾음

        if (queue.size() == L) {
            answer = new ArrayList<>(queue);
            return;
        }

        if (!hm.containsKey(now)) return; // 더 갈 곳이 없음
        
        for (String[] next : hm.get(now)) {
            int nextId = Integer.parseInt(next[0]);
            String nextCity = next[1];
            
            if (visited[nextId]) continue;
            
            visited[nextId] = true;
            queue.addLast(nextCity);
            dfs(nextCity, L);
            queue.pollLast();
            visited[nextId] = false;
            
            if (!answer.isEmpty()) return;
        }
        
        
    }
}