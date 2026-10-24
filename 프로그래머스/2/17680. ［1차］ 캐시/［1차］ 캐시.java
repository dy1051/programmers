import java.util.*;
class Solution {
    public int solution(int cacheSize, String[] cities) {
        if(cacheSize == 0) return cities.length * 5;
        
        int answer = 0;
        
        int[] cacheIdx = new int[cacheSize];
        Arrays.fill(cacheIdx, -1);
 
        for(int i = 0 ; i < cities.length ; i++) {
            boolean flag = false;
            for(int ci = 0 ; ci < cacheSize ; ci++) {
                if (cacheIdx[ci] != -1 && cities[cacheIdx[ci]].equalsIgnoreCase(cities[i])) {
                    flag = true;
                    cacheIdx[ci] = i;
                    break;
                }
            }
            
            if (flag) {
                answer++;
            } else {
                answer += 5;
                int minVal = cacheIdx[0]; int minCIdx = 0;
                for(int ci = 1 ; ci < cacheSize ; ci++) {
                    if(minVal > cacheIdx[ci]) {
                        minVal = cacheIdx[ci];
                        minCIdx = ci;
                    }
                }

                cacheIdx[minCIdx] = i;
            }
            
        }
        
        return answer;
    }
}