import java.util.*;
class Solution {
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        
        Deque<Integer> stack = new ArrayDeque<>();
        
        for (int i = 0 ; i < numbers.length ; i++) {
            if (stack.isEmpty()) {
                stack.addLast(i);
            } else {
                while(!stack.isEmpty() && numbers[stack.peekLast()] < numbers[i]) {
                    int poll = stack.pollLast();
                    answer[poll] = numbers[i];
                }
                stack.addLast(i);
            }
        }
        
        while(!stack.isEmpty()) {
            answer[stack.pollLast()] = -1;
        }
        
        return answer;
    }
}