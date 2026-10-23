import java.util.*;
class Solution {
    public long solution(int n, int[] times) {
        Arrays.sort(times);
        
        long answer = (long)times[0] * n;
        long left = 1;
        long right = (long)times[0] * n;
        
        while(left <= right) {
            long mid = (left + right) / 2;
            
            long count = 0;
            for(long time : times) {
                if(mid >= time) {
                    count += mid / time;
                }
            }
            
            if(count >= n) {
                answer = Math.min(answer, mid);
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        return answer;
    }
}