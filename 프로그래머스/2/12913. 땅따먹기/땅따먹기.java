import java.util.*;
class Solution {
    int solution(int[][] land) {
        int answer = 0;
        int M = land.length;
        int[][] dp = new int[M+1][5];
        
        for(int i = 1; i <= M ; i++) {
            dp[i][1] = Math.max(Math.max(dp[i-1][2], dp[i-1][3]), dp[i-1][4]) + land[i-1][0];
            dp[i][2] = Math.max(Math.max(dp[i-1][1], dp[i-1][3]), dp[i-1][4]) + land[i-1][1];
            dp[i][3] = Math.max(Math.max(dp[i-1][1], dp[i-1][2]), dp[i-1][4]) + land[i-1][2];
            dp[i][4] = Math.max(Math.max(dp[i-1][1], dp[i-1][2]), dp[i-1][3]) + land[i-1][3];
        }
        
        return Math.max(Math.max(Math.max(dp[M][1], dp[M][2]), dp[M][3]), dp[M][4]);
    }
}