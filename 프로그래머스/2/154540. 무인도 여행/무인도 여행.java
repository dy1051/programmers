import java.util.*;
class Solution {
    Deque<int[]> queue = new ArrayDeque<>();
    List<Integer> answer = new ArrayList<>();
    boolean[][] visited;
    
    public int[] solution(String[] maps) {
        int rows = maps.length;
        int cols = maps[0].length();
        
        String[][] graph = new String[rows][cols];
        
        for (int i = 0 ; i < rows ; i++) {
            for (int j = 0 ; j < cols ; j++) {
                graph[i][j] = maps[i].charAt(j) + "";
            }
        }
        
        visited = new boolean[rows][cols];
        
        for(int i = 0 ; i < rows ; i++) {
            for(int j = 0 ; j < cols ; j++) {
                int cnt = 0;
                if(!graph[i][j].equals("X") && !visited[i][j]) {
                    visited[i][j] = true;
                    queue.addLast(new int[]{i, j});
                    cnt = bfs(graph, rows, cols, cnt);
                }
                if (cnt > 0)
                    answer.add(cnt);
            }
        }
        
        if (answer.size() > 0)
            Collections.sort(answer);
        else
            answer.add(-1);
        
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
    
    int bfs(String[][] graph, int rows, int cols, int cnt) {
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        
        while(!queue.isEmpty()) {
            int[] now = queue.pollFirst();
            
            if(!graph[now[0]][now[1]].equals("X")) 
                cnt += Integer.parseInt(graph[now[0]][now[1]]);
            
                
            
            for (int i = 0 ; i < 4 ; i++) {
                int x = now[0] + dr[i];
                int y = now[1] + dc[i];
                
                if(x >= 0 && x < rows && y >= 0 && y < cols && !visited[x][y] && !graph[x][y].equals("X")) {
                    visited[x][y] = true;
                    
                    queue.addLast(new int[]{x, y});
                }
            }
        }
        
        return cnt;
    }
}