import java.util.*;
class Solution {
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        
        answer[numbers.length - 1] = -1;
        
        for(int i = numbers.length - 2 ; i >= 0 ; i--) {
            if (numbers[i] < numbers[i+1]) {
                answer[i] = i+1;
            } else {
                Deque<Integer> deque = new ArrayDeque<>();
                
                int tmp = i+1;
                while (answer[tmp] != -1) {
                    deque.addLast(answer[tmp]);
                    tmp = answer[tmp];
                }
                answer[i] = -1;
                
                while(!deque.isEmpty()) {
                    int poll = deque.pollFirst();
                    
                    if (numbers[i] < numbers[poll]) {
                        answer[i] = poll;
                        break;
                    }
                }
            }
        }
        
        for(int i = 0 ; i < answer.length ; i++) {
            if(answer[i] != -1)
                answer[i] = numbers[answer[i]];
        }
        
        return answer;
    }
}