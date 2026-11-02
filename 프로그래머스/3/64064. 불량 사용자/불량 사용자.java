import java.util.*;
class Solution {
    Set<Set<Integer>> set = new HashSet<>();
    
    public int solution(String[] user_id, String[] banned_id) {
    
        visited = new boolean[banned_id.length];
        dfs(-1, user_id, banned_id);
        
        return set.size();
    }
    boolean[] visited;
    Deque<Integer> deque = new ArrayDeque<>();
    void dfs(int idx, String[] user_id, String[] banned_id) {
        if(deque.size() == banned_id.length) {
            Set<Integer> tmp = new HashSet<>(deque);
            set.add(tmp);
            return;
        }
        
        for(int i = idx+1 ; i < user_id.length ; i++) {
            String now = user_id[i];
            
            for(int j = 0 ; j < banned_id.length ; j++) {
                if(visited[j]) continue;
                if(!match(now, banned_id[j])) continue;
                
                visited[j] = true;
                deque.offerLast(i);
                dfs(i, user_id, banned_id);
                deque.pollLast();
                visited[j] = false;
            }
        }
    }
    
    boolean match(String a, String b) {
        if(a.length() != b.length()) return false;
        
        boolean flag = true;
        for(int c = 0 ; c < a.length() ; c++) {
            if(b.charAt(c) == '*') continue;
            if(a.charAt(c) != b.charAt(c)) {
                flag = false;
                break;
            }
        }
        
        return flag;
    }
}