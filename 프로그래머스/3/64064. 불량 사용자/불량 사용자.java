import java.util.*;
class Solution {
    boolean[] visited;

    public int solution(String[] user_id, String[] banned_id) {
        visited = new boolean[user_id.length];

        dfs(0, user_id, banned_id);

        return set.size();
    }

    Set<String> set = new HashSet<>();
    void dfs(int idx, String[] user_id, String[] banned_id) {
        if (idx == banned_id.length) {
            String tmp = "";
            for(int i = 0 ; i < visited.length ; i++) {
                if(visited[i]) tmp = tmp+i;
            }
            
            if(!tmp.equals("")) set.add(tmp);
            
            return;
        }

        // 후보 선택 → 방문 처리 → 재귀 → 방문 복구
        for (int i = 0 ; i < user_id.length ; i++) {
            if (visited[i]) continue;
            if (!match(user_id[i], banned_id[idx])) continue;
            
            visited[i] = true;
            dfs(idx+1, user_id, banned_id);
            visited[i] = false;
        }
    }

    boolean match(String user, String ban) {
        if(user.length() != ban.length()) return false;
        
        for(int i = 0 ; i < ban.length() ; i++) {
            if(ban.charAt(i) == '*') continue;
            
            if(user.charAt(i) != ban.charAt(i)) {
                return false;
            }
        }
        
        return true;
    }
}