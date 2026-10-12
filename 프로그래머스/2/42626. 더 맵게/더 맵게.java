import java.util.*;
class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        
        for(int i = 0 ; i < scoville.length ; i++) {
            heap.add(scoville[i]);
        }
        
        
        while(!heap.isEmpty()) {
            int first = heap.poll();
            
            if(first >= K) break;
            
            if(heap.isEmpty()) {
                if (first < K) answer = -1;
                break;
            }
            
            int second = heap.poll();
            
            int tmp = first + (second * 2);
            
            heap.add(tmp);
            
            answer++;
        }
        
        return answer;
    }
}