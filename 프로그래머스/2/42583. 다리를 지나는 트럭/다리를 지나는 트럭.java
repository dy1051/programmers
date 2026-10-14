import java.util.*;
class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        
        Deque<Integer> deque = new ArrayDeque<>();
        Deque<Integer> bridge = new ArrayDeque<>();
        
        for(int truck : truck_weights) deque.addLast(truck);
        for(int i = 0 ; i < bridge_length ; i++) bridge.add(0);
        
        
        int total = 0;
        while(!deque.isEmpty()) {
            int tmp = deque.peekFirst();
            
            total -= bridge.pollFirst();
            answer++;
            
            if (total + tmp > weight) {
                bridge.addLast(0);
            } else {
                total += deque.pollFirst();
                bridge.addLast(tmp);
            }
        }
        
        return answer + bridge.size();
    }
}