import java.util.*;
class Solution {
    public int solution(String numbers) {
        String[] nums = numbers.split("");
        boolean[] visited = new boolean[nums.length];
        
        dfs(0, nums, visited, "");
                
        return set.size();
    }
    
    Set<Integer> set = new HashSet<>();
    void dfs(int n, String[] nums, boolean[] visited, String str) {
        if(!str.equals("") && Integer.parseInt(str) > 1) {
            boolean flag = false;
            for(int c = 2 ; c <= Math.sqrt(Integer.parseInt(str)) ; c++) {
                if (Integer.parseInt(str) % c == 0)
                    flag = true;
            }

            if(!flag)
                set.add(Integer.parseInt(str));            
        }

        for(int i = 0 ; i < nums.length ; i++) {
            if (visited[i])
                continue;
            
            visited[i] = true;
            dfs(n+1, nums, visited, str+nums[i]);
            visited[i] = false;
        }
        
    }
}