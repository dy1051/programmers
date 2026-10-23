class Solution {
    public int solution(int n) {
        int answer = 0;
        
        int[] prefix = new int[n+1];
        
        for(int i = 1 ; i <= n ; i++) {
            prefix[i] = prefix[i-1] + i;
        }
        
        for(int i = 0 ; i < n ; i++) {
            int result = lowerBound(prefix, n + prefix[i]);
            if(result == n+1) continue;
            answer++;
        }
        
        return answer;
    }
    
    int lowerBound(int[] prefix, int target) {
        int left = 0;
        int right = prefix.length - 1;
        
        while(left <= right) {
            int mid = (left+right)/2;
            
            if(prefix[mid] == target) {
                return mid;
            } else if (prefix[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return prefix.length;
    }
}