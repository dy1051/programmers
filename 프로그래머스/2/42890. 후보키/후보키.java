import java.util.*;
class Solution {
    public int solution(String[][] relation) {
        dfs(0, relation[0].length, "", relation);
        
        uniqueKeys.sort(Comparator.comparingInt(String::length));
        List<String> candidateKeys = new ArrayList<>();
        
        for (String key : uniqueKeys) {
            boolean flag = false;

            for (String c : candidateKeys) {
                boolean includesAll = true;

                for (char column : c.toCharArray()) {
                    if (key.indexOf(column) == -1) {
                        includesAll = false;
                        break;
                    }
                }

                if (includesAll) {
                    flag = true;
                    break;
                }
            }

            if (!flag) {
                candidateKeys.add(key);
            }
        }
        
        return candidateKeys.size();
    }
    
    List<String> uniqueKeys = new ArrayList<>();
    void dfs(int start, int L, String str, String[][] relation) {
        if(!str.equals("") && check(str, relation)) {
            uniqueKeys.add(str);
        }
        
        for(int i = start ; i < L ; i++) {
            dfs(i+1, L, str+i, relation);
        }
    }
    
    boolean check(String str, String[][] relation) {
        int[] idxs = Arrays.stream(str.split("")).mapToInt(n -> Integer.parseInt(n)).toArray();
        
        HashSet<ArrayList<String>> set = new HashSet<>();
        
        for(int r = 0 ; r < relation.length ; r++) {
            ArrayList<String> tmp = new ArrayList<>();
            for(int idx : idxs) {
                tmp.add(relation[r][idx]);
            }
            set.add(tmp);
        }
        
        return set.size() == relation.length;
    }
}