import java.util.*;
class Solution {
    boolean solution(String s) {
        boolean answer = true;

        Deque<Character> deque = new ArrayDeque<>();
        
        for(int i = 0 ; i < s.length() ; i++) {
            char now = s.charAt(i);
            
            if (now == '(') {
                deque.addLast(now);
            } else {
                if (deque.isEmpty()) {
                    answer = false;
                    break;
                } else {
                    deque.pollLast();
                }
            }
            
        }
        
        if (answer) {
            if (!deque.isEmpty()) answer = false;
        }
        

        return answer;
    }
}