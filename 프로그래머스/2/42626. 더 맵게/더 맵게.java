import java.util.*;
class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        
        PriorityQueue<Integer> pQ = new PriorityQueue<>();
        
        for(int s : scoville) pQ.add(s);
        
        while(!pQ.isEmpty() && pQ.size() > 1) {
            int first = pQ.poll();
            
            if (first < K) {
                int second = pQ.poll();
                pQ.add(first + second * 2);
                answer++;
            } else {
                pQ.add(first);
                break;
            }
        } 
        
        if (pQ.peek() < K) answer = -1;
        
        return answer;
    }
}