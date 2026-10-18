import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        ArrayList<Integer> answer = new ArrayList<>();
        
        HashMap<String, Integer> map = new HashMap<>();
        HashMap<String, ArrayList<Integer>> gg = new HashMap<>();
        for (int i = 0 ; i < genres.length ; i++) {
            map.put(genres[i], map.getOrDefault(genres[i], 0) + plays[i]);
            ArrayList<Integer> tmp = gg.getOrDefault(genres[i], new ArrayList<>());
            tmp.add(i);
            gg.put(genres[i], tmp);
        }

        
        List<String> topGenres = map.entrySet().stream()
                                    .sorted((a, b) -> Integer.compare(b.getValue(), a.getValue()))
                                    .map(n -> n.getKey())
                                    .toList();

        for(String g : topGenres) {
            List<Integer> tmp = gg.get(g).stream()
                                    .sorted((a,b) -> Integer.compare(plays[b], plays[a]))
                                    .toList();
            
            answer.add(tmp.get(0));
            if(tmp.size() >= 2) {
                answer.add(tmp.get(1));   
            }
        }
        
        return answer.stream().mapToInt(n->n).toArray();
    }
}