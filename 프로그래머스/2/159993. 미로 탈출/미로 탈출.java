import java.util.*;
class Solution {
    // 1. 상태: 현재 내 위치에서 갈 수 있는 곳
    // 2. 이동: 벽이 아닌 곳으로 이동 가능
    // 3. 종료: S에서 시작했을 때는 L을 만나면 종료. L에서 시작했을 땐 E를 만났을때 종료
    char[][] graph;
    int[][] check;
    int M = 0; int N = 0;
    public int solution(String[] maps) {
        int answer = 0;
        
        M = maps.length; N = maps[0].length();
        graph = new char[M][N];
        check = new int[M][N];
        
        for(int i = 0 ; i < M ; i++)
            Arrays.fill(check[i], Integer.MAX_VALUE);
        
        int startX = 0; int startY = 0;
        int leverX = 0; int leverY = 0;
        int exitX = 0; int exitY = 0;
        
        for (int i = 0 ; i < M ; i++) {
            for (int j = 0 ; j < N ; j++) {
                graph[i][j] = maps[i].charAt(j);
                if (graph[i][j] == 'S') {
                    startX = i ; startY = j;
                } else if (graph[i][j] == 'L') {
                    leverX = i; leverY = j;
                } else if (graph[i][j] == 'E') {
                    exitX = i; exitY = j;
                }
            }
        }
        
        bfs(startX, startY, leverX, leverY, 0);
        if(check[leverX][leverY] == Integer.MAX_VALUE) return -1;
        
        int tmp = check[leverX][leverY];
        check = new int[M][N];
        
        for(int i = 0 ; i < M ; i++)
            Arrays.fill(check[i], Integer.MAX_VALUE);
        
        bfs(leverX, leverY, exitX, exitY, tmp);
        
        return check[exitX][exitY] == Integer.MAX_VALUE ? -1 : check[exitX][exitY];
    }
    
    void bfs(int startX, int startY, int endX, int endY, int cnt) {
        Deque<int[]> queue = new ArrayDeque<>();
        queue.addLast(new int[]{startX, startY, cnt});
        check[startX][startY] = cnt;
        
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        
        while (!queue.isEmpty()) {
            int[] now = queue.pollFirst();
            int r = now[0];
            int c = now[1];
            int count = now[2];
            
            for (int i = 0 ; i < 4 ; i++) {
                int x = r + dr[i];
                int y = c + dc[i];
                
                if(x>=0 && x<M && y>=0 && y<N && graph[x][y] != 'X') {
                    if(check[x][y] > count+1) {
                        check[x][y] = count+1;
                        queue.addLast(new int[]{x, y, count+1});
                    }  
                }
            }
        }
    }
}