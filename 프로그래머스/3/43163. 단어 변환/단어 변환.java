import java.util.*;
class Solution {
    int answer = Integer.MAX_VALUE;
    public int solution(String begin, String target, String[] words) {
        
        visited = new boolean[words.length];
        dfs(0, begin, target, words);
        
        return answer == Integer.MAX_VALUE ? 0 : answer;
    }
    
    Deque<String> queue = new ArrayDeque<>();
    boolean[] visited;
    void dfs(int n, String begin, String target, String[] words) {
        if(!queue.isEmpty() && answer > queue.size()) {
            String tmp = queue.peekLast();
            if(tmp.equals(target)) {
                answer = Math.min(answer, queue.size());
                return;
            }
        }
        if(n == words.length) return;
        
        for(int i = 0 ; i < words.length ; i++) {
            if(visited[i]) continue;
            
            if(!check(begin, words[i])) {
                continue;
            }
            
            visited[i] = true;
            queue.addLast(words[i]);
            dfs(n+1, words[i], target, words);
            queue.pollLast();
            visited[i] = false;
        }
    }
    
    boolean check(String begin, String word) {
        int cnt = 0; 
        for(int i = 0 ; i < begin.length() ; i++) {
            if(begin.charAt(i) != word.charAt(i)) {
                cnt++;
            }
            if(cnt > 1)
                return false;
        }
        return true;
    }
}