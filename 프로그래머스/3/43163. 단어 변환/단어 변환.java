import java.util.*;
class Solution {
    public int solution(String begin, String target, String[] words) {
        boolean flag = false;
        for(int i = 0 ; i < words.length ; i++) {
            if(words[i].equals(target)) {
                flag = true;
                break;
            }
        }
        
        if(!flag) return 0;
        
        int answer = Integer.MAX_VALUE;
        
        for(int i = 0 ; i < words.length ; i++) {
            int result = check(words[i], begin);
            
            if(result == 0) {
                answer = 1;
                break;
            } else if (result == 1) {
                answer = Math.min(answer, bfs(i, words[i], target, words));
            }
        }
        
        return answer;
    }
    
    int bfs(int idx, String begin, String target, String[] words) {
        int result = 1;
        
        Deque<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[words.length];
        int[] dist = new int[words.length];
        
        queue.offerLast(idx);
        visited[idx] = true;
        dist[idx] = 1;
        
        while (!queue.isEmpty()) {
            int now = queue.pollFirst();
            String nowStr = words[now];
            
            if (nowStr.equals(target)) {
                return dist[now];
            }
            
            for(int i = 0 ; i < words.length ; i++) {
                if(visited[i]) continue;
                
                int res = check(nowStr, words[i]);
                
                if (res != 1) continue;

                dist[i] = dist[now] + 1;
                visited[i] = true;
                queue.offerLast(i);
                
            }
            
        }
        
        
        return result;
    }
    
    int check(String now, String check) {
        int cnt = 0;
        for(int i = 0 ; i < now.length() ; i++) {
            if(now.charAt(i) != check.charAt(i)) {
                cnt++;
            }
        }
        return cnt;
    }
}