import java.util.*;
class Solution {
    public int[] solution(String s) {
        ArrayList<Integer> answer = new ArrayList<>();
        
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
        
       
        List<Integer> base = list.get(0);
        answer.add(list.get(0).get(0));
        
        for(int i = 1 ; i < list.size() ; i++) {
            List<Integer> compare = list.get(i);
            int res = minus(base, compare);
            answer.add(res);
            base = compare;
        }
        
        
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
    
    int minus(List<Integer> base, List<Integer> compare) {
        HashMap<Integer, Integer> baseMap = new HashMap<>();
        for(int i : base) {
            baseMap.put(i, baseMap.getOrDefault(i, 0)+1);
        }
        
        HashMap<Integer, Integer> compareMap = new HashMap<>();
        for(int i : compare) {
            compareMap.put(i, compareMap.getOrDefault(i, 0)+1);
        }
        
        for(int key : compareMap.keySet()) {
            if(!baseMap.containsKey(key))
                return key;
            else {
                compareMap.put(key, compareMap.get(key) - baseMap.get(key));
            }
        }
        
        for(int key : compareMap.keySet()) {
            if(compareMap.get(key) != 0)
                return key;
        }
        
        
        return -1;
    }
}