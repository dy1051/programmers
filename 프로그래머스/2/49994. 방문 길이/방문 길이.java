import java.util.*;
class Solution {
    boolean[][][] check = new boolean[11][11][4];
    public int solution(String dirs) {
        
        bfs(5, dirs);
        
        return answer;
    }
    int answer = 0;
    void bfs(int n, String dirs) {
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        
        int r = 5; int c = 5;
        for(int i = 0 ; i < dirs.length() ; i++) {
            char dir = dirs.charAt(i);
            
            int idx = 0;
            if (dir == 'D')
                idx = 1;
            else if (dir == 'L')
                idx = 2;
            else if (dir == 'R')
                idx = 3;
            
            int x = r + dr[idx]; int y = c + dc[idx];
            
            
            if(x >= 0 && x < 11 && y>=0 && y < 11) {
                if(!check[x][y][idx]) {
                    check[x][y][idx] = true;
                    check[r][c][idx%2==0?idx+1:idx-1] = true;
                    answer++;
                }
                
                r = x; c = y;
            }
        }
        
    }
}