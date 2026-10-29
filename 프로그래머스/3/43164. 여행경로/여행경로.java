import java.util.*;
class Solution {
    Map<String, Map<String, Integer>> map = new HashMap<>();
    List<String> l = new ArrayList<>();
    public String[] solution(String[][] tickets) {
        String[] answer = {};
        
        boolean[] visited = new boolean[tickets.length];
        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < tickets.length; i++) {
            if (!tickets[i][0].equals("ICN")) continue;

            visited[i] = true;
            deque.offerLast(i);

            dfs(i, tickets, visited, deque);

            deque.pollLast();
            visited[i] = false;
        }
        
        return l.stream().toArray(String[]::new);
    }
    
    void dfs(int now, String[][] tickets, boolean[] visited, Deque<Integer> deque) {
        if (deque.size() == tickets.length) {
            List<String> route = new ArrayList<>();
            route.add("ICN");

            for (int ticketIdx : deque) {
                route.add(tickets[ticketIdx][1]);
            }

            if (l.isEmpty()) l = route;
            else {
                for(int i = 0 ; i < tickets.length ; i++) {
                    if (l.get(i).compareTo(route.get(i)) > 0) {
                        l = route;
                        break;
                    } else if (l.get(i).compareTo(route.get(i)) < 0) {
                        break;
                    }
                }
            }
            return;
        }
        
        for(int i = 0 ; i < tickets.length ; i++) {
            if(visited[i]) continue;
            if(tickets[now][1].equals(tickets[i][0])) {
                deque.offerLast(i);
                visited[i] = true;
                dfs(i, tickets, visited, deque);
                deque.pollLast();
                visited[i] = false;
            }
        }
    }
}