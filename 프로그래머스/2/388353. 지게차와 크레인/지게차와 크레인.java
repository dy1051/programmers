import java.util.*;
class Solution {
    int M; int N;
    char[][] graph;
    public int solution(String[] storage, String[] requests) {
        int answer = 0;
        M = storage.length; N = storage[0].length();
        graph = new char[M+2][N+2];
        for(int i = 0 ; i < M+2 ; i++)
            Arrays.fill(graph[i], '.');
        
        for(int i = 1 ; i < M + 1 ; i++) {
            for(int j = 1 ; j < N + 1; j++) {
                graph[i][j] = storage[i-1].charAt(j-1);
            }
        }
        for(String request : requests) {
            if (request.length() == 2) {
                bfsAll(request.charAt(0));
            } else {
                bfs(request.charAt(0));
            }
        }
        
        for(int i = 1 ; i < M+1 ; i++) {
            for(int j = 1 ; j < N + 1; j++) {
                if(graph[i][j] != '.') answer++;
            }
        }
            
        
        return answer;
    }
    
    void bfsAll(char target) {
        Deque<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[M+2][N+2];
        
        queue.offerLast(new int[]{1,1});
        visited[1][1] = true;
        
        if(graph[1][1] == target)
            graph[1][1] = '.';
        
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        
        while (!queue.isEmpty()) {
            int[] now = queue.pollFirst();
            
            for(int i = 0 ; i < 4 ; i++) {
                int x = now[0] + dr[i];
                int y = now[1] + dc[i];
                
                if(x>=1 && x<M+1 && y>=1 && y<N+1 && !visited[x][y]) {
                    visited[x][y] = true;
                    if(graph[x][y] == target)
                        graph[x][y] = '.';
                    queue.offerLast(new int[]{x, y});
                }
            }
        }
    }
    
    void bfs(char target) {
        Deque<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[M+2][N+2];
        List<int[]> list = new ArrayList<>();
        
        queue.offerLast(new int[]{0, 0});
        visited[0][0] = true;
        
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        
        while(!queue.isEmpty()) {
            int[] now = queue.pollFirst();
            
            for(int i = 0 ; i < 4 ; i++) {
                int x = now[0] + dr[i];
                int y = now[1] + dc[i];
                
                if(x>=0 && x<M+2 && y>=0 && y<N+2 && !visited[x][y]) {
                    visited[x][y] = true;
                    if (graph[x][y] == '.') {
                        queue.offerLast(new int[]{x,y});
                    } else if(graph[x][y] == target) {
                        list.add(new int[]{x,y});
                    }
                }
            }
        }
        
        for(int[] l : list)
            graph[l[0]][l[1]] = '.';
    }
}