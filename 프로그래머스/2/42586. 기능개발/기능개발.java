import java.util.*;
class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        Deque<Integer> deque = new ArrayDeque<>();
        
        ArrayList<Integer> list = new ArrayList<>();
        
        for(int i = 0 ; i < speeds.length ; i++) {
            int remain = (100 - progresses[i]) % speeds[i] == 0 ?
                        (100 - progresses[i]) / speeds[i] : (100 - progresses[i]) / speeds[i] + 1;
            if (deque.isEmpty()) {
                deque.addLast(remain);
            } else {
                int peek = deque.peekFirst();
                
                if (peek >= remain) {
                    deque.addLast(remain);
                } else {
                    list.add(deque.size());
                    deque.clear();
                    deque.addLast(remain);
                }
                
            }
        }
        
        if (!deque.isEmpty())
            list.add(deque.size());
        
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}