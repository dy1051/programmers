class Solution {
    int answer = 0;
    public int solution(int[] numbers, int target) {
        dfs(0, numbers, target, 0);
        return answer;
    }
    
    void dfs(int idx, int[] numbers, int target, int sum) {
        if(idx == numbers.length) {
            if (target == sum)
                answer++;
            return;
        }
        
        dfs(idx+1, numbers, target, sum+numbers[idx]);
        dfs(idx+1, numbers, target, sum-numbers[idx]);
    }
}