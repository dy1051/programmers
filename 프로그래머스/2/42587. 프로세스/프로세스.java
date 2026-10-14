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
        
        int max = pQ.poll();
        while(!pQ.isEmpty()) {
            int i = deque.pollFirst();
            if (max == priorities[i]) {
                answer++;
                max = pQ.poll();

                if(location == i) break;
            } else {
                deque.addLast(i);
            }
        }
        
        if(!deque.isEmpty() && deque.peekFirst() == location) answer++;

        return answer;
    }
}