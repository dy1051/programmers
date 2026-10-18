class Solution {
    public int solution(String word) {
        dfs(0, "", word);
        return answer;
    }
    
    int answer = 0; int count = 0;
    String[] str = {"A", "E", "I", "O", "U"};
    void dfs(int l, String s, String word) {
        if(s.equals(word)) {
            answer = count;
        }
        
        count++;
        
        if(l == 5) return;
        
        for (int i = 0 ; i < 5 ; i++) {
            dfs(l+1, s+str[i], word);
        }
    }
}