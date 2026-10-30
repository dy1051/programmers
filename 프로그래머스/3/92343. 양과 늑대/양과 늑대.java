import java.util.*;
class Solution {
    int answer = 0;
    int[] link;
    public int solution(int[] info, int[][] edges) {
        visited = new boolean[info.length];
        link = new int[info.length];
        
        for(int i = 0 ; i < edges.length ; i++) {
            link[edges[i][1]] = edges[i][0];
        }
        
        visited[0] = true;
        dfs(1, 0, info);
        
        return answer;
    }
    
    boolean[] visited;
    void dfs(int s, int w, int[] info) {
        if(s <= w) {
            return;
        }
        
        for(int i = 1 ; i < visited.length ; i++) {
            if(visited[i]) continue;
            
            if(!visited[link[i]]) continue;
            
            int tmpW = w;
            int tmpS = s;
            if(info[i] == 1) {
                tmpW++;
            } else {
                tmpS++;
            }
            
            if(w < s) {
                visited[i] = true;
                answer = Math.max(answer, tmpS);
                dfs(tmpS, tmpW, info);
                visited[i] = false;
            }
        }
    }
}