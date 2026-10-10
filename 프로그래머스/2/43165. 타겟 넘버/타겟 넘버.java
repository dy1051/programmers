class Solution {
    public int solution(int[] numbers, int target) {
        
        dfs(0, target, 0, numbers);
        
        return answer;
    }
    
    int answer = 0;
    void dfs(int n, int target, int sum, int[] numbers) {
        if (n == numbers.length) {
            if (sum == target) {
                answer++;
            }
            return;
        }
                   
        dfs(n+1, target, sum + numbers[n], numbers);
        dfs(n+1, target, sum - numbers[n], numbers);
        
    }
    
}