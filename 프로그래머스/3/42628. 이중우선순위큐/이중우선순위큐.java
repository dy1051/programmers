import java.util.*;
class Solution {
    public int[] solution(String[] operations) {
        int[] answer = new int[2];
        
        PriorityQueue<Integer> pQ = new PriorityQueue<>();
        
        for (String operation : operations) {
            String[] oper = operation.split(" ");
            
            if (oper[0].equals("I")) {
                pQ.add(Integer.parseInt(oper[1]));
            } else if (oper[0].equals("D")) {
                if(Integer.parseInt(oper[1]) == 1) {
                    PriorityQueue<Integer> tmp = new PriorityQueue<>(Collections.reverseOrder());
                    tmp.addAll(pQ);
                    pQ.clear();
                    
                    tmp.poll();
                    pQ.addAll(tmp);
                } else {
                    pQ.poll();
                }
            }
        }
    
        if(!pQ.isEmpty()) {
            answer[1] = pQ.poll();
            answer[0] = answer[1];
            
            if (!pQ.isEmpty()) {
                PriorityQueue<Integer> tmp = new PriorityQueue<>(Collections.reverseOrder());
                tmp.addAll(pQ);
                answer[0] = tmp.poll();
            }      
        }
        
        return answer;
    }
}