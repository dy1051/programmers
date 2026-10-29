import java.util.*;

class Solution {
    int[] answer = {-1};
    public int[] solution(int n, int[] info) {
        dfs(0, n, info);
        return answer;
    }
    
    int[] lion = new int[11];
    void dfs(int idx, int remain, int[] info) {
        if(idx == 11) {
            lion[10] = remain;
            check(info);
            lion[10] = 0;
            return;
        }
        
        int need = info[idx] + 1;
        if(remain >= need) {
            lion[idx] = need;
            dfs(idx+1, remain - need, info);
            lion[idx] = 0;
        }
        
        dfs(idx+1, remain, info);
    }
    
    int maxDiff = 0;
    void check(int[] info) {
        int lionScore = 0;
        int apeachScore = 0;
        
        for (int i = 0; i < 10; i++) {
            int score = 10 - i;

            if (lion[i] > info[i]) {
                lionScore += score;
            } else if (info[i] > 0) {
                apeachScore += score;
            }
        }
        
        int diff = lionScore - apeachScore;
        if (diff <= 0) return;

        if (diff > maxDiff) {
            maxDiff = diff;
            answer = lion.clone();
        } else if (diff == maxDiff) {
            for(int i = 10 ; i >= 0 ; i--) {
                if (lion[i] > answer[i]) {
                    answer = lion.clone();
                    break;
                } else if (lion[i] < answer[i]) {
                    break;
                }
            }
        }
        
    }
}