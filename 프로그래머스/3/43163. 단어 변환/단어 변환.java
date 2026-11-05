import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        int answer = Integer.MAX_VALUE;
        
        for(int idx = 0 ; idx < words.length ; idx++) {
            String next = words[idx];
            
            if (countDiff(begin, next) == 1) {
                int res = bfs(idx, words, target);
                answer = res == 0 ? answer : Math.min(answer, res);
            }
        }
        
        
        return answer == Integer.MAX_VALUE ? 0 : answer;
    }
    
    int bfs(int idx, String[] words, String target) {
        Deque<Integer> queue = new ArrayDeque<>();
        int[] dist = new int[words.length];
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        queue.offerLast(idx);
        dist[idx] = 1;
        
        int result = 0;
        while (!queue.isEmpty()) {
            int now = queue.pollFirst();
            String nowStr = words[now];
            
            if(countDiff(nowStr, target) == 0) {
                result = dist[now];
                break;
            }
            
            for(int i = 0 ; i < words.length ; i++) {
                if (dist[i] <= dist[now] + 1) continue;
                
                int res = countDiff(nowStr, words[i]);
                if (res > 1) {
                    continue;
                } else if (res == 1) {
                    dist[i] = dist[now] + 1;
                    queue.offerLast(i);
                }
            }
        }
        
        return result;
    }
    
    int countDiff(String base, String comp) {
        int cnt = 0;
        for(int i = 0 ; i < base.length() ; i++) {
            if(base.charAt(i) != comp.charAt(i)) {
                cnt++;
            }
        }
        return cnt;
    }
}