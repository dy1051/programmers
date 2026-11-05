import java.util.*;

class Solution {
    Deque<Integer> queue = new ArrayDeque<>();
    int[] dist;
    public int solution(String begin, String target, String[] words) {
        int answer = Integer.MAX_VALUE;
        
        dist = new int[words.length];
        Arrays.fill(dist, -1);
        
        for(int idx = 0 ; idx < words.length ; idx++) {
            String next = words[idx];
            
            if (countDiff(begin, next)) {
                queue.offerLast(idx);
                dist[idx] = 1;
            }
        }
        
        answer = bfs(words, target);
        
        return answer;
    }
    
    int bfs(String[] words, String target) {
        int result = 0;
        while (!queue.isEmpty()) {
            int now = queue.pollFirst();
            String nowStr = words[now];
            
            if(nowStr.equals(target)) {
                result = dist[now];
                break;
            }
            
            for(int i = 0 ; i < words.length ; i++) {
                if (dist[i] != -1) continue;
                
                if (countDiff(nowStr, words[i])) {
                    dist[i] = dist[now] + 1;
                    queue.offerLast(i);
                }
            }
        }
        
        return result;
    }
    
     boolean countDiff(String base, String comp) {
        int cnt = 0;
        for(int i = 0 ; i < base.length() ; i++) {
            if(base.charAt(i) != comp.charAt(i)) {
                cnt++;
            }
        }
        return cnt == 1;
    }
}