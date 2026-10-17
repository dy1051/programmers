import java.util.*;
class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        
        Deque<Integer> deque = new ArrayDeque<>();
        PriorityQueue<Integer> pQ = new PriorityQueue<>(Collections.reverseOrder());
        
        for(int i = 0 ; i < priorities.length ; i++) {
            deque.addLast(i);
            pQ.add(priorities[i]);
        }
        
        while(!deque.isEmpty()) {
            int poll = pQ.poll();
            int idx = deque.pollFirst();
            
            if(priorities[idx] == poll) {
                answer++;
                if(idx == location) {
                    break;
                }
            } else {
                deque.addLast(idx);
                pQ.add(poll);
            }
        }
        
        return answer;
    }
}