import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        HashMap<String, Integer> map = new HashMap<>(); 
        HashMap<String, HashMap<Integer, Integer>> indexMap = new HashMap<>();
        
        for(int i = 0 ; i < plays.length ; i++) {
            map.put(genres[i], map.getOrDefault(genres[i], 0) + plays[i]);
            
            HashMap<Integer, Integer> tmp = indexMap.getOrDefault(genres[i], new HashMap<>());
            tmp.put(i, plays[i]);
            
            indexMap.put(genres[i], tmp);
        }
        
        
        List<String> list = map.entrySet().stream()
                        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                        .map(Map.Entry::getKey)
                        .toList();
        
        
        ArrayList<Integer> answer = new ArrayList<>();
        
        for(String key : list) {
            HashMap<Integer, Integer> tmp = indexMap.get(key);
            
            List<Integer> idx = tmp.entrySet().stream()
                                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                                .map(Map.Entry::getKey)
                                .toList();
            
            answer.add(idx.get(0));
            if(idx.size() > 1)
                answer.add(idx.get(1));
        }
        
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}