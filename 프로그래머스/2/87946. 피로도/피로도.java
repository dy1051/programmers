import java.util.*;

class Solution {
    public int solution(int k, int[][] dungeons) {
        boolean[] visited = new boolean[dungeons.length];
        dfs(dungeons.length, 0, visited, "", k, dungeons);
        
        return answer;
    }
    
    int answer = 0;
    void dfs(int L, int n, boolean[] visited, String str, int K, int[][] dungeons) {
        if(n == L) {
            int tmp = K;
            int cnt = 0;
            for(int i = 0 ; i < L ; i++) {
                int idx = str.charAt(i) - '0';
                if(tmp >= dungeons[idx][0]) {
                    tmp -= dungeons[idx][1];
                    cnt++;
                } else {
                    break;
                }
            }
            answer = Math.max(answer, cnt);
            return;
        }
        
        for (int i = 0 ; i < L ; i++) {
            if (visited[i])
                continue;
            
            visited[i] = true;
            dfs(L, n + 1, visited, str+i, K, dungeons);
            visited[i] = false;
        }
    }
}