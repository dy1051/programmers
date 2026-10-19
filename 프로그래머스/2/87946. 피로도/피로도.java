import java.util.*;

class Solution {
    public int solution(int k, int[][] dungeons) {
        boolean[] visited = new boolean[dungeons.length];
        dfs(dungeons.length, 0, visited, k, dungeons);
        
        return answer;
    }
    
    int answer = 0;
    void dfs(int L, int n, boolean[] visited, int k, int[][] dungeons) {
        answer = Math.max(answer, n);
        
        for (int i = 0 ; i < L ; i++) {
            if (visited[i] || dungeons[i][0] > k)
                continue;
            
            visited[i] = true;
            dfs(L, n + 1, visited, k - dungeons[i][1], dungeons);
            visited[i] = false;
        }
    }
}