import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        
        Deque<Integer> deque = new ArrayDeque<>();
        
        for(int i = 0 ; i < prices.length ; i++) {
            int now = prices[i];
            
            if (deque.isEmpty()) {
                deque.addLast(i);
            } else {
                int peek = deque.peekLast();

                if (prices[peek] > now) {
                    while(!deque.isEmpty()) {
                        int poll = deque.pollLast();
                        answer[poll] = i - poll;
                        
                        if(prices[poll] <= now) {
                            deque.addLast(poll);
                            break;
                        }
                    }
                }
                
                deque.addLast(i);
            }
        }
        
        if (!deque.isEmpty()) {
            int peek = deque.peekLast();
            
            while(!deque.isEmpty()) {
                int poll = deque.pollLast();
                
                answer[poll] = peek - poll;
            }
        }
        
        return answer;
    }
}