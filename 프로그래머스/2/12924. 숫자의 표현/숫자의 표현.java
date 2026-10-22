class Solution {
    public int solution(int n) {
        int answer = 0;
        
        
        int right = 1;
        int left = 1;
        int sum = 1;
        
        while(left <= n) {
            if(sum < n) {
                if(right == n) break;
                right++;
                sum += right;
            } else if(sum > n) {
                sum -= left;
                left++;
            } else {
                answer++;
                sum -= left;
                left++;
            }
        }
        
        return answer;
    }
}