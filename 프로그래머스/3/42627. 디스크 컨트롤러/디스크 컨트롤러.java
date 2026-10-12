import java.util.*;
class Solution {
    public int solution(int[][] jobs) {
        int L = jobs.length;
        int answer = 0;
        
        ArrayList<Disk> list = new ArrayList<>();
        
        for(int i = 0 ; i < L ; i++) {
            list.add(new Disk(i, jobs[i][0], jobs[i][1]));
        }
        
        list.sort((d1, d2) -> Integer.compare(d1.start(), d2.start()));
        
        PriorityQueue<Disk> pQ = new PriorityQueue<>((t1, t2) -> {
            if(t1.time() == t2.time()) {
                if(t1.start() == t2.start()) 
                    return t1.num() - t2.num();
                
                return t1.start() - t2.start();
            }
            return t1.time() - t2.time();
        });
        
        int t = 0; int idx = 0;
        
        while (idx < L || !pQ.isEmpty()) {
            
            while (idx < L && list.get(idx).start() <= t) {
                pQ.add(list.get(idx));
                idx++;
            }
            
            if (pQ.isEmpty()) {
                t = list.get(idx).start();
                continue;
            }
            
            Disk now = pQ.poll();
            t += now.time();
            
            answer += t - now.start();
        }
        
        return answer / L;
    }
}

record Disk(
    int num,
    int start,
    int time
){}