import java.util.*;
class Solution {
    ArrayList<ArrayList<Integer>> count = new ArrayList<>();
    
    public int solution(int[] cards) {
        int answer = 0;
        
        int L = cards.length;
        
        int[] pCards = new int[L + 1];
        
        for(int i = 1 ; i < L+1 ; i++) {
            pCards[i] = cards[i-1];
        }
        
        
        count.add(new ArrayList<>());
        
        
        for(int i = 1 ; i < L+1 ; i++) {
            ArrayList<Integer> result = game(i, pCards, L);
            count.add(result);
        }
        
        for(int i = 1 ; i < L+1 ; i++) {
            answer = Math.max(answer, pickGame(i, L));
        }
        
        return answer;
    }
    
    ArrayList<Integer> game(int i, int[] pCards, int L) {
        Deque<Integer> queue = new ArrayDeque<>();
        ArrayList<Integer> result = new ArrayList<>();
        
        boolean[] visited = new boolean[L+1];
        
        queue.offerLast(i);
        visited[i] = true;
        result.add(i);
        
        while(!queue.isEmpty()) {
            int now = queue.pollFirst();
            int next = pCards[now];
            
            if(now == next) break;
            
            if(!visited[next]) {
                result.add(next);
                queue.offerLast(next);
                visited[next] = true;
            }
        }  
        
        return result;
    }
    
    int pickGame(int now, int L) {
        ArrayList<Integer> nowList = count.get(now);
        
        int tmp = 0;
        for(int i = 1 ; i <= L ; i++) {
            if(!nowList.contains(i)) {
                tmp = Math.max(tmp, count.get(i).size());
            }
        }
        
        return nowList.size() * tmp;
    }
}