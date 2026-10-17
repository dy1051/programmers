class Solution {
    public int solution(int[] numbers, int target) {
        dfs(0, numbers.length, 0, target, numbers);
        return answer;
    }
    
    int answer = 0;
    void dfs(int n, int L, int sum, int target, int[] numbers) {
        if(n == L) {
            if(sum == target)
                answer += 1;
            return;
        }
        
        dfs(n+1, L, sum + numbers[n], target, numbers);
        dfs(n+1, L, sum - numbers[n], target, numbers);
        
    }
}