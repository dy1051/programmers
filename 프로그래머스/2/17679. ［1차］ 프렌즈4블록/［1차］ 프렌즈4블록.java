import java.util.*;
class Solution {
    boolean[][] check;
    int answer = 0;
    public int solution(int m, int n, String[] board) {        
        check = new boolean[m][n];
        char[][] graph = new char[m][n];
        for(int i = 0 ; i < m ; i++) {
            for (int j = 0 ; j < n ; j++) {
                graph[i][j] = board[i].charAt(j);
            }
        }
        
        while(true) {
            int result = game(graph, m, n);
            if(result == 0)
                break;
            graph = change(graph, m, n);
        }
        
        return answer;
    }
    
    int game(char[][] graph, int m, int n) {
        int[] dr = {0, 1, 1};
        int[] dc = {1, 0, 1};
        
        int res = 0;
        for (int i = 0 ; i <= m-2 ; i++) {
            for (int j = 0 ; j <= n-2 ; j++) {
                char now = graph[i][j];
                boolean flag = true;
                
                if(now == '.') continue;
                
                for(int idx = 0 ; idx < 3 ; idx++) {
                    int x = i + dr[idx];
                    int y = j + dc[idx];
                    
                    if(x>=0 && x < m && y>=0 && y<n && graph[x][y] != now) {
                        flag = false;
                    }
                }
                
                if(flag) {
                    res++;
                    for(int idx = 0 ; idx < 3 ; idx++) {
                        int x = i + dr[idx];
                        int y = j + dc[idx];
                        check[x][y] = true;
                    }
                    check[i][j] = true;
                }
            }
        }
        
        return res;
    }
    
    char[][] change(char[][] graph, int m, int n) {
        char[][] result = new char[m][n];
        
        for(int i = 0 ; i < m ;i++)
            Arrays.fill(result[i], '.');
        
        for(int j = 0 ; j < n ; j++) {
            List<Character> tmp = new ArrayList<>();
            for (int i = m-1 ; i >= 0 ; i--) {
                if(!check[i][j])
                    tmp.add(graph[i][j]);
                else
                    answer++;
            }
            
            for(int i = 0 ; i < tmp.size() ; i++) {
                result[m-1-i][j] = tmp.get(i);
            }
        }
    
        for(int i = 0 ; i < m ;i++)
            Arrays.fill(check[i], false);
        return result;
    }
}