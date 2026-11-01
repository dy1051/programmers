import java.util.*;
class Solution {
    public String[] solution(String[] record) {
        ArrayList<String> answer = new ArrayList<>();
        
        ArrayList<String[]> list = new ArrayList<>();
        HashMap<String, String> map = new HashMap<>();
        
        for(String rec : record) {
            String[] comm = rec.split(" ");
            
            if(comm[0].equals("Enter")) {
                list.add(new String[]{comm[1], "Enter"});
                map.put(comm[1], comm[2]);
            } else if(comm[0].equals("Leave")) {
                list.add(new String[]{comm[1], "Leave"});
            } else if(comm[0].equals("Change")) {
                map.put(comm[1], comm[2]);
            }
        }
        
        for(String[] comm : list) {
            String tmp = map.get(comm[0]);
            
            if(comm[1].equals("Enter")) {
                tmp+= "님이 들어왔습니다.";
            } else if(comm[1].equals("Leave")) {
                tmp+= "님이 나갔습니다.";
            }
            answer.add(tmp);
        }
        
        return answer.stream().toArray(String[]::new);
    }
}