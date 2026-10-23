import java.util.*;
class Solution {
    boolean[][] visited;
    char[][] graph;
    public int solution(int m, int n, String[] board) {
        int answer = 0;
        
        graph = new char[m][n];
        
        for(int i = 0 ; i < m ; i++) {
            for(int j = 0 ; j < n ; j++) {
                graph[i][j] = board[i].charAt(j);
            }
        }
        
        while(true) {
            visited = new boolean[m][n];
        
            boolean check = checkGraph(m, n);
        
            if(check) {
                answer += changeGraph(m, n);
            } else {
                break;
            }
        }
        
        
        return answer;
    }
    
    boolean checkGraph(int m, int n) {
        boolean check = false;
        
        int[] dr = {0, 1, 1};
        int[] dc = {1, 0, 1};
        
        for(int i = 0 ; i < m-1 ; i++) {
            for (int j = 0 ; j < n-1 ; j++) {
                char now = graph[i][j];
                if(now == '.') continue;
                boolean flag = true;
                for(int idx = 0 ; idx < 3 ; idx++) {
                    char next = graph[i+dr[idx]][j+dc[idx]];
                    if(now != next) {
                        flag = false;
                        break;
                    }
                }
                
                if(flag) {
                    visited[i][j] = true;
                    for(int idx = 0 ; idx < 3 ; idx++) {
                        visited[i+dr[idx]][j+dc[idx]] = true;
                    }
                    check = true;
                }
            }
        }
        return check;
    }
    int changeGraph(int m, int n) {
        int result = 0;
        Deque<Character> queue = new ArrayDeque<>();
        
        for(int j = 0 ; j < n ; j++) {
            for(int i = m-1 ; i >= 0 ; i--) {
                if(!visited[i][j]) {
                    queue.addLast(graph[i][j]);
                } else {
                    result++;
                }
            }
            for(int i = m-1 ; i >= 0 ; i--) {
                if(queue.isEmpty()) {
                    graph[i][j] = '.';
                } else {
                    graph[i][j] = queue.pollFirst();
                }
            }
        }
        
        return result;
    }
}