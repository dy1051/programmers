import java.util.*;
class Solution {
    ArrayList<String> answer = new ArrayList<>();
    public String[] solution(String[][] tickets) {
        visited = new boolean[tickets.length];
        dfs("ICN", tickets);
        
        return answer.toArray(String[]::new);
    }
    
    boolean[] visited;
    Deque<Integer> deque = new ArrayDeque<>();
    void dfs(String start, String[][] tickets) {
        if(deque.size() == tickets.length){
            ArrayList<Integer> tmp = new ArrayList<>(deque);
            boolean flag = true;
            
            if (answer.size() != 0) {
                for(int i = 0 ; i < deque.size() ; i++) {
                    String now = tickets[tmp.get(i)][0];
                    if (answer.get(i).compareTo(now) > 0) {
                        flag = false;
                        break;
                    } else if(answer.get(i).compareTo(now) < 0) {
                        break;
                    }
                }
            } else {
                flag = false;
            }
            
            if(!flag) {
                answer.clear();
                for(int s : tmp) {
                    answer.add(tickets[s][0]);
                }
                answer.add(tickets[tmp.get(tmp.size()-1)][1]);
            }
        }
        
        for(int i = 0 ; i < tickets.length ; i++) {
            if(visited[i]) continue;
            
            if(start.equals(tickets[i][0])) {
                visited[i] = true;
                deque.offerLast(i);
                dfs(tickets[i][1], tickets);
                deque.pollLast();
                visited[i] = false;
            }
        }
    }
}