import java.util.*;

class Solution {
    public int solution(int[] cards) {
        int answer = 0;
        int L = cards.length;
        
        visited = new boolean[L+1];
        int[] pCards = new int[L+1];
        
        for(int i = 0 ; i < L ; i++) {
            pCards[i+1] = cards[i];
        }
        
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 1 ; i <= L ; i++) {
            if(visited[i]) continue;
            list.add(bfs(i, pCards));
        }
        
        list.sort(Collections.reverseOrder());
        
        return list.size() > 1 ? list.get(0) * list.get(1) : 0;
    }
    
    boolean[] visited;
    int bfs(int i, int[] pCards) {
        Deque<Integer> queue = new ArrayDeque<>();
        
        queue.offerLast(i);
        visited[i] = true;
        
        int result = 1;
        while(!queue.isEmpty()) {
            int now = queue.pollFirst();
            int next = pCards[now];
            
            if(!visited[next]) {
                queue.offerLast(next);
                visited[next] = true;
                result++;
            }
        }
        return result;
    }
}