class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        int M = triangle.length;
        
        int[][] newTriangle = new int[M][M];
        for(int i = 0 ; i < M ; i++) {
            for(int j = 0 ; j < triangle[i].length ; j++) {
                newTriangle[i][j] = triangle[i][j];
            }
        }
        
        int[][] dp = new int[M + 1][M + 1];
        
        for(int i = 1 ; i <= M ; i++) {
            for(int j = 1 ; j <= M ; j++) {
                dp[i][j] = Math.max(dp[i-1][j-1], dp[i-1][j])+newTriangle[i-1][j-1];
            }
        }
        
        for(int i = 1; i <= M ; i++) {
            answer = Math.max(answer, dp[M][i]);
        }
        
        
        return answer;
    }
}