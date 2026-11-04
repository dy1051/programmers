import java.util.*;
class Solution {
    public long solution(String expression) {
        long answer = 0;
        
        answer = Math.max(answer, Math.abs(calculate(expression, "+-*")));
        answer = Math.max(answer, Math.abs(calculate(expression, "+*-")));
        answer = Math.max(answer, Math.abs(calculate(expression, "-*+")));
        answer = Math.max(answer, Math.abs(calculate(expression, "-+*")));
        answer = Math.max(answer, Math.abs(calculate(expression, "*+-")));
        answer = Math.max(answer, Math.abs(calculate(expression, "*-+")));
        
        return answer;
    }
    
    
    Deque<String> makeExp(String expression) {
        Deque<String> exp = new ArrayDeque<>();
    
        String tmp = "";
        for(char c : expression.toCharArray()) {
            if (c == '+' || c == '-' || c == '*') {
                exp.offerLast(tmp);
                tmp = "";
                exp.offerLast(c + "");
            } else {
                tmp += c+"";
            }
        }
       
        exp.add(tmp);
    
        return exp;
    }
    
    long calculate(String expression, String calc) {        
        Deque<String> origin = makeExp(expression);
        
        for(String c : calc.split("")) {
            origin = func(c, origin);
        }
        
        return Long.parseLong(origin.pollLast());
    }
    
    Deque<String> func(String target, Deque<String> exp) {
        Deque<String> stack = new ArrayDeque<>();
        stack.offerLast(exp.pollFirst());
        
        while (!exp.isEmpty()) {
            String now = exp.pollFirst();
            
            try {
                long nowInt = Long.parseLong(now);
                String bef = stack.pollLast();
                
                if(bef.equals(target)) {
                    long befInt = Long.parseLong(stack.pollLast());
                    
                    if (bef.equals("+")) {
                        stack.offerLast((nowInt + befInt)+"");
                    } else if (bef.equals("-")) {
                        stack.offerLast((befInt - nowInt)+"");
                    } else if (bef.equals("*")) {
                        stack.offerLast((nowInt * befInt)+"");
                    }
                } else {
                    stack.offerLast(bef);
                    stack.offerLast(now);
                }
            } catch(Exception e) {
                stack.offerLast(now);
            }
        }
        
        return stack;
    }
}