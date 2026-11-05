import java.util.*;
class Solution {
    int[][] costs;
    int[] answer = {0, 0};
    public int[] solution(int[][] users, int[] emoticons) {
        
        costs = new int[emoticons.length][4];
        
        for(int i = 0 ; i < emoticons.length ; i++) {
            for(int j = 0 ; j < 4 ; j++) {
                int discount = (j + 1) * 10;
                costs[i][j] = emoticons[i] * (100 - discount) / 100;
            }
        }
        dfs(0, users);
        
        return answer;
    }
    
    Deque<int[]> stack = new ArrayDeque<>();
    void dfs(int idx, int[][] users) {
        if(idx == costs.length) {
            check(users);
            return;
        }
        
        for(int j = 0 ; j < costs[0].length ; j++) {
            stack.offerLast(new int[]{idx, j});
            dfs(idx+1, users);
            stack.pollLast();
        }
    }
    
    void check(int[][] users) {
        ArrayList<int[]> list = new ArrayList<>(stack);
        
        int emoticonPlus = 0;
        int emoticonCost = 0;
        for(int[] user : users) {
            int possible = user[0];
            int maxCost = user[1];
            
            int tmp = 0;
            for(int[] l : list) {
                int cost = costs[l[0]][l[1]];
                int percent = l[1] + 1;
                if (possible <= percent * 10) {
                    tmp += cost;
                }
            }
            if (tmp >= maxCost) {
                emoticonPlus++;
            } else {
                emoticonCost += tmp;
            }
        }
        
        if (answer[0] < emoticonPlus) {
            answer[0] = emoticonPlus;
            answer[1] = emoticonCost;
        } else if (answer[0] == emoticonPlus) {
            answer[1] = Math.max(answer[1], emoticonCost);
        }
    }
}