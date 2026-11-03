import java.util.*;
class Solution {
    ArrayList<Integer> answer = new ArrayList<>();
    public int[] solution(String s) {        
        List<List<Integer>> list = new ArrayList<>();
        s = s.substring(2, s.length() - 2);
        s = s.replace("},{", "/");
        
        String[] tmp = s.split("/");
        
        for(String t : tmp) {
            List<Integer> l = Arrays.stream(t.split(",")).map(ss -> Integer.parseInt(ss)).toList();
            list.add(l);
        }
        
        Collections.sort(list, (a, b) -> {
           return a.size() - b.size(); 
        });
        
       
        compare(list);
        
        
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
    
    void compare(List<List<Integer>> compare) {
        Set<Integer> seen = new HashSet<>();
        
        for(List<Integer> list : compare) {
            for(int value : list) {
                if(seen.add(value)) {
                    answer.add(value);
                }
            }
        }
    }
}